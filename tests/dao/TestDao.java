package dao;

import com.dd.daoImpl.TestDaoImpl;
import net.sf.json.JSONObject;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TestDao {
	TestDaoImpl testDaoImpl = TestDaoImpl.getInstance();
	@Test
	public void test() {
		Map<String, Object> map = new HashMap<>();
		map.put("key1", "简直了");
		map.put("key2", "还可以吧");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("data", map);
		System.out.println(jsonObject);
		
		//System.out.println("asdasdasd");
		//testDaoImpl.testMethod();
		
	}
	
	@Test
	public void testMethodOne() {
	}
}
