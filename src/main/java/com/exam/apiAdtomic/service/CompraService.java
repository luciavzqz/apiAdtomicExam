package com.exam.apiAdtomic.service;
import java.util.List;
import java.util.Map;

import com.exam.apiAdtomic.entity.model.Compra;

public interface CompraService {
	
	public List<Compra> findAll();
	
	public Compra findById(int id);
	
	public void save(Compra compra);
	
	public void deleteById(int id);

	public Compra save(Map<String, Object> detalleCompra);
}