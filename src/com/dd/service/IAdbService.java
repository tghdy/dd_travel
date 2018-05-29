package com.dd.service;

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


    int updatepic(int id, String url)throws Exception;

	List<Map<String,Object>> selectAllAdb()throws Exception;

    Map<String,Object> showOneAdv(int id)throws Exception;

	int submitAdb(int id, String travel_id, String line_type, String url)throws Exception;

    int addAdb(int travel_id, int line_type,int is_aside ,String url)throws Exception;

    int delete(int len, String[] strArray) throws Exception;
}
