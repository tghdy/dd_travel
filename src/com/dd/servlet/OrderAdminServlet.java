package com.dd.servlet;

import com.dd.entity.TravelOrder;
import com.dd.entity.TravelOrderDetail;
import com.dd.entity.TravelUser;
import com.dd.service.ILineService;
import com.dd.service.IOrderService;
import com.dd.serviceImpl.LineServiceImpl;
import com.dd.serviceImpl.OrderServiceImpl;
import com.dd.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet("/adminOrder")
public class OrderAdminServlet extends HttpServlet {

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
		if ("orderList".equals(method)) {
			orderList(request, response);
		}

	}

	private void orderList(HttpServletRequest request, HttpServletResponse response) {
		JsonData jsonData = null;
		try {
			TravelUser user = (TravelUser) UserUtil.getUser(request);

			List<Map<String, Object>> list = orderService.adminSelectOrderList();
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
