package dao;

import com.dd.dao.IUserDao;
import com.dd.daoImpl.UserDaoImpl;
import com.dd.dto.UserSearchItem;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class UserDao {
	private IUserDao userDao = UserDaoImpl.getInstance();
	
	@Test
	public void testMethodGetTotal() throws SQLException {
		System.out.println(userDao.selectAccountTotal(null));
	}
	@Test
	public void testMethodUsersSearch() throws Exception {
		UserSearchItem searchItem = new UserSearchItem();
		searchItem.setName("红红火火");
		searchItem.setEmail("123");
		searchItem.setPhone("18888643840");
		searchItem.setState(1);
		searchItem.setStartIndex(2);
		System.out.println(userDao.selectUserListByUserSearchItem(searchItem).size());
	}
	@Test
	public void testMethodTransction() {
		userDao.testShiwu();
	    
	}
}
