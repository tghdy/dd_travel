package com.dd.serviceImpl;

import com.dd.dao.ILineDao;
import com.dd.daoImpl.LineDaoImpl;
import com.dd.dto.LineSearchItem;
import com.dd.entity.LineDetail;
import com.dd.entity.LinePlan;
import com.dd.entity.LineSchedule;
import com.dd.entity.TravelLine;
import com.dd.service.ILineService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LineServiceImpl implements ILineService {
	private static LineServiceImpl lineService = new LineServiceImpl();

	public static LineServiceImpl getInstance() {
		return lineService;
	}

	private ILineDao lineDao = LineDaoImpl.getInstance();


	@Override
	public Map<String, Object> selectIndexAdbLineByLineType(int lineType) throws Exception {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> asideAdb = lineDao.selectAsideByLineType(lineType);
		List<Map<String, Object>> notasideAdb = lineDao.selectNotAsideByLineType(lineType);
		if (asideAdb == null && notasideAdb.size() == 0) {
			return null;
		}
		map.put("asideAdb", lineDao.selectAsideByLineType(lineType));
		map.put("notasideAdb", lineDao.selectNotAsideByLineType(lineType));
		return map;
	}

	@Override
	public Map<String, Object> selectLineAllInf(int lineId) throws Exception {
		Map<String, Object> map = lineDao.selectLineInf(lineId);
		if (map == null)
			return null;
		map.put("schedules", lineDao.selectLineScheduleList(lineId));
		map.put("plans", lineDao.selectLinePlanListByLineId(lineId));
		map.put("relatedLine", lineDao.selectRelatedLineList(((Integer) map.get("travel_to"))));
		return map;
	}

	@Override
	public List<Map<String, Object>> searchLineByItem(LineSearchItem searchItem) throws Exception {
		return lineDao.searchLineByItem(searchItem);
	}

	@Override	
	public Map<String, Object> selectAllOrderInfByOrderId(int orderId) throws Exception{
		return lineDao.selectOrderDetailByOrderId(orderId);
	}

	@Override
	public List<Map<String, Object>> selectLineList() throws Exception {
		return lineDao.selectLineList();
	}

	@Override
	public Map<String, Object> selectTravelLine(int travelId) throws Exception{
		return lineDao.selectTravelLine(travelId);
	}
	
	@Override
	public Map<String, Object> selectLineDetail(int travelId) throws Exception{
		return lineDao.selectLineDetail(travelId);
	}

	@Override
	public List<Map<String, Object>> selectLinePlanList(int travelId) throws Exception {
		return lineDao.selectLinePlanList(travelId);
	}

	@Override
	public List<Map<String, Object>> selectLineScheduleList(int travelId) throws Exception {
		return lineDao.selectLineScheduleList(travelId);
	}
	
	@Override
	public int insertTravelLine(TravelLine travelLine) throws Exception {
		return lineDao.insertTravelLine(travelLine);
	}

	@Override
	public int insertLinePlan(LinePlan linePlan) throws Exception {
		return lineDao.insertLinePlan(linePlan);
	}
	
	@Override
	public int insertLineSchedule(LineSchedule lineSchedule) throws Exception {
		return lineDao.insertLineSchedule(lineSchedule);
	}
	
	@Override
	public int updateTravelLineState(int id, int state) throws Exception {
		return lineDao.updateTravelLineState(id, state);
	}

	@Override
	public int updateTravelLine(TravelLine line) throws Exception {
		return lineDao.updateTravelLine(line);
	}
	
	@Override
	public int updateLineDetail(LineDetail detail) throws Exception {
		return lineDao.updateLineDetail(detail);
	}
	
	@Override
	public int updateLinePlan(LinePlan plan) throws Exception {
		return lineDao.updateLinePlan(plan);
	}
	
	@Override
	public int updateLineSchedule(LineSchedule schedule) throws Exception {
		return lineDao.updateLineSchedule(schedule);
	}

	@Override
	public Map<String, Object> selectLinePlan(Integer id, Integer seq) throws Exception{
		return lineDao.selectLinePlan(id, seq);
	}

	@Override
	public Map<String, Object> selectLineSchedule(Integer id, Integer seq) throws Exception{
		return lineDao.selectLineSchedule(id, seq);
	}

	@Override
	public int insertLineSchedules(List<LineSchedule> schedules) throws Exception {
		return lineDao.insertLineSchedules(schedules);
	}

}
