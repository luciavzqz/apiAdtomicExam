package com.exam.apiAdtomic.dao;

import java.util.List;

import com.exam.apiAdtomic.entity.model.Compra;

public interface CompraDAO {
	
	public List<Compra> findAll();
	
	public Compra findById(int id);
	
	public void save(Compra object);
	
	public void deleteById(int id);
}