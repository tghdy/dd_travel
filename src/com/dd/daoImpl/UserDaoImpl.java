package com.dd.daoImpl;

import com.dd.dao.IUserDao;
import com.dd.dto.UserSearchItem;
import com.dd.entity.TravelUser;
import com.dd.util.JdbcUtils_DBCP;
import com.dd.util.TransactionUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class UserDaoImpl implements IUserDao {

	final private String ALL_COLUMN = " id, user_account, user_name, password, last_login, mobile_phone, email, sex, id_card, head, type, state, permissions ";

	private static UserDaoImpl userDapImpl = new UserDaoImpl();

	public static UserDaoImpl getInstance() {
		return userDapImpl;
	}

	@Override
	public TravelUser selectByAccount(String account) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM travel_user WHERE user_account = ?");
		TravelUser travelUser = JdbcUtils_DBCP.select(sql.toString(), new Object[]{account}, TravelUser.class);
		return travelUser;
	}

	@Override
	public TravelUser selectByAccountAndPassword(String account, String password) throws Exception{
		String sql = "SELECT * FROM travel_user WHERE user_account = ? AND password = ?";
		TravelUser travelUser = JdbcUtils_DBCP.select(sql, new Object[]{account, password}, TravelUser.class);
		return travelUser;
	}

	@Override
	public int insert(TravelUser travelUser) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO travel_user (")
				.append(ALL_COLUMN)
				.append(") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
		int flag = JdbcUtils_DBCP.update(sql.toString(), travelUser.geParams());
		return flag;
	}

	@Override
	public long selectAccountTotal(UserSearchItem searchItem) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM travel_user WHERE state = 1");
		if (searchItem.getName() != null) {
			sql.append(" AND user_name = '").append(searchItem.getName()).append("'");
		}
		if (searchItem.getPhone() != null) {
			sql.append(" AND mobile_phone = '").append(searchItem.getPhone()).append("'");
		}
		if (searchItem.getEmail() != null) {
			sql.append(" AND email = '").append(searchItem.getEmail()).append("'");
		}
		if (searchItem.getState() != null) {
			sql.append(" AND state = ").append(searchItem.getState());
		}
		return (long) JdbcUtils_DBCP.selectColumnValue(sql.toString(), null);
	}

	@Override
	public List<Map<String, Object>> selectUserListByUserSearchItem(UserSearchItem searchItem) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM travel_user WHERE state = 1");
		if (searchItem.getName() != null) {
			sql.append(" AND user_name = '").append(searchItem.getName()).append("'");
		}
		if (searchItem.getPhone() != null) {
			sql.append(" AND mobile_phone = '").append(searchItem.getPhone()).append("'");
		}
		if (searchItem.getEmail() != null) {
			sql.append(" AND email = '").append(searchItem.getEmail()).append("'");
		}
		if (searchItem.getState() != null) {
			sql.append(" AND state = ").append(searchItem.getState());
		}
		sql.append(" LIMIT ")
				.append((searchItem.getStartIndex() - 1) * 10)
				.append(", 10");
		return JdbcUtils_DBCP.selectMapList(sql.toString(), null);
	}

	@Override
	public List<Map<String, Object>> selectAllUser() throws Exception {
		String sql = "SELECT * FROM travel_user WHERE type = 0";
		return JdbcUtils_DBCP.selectMapList(sql, null);
	}


	@Override
	public long updateState(long id, int state) throws Exception {
		String sql = "UPDATE travel_user SET state = ? WHERE id = ?";
		return JdbcUtils_DBCP.update(sql, new Object[]{state, id});
	}

	@Override
	public Map<String, Object> selectById(long id) throws Exception{
		String sql = "SELECT * FROM travel_user WHERE id = ?";
		return JdbcUtils_DBCP.selectMap(sql, new Object[]{id});
	}

	@Override
	public int updateUserPermission(long id, String permission) throws Exception {
		String sql = "UPDATE travel_user SET permission = ? WHERE id = ?";
		return JdbcUtils_DBCP.update(sql, new Object[]{permission, id});
	}

	@Override
	public void testShiwu() {
		String sql1 = "insert into test_t (name,sex) values ('asd',1)";
		//String sql2 = "update travel_user set sex =  3 WHERE id = 2";
		TransactionUtil.startTransaction();
		QueryRunner runner = new QueryRunner(JdbcUtils_DBCP.getDataSource());
		try {
			//runner.update(TransactionUtil.getConnection(), sql2);
			runner.update(TransactionUtil.getConnection(), sql1);
			System.out.println(runner.query(TransactionUtil.getConnection(), "SELECT LAST_INSERT_ID();", new ScalarHandler<Integer>()));

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("asdasd");
			TransactionUtil.roolback();
		} finally {
			TransactionUtil.commit();
			TransactionUtil.release();

		}
	}

	@Override
	public int updateUserType(int id, int type) throws Exception {
		String sql = "update travel_user set type =  ? where id = ?";
		return JdbcUtils_DBCP.update(sql, new Object[]{type, id});
	}

	@Override
	public List<Map<String, Object>> selectUserByType(int type) throws Exception {
		String sql = "select * from travel_user where type = ?";
		return JdbcUtils_DBCP.selectMapList(sql, new Object[]{type});
	}

	@Override
	public int insertDemo(String name, String sex) throws Exception {
		String sql = "insert into demo_t (name, sex) values (?, ?)";
		return JdbcUtils_DBCP.update(sql, new Object[]{name, sex});
	}

	@Override
	public int updateUserInf(TravelUser user) throws Exception {
		String sql = "update travel_user set mobile_phone=?,email=?,user_name=?,sex=? where id = ?";
		System.out.println(sql);
		return JdbcUtils_DBCP.update(sql, new Object[]{user.getMobilePhone(), user.getEmail(), user.getUserName(), user.getSex(), user.getId()});
	}
}
		