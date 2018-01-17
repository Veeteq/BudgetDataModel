package com.budget.data.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.budget.data.dao.ExpenseDAO;
import com.budget.data.dao.ItemDAO;
import com.budget.data.model.Category;
import com.budget.data.model.Expense;
import com.budget.data.model.Item;
import com.budget.data.service.CategoryService;
import com.budget.data.service.ItemService;

@ContextConfiguration("classpath*:META-INF/spring/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestItemDAO {

	private Logger logger = LoggerFactory.getLogger(TestItemDAO.class);
	
	@Autowired
	ItemDAO itemDAO;
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ExpenseDAO expenseDAO;
	
	@Test
	public void getAll(){
		logger.info("running tests");
		List<Item> items = itemDAO.getAll();
		logger.info("items count: " + items.size());
		Assert.assertEquals(782, items.size());
	}
	
	@Test
	public void getAllCategories(){
		logger.info("testing categories");
		List<Category> categories = categoryService.getAll();
		logger.info("categories count: " + categories.size());
	}

	//@Test
	public void getItemById(){
		logger.info("testing item by id");
		Item item = itemService.getById(2);
		logger.info("item name: " + item.getCategory().toString());
	}
	
	//@Test
	public void addItem(){
		logger.info("testing adding new item");
		Item item = new Item();
		item.setId(22);
		item.setCategory(categoryService.getById(12));
		item.setName("Bilety do ...");
		//itemService.save(item);
	}
	
	@Test
	public void listExp(){
		List<Expense> expenses = expenseDAO.getAll();
		expenses.forEach(System.out::println);
	}
}