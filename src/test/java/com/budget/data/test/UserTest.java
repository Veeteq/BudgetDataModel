package com.budget.data.test;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.budget.data.dao.UserDAO;
import com.budget.data.model.User;
import com.budget.data.service.UserService;

@ContextConfiguration("classpath*:META-INF/spring/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UserTest {

	private Logger logger = LoggerFactory.getLogger(TestItemDAO.class);
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	private UserService userService;
	
	@Before
	@Commit
	public void init(){
		User user = new User();
		user.setId(6L);
		user.setName("Świnia");
		//userService.save(user);
	}
	
	@Test
	public void getCount() {
		long count = userService.countAll();
		logger.info("users count: " + count);
		Assert.assertEquals(30, count);
	}
	
	@Test
	@Rollback(value=false)
	public void getUserById() {
		User user = userService.getById(2L);
		Assert.assertEquals("Witek", user.getName());
	}
	
	@After
	public void closeUp(){
		List<User> users = userService.getAll();
		for(User user : users){
			logger.info(user.getName());
		}
		userDAO.flush();
	}
}
