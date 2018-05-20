package com.dd.daoImpl;

import com.dd.entity.TravelOrder;
import com.dd.entity.TravelOrderDetail;
import com.dd.dao.IOrderDao;
import com.dd.util.JdbcUtils_DBCP;
import com.dd.util.TransactionUtil;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public class OrderDaoImpl implements IOrderDao {

	private static OrderDaoImpl orderDao = new OrderDaoImpl();

	public static OrderDaoImpl getInstance() {
		return orderDao;
	}

	@Override
	public int insertOrder(TravelOrder order) throws Exception {
		String sql = "insert into travel_order (order_no,order_state,user_id,travel_id,plan_id,order_price,order_time)values (?,?,?,?,?,?,?)";
		JdbcUtils_DBCP.update(sql, new Object[]{order.getOrderNo(), order.getOrderState(), order.getUserId(), order.getTravelId(), order.getPlanId(), order.getOrderPrice(), order.getOrderTime()});
		return ((BigInteger) JdbcUtils_DBCP.selectColumnValue("SELECT LAST_INSERT_ID()", null)).intValue();

	}

	@Override
	public int insertOrderDetail(TravelOrderDetail orderDetail) throws Exception {
		String sql = "insert into travel_order_detail (order_id,adult_num,child_num,order_remarks,order_user,order_mobile,order_email,order_idcard)" +
				" values (?,?,?,?,?,?,?,?)";
		return JdbcUtils_DBCP.update(sql, new Object[]{orderDetail.getOrderId(), orderDetail.getAdultNum(),
				orderDetail.getChildNum(), orderDetail.getOrderRemarks(), orderDetail.getOrderUser(),
				orderDetail.getOrderMobile(), orderDetail.getOrderEmail(), orderDetail.getOrderIdcard()});
	}

	@Override
	public List<Map<String, Object>> selectOrderList(Long userId, Integer lineType, Integer orderState) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT tot.*, tlt.travel_name, lp.start_time FROM travel_order tot, travel_line tlt, line_plan lp WHERE tot.travel_id = tlt.id ")
				.append(" AND lp.travel_id=tot.travel_id AND lp.id=tot.plan_id");
		if (userId != null) {
			sql.append(" AND tot.user_id = ").append(userId);
		}
		if (lineType != null && lineType > -1) {
			sql.append(" AND tlt.line_type = ").append(lineType);
		}
		if (orderState != null && orderState > -1) {
			sql.append(" AND tot.order_state = ").append(orderState);
		}
		//sql.append(" limit 10");
		//System.out.println(sql);

		return JdbcUtils_DBCP.selectMapList(sql.toString(), null);
	}

	@Override
	public List<Map<String, Object>> adminSelectOrderList() throws Exception {
		String sql = "select * from travel_order";
		return JdbcUtils_DBCP.selectMapList(sql, null);
	}

	@Override
	public Map<String, Object> adminSelectOrderDetail(int id) throws Exception {
		String sql = "select * from travel_order tot, travel_order_detail tod WHERE tot.id=tod.travel_id AND tot.id="+id;
		return JdbcUtils_DBCP.selectMap(sql, null);
	}

	@Override
	public Map<String, Object> selectOrderDetail(int id, int planId) throws Exception {
		String sql = "SELECT tot.*, tod.*, lp.* , tl.travel_name " +
				"FROM travel_order tot , travel_order_detail tod , travel_line tl , line_plan lp " +
				"WHERE tot.id = tod.order_id AND tot.id = ? AND tot.travel_id = tl.id AND tl.id = lp.travel_id AND lp.id=?";
		System.out.println(sql);
		return JdbcUtils_DBCP.selectMap(sql, new Object[]{id, planId});
	}

	@Override
	public int adminUpdateOrderInf(Map<String, Object> map) {
		//System.out.println(map);
		int flag = 0;
		//String baseSql = "update travel_order set order_price = 8000, order_state = 1 where id = 6";
		String baseSql = "update travel_order set order_price = ?, order_state = ? where id = ?";
		String detailSql = "update travel_order_detail set order_user=?,order_mobile=?,order_email=?,order_remarks=? where order_id=?";
		try {
			TransactionUtil.startTransaction();
			//JdbcUtils_DBCP.transctionInsert(baseSql, null);
			JdbcUtils_DBCP.transctionInsert(baseSql, new Object[]{map.get("order_price"), map.get("order_state"), map.get("id")});
			JdbcUtils_DBCP.transctionInsert(detailSql,
					new Object[]{map.get("order_user"), map.get("order_mobile"), map.get("order_email"),
							map.get("order_remarks"), map.get("order_id")});
			TransactionUtil.commit();
			flag = 1;
			
		} catch (Exception e) {
			TransactionUtil.roolback();
			e.printStackTrace();
			
		} finally {
			TransactionUtil.release();
			
		}
		
		return flag;
	}

}
