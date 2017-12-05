package com.budget.data.test;

import org.junit.Assert;
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

import com.budget.data.dao.CategoryDAO;
import com.budget.data.model.Category;
import com.budget.data.service.CategoryService;

@ContextConfiguration("classpath*:META-INF/spring/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class DAOTest {

	private Logger logger = LoggerFactory.getLogger(DAOTest.class);
	
	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private CategoryService categoryService;

	@Test
	public void launchDAO() {
		logger.info("running tests: countAll");
		Assert.assertEquals(0, categoryDAO.countAll());
	}

	//@Test
	@Rollback(value=false)
	public void launchService() {
		logger.info("running tests: save");
		Category category = new Category();
		category.setId(1);
		category.setName("Apteczka");
		categoryService.save(category);
		logger.info("count: " + categoryDAO.countAll());
		//Assert.assertEquals("Apteczka", categoryService.getById(1L).getName());
	}
	
	//@Test
	//@Rollback(value=false)
	@Commit
	public void launchDAO2() {
		logger.info("running tests: DAO save");
		Category category = new Category();
		category.setId(1);
		category.setName("Apteczka");
		categoryDAO.save(category);
		logger.info("count: " + categoryDAO.countAll());
		//Assert.assertEquals("Apteczka", categoryService.getById(1L).getName());
	}
}
