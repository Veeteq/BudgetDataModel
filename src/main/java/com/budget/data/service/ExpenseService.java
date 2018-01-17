package com.budget.data.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.budget.data.dao.ExpenseDAO;
import com.budget.data.model.Expense;

@Service
@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
public class ExpenseService {

	@Autowired
	ExpenseDAO expenseDAO;
	
	public long countAll(){
		return expenseDAO.countAll();
	}
	
	public List<Expense> getAll(){
		return expenseDAO.getAll();
	}

	public Expense getById(long id) {
		return expenseDAO.getById(id);
	}
	
	public void save(Expense expense){
		expenseDAO.save(expense);
	}

}
