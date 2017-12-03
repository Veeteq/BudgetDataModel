package com.budget.data.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.budget.data.dao.CategoryDAO;
import com.budget.data.model.Category;

@Service
@Transactional
public class CategoryService {

	@Autowired
	CategoryDAO categoryDAO;
	
	public long countAll(){
		return categoryDAO.countAll();
	}
	
	public List<Category> getAll(){
		return categoryDAO.getAll();
	}

	public Category getById(long id) {
		return categoryDAO.getById(id);
	}
	
	public void save(Category category){
		categoryDAO.save(category);
	}
}
