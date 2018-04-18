package com.dd.daoImpl;

import com.dd.dao.IDemoDao;
import com.dd.util.JdbcUtils_DBCP;

import java.sql.SQLException;
import java.util.Map;

public class DemoDaoImpl implements IDemoDao {
	private static DemoDaoImpl demoDao = new DemoDaoImpl();//初始化

	public static DemoDaoImpl getInstance() {
		return demoDao;
	}
	
	@Override
	public Map<String, Object> selectDemoById(Integer id) throws Exception{
		String sql = "select * from demo_t where id = ?";
		return JdbcUtils_DBCP.selectMap(sql, new Object[]{id});
	}
	
}
