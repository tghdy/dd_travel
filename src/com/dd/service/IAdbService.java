package com.dd.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IAdbService {
	/**
	 * @Author: TusuZer
	 * @Date: 2018/3/12 上午11:17
	 * @Des: 获取首页轮播图片list
	 **/
	List<Map<String, Object>> selectIndexSlideAdb() throws Exception;

	/**
	 * @Author: TusuZer
	 * @Date: 2018/3/27 上午10:44
	 * @Des: 获取热门线路广告
	 **/
	List<Map<String,Object>> selectHotLineAdb() throws Exception;
	
	
}
