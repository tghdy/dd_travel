package service;

import com.dd.service.IUserService;
import com.dd.serviceImpl.UserServiceImpl;
import org.junit.Test;

import java.sql.SQLException;

public class TestUserService {
	private IUserService iUserService = UserServiceImpl.getInstance();
	
	@Test
	public void testMethod() throws SQLException {
		System.out.println(iUserService.selectByAccount("qwe"));
	}
}
