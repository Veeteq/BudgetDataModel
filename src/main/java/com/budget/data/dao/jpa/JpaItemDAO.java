package com.budget.data.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.budget.data.dao.ItemDAO;
import com.budget.data.model.Item;

@Repository
public class JpaItemDAO implements ItemDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Item> getAll() {
		String sqlString = "SELECT c from Item c";
		return entityManager.createQuery(sqlString, Item.class).getResultList();
	}

	@Override
	public Item getById(long id) {
		return entityManager.find(Item.class, id);
	}

	@Override
	public Item save(Item item) {
		entityManager.persist(item);
		return item;
	}

	@Override
	public Item delete(Item item) {
		if (this.entityManager.contains(item)) {
			this.entityManager.remove(item);
		} else {
			Item attached = getById(item.getId());
			this.entityManager.remove(attached);
		}
		return null;
	}

	@Override
	public Item update(Item item) {
		Item merged = this.entityManager.merge(item);
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
