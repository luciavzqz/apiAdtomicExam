package com.exam.apiAdtomic.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.exam.apiAdtomic.dao.ItemCompraDAO;
import com.exam.apiAdtomic.entity.model.ItemCompra;

@Repository
public class ItemCompraDAOImpl implements ItemCompraDAO{

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<ItemCompra> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);

		Query<ItemCompra> query = currentSession.createQuery("from ItemCompra", ItemCompra.class);
		
		List<ItemCompra> itemsCompra = query.getResultList();
		
		return itemsCompra ;

	}

	@Override
	public ItemCompra findById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);

		ItemCompra itemCompra = currentSession.get(ItemCompra.class, id);
		
		return itemCompra;
	}

	@Override
	public void save(ItemCompra itemCompra) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		currentSession.saveOrUpdate(itemCompra);	
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);

		Query<ItemCompra> query  = currentSession.createQuery("delete from ItemCompra where id=:idItemCompra");
		
		query.setParameter("idItemCompra", id);
		query.executeUpdate();
		
	}

	
}