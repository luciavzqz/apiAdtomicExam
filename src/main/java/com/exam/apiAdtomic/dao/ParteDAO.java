package com.exam.apiAdtomic.dao;

import java.util.List;

import com.exam.apiAdtomic.entity.enums.DescripcionParte;
import com.exam.apiAdtomic.entity.model.Parte;

public interface ParteDAO {
	
	public List<Parte> findAll();
	
	public Parte findById(int id);
	
	public void save(Parte user);
	
	public void deleteById(int id);

	public Parte findByDescripcionParte(DescripcionParte descripcionParte);
}