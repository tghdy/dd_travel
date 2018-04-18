package com.dd.serviceImpl;

import com.dd.dao.IUserDao;
import com.dd.daoImpl.UserDaoImpl;
import com.dd.dto.UserSearchItem;
import com.dd.entity.TravelUser;
import com.dd.service.IUserService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UserServiceImpl implements IUserService {

	private static  IUserDao iUserDao = UserDaoImpl.getInstance();

	private static UserServiceImpl userServiceImpl = new UserServiceImpl();

	public static UserServiceImpl getInstance() {
		return userServiceImpl;
	}
	
	@Override
	public TravelUser selectByAccount(String account) throws Exception{
		TravelUser travelUser = iUserDao.selectByAccount(account);
		return travelUser;
	}

	@Override
	public TravelUser selectByAccountAndPassword(String account, String password) throws Exception{
		TravelUser travelUser = iUserDao.selectByAccountAndPassword(account, password);
		return travelUser;
	}

	@Override
	public int insert(TravelUser travelUser) throws Exception {
		int flag = iUserDao.insert(travelUser);
		return flag;
	}

	@Override
	public long selectAccountTotal(UserSearchItem searchItem) throws Exception {
		return iUserDao.selectAccountTotal(searchItem);
	}
	
	@Override
	public Map<String, Object> selectUserPageDataByItem(UserSearchItem searchItem) throws Exception {
		Map<String, Object> map = new HashMap<>();
		//获取普通用户的记录总数
		long total = iUserDao.selectAccountTotal(searchItem);
		if (total == 0)
			return null;
		map.put("total", total);
		//当前获取页号
		long page = searchItem.getStartIndex();
		map.put("page", searchItem.getStartIndex());
		//获取根据条件获取十条结果
		List<Map<String, Object>> users = iUserDao.selectUserListByUserSearchItem(searchItem);
		map.put("users", users);
		//总页数
		long totalPage = total % 10 == 0 ? total / 10 : (total / 10) + 1;
		map.put("totalPage", totalPage);
		return map;
	}

	@Override
	public List<Map<String, Object>> selectAllUser() throws Exception {
		return iUserDao.selectAllUser();
	}

	@Override
	public long diableUser(long id) throws Exception {
		return iUserDao.updateState(id, 0);
	}

	@Override
	public long enableUser(long id) throws Exception {
		return iUserDao.updateState(id, 1);
	}

	@Override
	public Map<String, Object> selectUserAllInf(long id) throws Exception{
		return iUserDao.selectById(id);
	}

	@Override
	public int updateUserPermission(long id, String permission) throws Exception {
		return iUserDao.updateUserPermission(id, permission);
	}

	@Override
	public int updateUserType(int id, int type) throws Exception {
		return iUserDao.updateUserType(id, type);
	}

	@Override
	public List<Map<String, Object>> selectUserByType(int type) throws Exception {
		return iUserDao.selectUserByType(type);
	}

	@Override
	public int insertDemo(String name, String sex) throws Exception {
		//service的实现类
		return iUserDao.insertDemo(name, sex);
	}

	@Override
	public int updateUserInf(TravelUser user) throws Exception {
		return iUserDao.updateUserInf(user);
	}


}
