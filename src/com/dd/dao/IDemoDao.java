package com.dd.dao;

import java.sql.SQLException;
import java.util.Map;

public interface IDemoDao {
	Map<String, Object> selectDemoById(Integer id) throws SQLException; 
}
