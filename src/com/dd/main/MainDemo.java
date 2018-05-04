package com.dd.main;


import com.dd.entity.Travel;
import com.dd.entity.TravelLine;
import com.dd.util.BeanUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import sun.security.ssl.SSLContextImpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class MainDemo {
	public static void main(String args[]) throws Exception {


		String cols="[{\"id\":1,\"travelName\":\"名字吧\"},{\"id\":1,\"travelName\":\"名字吧\"},{\"id\":1,\"travelName\":\"名字吧\"},{\"id\":1,\"travelName\":\"名字吧\"}]";
		String bean = "{\"id\":1,\"travelName\":\"名字吧\"}";
		int[] ids = {1, 2, 3};
		ObjectMapper mapper = new ObjectMapper();
		List<TravelLine> lst = mapper.readValue(cols,new TypeReference<List<TravelLine>>() { });
		//System.out.println(travelLine);
		
		//List<TravelLine> lst =  (List<TravelLine>)mapper.readValue(cols, javaType);
		System.out.println(lst);
		
	}
}