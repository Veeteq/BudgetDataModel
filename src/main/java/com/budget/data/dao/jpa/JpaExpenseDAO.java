package com.budget.data.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.budget.data.dao.ExpenseDAO;
import com.budget.data.model.Expense;

@Repository
public class JpaExpenseDAO implements ExpenseDAO{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public long countAll() {
		String sqlString = "SELECT COUNT(e) from Expense e";
		return entityManager.createQuery(sqlString, Long.class).getSingleResult();
	}

	@Override
	public List<Expense> getAll() {
		String sqlString = "SELECT e from Expense e";
		return entityManager.createQuery(sqlString, Expense.class).getResultList();
	}

	@Override
	public Expense save(Expense expense) {
		entityManager.persist(expense);
		return expense;
	}

	@Override
	public void delete(Expense expense) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Expense update(Expense expense) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expense getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void flush() {
		this.entityManager.flush();
	}

	public void clear() {
		this.entityManager.clear();
	}
}