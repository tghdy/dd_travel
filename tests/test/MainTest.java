package test;


import com.dd.entity.TravelUser;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import sun.nio.ch.ThreadPool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

//@RunWith()
public class MainTest {
	
	@Test
	public void testMethod() {
		
		List<TravelUser> travelUsers = new ArrayList<>();
		TravelUser user1 = new TravelUser();
		TravelUser user2 = new TravelUser();
		user1 .setId(1);
		user2.setId(2);
		user1.setLastLogin("qqqqq");
		user1.setLastLogin("qkkkkk");
		travelUsers.add(user1);
		travelUsers.add(user2);
		
		List<TravelUser> travelUsers1 = travelUsers.stream().map(user -> {
			user.setType(1);
			return user;
		}).collect(Collectors.toList());
		
		
		
		
	}
	public void add (){
		for (int i=0;i<100;i++){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("add:"+i);
		}
	}
	public void delete (){
		for (int i=100;i>0;i--){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("delete:"+i);
		}
	}
}
