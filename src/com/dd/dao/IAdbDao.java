package com.dd.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IAdbDao {

	/**
	 * @Author: TusuZer
	 * @Date: 2018/3/13 上午8:57
	 * @Des: 获取首页轮播广告
	 **/
	List<Map<String,Object>> selectIndexSlideAdb() throws Exception, Exception;

	/**
	 * @Author: TusuZer
	 * @Date: 2018/3/27 上午10:44
	 * @Des: 获取热门线路广告
	 **/
	List<Map<String, Object>> selectHotLineAdb() throws Exception, Exception;
	
}
