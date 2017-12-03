package com.budget.data.dao;

import java.util.List;

import com.budget.data.model.Category;

public interface CategoryDAO {

	public long countAll();
	public List<Category> getAll();
	public Category save(Category category);
	public void delete(Category category);
	public Category update(Category category);
	public Category getById(Long id);
}
