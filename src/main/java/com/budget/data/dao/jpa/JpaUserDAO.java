package com.budget.data.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.budget.data.dao.UserDAO;
import com.budget.data.model.User;

@Repository
public class JpaUserDAO implements UserDAO{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public long countAll() {
		String sqlString = "SELECT COUNT(u) from User u";
		return entityManager.createQuery(sqlString, Long.class).getSingleResult();
	}

	@Override
	public List<User> getAll() {
		String sqlString = "SELECT u from User u";
		return entityManager.createQuery(sqlString, User.class).getResultList();
	}

	public TypedQuery<User> getByName(String name){
		if (name == null || name.length() == 0) throw new IllegalArgumentException("The name argument is required");
		String sqlString = "SELECT u from User u WHERE u.name= :name";
		TypedQuery<User> query = entityManager.createQuery(sqlString, User.class);
		query.setParameter("name", name);
		return query;
	}
	
	@Override
	public User getById(long id) {
		return entityManager.find(User.class, id);
	}

	@Override
	public User save(User user) {
		entityManager.persist(user);
		return user;
	}

	@Override
	public boolean delete(User user) {
		if (this.entityManager.contains(user)) {
			this.entityManager.remove(user);
		} else {
			User attached = getById(user.getId());
			this.entityManager.remove(attached);
		}
		return true;
	}

	@Override
	public User update(User user) {
		User merged = this.entityManager.merge(user);
		this.entityManager.flush();
		return merged;
	}

	public void flush() {
		this.entityManager.flush();
	}

	public void clear() {
		this.entityManager.clear();
	}
}
