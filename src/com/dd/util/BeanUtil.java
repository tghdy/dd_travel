package com.dd.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BeanUtil {
	
	@Test
	public void testMethod() {
		Object object = new String("asdasd");
		System.out.println(object.getClass());
		
	}

	public static <T> T createBeanByParameter(HttpServletRequest request, Class<T> clazz) {
		try {
			T t = clazz.newInstance();
			Map<String, String[]> map = request.getParameterMap();
			Field[] fields = clazz.getDeclaredFields();
			List<Method> methods = new ArrayList<>();
			for (Map.Entry<String, String[]> entry : map.entrySet()) {
				String key = entry.getKey();
				try {

					Field field = clazz.getDeclaredField(key);
					//field.getType().getClass();
					String methodName = "set" + key.substring(0, 1).toUpperCase() + key.substring(1);
					Method method = null;
					//Class parameterType = method.getParameters()[0].getType();
					String[] value = entry.getValue();
					//参数类型
					String pTypeName = field.getType().getSimpleName().toUpperCase();
					if (pTypeName.startsWith("STRING")) {
						method = clazz.getDeclaredMethod(methodName, String.class);
						method.invoke(t, (String) entry.getValue()[0]);
					}
					if (pTypeName.startsWith("BYTE")) {
						method = clazz.getDeclaredMethod(methodName, Byte.class);
						method.invoke(t, Byte.parseByte(entry.getValue()[0]));
					}
					if (pTypeName.startsWith("SHORT")) {
						method = clazz.getDeclaredMethod(methodName, Short.class);

						method.invoke(t, Short.parseShort(entry.getValue()[0]));
					}
					if (pTypeName.startsWith("INT")) {
						method = clazz.getDeclaredMethod(methodName, Integer.class);
						method.invoke(t, Integer.parseInt(entry.getValue()[0]));
					}
					if (pTypeName.startsWith("LONG")) {
						method = clazz.getDeclaredMethod(methodName, Long.class);
						method.invoke(t, Long.parseLong(entry.getValue()[0]));
					}
					if (pTypeName.startsWith("FLOAT")) {
						method = clazz.getDeclaredMethod(methodName, Float.class);
						method.invoke(t, Float.parseFloat(entry.getValue()[0]));
					}
					if (pTypeName.startsWith("DOUBLE")) {
						method = clazz.getDeclaredMethod(methodName, Double.class);
						method.invoke(t, Double.parseDouble(entry.getValue()[0]));
					}
					if (pTypeName.startsWith("CHAR")) {
						method = clazz.getDeclaredMethod(methodName, Character.class);
						method.invoke(t, entry.getValue()[0].charAt(0));
					}
					if (pTypeName.startsWith("BOOLEAN")) {
						method = clazz.getDeclaredMethod(methodName, Boolean.class);
						method.invoke(t, Boolean.parseBoolean(entry.getValue()[0]));
					}

					//if (method != null) {
					//	methods.add(method);
					//}
					System.out.println(method.getName());

					//if (value.length > 1) {

					//}

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
			System.out.println(t);


		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void baseTypeInject(HttpServletRequest request, Object... args) {
		//System.out.println("参数个数");
		//System.out.println(args.length);
		int length = args.length;
		for (int i = 0; i < length; i += 2) {
			Class clazz = args[i].getClass();
			String pTypeName = clazz.getSimpleName().toUpperCase();
			if (pTypeName.startsWith("STRING")) {
				String value = request.getParameter((String) args[i + 1]);
				args[i] = new String(value);
			} else if (pTypeName.startsWith("BYTE")) {
				Byte value = Byte.valueOf(request.getParameter((String) args[i + 1]));
				args[i] = value;
			} else if (pTypeName.startsWith("SHORT")) {
				Short value = Short.valueOf(request.getParameter((String) args[i + 1]));
				args[i] = value;
			} else if (pTypeName.startsWith("INT")) {
				Integer value = Integer.valueOf(request.getParameter((String) args[i + 1]));
				args[i] = value;
			} else if (pTypeName.startsWith("LONG")) {
				Long value = Long.valueOf(request.getParameter((String) args[i + 1]));
				args[i] = value;
			} else if (pTypeName.startsWith("FLOAT")) {
				Float value = Float.valueOf(request.getParameter((String) args[i + 1]));
				args[i] = value;
			} else if (pTypeName.startsWith("DOUBLE")) {
				Double value = Double.valueOf(request.getParameter((String) args[i + 1]));
				args[i] = value;
			} else if (pTypeName.startsWith("CHAR")) {
				Character value = (request.getParameter((String) args[i + 1])).charAt(0);
				args[i] = value;
			} else if (pTypeName.startsWith("BOOLEAN")) {
				Boolean value = Boolean.valueOf(request.getParameter((String) args[i + 1]));
				args[i] = value;
			}
		}

	}

	public static <T> T jsonFormatInject(HttpServletRequest request, Class<T> clazz) throws Exception {
		T obj = null;
		ObjectMapper objectMapper = new ObjectMapper();
		obj = objectMapper.readValue(request.getParameter("jsonData"), clazz);
		return obj;
	}
	
	public static String  dealParameter(HttpServletRequest request, String paramName) {
		String paramValue = request.getParameter(paramName);
		if (paramValue != null && paramValue != "") {
			return paramValue;
		}
		return null;
	}
	
}
