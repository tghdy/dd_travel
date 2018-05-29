package com.dd.daoImpl;

import com.dd.dao.IAdbDao;
import com.dd.util.JdbcUtils_DBCP;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class AdbDaoImpl implements IAdbDao {

	private static AdbDaoImpl adbDao = new AdbDaoImpl();

	public static AdbDaoImpl getInstance() {
		return  adbDao;
	}

	@Override
	public List<Map<String, Object>> selectIndexSlideAdb() throws Exception {
		String sql = "SELECT * FROM slide_adb WHERE state = 1";
		return JdbcUtils_DBCP.selectMapList(sql, null);
	}

	@Override
	public List<Map<String, Object>> selectHotLineAdb() throws Exception {
		String sql = "SELECT ta.*, tl.* FROM travel_adb ta, travel_line tl where ta.line_type = 10 AND tl.id = ta.travel_id AND ta.is_aside<>1";
		return JdbcUtils_DBCP.selectMapList(sql, null);
	}

	@Override
	public int updatepic(int id, String url) throws Exception {
		String sql = "update slide_adb set travel_picture = ? where id =?";
		return JdbcUtils_DBCP.update(sql,new Object[]{url,id});
	}

	@Override
	public List<Map<String, Object>> selectAllAdb() throws Exception {
		String sql = "SELECT adb_id,travel_name,aside_picture,travel_adb.line_type " +
				"FROM travel_adb LEFT JOIN travel_line " +
				"ON travel_adb.travel_id=travel_line.id";
		return JdbcUtils_DBCP.selectMapList(sql, null);
	}

	@Override
	public Map<String, Object> showOneAdv(int id) throws Exception {
		String sql = "select * from travel_adb where adb_id = ?";
		return JdbcUtils_DBCP.selectMap(sql, new Object[]{id});
	}

	@Override
	public int submitAdb(int id, String travel_id, String line_type, String url) throws Exception {
		String sql = "update travel_adb set travel_id = ? , aside_picture = ? , line_type = ?" +
				" where adb_id =?";
		return JdbcUtils_DBCP.update(sql,new Object[]{travel_id,url,line_type,id});
	}

	@Override
	public int addAdb(int travel_id, int line_type,int is_aside, String url) throws Exception {
		String sql = "insert into travel_adb (travel_id,line_type,is_aside,aside_picture) values (?,?,?,?)";
		return JdbcUtils_DBCP.update(sql,new Object[]{travel_id,line_type,is_aside,url});
	}

	@Override
	public int delete(int len, String[] strArray) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM travel_adb WHERE adb_id IN (");
		for(int i=0;i<len-1;i++){
			sql.append(strArray[i]).append(",");
		}
		sql.append(strArray[len-1]).append(")");
		return JdbcUtils_DBCP.update(sql.toString(), null);
	}

}
