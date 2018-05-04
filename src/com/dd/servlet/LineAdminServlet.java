package com.dd.servlet;

import com.dd.entity.LineDetail;
import com.dd.entity.LinePlan;
import com.dd.entity.LineSchedule;
import com.dd.entity.TravelLine;
import com.dd.service.ILineService;
import com.dd.serviceImpl.LineServiceImpl;
import com.dd.util.BeanUtil;
import com.dd.util.JsonData;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xerces.internal.impl.xs.util.LSInputListImpl;
import jdk.nashorn.internal.scripts.JD;
import org.json.JSONArray;

import javax.jws.Oneway;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/lineAdmin")
public class LineAdminServlet extends HttpServlet {
	Object object = new Integer(3);
	
	Integer a = new Integer(3);
	
	private ILineService iLineService = LineServiceImpl.getInstance();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		object.toString();
		//上转型
		String method = request.getParameter("method");
		if ("lineList".equals(method)) {
			lineList(request, response);
		}
		if ("selectTravelLine".equals(method)) {
			selectTravelLine(request, response);
		}
		if ("selectLineDetail".equals(method)) {
			selectLineDetail(request, response);
		}
		if ("selectLinePlanList".equals(method)) {
			selectLinePlanList(request, response);
		}
		if ("selectLinePlan".equals(method)) {
			selectLinePlan(request, response);
		}
		if ("selectLineScheduleList".equals(method)) {
			selectLineScheduleList(request, response);
		}
		if ("selectLineSchedule".equals(method)) {
			selectLineSchedule(request, response);
		}
		if ("insertLine".equals(method)) {
			insertLine(request, response);
		}
		if ("insertLinePlan".equals(method)) {
			insertLinePlan(request, response);
		}
		if ("insertLineSchedule".equals(method)) {
			insertLineSchedule(request, response);
		}
		if ("insertLineSchedules".equals(method)) {
			insertLineSchedules(request, response);
		}
		if ("updateTravelLineState".equals(method)) {
			updateTravelLineState(request, response);
		}
		if ("updateTravelLine".equals(method)) {
			updateTraveLine(request, response);
		}
		if ("updateLineDetail".equals(method)) {
			updateLineDetail(request, response);
		}
		if ("updateLinePlan".equals(method)) {
			updateLinePlan(request, response);
		}
		if ("updateLineSchedule".equals(method)) {
			updateLineSchedule(request, response);
		}

	}

	private void insertLineSchedules(HttpServletRequest request, HttpServletResponse response) {
		JsonData jsonData = null;
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			List<LineSchedule> schedules = mapper.readValue(request.getParameter("jsonArray"),new TypeReference<List<LineSchedule>>() {});
			Map<String, Object> map = new HashMap<>();
			int flag = iLineService.insertLineSchedules(schedules);
			
			if (flag == 0) {
				jsonData = new JsonData(JsonData.FAILED, "插入失败") ;
				return;
			}
			if (flag == 1)
				jsonData = new JsonData(JsonData.SUCCESS, "插入成功") ;
			
		} catch (Exception e) {
			jsonData = new JsonData(JsonData.FAILED, "异常");
			e.printStackTrace();

		} finally {
			output(response, jsonData);
			
		}

	}

	private void lineList(HttpServletRequest request, HttpServletResponse response) {
		JsonData jsonData = null;
		try {
			List<Map<String, Object>> list = iLineService.selectLineList();
			if (list != null) {
				jsonData = new JsonData(JsonData.SUCCESS, "获取成功", list);
			} else {
				jsonData = new JsonData(JsonData.FAILED, "结果为空");
			}
		} catch (Exception e) {
			jsonData = new JsonData(JsonData.FAILED, "发生错误");
			e.printStackTrace();
		} finally {
			output(response, jsonData);
		}

	}

	private void selectTravelLine(HttpServletRequest request, HttpServletResponse response) {
		JsonData jsonData = null;
		Integer id;
		try {
			id = Integer.parseInt(request.getParameter("id"));
			Map<String, Object> map = iLineService.selectTravelLine(id);
			if (map != null) {
				jsonData = new JsonData(JsonData.SUCCESS, "获取成功", map);
			} else {
				jsonData = new JsonData(JsonData.FAILED, "结果为空");
			}

		} catch (Exception e) {
			jsonData = new JsonData(JsonData.FAILED, "发生错误");
			e.printStackTrace();

		} finally {
			output(response, jsonData);

		}
	}

	private void selectLineDetail(HttpServletRequest request, HttpServletResponse response) {
		JsonData jsonData = null;
		Integer id;
		try {
			id = Integer.parseInt(request.getParameter("id"));
			Map<String, Object> map = iLineService.selectLineDetail(id);
			if (map != null) {
				jsonData = new JsonData(JsonData.SUCCESS, "获取成功", map);
			} else {
				jsonData = new JsonData(JsonData.FAILED, "结果为空");
			}
		} catch (Exception e) {
			jsonData = new JsonData(JsonData.FAILED, "发生错误");
			e.printStackTrace();
		} finally {
			output(response, jsonData);
		}
	}

	private void selectLinePlanList(HttpServletRequest request, HttpServletResponse response) {
		JsonData jsonData = null;
		Integer id;
		try {
			id = Integer.parseInt(request.getParameter("id"));
			List<Map<String, Object>> list = iLineService.selectLinePlanList(id);
			if (list != null) {
				jsonData = new JsonData(JsonData.SUCCESS, "获取成功", list);
			} else {
				jsonData = new JsonData(JsonData.FAILED, "结果为空");
			}
		} catch (Exception e) {
			jsonData = new JsonData(JsonData.FAILED, "发生错误");
			e.printStackTrace();
		} finally {
			output(response, jsonData);
		}
	}

	
	private void selectLinePlan(HttpServletRequest request, HttpServletResponse response) {
		JSONArray jsonArray = new JSONArray("asd");
		JsonData jsonData = null;
		Integer id;
		Integer seq;
		try {
			id = Integer.parseInt(request.getParameter("id"));
			seq = Integer.parseInt(request.getParameter("seq"));
			Map<String, Object> map = iLineService.selectLinePlan(id, seq);
			if (map != null) {
				jsonData = new JsonData(JsonData.SUCCESS, "获取成功", map);
			} else {
				jsonData = new JsonData(JsonData.FAILED, "结果为空");
			}
		} catch (Exception e) {
			jsonData = new JsonData(JsonData.FAILED, "发生错误");
			e.printStackTrace();
		} finally {
			output(response, jsonData);
		}

	}

	private void selectLineScheduleList(HttpServletRequest request, HttpServletResponse response) {
		JsonData jsonData = null;
		Integer id;
		try {
			id = Integer.parseInt(request.getParameter("id"));
			List<Map<String, Object>> list = iLineService.selectLineScheduleList(id);
			if (list != null) {
				jsonData = new JsonData(JsonData.SUCCESS, "获取成功", list);
			} else {
				jsonData = new JsonData(JsonData.FAILED, "结果为空", list);
			}
		} catch (Exception e) {
			jsonData = new JsonData(JsonData.FAILED, "发生错误");
			e.printStackTrace();
		} finally {
			output(response, jsonData);
		}
	}

	private void selectLineSchedule(HttpServletRequest request, HttpServletResponse response) {
		JsonData jsonData = null;
		Integer id;
		Integer seq;
		try {
			id = Integer.parseInt(request.getParameter("id"));
			seq = Integer.parseInt(request.getParameter("seq"));
			Map<String, Object> map = iLineService.selectLineSchedule(id, seq);
			if (map != null) {
				jsonData = new JsonData(JsonData.SUCCESS, "获取成功", map);
			} else {
				jsonData = new JsonData(JsonData.FAILED, "结果为空");
			}
		} catch (Exception e) {
			jsonData = new JsonData(JsonData.FAILED, "发生错误");
			e.printStackTrace();
		} finally {
			output(response, jsonData);
		}

	}

	private void insertLine(HttpServletRequest request, HttpServletResponse response) {
		JsonData jsonData = null;
		try {
			TravelLine line = BeanUtil.jsonFormatInject(request, TravelLine.class);
			int flag = iLineService.insertTravelLine(line);
			jsonData = new JsonData(flag > 0 ? JsonData.SUCCESS : JsonData.FAILED, flag > 0 ? "插入成功" : "插入失败");
		} catch (Exception e) {
			jsonData = new JsonData(JsonData.FAILED, "发生错误");
			e.printStackTrace();
		} finally {
			output(response, jsonData);
		}

	}

	private void insertLinePlan(HttpServletRequest request, HttpServletResponse response) {
		JsonData jsonData = null;
		try {
			LinePlan plan = BeanUtil.jsonFormatInject(request, LinePlan.class);
			int flag = iLineService.insertLinePlan(plan);
			jsonData = new JsonData(flag > 0 ? JsonData.SUCCESS : JsonData.FAILED, flag > 0 ? "插入成功" : "插入失败");

		} catch (Exception e) {
			jsonData = new JsonData(JsonData.FAILED, "发生错误");
			e.printStackTrace();
			System.out.println("LPOk");
		} finally {
			output(response, jsonData);
		}


	}

	private void insertLineSchedule(HttpServletRequest request, HttpServletResponse response) {
		JsonData jsonData = null;
		try {
			LineSchedule schedule = BeanUtil.jsonFormatInject(request, LineSchedule.class);
			int flag = iLineService.insertLineSchedule(schedule);
			jsonData = new JsonData(flag > 0 ? JsonData.SUCCESS : JsonData.FAILED, flag > 0 ? "插入成功" : "插入失败");

		} catch (Exception e) {
			jsonData = new JsonData(JsonData.FAILED, "发生错误");
			e.printStackTrace();
		} finally {
			output(response, jsonData);
		}

	}

	private void updateTravelLineState(HttpServletRequest request, HttpServletResponse response) {
		JsonData jsonData = null;
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			int state = Integer.parseInt(request.getParameter("state"));
			int flag = iLineService.updateTravelLineState(id, state);
			jsonData = new JsonData(flag > 0 ? JsonData.SUCCESS : JsonData.FAILED, flag > 0 ? "修改成功" : "修改失败");
		} catch (Exception e) {
			jsonData = new JsonData(JsonData.FAILED, "发生错误");
			e.printStackTrace();
		} finally {
			output(response, jsonData);
		}

	}

	private void updateTraveLine(HttpServletRequest request, HttpServletResponse response) {
		JsonData jsonData = null;
		try {
			TravelLine line = BeanUtil.jsonFormatInject(request, TravelLine.class);
			int flag = iLineService.updateTravelLine(line);
			jsonData = new JsonData(flag > 0 ? JsonData.SUCCESS : JsonData.FAILED, flag > 0 ? "修改成功" : "修改失败");
		} catch (Exception e) {
			jsonData = new JsonData(JsonData.FAILED, "发生错误");
			e.printStackTrace();
		} finally {
			output(response, jsonData);
		}

	}

	private void updateLineDetail(HttpServletRequest request, HttpServletResponse response) {
		JsonData jsonData = null;
		try {
			LineDetail detail = BeanUtil.jsonFormatInject(request, LineDetail.class);
			int flag = iLineService.updateLineDetail(detail);
			jsonData = new JsonData(flag > 0 ? JsonData.SUCCESS : JsonData.FAILED, flag > 0 ? "修改成功" : "修改失败");
		} catch (Exception e) {
			jsonData = new JsonData(JsonData.FAILED, "发生错误");
			e.printStackTrace();
		} finally {
			output(response, jsonData);
		}

	}


	private void updateLinePlan(HttpServletRequest request, HttpServletResponse response) {
		JsonData jsonData = null;
		try {
			LinePlan plan = BeanUtil.jsonFormatInject(request, LinePlan.class);
			int flag = iLineService.updateLinePlan(plan);
			jsonData = new JsonData(flag > 0 ? JsonData.SUCCESS : JsonData.FAILED, flag > 0 ? "修改成功" : "修改失败");
		} catch (Exception e) {
			jsonData = new JsonData(JsonData.FAILED, "发生错误");
			e.printStackTrace();
		} finally {
			output(response, jsonData);
		}

	}

	private void updateLineSchedule(HttpServletRequest request, HttpServletResponse response) {
		JsonData jsonData = null;
		try {
			LineSchedule schedule = BeanUtil.jsonFormatInject(request, LineSchedule.class);
			int flag = iLineService.updateLineSchedule(schedule);
			jsonData = new JsonData(flag > 0 ? JsonData.SUCCESS : JsonData.FAILED, flag > 0 ? "修改成功" : "修改失败");
		} catch (Exception e) {
			jsonData = new JsonData(JsonData.FAILED, "发生错误");
			e.printStackTrace();
		} finally {
			output(response, jsonData);
		}

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
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
