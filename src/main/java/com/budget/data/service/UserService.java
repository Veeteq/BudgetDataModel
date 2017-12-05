package com.budget.data.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.budget.data.dao.UserDAO;
import com.budget.data.model.User;

@Service
public class UserService {

	@Autowired
	UserDAO userDAO;
	
	public long countAll() {
		return userDAO.countAll();
	}

	public List<User> getAll(){
		return userDAO.getAll();
	}
	
	public User getById(Long id){
		return userDAO.getById(id);
	}

	public User getByName(String name){
		return userDAO.getByName(name).getSingleResult();
	}
	
	@Transactional
	public User save(User user){
		return userDAO.save(user);
	}

	@Transactional
	public User update(User user) {
		return userDAO.update(user);
	}
}
