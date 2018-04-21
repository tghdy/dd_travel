package com.dd.main;

import com.dd.entity.Travel;
import com.dd.entity.TravelUser;
import javafx.beans.binding.ObjectExpression;
import org.junit.jupiter.api.Test;
import sun.util.resources.cldr.az.CalendarData_az_Latn_AZ;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainTest {
	@Test
	public void testMethod() {
		
	}
	public static void main(String[] args) {
		Class<TravelUser> clazz = TravelUser.class;
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("name", 123);
			map.put("sex", "男");
			System.out.println(map);

			System.out.println(clazz.equals(TravelUser.class));
			TravelUser user = clazz.newInstance();
			Method method = clazz.getMethod("setSex", Integer.class);
			System.out.println(Integer.class.getSimpleName());
			System.out.println(Character[].class.getSimpleName());
			System.out.println("asdasd".toCharArray());

			//Object s = "asdas";
			//System.out.println("类型名");
			//System.out.println(clazz.getTypeName());
			//System.out.println(method.getParameters()[0].getType().toString());
			//System.out.println("结果");
			//System.out.println(s.getClass().toString());

			method.invoke(user, 123);
			//Map<String, Object> map = new HashMap<>();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
