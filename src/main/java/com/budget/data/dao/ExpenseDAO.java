package com.budget.data.dao;

import java.util.List;

import com.budget.data.model.Expense;

public interface ExpenseDAO {

	public long countAll();
	public List<Expense> getAll();
	public Expense save(Expense expense);
	public void delete(Expense expense);
	public Expense update(Expense expense);
	public Expense getById(Long id);

}
