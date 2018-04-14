package com.dd.service;

import com.dd.dto.UserSearchItem;
import com.dd.entity.TravelUser;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IUserService {
	/**
	 * @Author: TusuZer
	 * @Date: 2018/2/13 下午9:58
	 * @Des: 根据账号获取用户
	 **/
	TravelUser selectByAccount(String account) throws SQLException;

	/**
	 * @Author: TusuZer
	 * @Date: 2018/2/13 下午9:58
	 * @Des: 根据账号密码获取用户
	 **/
	TravelUser selectByAccountAndPassword(String account, String password) throws SQLException;

	/**
	 * @Author: TusuZer
	 * @Date: 2018/2/13 下午9:57
	 * @Des: 创建用户
	 **/
	int insert(TravelUser travelUser) throws Exception;

	/**
	 * @Author: TusuZer
	 * @Date: 2018/2/25 下午1:59
	 * @Des: 获取用户总数
	 **/
	long selectAccountTotal(UserSearchItem searchItem) throws SQLException;

	/**
	 * @Author: TusuZer
	 * @Date: 2018/2/25 下午3:18
	 * @Des: 根据搜索条件获取一页的用户信息，一页十条
	 **/
	Map<String, Object> selectUserPageDataByItem(UserSearchItem searchItem) throws Exception;

	/**
	 * @Author: TusuZer
	 * @Date: 2018/2/26 下午1:43
	 * @Des: 获取所有用户
	 **/
	List<Map<String, Object>> selectAllUser() throws Exception;

	/**
	 * @Author: TusuZer
	 * @Date: 2018/2/28 上午11:23
	 * @Des: 冻结用户
	 **/
	long diableUser(long id) throws Exception;
	
	/**
	 * @Author: TusuZer
	 * @Date: 2018/2/28 上午11:23
	 * @Des: 启用用户
	 **/
	long enableUser(long id) throws Exception;

	/**
	 * @Author: TusuZer
	 * @Date: 2018/2/28 上午11:32
	 * @Des: 通过id获取travel_user
	 **/
	Map<String, Object> selectUserAllInf(long id) throws SQLException;

	/**
	 * @Author: TusuZer
	 * @Date: 2018/3/16 上午11:13
	 * @Des: 更新用户权限
	 **/
	int updateUserPermission(long id, String permission) throws Exception;

	/**
	 * @Author: TusuZer
	 * @Date: 2018/3/16 下午4:00
	 * @Des: 更新用户0为普通用户1为管理员
	 **/
	int updateUserType(int id, int type) throws Exception;

	/**
	 * @Author: TusuZer
	 * @Date: 2018/3/16 下午4:17
	 * @Des: 根据类型获取用户
	 **/
	List<Map<String,Object>> selectUserByType(int type) throws Exception;

	int insertDemo(String name, String sex) throws Exception;

	int updateUserInf(TravelUser user) throws Exception;
}
