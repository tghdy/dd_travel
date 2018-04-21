package com.dd.dao;

import com.dd.entity.TravelOrder;
import com.dd.entity.TravelOrderDetail;

import java.util.List;
import java.util.Map;

public interface IOrderDao {
	/**
	 * @Author: TusuZer
	 * @Date: 2018/3/10 下午1:31
	 * @Des: 插入订单
	 **/
	int insertOrder(TravelOrder order) throws Exception;

	/**
	 * @Author: TusuZer
	 * @Date: 2018/3/10 下午4:30
	 * @Des: 插入订单详情
	 **/
	int insertOrderDetail(TravelOrderDetail orderDetail) throws Exception;

	/**
	 * @Author: TusuZer
	 * @Date: 2018/3/11 上午11:06
	 * @Des: 根据用户id获取订单list
	 * @param id
	 * @param lineType
	 * @param orderState */
	List<Map<String,Object>> selectOrderList(Long userId, Integer lineType, Integer orderState) throws Exception;

	/**
	 * @Author: TusuZer
	 * @Date: 2018/3/12 上午9:32
	 * @Des: 管理员获取全部订单列表
	 **/
	List<Map<String,Object>> adminSelectOrderList() throws Exception;

	/**
	 * @Author: TusuZer
	 * @Date: 2018/4/19 下午7:39
	 * @Des: 管理员获取订单详情
	 **/
	Map<String, Object> adminSelectOrderDetail(int id) throws Exception;

	/**
	 * @Author: TusuZer
	 * @Date: 2018/4/19 下午1:27
	 * @Des: 获取订单详情
	 **/
	Map<String,Object> selectOrderDetail(int id, int seq) throws Exception;

	/**
	 * @Author: TusuZer
	 * @Date: 2018/4/20 上午8:45
	 * @Des: 更新订单信息
	 **/
	int adminUpdateOrderInf(Map<String, Object> map);
}
