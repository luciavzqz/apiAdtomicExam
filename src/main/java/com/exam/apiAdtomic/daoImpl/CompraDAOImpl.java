package com.exam.apiAdtomic.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.exam.apiAdtomic.dao.CompraDAO;
import com.exam.apiAdtomic.entity.model.Compra;

@Repository
public class CompraDAOImpl implements CompraDAO{

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<Compra> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);

		Query<Compra> query = currentSession.createQuery("from Compra", Compra.class);
		
		List<Compra> compras = query.getResultList();
		
		return compras;

	}

	@Override
	public Compra findById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);

		Compra compra = currentSession.get(Compra.class, id);
		
		return compra;
	}

	@Override
	public void save(Compra compra) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		currentSession.saveOrUpdate(compra);	
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);

		Query<Compra> query = currentSession.createQuery("delete from Compra where id=:idCompra");
		
		query.setParameter("idCompra", id);
		query.executeUpdate();
		
	}

	
}