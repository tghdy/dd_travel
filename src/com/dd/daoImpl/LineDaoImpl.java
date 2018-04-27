package com.dd.daoImpl;

import com.dd.dao.ILineDao;
import com.dd.dto.LineSearchItem;
import com.dd.entity.LineDetail;
import com.dd.entity.LinePlan;
import com.dd.entity.LineSchedule;
import com.dd.entity.TravelLine;
import com.dd.util.JdbcUtils_DBCP;
import com.dd.util.TransactionUtil;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LineDaoImpl implements ILineDao {

	private static LineDaoImpl lineDao = new LineDaoImpl();

	public static LineDaoImpl getInstance() {
		return lineDao;
	}

	@Override
	public List<Map<String, Object>> selectNotAsideByLineType(int lineType) throws Exception {
		String sql = "SELECT * FROM travel_adb ta, travel_line tl WHERE ta.travel_id=tl.id AND ta.line_type=? AND is_aside=0";
		return JdbcUtils_DBCP.selectMapList(sql, new Object[]{lineType});
	}

	@Override
	public Map<String, Object> selectAsideByLineType(int lineType) throws Exception{
		String sql = "SELECT * FROM travel_adb ta, travel_line tl WHERE ta.travel_id=tl.id AND ta.line_type=?";
		Map<String, Object> map = JdbcUtils_DBCP.selectMap(sql, new Object[]{lineType});
		return map;
	}

	@Override
	public Map<String, Object> selectLineInf(int lineId) throws Exception{
		String sql = "SELECT tl.*,ld.*,tp1.place_name fromName,tp2.place_name toName FROM travel_line tl, line_detail ld, travel_place tp1, travel_place tp2 " +
				" WHERE tl.id=? AND tl.id=ld.travel_id AND ld.travel_from=tp1.id AND ld.travel_to=tp2.id";
		return JdbcUtils_DBCP.selectMap(sql, new Object[]{lineId});
	}

	@Override
	public List<Map<String, Object>> selectLinePlanListByLineId(int lineId) throws Exception {
		String sql = "SELECT * FROM line_plan WHERE travel_id=?";
		return JdbcUtils_DBCP.selectMapList(sql, new Object[]{lineId});
	}

	@Override
	public List<Map<String, Object>> selectLineScheduleListByLineId(int lineId) throws Exception {
		String sql = "SELECT * FROM line_schedule WHERE travel_id=?";
		return JdbcUtils_DBCP.selectMapList(sql, new Object[]{lineId});
	}

	@Override
	public List<Map<String, Object>> searchLineByItem(LineSearchItem searchItem) throws Exception {
		StringBuilder sql = new StringBuilder();
		List<Object> paramList = new ArrayList<>();
		 
		sql.append("SELECT tl.*, ld.*, tp.place_name son_area, tp2.place_name area FROM travel_line tl, line_detail ld, ")
				.append("travel_place tp, travel_place tp2 ")
				.append("WHERE tl.id=ld.travel_id AND ld.travel_to=tp.id AND tp.parent_id=tp2.id ");
		if (searchItem.getToName() != null && searchItem.getToName() != "") {
			sql.append(" AND tp.place_name='")
					.append(searchItem.getToName())
					.append("'");
		}
		if (searchItem.getToPname() != null && searchItem.getToPname() != "") {
			sql.append(" AND tp2.place_name='")
					.append(searchItem.getToPname())
					.append("'");
		}
		if (searchItem.getTitle() != null && searchItem.getTitle() != "") {
			sql.append(" AND tl.travel_name LIKE '%")
					.append(searchItem.getTitle())
					.append("%'");
		}
		if (searchItem.getDay() != null) {
			if (searchItem.getDay() > 0) {
				sql.append(" AND tl.travel_day=")
						.append(searchItem.getDay());
			}
		}
		if (searchItem.getLineType() != null && searchItem.getLineType() > -1) {
			sql.append(" AND tl.line_type=").append(searchItem.getLineType());
		}

		//指定排序方式查询 1升序，2降序
		if (searchItem.getSort() != null) {
			if (searchItem.getSort() == 1) {
				sql.append(" ORDER BY travel_price");
			} else if (searchItem.getSort() == 2) {
				sql.append(" ORDER BY travel_price DESC ");
			}
		}
		//System.out.println(sql.toString());
		
		return JdbcUtils_DBCP.selectMapList(sql.toString(), null);
	}


	@Override
	public Map<String, Object> selectOrderDetailByOrderId(int orderId) throws Exception{
		String sql = "SELECT tot.*, todt.*, tlt.travel_name FROM travel_order tot, travel_order_detail todt, travel_line tlt WHERE tot.id=? and " +
				"tot.id=todt.order_id and tlt.id=tot.travel_id";
		return JdbcUtils_DBCP.selectMap(sql, new Object[]{orderId});
	}

	@Override
	public List<Map<String, Object>> selectLineList() throws Exception {
		String sql = "SELECT * FROM travel_line";
		return JdbcUtils_DBCP.selectMapList(sql, null);
	}

	@Override
	public Map<String, Object> selectTravelLine(int travelId) throws Exception{
		String sql = "SELECT * FROM travel_line WHERE  id = ?";
		return JdbcUtils_DBCP.selectMap(sql, new Object[]{travelId});
	}

	@Override
	public Map<String, Object> selectLineDetail(int travelId) throws Exception{
		String sql = "SELECT * FROM line_detail ld, travel_place tp WHERE  travel_id = ? ld.";
		return JdbcUtils_DBCP.selectMap(sql, new Object[]{travelId});
	}

	@Override
	public List<Map<String, Object>> selectLinePlanList(int travelId) throws Exception {
		String sql = "SELECT * FROM line_plan WHERE  travel_id = ?";
		return JdbcUtils_DBCP.selectMapList(sql, new Object[]{travelId});
	}

	@Override
	public List<Map<String, Object>> selectLineScheduleList(int travelId) throws Exception {
		String sql = "SELECT * FROM line_schedule WHERE  travel_id = ?";
		return JdbcUtils_DBCP.selectMapList(sql, new Object[]{travelId});
	}

	@Override
	public int insertTravelLine(TravelLine travelLine) {
		int flag = 0;
		Object[] lineParams = travelLine.params();
		String lineSql = "insert into travel_line values (?,?,?,?,?,?,?,?,?,?,?)";
		String detailSql = "insert into line_detail (travel_id) values(?)";
		BigInteger id;
		try {
			TransactionUtil.startTransaction();
			JdbcUtils_DBCP.transctionInsert(lineSql, lineParams);
			//JdbcUtils_DBCP.transctionInsert(lineSql, null);
			id = (BigInteger) JdbcUtils_DBCP.transctionSelect("SELECT LAST_INSERT_ID()", null);
			JdbcUtils_DBCP.transctionInsert(detailSql, new Object[]{id.intValue()});
		} catch (Exception e) {
			TransactionUtil.roolback();
			e.printStackTrace();
			return 0;
		} finally {
			TransactionUtil.commit();
			TransactionUtil.release();
		}
		return id.intValue();
	}

	@Override
	public int insertLinePlan(LinePlan linePlan) throws Exception {
		String sql = "insert into line_plan values (?,?,?,?,?,?,?,?)";
		Object[] params = linePlan.params();
		return JdbcUtils_DBCP.update(sql, params);
	}

	@Override
	public int insertLineSchedule(LineSchedule lineSchedule) throws Exception {
		String sql = "insert into line_schedule values (?,?,?,?,?,?,?,?,?)";
		Object[] params = lineSchedule.params();
		int flag = 0;
		flag = JdbcUtils_DBCP.update(sql, params);
		return flag;
	}

	@Override
	public int updateTravelLineState(int id, int state) throws Exception {
		String sql = "update travel_line set state = ? where id = ?";
		return JdbcUtils_DBCP.update(sql, new Object[]{state, id});
	}

	@Override
	public int updateTravelLine(TravelLine line) throws Exception {
		String sql = "update travel_line set travel_no=?, travel_name=?, traffic_type=?, travel_count=?, travel_order_type=?, " +
				"line_type=?, travel_day=?,travel_price=?, travel_child_price=?, state=? where id=?";
		return JdbcUtils_DBCP.update(sql, line.updatePrams());
	}

	@Override
	public int updateLineDetail(LineDetail detail) throws Exception {
		String sql = "update line_detail set travel_subtitle=?, travel_feature=?, travel_tips=?, travel_from=?, " +
				"travel_to=?, travel_views=?,line_labels=?, travel_info=?, travel_picture=?, travel_picture2=?, travel_picture3=?, travel_picture4=?, schedules_pdf=?  where travel_id =?";
		return JdbcUtils_DBCP.update(sql, detail.updateParams());
	}
	
	@Override
	public int updateLinePlan(LinePlan plan) throws Exception {
		String sql = "update line_plan set start_time=?, plan_price=?, plan_child_price=?, " +
				"gather_time=?, gather_place=?,dismiss_place=? where travel_id =? and seq=?";
		return JdbcUtils_DBCP.update(sql, plan.updateParams());
	}

	@Override
	public int updateLineSchedule(LineSchedule schedule) throws Exception {
		String sql = "update line_schedule set sche_detail=?, sche_stay_level=?, stay_hotel=?, " +
				"sche_meal=?, sche_meal2=?, sche_meal3=?, sche_views=? where travel_id =? and seq=?";

		return JdbcUtils_DBCP.update(sql, schedule.updateParams());
	}

	@Override
	public Map<String, Object> selectLinePlan(Integer id, Integer seq) throws Exception{
		String sql = "SELECT * FROM line_plan WHERE  travel_id = ? AND seq = ?";
		return JdbcUtils_DBCP.selectMap(sql, new Object[]{id, seq});
	}

	@Override
	public Map<String, Object> selectLineSchedule(Integer id, Integer seq) throws Exception{
		String sql = "SELECT * FROM line_schedule WHERE  travel_id = ? AND seq = ?";
		return JdbcUtils_DBCP.selectMap(sql, new Object[]{id, seq});
	}

	@Override
	public List<Map<String, Object>>  selectRelatedLineList(Integer toId) throws Exception {
		String sql = "SELECT tl.*, ld.travel_picture FROM travel_line tl, line_detail ld WHERE travel_to = ? LIMIT 4";
		return JdbcUtils_DBCP.selectMapList(sql, new Object[]{toId});
	}
	
}
