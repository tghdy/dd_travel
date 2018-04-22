package com.dd.servlet;

import com.dd.entity.TravelOrder;
import com.dd.entity.TravelOrderDetail;
import com.dd.entity.TravelUser;
import com.dd.service.ILineService;
import com.dd.service.IOrderService;
import com.dd.serviceImpl.LineServiceImpl;
import com.dd.serviceImpl.OrderServiceImpl;
import com.dd.util.*;
import com.sun.org.apache.xerces.internal.impl.xs.util.LSInputListImpl;
import javafx.beans.binding.ObjectExpression;
import jdk.nashorn.internal.scripts.JD;
import net.sf.json.processors.JsDateJsonBeanProcessor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

	private ILineService lineService = LineServiceImpl.getInstance();

	private IOrderService orderService = OrderServiceImpl.getInstance();


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println(request.getSession().getId());

		String method = request.getParameter("method");
		if ("orderPageInit".equals(method)) {
			orderPageInit(request, response);
		}
		if ("insertOrder".equals(method)) {
			insertOrder(request, response);
		}
		if ("insertOrderDetail".equals(method)) {
			insertOrderDetail(request, response);
		}
		if ("orderList".equals(request.getParameter("method"))) {
			orderList(request, response);
		}
		if ("orderDetail".equals(request.getParameter("method"))) {
			orderDetail(request, response);
		}
		if ("adminUpdateOrderInf".equals(request.getParameter("method"))) {
			adminUpdateOrderInf(request, response);
		}
		

	}

	private void adminUpdateOrderInf(HttpServletRequest request, HttpServletResponse response) {
		JsonData jsonData = null;
		Map<String, String[]> parameterMap = request.getParameterMap();
		Map<String, Object> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
			map.put(entry.getKey(), entry.getValue()[0]);
		}
		int flag = orderService.adminUpdateOrderInf(map);
		if (flag == 1) {
			jsonData = new JsonData(JsonData.SUCCESS, "修改成功");
		} else {
			jsonData = new JsonData(JsonData.FAILED, "修改失败");
		}
		output(response, jsonData);
		
	}

	private void orderDetail(HttpServletRequest request, HttpServletResponse response) {
		JsonData jsonData = null;
		Map<String, Object> map = null;
		try {
			Integer id = Integer.valueOf(request.getParameter("id"));
			Integer seq = Integer.valueOf(request.getParameter("seq"));
			map = orderService.selectOrderDetail(id, seq);
			if (map != null) {
				jsonData = new JsonData(JsonData.SUCCESS, "获取成功", map);
				return;
			}
			jsonData = new JsonData(JsonData.FAILED, "为空");
			
		} catch (Exception e) {
			jsonData = new JsonData(JsonData.FAILED, "异常");
			e.printStackTrace();
			
		} finally {
			output(response, jsonData);

		}
	}


	private void orderList(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("fuck");
		JsonData jsonData = null;
		try {
			TravelUser user = (TravelUser) UserUtil.getUser(request);
			if (user == null) {
				jsonData = new JsonData(JsonData.FAILED, "未登录");
				return;
			}
			long id = user.getId();

			Integer lineType = request.getParameter("lineType") != null ? Integer.parseInt(request.getParameter("lineType")) : null;
			Integer orderState = request.getParameter("orderState") != null ? Integer.parseInt(request.getParameter("orderState")) : null;
			//Integer i = Integer.parseInt(null);

			List<Map<String, Object>> list = orderService.selectOrderList(id, lineType, orderState);
			if (list != null) {
				jsonData = new JsonData(JsonData.SUCCESS, "成功", list);
				return;
			}
			jsonData = new JsonData(JsonData.FAILED, "为空");

		} catch (Exception e) {
			jsonData = new JsonData(JsonData.FAILED, "异常");
			e.printStackTrace();

		} finally {
			output(response, jsonData);
			//System.out.println("asdasd");
			//System.out.println("sssss");
		}
	}


	private void insertOrderDetail(HttpServletRequest request, HttpServletResponse response) {
		JsonData jsonData = null;
		try {
			TravelOrderDetail orderDetail = BeanUtil.jsonFormatInject(request, TravelOrderDetail.class);
			int flag = orderService.insertOrderDetail(orderDetail);
			if (flag > 0) {
				jsonData = new JsonData(JsonData.SUCCESS, "生成成功");
				return;
			}
			jsonData = new JsonData(JsonData.FAILED, "失败");
		} catch (Exception e) {
			jsonData = new JsonData(JsonData.FAILED, "异常");
			e.printStackTrace();
		} finally {
			output(response, jsonData);
		}
	}

	private void insertOrder(HttpServletRequest request, HttpServletResponse response) {
		JsonData jsonData = null;
		try {
			//获取前端参数
			int travelId = Integer.parseInt(request.getParameter("travelId"));
			int seq = Integer.parseInt(request.getParameter("seq"));
			int childNum = Integer.parseInt(request.getParameter("child"));
			int adultNum = Integer.parseInt(request.getParameter("adult"));

			//获取session中用户信息
			TravelUser user = UserUtil.getUser(request);
			if (user == null) {
				jsonData = new JsonData(JsonData.FAILED, "请先登录");
				return;
			}

			//初始化订单
			TravelOrder order = new TravelOrder();

			//计算订单价格
			Map<String, Object> map = lineService.selectLinePlan(travelId, seq);
			Integer childPrice = (Integer) map.get("plan_child_price");
			Integer adultPrice = (Integer) map.get("plan_price");
			int totalPrice = childPrice * childNum + adultPrice * adultNum;

			//注入订单信息
			order.setOrderNo(GenerateRandom.generateShortUuid());

			order.setOrderState(1);
			order.setUserId(user.getId());
			order.setTravelId(travelId);
			order.setOrderPrice(totalPrice);
			order.setOrderTime(DateUtil.getNowDateTime());

			int orderId = orderService.insertOrder(order);
			if (orderId > 0) {
				jsonData = new JsonData(JsonData.SUCCESS, "成功", orderId);
				return;
			}
			jsonData = new JsonData(JsonData.FAILED, "失败");

		} catch (Exception e) {
			jsonData = new JsonData(JsonData.FAILED, "出错");
			e.printStackTrace();

		} finally {
			output(response, jsonData);

		}


	}

	private void orderPageInit(HttpServletRequest request, HttpServletResponse response) {
		JsonData jsonData = null;
		Map<String, Object> map = null;
		try {
			int travelId = Integer.parseInt(request.getParameter("id"));
			int seq = Integer.parseInt(request.getParameter("seq"));
			map = orderService.orderPageInit(travelId, seq);
			jsonData = new JsonData(JsonData.SUCCESS, "获取成功", map);

		} catch (Exception e) {
			jsonData = new JsonData(JsonData.FAILED, "获取成功", map);
			e.printStackTrace();

		} finally {
			output(response, jsonData);

		}

	}

	private void output(HttpServletResponse response, JsonData jsonData) {
		try {
			PrintWriter out = response.getWriter();
			out.println(jsonData.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
