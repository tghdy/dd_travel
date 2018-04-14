package com.dd.servlet;

import com.dd.dto.LineSearchItem;
import com.dd.entity.TravelUser;
import com.dd.service.ILineService;
import com.dd.serviceImpl.LineServiceImpl;
import com.dd.util.BeanUtil;
import com.dd.util.JsonData;
import com.dd.util.UserUtil;
import com.oracle.javafx.jmx.json.JSONDocument;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.sampled.Line;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet("/line")
public class LineServlet extends HttpServlet {

	private ILineService lineService = LineServiceImpl.getInstance();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		String method = request.getParameter("method");
		if ("orderList".equals(method)) {
			orderList(request, response);
		}
		if ("lineSearch".equals(method)) {
			lineSearch(request, response);
		}
		if ("lineAllInf".equals(method)) {
			lineAllInf(request, response);
		}
		
	}

	private void lineAllInf(HttpServletRequest request, HttpServletResponse response) {
		JsonData jsonData = null;
		try {
			Integer id = Integer.valueOf(request.getParameter("id"));
			Map<String, Object> map = lineService.selectLineAllInf(id);
			
			if (map != null) {
				jsonData = new JsonData(JsonData.SUCCESS, "获取成功", map);
			} else {
				jsonData = new JsonData(JsonData.FAILED, "无结果");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonData = new JsonData(JsonData.FAILED, "异常");
		} finally {
			output(response, jsonData);
		}
	}

	private void lineSearch(HttpServletRequest request, HttpServletResponse response) {
		JsonData jsonData = null;
		try {
			LineSearchItem searchItem = BeanUtil.jsonFormatInject(request, LineSearchItem.class);
			//LineSearchItem searchItem = new LineSearchItem();
			//searchItem.setToName("宁波");
			//searchItem.setToPname("浙江");
			//searchItem.setTitle("宁波");
			//searchItem.setDay(0);
			//searchItem.setSort(1);
			
			//System.out.println(searchItem.getDay()); 
			
			List<Map<String, Object>> list = lineService.searchLineByItem(searchItem);
			if (list != null) {
				jsonData = new JsonData(JsonData.SUCCESS, "获取成功", list);
			} else {
				jsonData = new JsonData(JsonData.FAILED, "无结果");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonData = new JsonData(JsonData.FAILED, "异常");
		} finally {
			output(response, jsonData);
		}
	}
	
	private void orderList(HttpServletRequest request, HttpServletResponse response) {
	//	未开发
	}

	private void output(HttpServletResponse response, JsonData jsonData) {
		try {
			PrintWriter out = response.getWriter();
			out.println(jsonData.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
