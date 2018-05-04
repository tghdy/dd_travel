package com.dd.servlet;

import com.dd.entity.TravelUser;
import com.dd.serviceImpl.UserServiceImpl;
import com.dd.util.BeanUtil;
import com.dd.util.JsonData;
import com.dd.util.UserUtil;
import com.sun.javafx.collections.MappingChange;
import jdk.nashorn.internal.scripts.JD;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

	UserServiceImpl userService = UserServiceImpl.getInstance();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if ("login".equals(method)) {
			login(request, response);
		}
		if ("register".equals(method)) {
			register(request, response);
		}
		if ("exit".equals(method)) {
			exit(request, response);
		}
		if ("userInf".equals(method)) {
			userInf(request, response);
		}
		if ("updateUserInf".equals(method)) {
			updateUserInf(request, response);
		}

	}

	private void updateUserInf(HttpServletRequest request, HttpServletResponse response) {
		JsonData jsonData = null;
		TravelUser user;
		try {
			user = BeanUtil.jsonFormatInject(request, TravelUser.class);
			TravelUser nowUser = (TravelUser) request.getSession().getAttribute("user");
			System.out.println("now user");
			System.out.println(nowUser);
			
			if (nowUser == null) {
				jsonData = new JsonData(JsonData.FAILED,"身份过期，请重新登陆");
				return;
			}
			user.setId(nowUser.getId());
			int flag = userService.updateUserInf(user);
			if (flag == 1) {
				nowUser.setUserName(user.getUserName());
				nowUser.setMobilePhone(user.getMobilePhone());
				nowUser.setSex(user.getSex());
				nowUser.setEmail(user.getEmail());
				request.getSession().setAttribute("user", nowUser);
				jsonData = new JsonData(JsonData.SUCCESS, "修改成功");
				return;
			}
			jsonData = new JsonData(JsonData.SUCCESS, "修改失败");
			
		} catch (Exception e) {
			jsonData = new JsonData(JsonData.FAILED, "异常");
			e.printStackTrace();

		} finally {
			output(response, jsonData);

		}

	}

	private void userInf(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<>();

		JsonData jsonData = null;
		try {
			TravelUser user = UserUtil.getUser(request);
			if (user != null) {
				jsonData = new JsonData(JsonData.SUCCESS, "成功", user);
				return;
			}
			jsonData = new JsonData(JsonData.FAILED, "身份过期，请重新登陆");

		} catch (Exception e) {
			jsonData = new JsonData(JsonData.FAILED, "异常");
			e.printStackTrace();

		} finally {
			output(response, jsonData);

		}


	}

	private void testM(HttpServletRequest request, HttpServletResponse response) {
		System.out.println(request.getSession().getId());
		output(response, new JsonData(JsonData.SUCCESS, "成功了"));
	}

	private void register(HttpServletRequest request, HttpServletResponse response) {
		JsonData jsonData = null;
		try {
			TravelUser user = BeanUtil.jsonFormatInject(request, TravelUser.class);
			//设置类型为普通用户
			user.setType(0);
			System.out.println(user);
			int flag = userService.insert(user);
			if (flag == 1) {
				jsonData = new JsonData(1, "注册成功");
			} else {
				jsonData = new JsonData(0, "注册失败");
			} 
			

		} catch (Exception e) {
			jsonData = new JsonData(0, "异常");
			   
			e.printStackTrace();
		} finally {
			output(response, jsonData);
		}

	}

	private void exit(HttpServletRequest request, HttpServletResponse response) {
		JsonData jsonData = null;
		try {
			UserUtil.removeUserFromSession(request);
			jsonData = new JsonData(1, "退出成功");
		} catch (Exception e) {
			jsonData = new JsonData(0, "退出失败");
			e.printStackTrace();
		} finally {
			output(response, jsonData);
			
		}
		
	}

	private void login(HttpServletRequest request, HttpServletResponse response) {
		JsonData jsonData = null;
		//System.out.println(request.getSession().getId());
		try {
			String account = request.getParameter("account");
			String password = request.getParameter("password");
			
			TravelUser travelUser = userService.selectByAccount(account);
			if (travelUser != null) {
				travelUser = userService.selectByAccountAndPassword(account, password);
				if (travelUser != null) {
					jsonData = new JsonData(1, "登陆成功");
					UserUtil.saveUserToSession(travelUser, request);
					UserUtil.addUserInfToJsonData(request, jsonData);
	
				} else {
					jsonData = new JsonData(0, "账号或密码错误");
	
				}
				output(response, jsonData);
				return;
				
			}
			jsonData = new JsonData(0, "账号或者密码不存在");
			output(response, jsonData);
			
		} catch (Exception e) {
			jsonData = new JsonData(0, "登陆失败");
			output(response, jsonData);
			e.printStackTrace();
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
