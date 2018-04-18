package com.dd.dao;

import com.dd.dto.LineSearchItem;
import com.dd.entity.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ILineDao {
	
	/**
	 * @Author: TusuZer
	 * @Date: 2018/2/21 下午5:13
	 * @Des: 根据线路类型获取六条推荐线路
	 **/
	List<Map<String, Object>> selectNotAsideByLineType(int lineType) throws Exception;

	/**
	 * @Author: TusuZer
	 * @Date: 2018/2/22 下午12:42
	 * @Des: 根据线路类型获取侧边推荐线路
	 **/
	Map<String, Object> selectAsideByLineType(int lineType) throws Exception;

	/**
	 * @Author: TusuZer
	 * @Date: 2018/2/22 下午12:43
	 * @Des: 根据线路id获取线路详情，涵盖travel_line以及liene_detail信息
	 **/
	Map<String, Object> selectLineInf(int lineId) throws Exception;

	/**
	 * @Author: TusuZer
	 * @Date: 2018/2/22 下午1:43
	 * @Des: 根据线路id获取班次信息
	 **/
	List<Map<String, Object>> selectLinePlanListByLineId(int lineId) throws Exception;

	/**
	 * @Author: TusuZer
	 * @Date: 2018/2/22 下午2:00
	 * @Des: 根据线路id获取日程信息
	 **/
	List<Map<String, Object>> selectLineScheduleListByLineId(int lineId) throws Exception;

	/**
	 * @Author: TusuZer
	 * @Date: 2018/2/22 下午2:45
	 * @Des: 搜索线路
	 **/
	List<Map<String, Object>> searchLineByItem(LineSearchItem searchItem) throws Exception;
	

	/**
	 * @Author: TusuZer
	 * @Date: 2018/2/22 下午4:49
	 * @Des: 根据订单id获取订单详情
	 **/
	Map<String, Object> selectOrderDetailByOrderId(int orderId) throws Exception;

	/**
	 * @Author: TusuZer
	 * @Date: 2018/2/28 上午11:47
	 * @Des: 获取所有线路的list
	 **/
	List<Map<String, Object>> selectLineList() throws Exception;
	
	
	/**
	 * @Author: TusuZer
	 * @Date: 2018/3/1 下午4:43
	 * @Des: 获取线路基本信息
	 * 线路信息获取开始！！！
	 **/
	Map<String, Object> selectTravelLine(int travelId) throws Exception;
	/**
	 * @Author: TusuZer
	 * @Date: 2018/3/1 下午4:44
	 * @Des: 获取线路详情
	 **/
	Map<String, Object> selectLineDetail(int travelId) throws Exception;
	/**
	 * @Author: TusuZer
	 * @Date: 2018/3/1 下午4:44
	 * @Des: 获取线路班次列表
	 **/
	List<Map<String, Object>> selectLinePlanList(int travelId) throws Exception;
	
	/**
	 * @Author: TusuZer
	 * @Date: 2018/3/1 下午4:43
	 * @Des: 获取线路日程列表
	 * 线路信息获取结束！！！
	 **/
	List<Map<String, Object>> selectLineScheduleList(int travelId) throws Exception;


	/**
	 * @Author: TusuZer
	 * @Date: 2018/2/28 下午2:57
	 * @Des: 插入线路信息
	 **/
	int insertTravelLine(TravelLine travelLine) throws Exception;

	/**
	 * @Author: TusuZer
	 * @Date: 2018/2/28 下午6:45
	 * @Des: 插入一条班次
	 **/
	int insertLinePlan(LinePlan linePlan) throws Exception;

	/**
	 * @Author: TusuZer
	 * @Date: 2018/2/28 下午6:45
	 * @Des: 插入一条日程
	 **/
	int insertLineSchedule(LineSchedule lineSchedule) throws Exception;

	/**
	 * @Author: TusuZer
	 * @Date: 2018/2/28 下午6:46
	 * @Des: 更新线路状态
	 **/
	int updateTravelLineState(int id, int state) throws Exception;

	/**
	 * @Author: TusuZer
	 * @Date: 2018/2/28 下午6:50
	 * @Des: 更新线路基本信息
	 **/
	int updateTravelLine(TravelLine line) throws Exception;
	
	/**
	 * @Author: TusuZer
	 * @Date: 2018/2/28 下午6:51
	 * @Des: 更新线路详细信息
	 **/
	int updateLineDetail(LineDetail detail) throws Exception;

	/**
	 * @Author: TusuZer
	 * @Date: 2018/2/28 下午6:51
	 * @Des: 更新班次信息
	 **/
	int updateLinePlan(LinePlan plan) throws Exception;

	/**
	 * @Author: TusuZer
	 * @Date: 2018/2/28 下午6:52
	 * @Des: 更新日程信息
	 **/
	int updateLineSchedule(LineSchedule schedule) throws Exception;

	/**
	 * @Author: TusuZer
	 * @Date: 2018/3/2 下午1:40
	 * @Des: 根据线路id以及次序获取一条班次
	 **/
	Map<String, Object> selectLinePlan(Integer id, Integer seq) throws Exception;
	
	/**
	 * @Author: TusuZer
	 * @Date: 2018/3/2 下午1:40
	 * @Des: 根据线路id以及次序获取一条日程
	 **/
	Map<String,Object> selectLineSchedule(Integer id, Integer seq) throws Exception;

	/**
	 * @Author: TusuZer
	 * @Date: 2018/3/28 下午7:05
	 * @Des: 根据目的地id获取相关线路列表
	 **/
	List<Map<String, Object>>  selectRelatedLineList(Integer toId) throws Exception;
}
