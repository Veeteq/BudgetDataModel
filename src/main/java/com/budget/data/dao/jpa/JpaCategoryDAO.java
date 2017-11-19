package com.budget.data.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.budget.data.dao.CategoryDAO;
import com.budget.data.model.Category;

@Repository
public class JpaCategoryDAO implements CategoryDAO{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Category> getAll() {
		String sqlString = "SELECT c from Category c";
		return entityManager.createQuery(sqlString, Category.class).getResultList();
	}

	@Override
	public Category getById(long id) {
		return entityManager.find(Category.class, id);
	}
	
	@Override
	public Category save(Category category) {
		entityManager.persist(category);
		return null;
	}

	@Override
	public boolean delete(Category category) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Category update(Category category) {
		// TODO Auto-generated method stub
		return null;
	}
}
