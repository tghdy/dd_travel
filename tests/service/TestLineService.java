package service;

import com.dd.dto.LineSearchItem;
import com.dd.service.ILineService;
import com.dd.serviceImpl.LineServiceImpl;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Map;

public class TestLineService {
	private ILineService  lineService = LineServiceImpl.getInstance();
	@Test
	public void testMethodLineDetail() throws Exception {
		Map<String, Object> map = lineService.selectLineAllInf(1);
		//System.out.println(map.get("schedules"));
		//System.out.println(map.get("plans"));
		map.remove("schedules");
		map.remove("plans");
		System.out.println(map);
		//ok	
	}
	@Test
	public void testMethodSearch() {
		LineSearchItem item = new LineSearchItem();
		item.setDay(0);
		//item.setName("宁波");
		item.setSort(2);
		//item.setToId(1);
		//lineService.
		//String str="傻叉"
	}
}
