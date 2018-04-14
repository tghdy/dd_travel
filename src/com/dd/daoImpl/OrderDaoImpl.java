package com.dd.daoImpl;

import com.dd.entity.TravelOrder;
import com.dd.entity.TravelOrderDetail;
import com.dd.dao.IOrderDao;
import com.dd.util.JdbcUtils_DBCP;

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
		String sql = "insert into travel_order (order_no,order_state,user_id,travel_id,order_price,order_time)values (?,?,?,?,?,?)";
		JdbcUtils_DBCP.update(sql, new Object[]{order.getOrderNo(), order.getOrderState(), order.getUserId(), order.getTravelId(), order.getOrderPrice(), order.getOrderTime()});
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
	public List<Map<String, Object>> selectOrderList(long id, Integer lineType, Integer orderState) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT tot.*, tlt.travel_name FROM travel_order tot, travel_line tlt WHERE user_id = ").append(id)
				.append(" AND tot.travel_id = tlt.id");
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

}
