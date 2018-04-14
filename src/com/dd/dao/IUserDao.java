package com.dd.dao;

import com.dd.dto.UserSearchItem;
import com.dd.entity.TravelUser;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IUserDao {
	
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
	 * @Date: 2018/2/13 下午9:58
	 * @Des: 创建用户
	 **/
	int insert(TravelUser travelUser) throws Exception;

	/**
	 * @Author: TusuZer
	 * @Date: 2018/2/25 上午11:49
	 * @Des: 获得账号的总数量
	 **/
	long selectAccountTotal(UserSearchItem searchItem) throws SQLException;

	/**
	 * @Author: TusuZer
	 * @Date: 2018/2/25 下午2:12
	 * @Des: 根据开始位置获取用户信息
	 **/
	List<Map<String, Object>> selectUserListByUserSearchItem(UserSearchItem searchItem) throws Exception;

	/**
	 * @Author: TusuZer
	 * @Date: 2018/2/26 下午1:39
	 * @Des: 获取所有用户
	 **/
	List<Map<String, Object>> selectAllUser() throws Exception;

	/**
	 * @Author: TusuZer
	 * @Date: 2018/2/28 上午11:26
	 * @Des: 更新用户状态
	 **/
	long updateState(long id, int state) throws Exception;

	/**
	 * @Author: TusuZer
	 * @Date: 2018/2/28 上午11:31
	 * @Des: 获取用户详细信息
	 **/
	Map<String, Object> selectById(long id) throws SQLException;

	/**
	 * @Author: TusuZer
	 * @Date: 2018/3/16 上午10:16
	 * @Des: 更新用户权限
	 **/
	int updateUserPermission(long id, String permission) throws Exception;
	
	void testShiwu();

	/**
	 * @Author: TusuZer
	 * @Date: 2018/3/16 下午4:02
	 * @Des: 更新用户类型
	 **/
	int updateUserType(int id, int type) throws Exception;

	/**
	 * @Author: TusuZer
	 * @Date: 2018/3/16 下午4:18
	 * @Des: 根据类型获取用户列表
	 **/
	List<Map<String,Object>> selectUserByType(int type) throws Exception;

	int insertDemo(String name, String sex) throws Exception;

	int updateUserInf(TravelUser user) throws Exception;
}
