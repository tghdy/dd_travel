package com.dd.service;

import com.dd.entity.TravelOrder;
import com.dd.entity.TravelOrderDetail;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IOrderService {

	/**
	 * @Author: TusuZer
	 * @Date: 2018/3/10 下午1:23
	 * @Des: 订单界面初始化信息获取
	 **/
	Map<String, Object> orderPageInit(int travelId, int seq) throws SQLException;

	int insertOrder(TravelOrder order) throws Exception;

	/**
	 * @Author: TusuZer
	 * @Date: 2018/3/10 下午4:28
	 * @Des: 插入订单详情
	 **/
	int insertOrderDetail(TravelOrderDetail orderDetail) throws Exception;

	/**
	 * @Author: TusuZer
	 * @Date: 2018/3/11 上午11:05
	 * @Des: 根据用户id获取订单list
	 * @param id
	 * @param lineType
	 * @param orderState
	 **/
	List<Map<String,Object>> selectOrderList(long id, Integer lineType, Integer orderState) throws Exception;

	
	/**
	 * @Author: TusuZer
	 * @Date: 2018/3/12 上午9:32
	 * @Des: 管理员获取全部订单列表
	 **/
	List<Map<String, Object>> adminSelectOrderList() throws Exception; 
}
