package com.dd.daoImpl;

import com.dd.dao.IAdbDao;
import com.dd.dao.IArticleDao;
import com.dd.entity.TravelNew;
import com.dd.util.JdbcUtils_DBCP;
import com.sun.javafx.image.impl.ByteIndexed;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ArticleDaoImpl implements IArticleDao {
	private static ArticleDaoImpl articleDao = new ArticleDaoImpl();

	public static ArticleDaoImpl getInstance() {
		return articleDao;
	}


	@Override
	public List<Map<String, Object>> selectAllArticle() throws Exception {
		String sql = "select * from travel_new";
		return JdbcUtils_DBCP.selectMapList(sql, null);
	}

	@Override
	public Map<String, Object> selectArticleById(Integer id) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM travel_new WHERE id = ").append(id);
		return JdbcUtils_DBCP.selectMap(sql.toString(),null);
	}

	@Override
	public int insertTravelNew(TravelNew obj) throws Exception {
		String sql = "insert into travel_new (category,title,news_content,create_time,last_edit,news_author,news_view," +
				"news_priority,state) values (?,?,?,?,?,?,?,?,?)";
		return JdbcUtils_DBCP.update(sql,new Object[]{obj.getCategory(), obj.getTitle(), obj.getNewsContent(),
				obj.getCreateTime(), obj.getLastEdit(), obj.getNewsAuthor(), obj.getNewsView(), obj.getNewsPriority(), obj.getState()});
	}

	@Override
	public int updateArticleState(int id, int state) throws Exception {
		String sql = "update travel_new set state = ? where id = ?";
		return JdbcUtils_DBCP.update(sql, new Object[]{state, id});
	}

	@Override
	public int updateArticle(TravelNew obj) throws Exception {
		String sql = "update travel_new set category=?,title=?,news_content=?,create_time=?,last_edit=?,news_author=?," +
				"news_view=?,news_priority=?,state=? where id = ?";
		return JdbcUtils_DBCP.update(sql,new Object[]{obj.getCategory(), obj.getTitle(), obj.getNewsContent(),
				obj.getCreateTime(), obj.getLastEdit(), obj.getNewsAuthor(), obj.getNewsView(), obj.getNewsPriority(), 
				obj.getState(), obj.getId()});
	}
}
