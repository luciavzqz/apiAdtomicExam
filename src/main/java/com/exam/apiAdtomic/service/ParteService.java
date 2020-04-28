package com.exam.apiAdtomic.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.exam.apiAdtomic.entity.enums.DescripcionParte;
import com.exam.apiAdtomic.entity.model.Parte;

public interface ParteService {
	
	public List<Parte> findAll();
	
	public Parte findById(int id);
	
	public void save(Parte parte);
	
	public void deleteById(int id);
	
	public Map<String, Object> obtenerMejorOpcionCompraFutura(Date fecha) throws ParseException;

	public Map<String, Object> obtenerMejorOpcionCompraFutura(int mes, int año) throws ParseException;

	Parte findByDescripcionParte(DescripcionParte descripcionParte);
}