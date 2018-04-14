package com.dd.servlet;

import com.dd.entity.TravelNew;
import com.dd.service.IArticleService;
import com.dd.serviceImpl.ArticleServiceImpl;
import com.dd.util.BeanUtil;
import com.dd.util.JsonData;
import com.oracle.javafx.jmx.json.JSONDocument;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet("/articleAdmin")
public class ArticleAdminServlet extends HttpServlet {

	private IArticleService articleService = ArticleServiceImpl.getInstance();


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if ("allArticle".equals(method)) {
			allArticle(request, response);
		}
		if ("selectArticle".equals(method)) {
			selectArticle(request, response);
		}
		if ("indexSlideAdb".equals(method)) {
			//indexSlideAdb(request, response); 
		}
		if ("addArticle".equals(method)) {
			addArticle(request, response); 
		}
		if ("updateArticleState".equals(method)) {
			updateArticleState(request, response); 
		}
		if ("updateArticle".equals(method)) {
			updateArticle(request, response); 
		}
		

	}

	private void updateArticle(HttpServletRequest request, HttpServletResponse response) {
		JsonData jsonData = null;
		try {
			TravelNew travelNew = BeanUtil.jsonFormatInject(request, TravelNew.class);
			//System.out.println(travelNew);
			int flag = articleService.updateArticle(travelNew);
			if (flag > 0) {
				jsonData = new JsonData(JsonData.SUCCESS, "修改成功");
				return;
			}
			jsonData = new JsonData(JsonData.FAILED, "修改失败");
		} catch (Exception e) {
			jsonData = new JsonData(JsonData.FAILED, "发生错误");
			e.printStackTrace();
		} finally {
			output(response, jsonData);
		}
	}

	private void updateArticleState(HttpServletRequest request, HttpServletResponse response) {
		JsonData jsonData = null;
		try {
			int state = Integer.parseInt(request.getParameter("state"));
			int id = Integer.parseInt(request.getParameter("id"));
			
			int flag = articleService.updateArticleState(id, state);
			if (flag > 0) {
				jsonData = new JsonData(JsonData.SUCCESS, "修改成功");
				return;
			}
			jsonData = new JsonData(JsonData.FAILED, "修改失败");
		} catch (Exception e) {
			jsonData = new JsonData(JsonData.FAILED, "发生错误");
			e.printStackTrace();
		} finally {
			output(response, jsonData);
		}
	}

	private void addArticle(HttpServletRequest request, HttpServletResponse response) {
		JsonData jsonData = null;
		try {
			TravelNew travelNew = BeanUtil.jsonFormatInject(request, TravelNew.class);
			int flag = articleService.insertTravelNew(travelNew);
			jsonData = new JsonData(flag > 0 ? JsonData.SUCCESS : JsonData.FAILED, flag > 0 ? "插入成功" : "插入失败");
		} catch (Exception e) {
			jsonData = new JsonData(JsonData.FAILED, "发生错误");
			e.printStackTrace();
		} finally {
			output(response, jsonData);
		}


	}

	private void selectArticle(HttpServletRequest request, HttpServletResponse response) {
		JsonData jsonData = null;
		Integer id = Integer.valueOf(request.getParameter("id"));
		try {
			Map<String, Object> map = articleService.selectArticleById(id);
			if (map != null) {
				jsonData = new JsonData(JsonData.SUCCESS, "成功", map);
			} else {
				jsonData = new JsonData(JsonData.FAILED, "为空");
			}
		} catch (Exception e) {
			jsonData = new JsonData(JsonData.FAILED, "异常");
			e.printStackTrace();
		} finally {
			output(response, jsonData);
		}

	}

	private void allArticle(HttpServletRequest request, HttpServletResponse response) {
		JsonData jsonData = null;
		try {
			List<Map<String, Object>> list = articleService.selectAllArticle();
			if (list != null) {
				jsonData = new JsonData(JsonData.SUCCESS, "成功", list);
			} else {
				jsonData = new JsonData(JsonData.FAILED, "为空");
			}
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
