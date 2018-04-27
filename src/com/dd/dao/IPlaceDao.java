package com.dd.dao;

import com.dd.entity.TravelPlace;

import java.util.List;
import java.util.Map;

public interface IPlaceDao {
	/**
	 * @Author: TusuZer
	 * @Date: 2018/4/23 下午8:05
	 * @Des: 获取地区信息
	 **/
	List<Map<String, Object>> selectSonAreaByPid(Integer pid) throws Exception;

	/**
	 * @Author: TusuZer
	 * @Date: 2018/4/24 上午11:06
	 * @Des: 更新地点信息
	 **/
	int uploadPlace(TravelPlace place) throws Exception;

	/**
	 * @Author: TusuZer
	 * @Date: 2018/4/24 下午1:38
	 * @Des: 获取单条地区记录
	 **/
	Map<String,Object> selectPlace(int id) throws Exception;

	/**
	 * @Author: TusuZer
	 * @Date: 2018/4/24 下午2:14
	 * @Des: 删除地点
	 **/
	int deletePlace(int id) throws Exception;

	/**
	 * @Author: TusuZer
	 * @Date: 2018/4/24 下午4:30
	 * @Des: 插入新地点
	 **/
	int insertPlace(TravelPlace place) throws Exception;
}
