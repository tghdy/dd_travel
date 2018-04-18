package com.dd.util;

import java.awt.geom.FlatteningPathIterator;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.management.Query;
import javax.sql.DataSource;

import com.dd.entity.TravelLine;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.*;

public class JdbcUtils_DBCP {

	private static DataSource ds = null;

	/**
	 * 静态代码块读取配置文件，初始化DBCP连接池
	 */
	static {
		try {
			InputStream in = JdbcUtils_DBCP.class.getClassLoader().getResourceAsStream("dbcpconfig.properties");
			Properties prop = new Properties();
			prop.load(in);

			BasicDataSourceFactory factory = new BasicDataSourceFactory();
			ds = factory.createDataSource(prop);
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	private static QueryRunner runner = new QueryRunner(getDataSource());
	
	/**
	 * 函数名称：getDataSource
	 * 功能描述：
	 * 参数说明：
	 * 返回值：DataSource
	 * 备注：
	 * 作者：ariclee		时间：2016年6月12日下午1:23:39
	 */
	public static DataSource getDataSource() {
		return ds;
	}

	/**
	 * 函数名称：getConnection
	 * 功能描述：
	 * 参数说明：
	 * 返回值：Connection
	 * 备注：
	 * 作者：ariclee		时间：2016年5月26日下午5:41:49
	 */
	public static Connection getConnection() {

		try {
			return ds.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException("JdbcUtils_DBCP--getConnection ERROR!!");
		}
	}
	/**
	 * @Author: TusuZer
	 * @Date: 2018/2/12 下午10:54
	 * @Des: 插入
	 **/	
	public static int update(String sql, Object[] params) throws Exception {
		int flag = 0;
		flag = runner.update(sql, params);
		return flag;
		
	}

	/**
	 * @Author: TusuZer
	 * @Date: 2018/2/13 上午8:06
	 * @Des: 根据sql与参数获取Bean
	 **/
	public static <T> T select(String sql, Object[] params, Class<T> clazz) throws Exception{
		T obj = null;
		obj =  runner.query(sql,
			(ResultSetHandler<T>) new BeanHandler(clazz, new BasicRowProcessor(new MyBeanProcessor(new HumpMatcher()))), params);
		return obj;
	}

	/**
	 * @Author: TusuZer
	 * @Date: 2018/2/13 上午10:47
	 * @Des: 根据sql与参数获取List
	 **/
	public static <T> List<T> selectList(String sql, Object[] params, Class<T> clazz) throws Exception{
		List<T> list = null;
		list = runner.query(sql,
			new BeanListHandler<>(clazz, new BasicRowProcessor(new MyBeanProcessor(new HumpMatcher()))), params);
			
		return list;
	}

	/**
	 * @Author: TusuZer
	 * @Date: 2018/2/13 上午11:37
	 * @Des: 根据sql与参数获取Map
	 **/
	public static Map<String, Object> selectMap(String sql, Object[] params) throws Exception{
		Map<String, Object> map = null;
		map = runner.query(sql, new MapHandler(), params);
		return map;

	}

	/**
	 * @Author: TusuZer
	 * @Date: 2018/2/13 上午11:37
	 * @Des: 根据sql与参数获取ListMap
	 **/
	public static List<Map<String, Object>> selectMapList(String sql, Object[] params) throws Exception {
		List<Map<String, Object>> map = null; 
		map = runner.query(sql, new MapListHandler(), params);
		return map;

	}

	public static int transctionInsert(String sql, Object[] params) throws Exception {
		return runner.update(TransactionUtil.getConnection(), sql, params); 
	}
	
	public static Object transctionSelect(String sql, Object[] params) throws Exception {
		return runner.query(TransactionUtil.getConnection(), sql, new ScalarHandler<Long>()); 
	}

	public static Object selectColumnValue(String sql, Object[] params) throws Exception {
		return runner.query(sql, new ScalarHandler<>(), params);
	}
	
	/**
	 * 函数名称：release
	 * 功能描述：
	 * 参数说明：
	 * 返回值：void
	 * 备注：
	 * 
	 * 作者：ariclee		时间：2016年5月26日下午5:41:55
	 */
	public static void release(Connection conn, Statement st, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			rs = null;
		}
		if (st != null) {
			try {
				st.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
