package com.budget.data.dao;

import java.util.List;

import com.budget.data.model.Item;

public interface ItemDAO {
	
	public List<Item> getAll();
	public Item getById(long id);
	public Item save(Item item);
	public Item delete(Item item);
	public Item update(Item item);
}
