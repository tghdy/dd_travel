package com.dd.util;

import java.text.SimpleDateFormat;

public class DateUtil {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	public static String getNowDateTime() {
		return sdf.format(System.currentTimeMillis()).toString();
	} 
}
