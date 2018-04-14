package com.dd.util;

import com.dd.entity.Travel;
import com.dd.entity.TravelUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserUtil {

	public static void saveUserToSession(TravelUser user, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		
	}

	public static void removeUserFromSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		
	}
	
	public static TravelUser getUser(HttpServletRequest request) {
		TravelUser user = null;
		HttpSession session = request.getSession();
		user = (TravelUser) session.getAttribute("user");
		return user;

	}		

	/**
	 * @Author: TusuZer
	 * @Date: 2018/2/17 下午8:56
	 * @Des:向JosnData中添加user信息，若为登陆则值为空
	 **/
	public static JsonData addUserInfToJsonData(HttpServletRequest request, JsonData jsonData) {
		jsonData.setUser(getUser(request));
		return jsonData;
		
	}
	
}
