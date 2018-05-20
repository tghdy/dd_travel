package com.dd.servlet;

import com.dd.dao.IPlaceDao;
import com.dd.daoImpl.PlaceDaoImpl;
import com.dd.entity.TravelPlace;
import com.dd.entity.TravelUser;
import com.dd.service.IAdbService;
import com.dd.service.ILineService;
import com.dd.serviceImpl.AdbServiceImpl;
import com.dd.serviceImpl.LineServiceImpl;
import com.dd.util.BeanUtil;
import com.dd.util.JsonData;
import sun.misc.Cleaner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet("/place")
public class PlaceServlet extends HttpServlet {

	private ILineService lineService = LineServiceImpl.getInstance();

	private IPlaceDao placeDao = PlaceDaoImpl.getInstance();


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		//根据pid获取地区集合
		if ("selectAres".equals(method)) {
			selectAres(request, response);
		}
		if ("uploadPlace".equals(method)) {
			uploadPlace(request, response);
		}
		//根据id获取单条地区信息
		if ("selectPlace".equals(method)) {
			selectPlace(request, response);
		}
		if ("deletePlace".equals(method)) {
			deletePlace(request, response);
		}
		if ("addPlace".equals(method)) {
			addPlace(request, response);
		}
		
	}

	private void addPlace(HttpServletRequest request, HttpServletResponse response) {


		JsonData jsonData = null;
		Map<String, Object> map = null;
		
		try {
			TravelPlace place = BeanUtil.jsonFormatInject(request, TravelPlace.class);
			int flag = placeDao.insertPlace(place);
			if (flag == 0) {
				jsonData = new JsonData(JsonData.FAILED, "插入失败");
				return;
			}
			jsonData = new JsonData(JsonData.SUCCESS, "插入成功");
			
		} catch (Exception e) {
			jsonData = new JsonData(JsonData.FAILED, "异常");
			e.printStackTrace();
			
		} finally {
			output(response, jsonData);
			
		}



	}

	private void deletePlace(HttpServletRequest request, HttpServletResponse response) {

		JsonData jsonData = null;
		Map<String, Object> map = null;

		try {
			int id = Integer.parseInt(request.getParameter("id"));

			int flag = placeDao.deletePlace(id);

			if (flag == 0) {
				jsonData = new JsonData(JsonData.FAILED, "删除失败");
				return;
			}
			jsonData = new JsonData(JsonData.SUCCESS, "删除成功");
		} catch (Exception e) {
			jsonData = new JsonData(JsonData.FAILED, "异常");
			e.printStackTrace();
		} finally {
			output(response, jsonData);
		}

	}

	private void selectPlace(HttpServletRequest request, HttpServletResponse response) {
		JsonData jsonData = null;
		Map<String, Object> map = null;

		try {
			int id = Integer.parseInt(request.getParameter("id"));
			map = placeDao.selectPlace(id);
			if (map.size() == 0) {
				jsonData = new JsonData(JsonData.SUCCESS, "不存在");
			}

			jsonData = new JsonData(JsonData.SUCCESS, "获取成功", map) ;
			
		} catch (Exception e) {
			jsonData = new JsonData(JsonData.FAILED, "异常");
			e.printStackTrace();

		} finally {
			output(response, jsonData);

		}

	}
	
	private void uploadPlace(HttpServletRequest request, HttpServletResponse response) {
		JsonData jsonData = null;
		Map<String, Object> map = null;

		try {
			TravelPlace place = BeanUtil.jsonFormatInject(request, TravelPlace.class);
			System.out.println(place);
			int flag = placeDao.uploadPlace(place);
			System.out.println(flag);
			if (flag == 0) {
				jsonData = new JsonData(JsonData.FAILED, "更新失败");
				return;
			}
			
			jsonData = new JsonData(JsonData.SUCCESS, "更新成功", map) ;

		} catch (Exception e) {
			jsonData = new JsonData(JsonData.FAILED, "异常");
			e.printStackTrace();

		} finally {
			output(response, jsonData);

		}

	}

	private void selectAres(HttpServletRequest request, HttpServletResponse response) {
		JsonData jsonData = null;
		List<Map<String, Object>> list = null;

		try {
			Integer pid = Integer.parseInt(request.getParameter("pid"));
			list = placeDao.selectSonAreaByPid(pid);
			
			if (list.size() == 0) {
				jsonData = new JsonData(JsonData.FAILED, "为空", list);
				return;
			}
			
			jsonData = new JsonData(JsonData.SUCCESS, "获取成功", list) ;

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
		} catch (IOException e) {
			e.printStackTrace();
		}
 
	}
	
}
