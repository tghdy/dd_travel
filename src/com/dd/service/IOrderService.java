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
	 * @Des: 订单添加页面信息获取，主要是线路信息以及
	 **/
	Map<String, Object> orderPageInit(int travelId, int planId) throws Exception;

	/**
	 * @Author: TusuZer
	 * @Date: 2018/4/19 下午1:10
	 * @Des: 插入订单基本信息
	 **/
	int insertOrder(TravelOrder order) throws Exception;

	/**
	 * @Author: TusuZer
	 * @Date: 2018/3/10 下午4:28
	 * @Des: 插入订单详情
	 **/
	int insertOrderDetail(TravelOrderDetail orderDetail) throws Exception;
	
	
	
	/**
	 * @Author: TusuZer
	 * @Date: 2018/4/19 下午1:26
	 * @Des: 获取订单详情
	 **/
	Map<String, Object> selectOrderDetail(int id, int plan_id) throws Exception;

	/**
	 * @Author: TusuZer
	 * @Date: 2018/3/11 上午11:05
	 * @Des: 根据用户id获取订单list
	 * @param id
	 * @param lineType
	 * @param orderState
	 **/
	List<Map<String,Object>> selectOrderList(Long id, Integer lineType, Integer orderState) throws Exception;

	
	/**
	 * @Author: TusuZer
	 * @Date: 2018/3/12 上午9:32
	 * @Des: 管理员获取全部订单列表
	 **/
	List<Map<String, Object>> adminSelectOrderList() throws Exception;

	/**
	 * @Author: TusuZer
	 * @Date: 2018/4/20 上午8:45
	 * @Des: 更新订单信息
	 **/
	int adminUpdateOrderInf(Map<String, Object> map);
}
