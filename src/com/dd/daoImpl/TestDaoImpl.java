package com.dd.daoImpl;

import com.dd.dao.TestDao;
import com.dd.entity.Travel;
import com.dd.util.JdbcUtils_DBCP;
import org.apache.commons.dbutils.QueryRunner;

import java.util.List;

public class TestDaoImpl implements TestDao {
	private static TestDaoImpl testDaoImpl = new TestDaoImpl();
	QueryRunner runner = new QueryRunner(JdbcUtils_DBCP.getDataSource());
	
	public static TestDaoImpl getInstance(){
		return testDaoImpl;
	}
	
	@Override
	public void testMethod() {
		String sql = "select travel_name label_name from travel_line";
		//TransactionUtil.startTransaction();

		try {
			Travel travel = JdbcUtils_DBCP.select(sql, null, Travel.class);
			List<Travel> list = JdbcUtils_DBCP.selectList(sql, null, Travel.class);
			//System.out.println(travel);
			list.forEach(travel1 -> {
				System.out.println("还可以");
				System.out.println(travel1);
			});
			//Map<String, Object> map = null;
			//map = runner.query(sql, new MapHandler());
			//System.out.println("laile");
			//System.out.println(map);

		} catch (Exception e) {
			//TransactionUtil.roolback();
			e.printStackTrace();
		} finally {
			//TransactionUtil.commit();
			//TransactionUtil.release();
		}

	}
}
