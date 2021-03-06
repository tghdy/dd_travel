package com.dd.service;

import com.dd.entity.TravelNew;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IArticleService {
	/**
	 * @Author: TusuZer
	 * @Date: 2018/3/14 上午9:46
	 * @Des: 获取全部文章
	 **/
	List<Map<String, Object>> selectAllArticle() throws Exception;

	/**
	 * @Author: TusuZer
	 * @Date: 2018/3/14 下午1:58
	 * @Des: 根据id获取文章
	 **/
	Map<String, Object> selectArticleById(Integer id) throws Exception;


	/**
	 * @Author: TusuZer
	 * @Date: 2018/3/15 上午10:58
	 * @Des: 插入文章
	 **/
	int insertTravelNew(TravelNew travelNew) throws Exception;

	
	int updateArticleState(int id, int state) throws Exception;

	int  updateArticle(TravelNew travelNew) throws Exception;
}
