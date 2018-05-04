package com.dd.servlet;

import com.dd.entity.TravelUser;
import com.dd.service.IAdbService;
import com.dd.service.ILineService;
import com.dd.serviceImpl.AdbServiceImpl;
import com.dd.serviceImpl.LineServiceImpl;
import com.dd.util.JsonData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet("/adb")
public class AdbServlet extends HttpServlet {

	private ILineService lineService = LineServiceImpl.getInstance();
	private IAdbService adbService = AdbServiceImpl.getInstance();


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		demo(request, response);
		if ("indexLineAdb".equals(method)) {
			indexLineAdb(request, response);
		}
		if ("indexSlideAdb".equals(method)) {
			indexSlideAdb(request, response);
		}
		if ("hotLineAdb".equals(method)) {
			hotLineAdb(request, response);
		}
		
	}

	private void demo(HttpServletRequest request, HttpServletResponse response) {
		String[] a = request.getParameterValues("ids");
		
	}

	private void testConsle(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("航航航航航航");
		JsonData jsonData = null;
		jsonData = new JsonData(JsonData.SUCCESS, "试试控制台", new TravelUser());
		output(response, jsonData);
	}
	
	

	private void hotLineAdb(HttpServletRequest request, HttpServletResponse response) {
		JsonData jsonData = null;
		try {
			List<Map<String, Object>> list = adbService.selectHotLineAdb();
			System.out.println(list);
			if (list != null) {
				System.out.println(jsonData);
				jsonData = new JsonData(JsonData.SUCCESS, "成功", list);
				System.out.println(jsonData);
				return;
			}
			jsonData = new JsonData(JsonData.FAILED, "为空");
			System.out.println(jsonData);
			
		} catch (Exception e) {
			jsonData = new JsonData(JsonData.FAILED, "异常");
			e.printStackTrace();

		} finally {
			output(response, jsonData);

		}
	}

	private void indexSlideAdb(HttpServletRequest request, HttpServletResponse response) {
		JsonData jsonData = null;
		try {
			List<Map<String, Object>> list = adbService.selectIndexSlideAdb();
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

	private void indexLineAdb(HttpServletRequest request, HttpServletResponse response) {
		JsonData jsonData = null;
		try {
			int type = Integer.parseInt(request.getParameter("lineType"));
			Map<String, Object> map = lineService.selectIndexAdbLineByLineType(type);
			if (map != null) {
				jsonData = new JsonData(JsonData.SUCCESS, "成功", map);
				return;
			}
			jsonData = new JsonData(JsonData.FAILED, "为空");
			
		} catch (Exception e) {
			jsonData = new JsonData(JsonData.FAILED, "出错");
			e.printStackTrace();
			
		} finally {
			output(response, jsonData);
		}

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
