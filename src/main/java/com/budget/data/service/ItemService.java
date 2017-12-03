package com.budget.data.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.budget.data.dao.ItemDAO;
import com.budget.data.model.Item;

@Service
@Transactional
public class ItemService {

	@Autowired
	ItemDAO itemDAO;
	
	public List<Item> getAll(){
		return itemDAO.getAll();
	}
	
	public Item getById(long id){
		return itemDAO.getById(id);
	}
	
	public Item save(Item item){
		return itemDAO.save(item);
	}
}
