package dao;

import com.dd.daoImpl.LineDaoImpl;
import com.dd.dto.LineSearchItem;
import com.dd.entity.LinePlan;
import com.dd.entity.Travel;
import com.dd.entity.TravelLine;
import com.dd.util.JdbcUtils_DBCP;
import net.sf.json.JSONObject;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class LineDao {
	private LineDaoImpl lineDao = LineDaoImpl.getInstance();
	@Test
	public void testMethod() throws Exception {
		
		System.out.println(lineDao.selectNotAsideByLineType(10));
	}
	
	@Test
	public void testMethodLineSearch() throws Exception{
		LineSearchItem item = new LineSearchItem();
		item.setDay(1);
		//item.setName("宁波");
		item.setSort(0);
		//item.setToId(1);
		//lineDao.searchLineByItem(item);

	}

	@Test
	public void testMethodLinePlan() throws Exception {
		JSONObject jsonObject = new JSONObject();
		List<Map<String, Object>> list = lineDao.selectLinePlanListByLineId(1);
		//		Map<String, Object> map = list.get(0);
		//		//jsonObject.put("data", map);
		LinePlan plan;
		plan = JdbcUtils_DBCP.select("SELECT start_time FROM line_plan WHERE travel_id=1", null, LinePlan.class);
		//jsonObject.put("obj", plan);
		Date date = new Date(123123);
		System.out.println();
		jsonObject.put("date", date.toString());
		System.out.println(jsonObject);
		//System.out.println(map.get("start_time").getClass());
	}
	
	@Test
	public void testMethodOrderList() throws Exception{
		//测试完毕
		//lineDao.selectOrderListByUserId(1).size();
		System.out.println(lineDao.selectOrderDetailByOrderId(7));
	}
	
	@Test
	public void testMethodTransctionInsert() throws Exception {
		TravelLine travelLine = new TravelLine();
		System.out.println(lineDao.insertTravelLine(travelLine));

	}
	
	@Test
	public void testMethodOne() throws Exception {
		System.out.println(lineDao.selectLinePlanList(1));
	}
	
}
