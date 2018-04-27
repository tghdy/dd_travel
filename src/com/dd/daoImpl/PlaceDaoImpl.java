package com.dd.daoImpl;

import com.dd.dao.IPlaceDao;
import com.dd.entity.TravelPlace;
import com.dd.util.JdbcUtils_DBCP;

import java.util.List;
import java.util.Map;

public class PlaceDaoImpl implements IPlaceDao {

	private static PlaceDaoImpl adbDao = new PlaceDaoImpl();

	public static PlaceDaoImpl getInstance() {
		return  adbDao;
	}

	@Override
	public List<Map<String, Object>> selectSonAreaByPid(Integer pid) throws Exception {
		String sql = "select * from travel_place ";
		if (pid != null && pid > -1)
			sql += " where parent_id = " + pid;
		return JdbcUtils_DBCP.selectMapList(sql, null);
	}

	@Override
	public int uploadPlace(TravelPlace place) throws Exception {
		String sql = "update travel_place set place_name = ?, place_desc = ? where id = ?";
		return JdbcUtils_DBCP.update(sql, new Object[]{place.getPlaceName(), place.getPlaceDesc(), place.getId()});
	}

	@Override
	public Map<String, Object> selectPlace(int id) throws Exception {
		String sql = "select * from travel_place where id = " + id;
		return JdbcUtils_DBCP.selectMap(sql,null);
	}

	@Override
	public int deletePlace(int id) throws Exception {
		String sql = "DELETE FROM travel_place WHERE id = " + id;
		return JdbcUtils_DBCP.update(sql, null);
	}

	@Override
	public int insertPlace(TravelPlace place) throws Exception {
		String sql = "insert into travel_place (parent_id,place_name,place_desc) values (?,?,?)";
		return JdbcUtils_DBCP.update(sql, new Object[]{place.getParentId(), place.getPlaceName(), place.getPlaceDesc()});
	}


}
