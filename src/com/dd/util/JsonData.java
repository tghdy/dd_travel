package com.dd.util;

import com.dd.entity.TravelUser;
import net.sf.json.JSONObject;
import sun.security.action.PutAllAction;

import javax.servlet.http.HttpServletRequest;
import java.awt.geom.FlatteningPathIterator;

public class JsonData {
	private Integer flag = 0;
	private String msg;
	private Object data;
	private TravelUser user;

	//成功标记
	final public static Integer SUCCESS = 1;
	//失败标记
	final public static Integer FAILED = 0;

	public JsonData(Integer flag, String msg) {
		this.flag = flag;
		this.msg = msg;
	}

	public JsonData(Integer flag, String msg, Object data) {
		this.flag = flag;
		this.msg = msg;
		this.data = data;
	}

	public JsonData(Integer flag, String msg, Object data, TravelUser user) {
		this.flag = flag;
		this.msg = msg;
		this.data = data;
		this.user = user;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Object getUser() {
		return user;
	}

	public void setUser(TravelUser user) {
		this.user = user;
	}
	
	public String toString() {
		//System.out.println("执行了");
		JSONObject json = new JSONObject();
		json.put("flag", flag);
		json.put("msg", msg);
		//System.out.println(data);
		json.put("data", data);
		json.put("user", user);
		//System.out.println(json);
		return json.toString();
		
	}
	
}
