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
		String sql = "SELECT ha.*, tl.* FROM hotline_adb ha, travel_line tl where ha.state = 1 AND tl.id = ha.travel_id";
		return JdbcUtils_DBCP.selectMapList(sql, null);
	}

}
