package com.dd.servlet;

import com.dd.entity.TravelUser;
import com.dd.service.IAdbService;
import com.dd.service.ILineService;
import com.dd.serviceImpl.AdbServiceImpl;
import com.dd.serviceImpl.LineServiceImpl;
import com.dd.util.JsonData;
import net.sf.json.JSONObject;

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
		if ("addPic".equals(method)) {
			addPic(request, response);
		}
		if ("showAllAdb".equals(method)) {
			showAllAdb(request, response);
		}
		if ("showOneAdv".equals(method)) {
			showOneAdv(request, response);
		}
		if ("submitAdb".equals(method)) {
			submitAdb(request, response);
		}
		if ("addAdb".equals(method)) {
			addAdb(request, response);
		}
		if ("delete".equals(method)) {
			delete(request, response);
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) {
		/*String a= request.getParameter("list");
		String [] strArray = new String [20];
		for(int i=0;i<20;i++){
			strArray[i] = "";
		}
		int j=0;
		for(int i=0;i<a.length();i++){
			if(a.charAt(i)>='0'&&a.charAt(i)<='9'){
				strArray[j]+=a.charAt(i);
			}
			else if(a.charAt(i)==','){
				j++;
			}
		}
		j++;
		int len=j;
		/*for(int i=0;i<j;i++){
			System.out.println(strArray[i]);
		}*/
		String[] strArray= request.getParameterValues("list[]");
		int len=strArray.length;
		JsonData jsonData = null;
		jsonData = new JsonData(JsonData.SUCCESS,"成功");
		try{
			int flag = adbService.delete(len,strArray);
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			output(response, jsonData);

		}

	}

	private void addAdb(HttpServletRequest request, HttpServletResponse response) {
		JsonData jsonData = null;
		jsonData = new JsonData(JsonData.SUCCESS,"成功");
		try{
			String a = request.getParameter("travel_id");
			String b = request.getParameter("line_type");
			String c = request.getParameter("is_aside");
			int travel_id=Integer.parseInt(a);
			int line_type=Integer.parseInt(b);
			int is_aside=Integer.parseInt(c);
			String url = request.getParameter("url");
			int flag = adbService.addAdb(travel_id,line_type,is_aside,url);
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			output(response, jsonData);

		}
	}

	private void submitAdb(HttpServletRequest request, HttpServletResponse response) {
		JsonData jsonData = null;
		jsonData = new JsonData(JsonData.SUCCESS,"成功");
		try{
			String a = request.getParameter("id");
			int id = Integer.parseInt(a);
			String travel_id = request.getParameter("travel_id");
			String line_type = request.getParameter("line_type");
			String url = request.getParameter("url");
			int flag = adbService.submitAdb(id,travel_id,line_type,url);
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			output(response, jsonData);

		}

	}

	private void showOneAdv(HttpServletRequest request, HttpServletResponse response) {
		JsonData jsonData = null;
		try {
            String a = request.getParameter("id");
            int id = Integer.parseInt(a);
			Map<String, Object> map = adbService.showOneAdv(id);
			if (map != null) {
				jsonData = new JsonData(JsonData.SUCCESS, "成功", map);
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

	private void showAllAdb(HttpServletRequest request, HttpServletResponse response) {
		JsonData jsonData = null;
		try {
			List<Map<String, Object>> list = adbService.selectAllAdb();
			//System.out.println(list);
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

	private void addPic(HttpServletRequest request, HttpServletResponse response) {
		JsonData jsonData = null;
		jsonData = new JsonData(JsonData.SUCCESS,"成功");
		try{
			String a = request.getParameter("id");
			int id = Integer.parseInt(a);
			String url = request.getParameter("url");
			int flag = adbService.updatepic(id,url);
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			output(response, jsonData);

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
			//System.out.println(list);
			if (list != null) {
				//System.out.println(jsonData);
				jsonData = new JsonData(JsonData.SUCCESS, "成功", list);
				//System.out.println(jsonData);
				return;
			}
			jsonData = new JsonData(JsonData.FAILED, "为空");
			//System.out.println(jsonData);
			
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
