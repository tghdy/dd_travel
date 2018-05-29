package com.dd.serviceImpl;

import com.dd.dao.IAdbDao;
import com.dd.daoImpl.AdbDaoImpl;
import com.dd.service.IAdbService;

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

	@Override
	public int updatepic(int id, String url) throws Exception {
		return adbDao.updatepic(id,url);
	}

	@Override
	public List<Map<String, Object>> selectAllAdb() throws Exception {
		return adbDao.selectAllAdb();
	}

	@Override
	public Map<String, Object> showOneAdv(int id) throws Exception {
		return adbDao.showOneAdv(id);
	}

	@Override
	public int submitAdb(int id, String travel_id, String line_type, String url) throws Exception {
		return adbDao.submitAdb(id,travel_id,line_type,url);
	}

	@Override
	public int addAdb(int travel_id, int line_type,int is_aside, String url) throws Exception {
		return adbDao.addAdb(travel_id,line_type,is_aside,url);
	}

	@Override
	public int delete(int len, String[] strArray) throws Exception{
		/*for(int i=0;i<strArray.length;i++){
			adbDao.delete(strArray[i]);
		}*/
		return adbDao.delete(len,strArray);
	}

}
