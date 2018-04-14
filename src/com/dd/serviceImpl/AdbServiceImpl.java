package com.dd.serviceImpl;

import com.dd.dao.IAdbDao;
import com.dd.daoImpl.AdbDaoImpl;
import com.dd.service.IAdbService;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class AdbServiceImpl implements IAdbService {
	private static AdbServiceImpl adbService = new AdbServiceImpl();

	public static AdbServiceImpl getInstance() {
		return adbService;
	}

	private IAdbDao adbDao = AdbDaoImpl.getInstance();

	@Override
	public List<Map<String, Object>> selectIndexSlideAdb() throws Exception {
		return adbDao.selectIndexSlideAdb();
	}

	@Override
	public List<Map<String, Object>> selectHotLineAdb() throws Exception {
		return adbDao.selectHotLineAdb();
	}
	
}
