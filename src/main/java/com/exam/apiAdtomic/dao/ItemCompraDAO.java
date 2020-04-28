package com.exam.apiAdtomic.dao;

import java.util.List;

import com.exam.apiAdtomic.entity.model.ItemCompra;

public interface ItemCompraDAO {
	
	public List<ItemCompra> findAll();
	
	public ItemCompra findById(int id);
	
	public void save(ItemCompra user);
	
	public void deleteById(int id);
}