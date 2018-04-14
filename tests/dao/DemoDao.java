package dao;

import com.dd.dao.IDemoDao;
import com.dd.daoImpl.DemoDaoImpl;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class DemoDao {
	private IDemoDao demoDao = DemoDaoImpl.getInstance();
	@Test
	public void testMethod() {
		try {
			System.out.println(demoDao.selectDemoById(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
