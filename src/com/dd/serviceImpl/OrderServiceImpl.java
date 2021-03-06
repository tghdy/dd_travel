package com.dd.serviceImpl;

import com.dd.dao.ILineDao;
import com.dd.dao.IOrderDao;
import com.dd.daoImpl.LineDaoImpl;
import com.dd.daoImpl.OrderDaoImpl;
import com.dd.entity.TravelOrder;
import com.dd.entity.TravelOrderDetail;
import com.dd.service.IOrderService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements IOrderService {
	private static OrderServiceImpl orderService = new OrderServiceImpl();
	
	public static OrderServiceImpl getInstance() {
		return orderService;
	}

	private IOrderDao orderDao = OrderDaoImpl.getInstance();
	private ILineDao lineDao = LineDaoImpl.getInstance();

	@Override
	public Map<String, Object> orderPageInit(int travelId, int planId) throws Exception{
		Map<String, Object> map = new HashMap<>();
		map.put("lineInf", lineDao.selectLineInf(travelId));
		map.put("linePlan", lineDao.selectLinePlan(planId));
		return map;
	}

	@Override
	public int insertOrder(TravelOrder order) throws Exception {
		return orderDao.insertOrder(order);
	}

	@Override
	public int insertOrderDetail(TravelOrderDetail orderDetail) throws Exception {
		return orderDao.insertOrderDetail(orderDetail);
	}

	@Override
	public Map<String, Object> selectOrderDetail(int id, int planId) throws Exception {
		return orderDao.selectOrderDetail(id, planId);
	}

	@Override
	public List<Map<String, Object>> selectOrderList(Long id, Integer lineType, Integer orderState) throws Exception {
		return orderDao.selectOrderList(id,lineType,orderState);
	}

	@Override
	public List<Map<String, Object>> adminSelectOrderList() throws Exception {
		return orderDao.adminSelectOrderList();
	}

	@Override
	public int adminUpdateOrderInf(Map<String, Object> map) {
		return orderDao.adminUpdateOrderInf(map);
	}

}
