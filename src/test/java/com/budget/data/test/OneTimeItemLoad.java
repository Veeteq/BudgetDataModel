package com.budget.data.test;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import com.budget.data.dao.CategoryDAO;

@ContextConfiguration("classpath*:META-INF/spring/applicationContext.xml")
public class OneTimeItemLoad {
	private static Logger logger = LoggerFactory.getLogger(CategoryDAO.class);
	
	private final static String cateString = "INSERT INTO categories(cate_id, cate_name_tx, cate_type_tx) values(?, ?, ?)";
	private final static String itemString = "INSERT INTO items(item_id, cate_id, item_name_tx) values(?, ?, ?)";
	private final static String userString = "INSERT INTO users(user_id, user_name_tx) values(?, ?)";
	private final static String expeString = "INSERT INTO expenses(expe_id, oper_dt, item_id, expe_item_cn, expe_pric_am, expe_comm_tx, user_id) values(?, ?, ?, ?, ?, ?, ?)";
	
	public static void main(String[] args) throws IOException {
		File cateFile = new File("f:/categories.txt");
		File itemFile = new File("f:/items.txt");
		File userFile = new File("f:/users.txt");
		File expeFile = new File("f:/expences.txt");
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:META-INF/spring/applicationContext.xml");
		EntityManagerFactory emf = (EntityManagerFactory) ctx.getBean("entityManagerFactory");
		EntityManager em = emf.createEntityManager();
				
		saveCategories(getListFromFile(cateFile), em);
		saveItems(getListFromFile(itemFile), em);		
		saveUsers(getListFromFile(userFile), em);
		saveExpenses(getListFromFile(expeFile), em);
		
		rebuildSequences("SELECT max(i.id) FROM Item i", "item", em);
		rebuildSequences("SELECT max(u.id) FROM User u", "user", em);
		rebuildSequences("SELECT max(c.id) FROM Category c", "cate", em);
		rebuildSequences("SELECT max(e.id) FROM Expense e", "expe", em);
		
		String sqlString2 = "SELECT COUNT(c) from Category c";
		Long result = em.createQuery(sqlString2, Long.class).getSingleResult();
		System.out.println("result is: " + result);
		em.close();
		
		((ConfigurableApplicationContext)ctx).close();
	}
	
	public static List<String> getListFromFile(File dataFile) throws IOException{
		List<String> dataList = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(dataFile),"UTF-8"));
		String lineTxt = br.readLine();
		while(lineTxt != null){
			dataList.add(lineTxt);
			lineTxt = br.readLine();
		}
		br.close();
		return dataList;
	}
	
	public static void saveItems(List<String> dataList, EntityManager em){
		EntityTransaction et = em.getTransaction();

		et.begin();
		Query delQuery = em.createNativeQuery("DELETE FROM items");
		delQuery.executeUpdate();
	    et.commit();

		dataList.forEach(s -> {
			String[] data = s.split("\\t");
			et.begin();
			Query query = em.createNativeQuery(itemString);
			query.setParameter(1, Integer.parseInt(data[0]));
			query.setParameter(2, Integer.parseInt(data[1]));
			query.setParameter(3, data[2]);
			query.executeUpdate();
			et.commit();
			
		});
	}
	
	public static void saveCategories(List<String> dataList, EntityManager em){
		EntityTransaction et = em.getTransaction();

		et.begin();
		Query delQuery = em.createNativeQuery("DELETE FROM categories");
	    delQuery.executeUpdate();
	    et.commit();
	    
		dataList.forEach(s -> {
			String[] data = s.split("\\t");
			et.begin();
			Query query = em.createNativeQuery(cateString);
			query.setParameter(1, Integer.parseInt(data[0]));
			query.setParameter(2, data[1]);
			query.setParameter(3, data[2]);
			query.executeUpdate();
			et.commit();
		});
	}

	private static void saveUsers(List<String> dataList, EntityManager em) {
		EntityTransaction et = em.getTransaction();

		et.begin();
		Query delQuery = em.createNativeQuery("DELETE FROM users");
	    delQuery.executeUpdate();
	    et.commit();
		
		dataList.forEach(s -> {
			String[] data = s.split("\\t");
			et.begin();
			Query query = em.createNativeQuery(userString);
			query.setParameter(1, Integer.parseInt(data[0]));
			query.setParameter(2, data[1]);
			query.executeUpdate();
			et.commit();
		});
	}
	
	private static void saveExpenses(List<String> dataList, EntityManager em) {
		EntityTransaction et = em.getTransaction();

		et.begin();
		Query delQuery = em.createNativeQuery("DELETE FROM expenses");
	    delQuery.executeUpdate();
	    et.commit();
	    
	    dataList.forEach(s -> {
			String[] data = s.split("\\t");
			et.begin();
			Query query = em.createNativeQuery(expeString);
			query.setParameter(1, Integer.parseInt(data[0]));
			query.setParameter(2, data[1]);
			query.setParameter(3, Integer.parseInt(data[2]));
			query.setParameter(4, data[3]);
			query.setParameter(5, data[4]);
			query.setParameter(6, data[5]);
			query.setParameter(7, Integer.parseInt(data[6]));
			query.executeUpdate();
			et.commit();
		});
	}
	
	public static String getId(String sqlString, EntityManager em){
		 Long id = em.createQuery(sqlString, Long.class).getSingleResult();
		 id++;
		 return id.toString();
	}
	
	public static void rebuildSequences(String sqlString, String entity, EntityManager em){
		EntityTransaction et = em.getTransaction();
		et.begin();
		
		String dropString = "DROP SEQUENCE :seq";
		dropString = dropString.replace(":seq", entity + "_seq");
		Query query1 = em.createNativeQuery(dropString);
		query1.executeUpdate();
		
		String createString = "CREATE SEQUENCE :seq AS INTEGER START WITH :id INCREMENT BY 1";
		createString = createString.replace(":seq", entity + "_seq");
		createString = createString.replace(":id", getId(sqlString, em));
		logger.info(createString);
		Query query2 = em.createNativeQuery(createString);
		query2.executeUpdate();
		et.commit();
	}
			
}
