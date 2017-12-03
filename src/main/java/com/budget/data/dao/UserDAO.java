package com.budget.data.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import com.budget.data.model.User;

public interface UserDAO {

	public long countAll();
	public List<User> getAll();
	public User getById(long id);
	public TypedQuery<User> getByName(String name);
	public User save(User user);
	public boolean delete(User user);
	public User update(User user);
	public void flush();
}
