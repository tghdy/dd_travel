package com.dd.servlet;

import com.dd.dto.UserSearchItem;
import com.dd.service.IUserService;
import com.dd.serviceImpl.UserServiceImpl;
import com.dd.util.BeanUtil;
import com.dd.util.JsonData;
import com.dd.util.UserUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet("/userAdmin")
public class UserAdminServlet extends HttpServlet {
	private IUserService iUserService = UserServiceImpl.getInstance();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if ("userList".equals(method)) {
			userList(request, response);
		}
		if ("allUser".equals(method)) {
			allUser(request, response);
		}
		if ("disabledUser".equals(method)) {
			disabledUser(request, response);
		}
		if ("updatePermission".equals(method)) {
			updatePermission(request, response);
		}
		if ("updateType".equals(method)) {
			updateType(request, response);
		}
		if ("selectUserByType".equals(method)) {
			selectUserByType(request, response);
		}
		if ("insertDemo".equals(method)) {
			insertDemo(request, response);
		}

	}

	private void insertDemo(HttpServletRequest request, HttpServletResponse response) {
		JsonData jsonData = null;
		try {
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			//servetl调用service调用dao调用数据库
			int flag = iUserService.insertDemo(name, sex);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void selectUserByType(HttpServletRequest request, HttpServletResponse response) {
		JsonData jsonData = null;
		try {
			int type = Integer.parseInt(request.getParameter("type"));
			List<Map<String, Object>> list = iUserService.selectUserByType(type);
			
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


	/**
	 * @Author: TusuZer
	 * @Date: 2018/3/16 下午3:55
	 * @Des: 取消管理员资格
	 **/
	private void updateType(HttpServletRequest request, HttpServletResponse response) {
		JsonData jsonData = null;
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			int type = Integer.parseInt(request.getParameter("type"));

			int flag = iUserService.updateUserType(id, type);

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

	/**
	 * @Author: TusuZer
	 * @Date: 2018/3/16 上午11:55
	 * @Des: 更新用户权限，入参：id(用户id),permission(权限id字符串,例如：1,2,3,4)
	 **/
	private void updatePermission(HttpServletRequest request, HttpServletResponse response) {
		JsonData jsonData = null;
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			String permission = request.getParameter("permission");

			int flag = iUserService.updateUserPermission(id, permission);

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

	/**
	 * @Author: TusuZer
	 * @Date: 2018/3/16 上午11:57
	 * @Des: 修改用户状态，入参：id（用户id），state（修改的状态0为停用， 1为启用）
	 **/
	private void disabledUser(HttpServletRequest request, HttpServletResponse response) {
		JsonData jsonData = null;
	}

	private void allUser(HttpServletRequest request, HttpServletResponse response) {
		JsonData jsonData = null;
		try {
			List<Map<String, Object>> list = iUserService.selectAllUser();
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

	private void userList(HttpServletRequest request, HttpServletResponse response) {
		UserSearchItem searchItem = null;
		JsonData jsonData = null;
		try {
			searchItem = BeanUtil.jsonFormatInject(request, UserSearchItem.class);
			Map<String, Object> map = iUserService.selectUserPageDataByItem(searchItem);
			if (map == null) {
				jsonData = new JsonData(JsonData.FAILED, "无搜索结果");
			} else {
				jsonData = new JsonData(JsonData.SUCCESS, "获取成功", map);
			}
		} catch (Exception e) {
			jsonData = new JsonData(JsonData.FAILED, "获取出错");
			e.printStackTrace();
		} finally {
			UserUtil.addUserInfToJsonData(request, jsonData);
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
