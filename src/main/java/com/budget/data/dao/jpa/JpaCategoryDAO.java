package com.budget.data.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.budget.data.dao.CategoryDAO;
import com.budget.data.model.Category;

@Repository
public class JpaCategoryDAO implements CategoryDAO{
	private Logger logger = LoggerFactory.getLogger(CategoryDAO.class);

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public long countAll(){
		String sqlString = "SELECT COUNT(c) FROM Category c";
		return entityManager.createQuery(sqlString, Long.class).getSingleResult();
	}

	@Override
	public List<Category> getAll() {
		String sqlString = "SELECT c from Category c";
		return entityManager.createQuery(sqlString, Category.class).getResultList();
	}

	@Override
	public Category getById(Long id) {
		if(id == null) return null;
		return entityManager.find(Category.class, id);
	}
	
	@Override
	public Category save(Category category) {
                logger.info("DAO: persist - " + category.getName());
		entityManager.persist(category);
		return null;
	}

	@Override
	public Category update(Category category) {
        Category merged = entityManager.merge(category);
		entityManager.flush();
		return merged;
	}

	@Override
    public void delete(Category category) {
		if (entityManager.contains(category)) {
			entityManager.remove(category);
		} else {
			category = getById(category.getId());
			entityManager.remove(category);
		}
	}

	public void flush() {
		entityManager.flush();
	}

	public void clear() {
		entityManager.clear();
	}
}
