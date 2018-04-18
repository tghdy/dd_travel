package com.dd.serviceImpl;

import com.dd.dao.IArticleDao;
import com.dd.daoImpl.AdbDaoImpl;
import com.dd.daoImpl.ArticleDaoImpl;
import com.dd.entity.TravelNew;
import com.dd.service.IArticleService;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ArticleServiceImpl implements IArticleService{
	private static ArticleServiceImpl articleService = new ArticleServiceImpl();

	public static ArticleServiceImpl getInstance() {
		return articleService;
	}

	private IArticleDao articleDao = ArticleDaoImpl.getInstance();
	
	@Override
	public List<Map<String, Object>> selectAllArticle() throws Exception {
		return articleDao.selectAllArticle();
	}

	@Override
	public Map<String, Object> selectArticleById(Integer id) throws Exception{
		return articleDao.selectArticleById(id);
	}

	@Override
	public int insertTravelNew(TravelNew travelNew) throws Exception {
		return articleDao.insertTravelNew(travelNew);
	}

	@Override
	public int updateArticleState(int id, int state) throws Exception {
		return articleDao.updateArticleState(id, state);
	}

	@Override
	public int updateArticle(TravelNew travelNew) throws Exception {
		return articleDao.updateArticle(travelNew);
	}

}
