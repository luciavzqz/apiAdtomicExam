package com.exam.apiAdtomic.daoImpl;

import java.util.List;
//org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.exam.apiAdtomic.dao.ParteDAO;
import com.exam.apiAdtomic.entity.enums.DescripcionParte;
import com.exam.apiAdtomic.entity.model.Parte;

@Repository
public class ParteDAOImpl implements ParteDAO{

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<Parte> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);

		Query<Parte> query = currentSession.createQuery("from Parte", Parte.class);
		
		List<Parte> partes = query.getResultList();
		
		return partes;

	}

	@Override
	public Parte findById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);

		Parte parte = currentSession.get(Parte.class, id);
		
		return parte;
	}

	@Override
	public void save(Parte parte) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		currentSession.saveOrUpdate(parte);	
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteById(int id) {
		Session currentSession = entityManager.unwrap(Session.class);

		Query<Parte> query = currentSession.createQuery("delete from Parte where id=:idParte");
		
		query.setParameter("idParte", id);
		query.executeUpdate();
		
	}

	@Override
	public Parte findByDescripcionParte(DescripcionParte descripcionParte) {
		Session currentSession = entityManager.unwrap(Session.class);

		Query<Parte> query = currentSession.createQuery("from Parte as parte where parte.descripcionParte = :descripcionParte", Parte.class);

		query.setParameter("descripcionParte", descripcionParte);
		
		Parte parte = query.uniqueResult();
		System.out.println("Parte: " + parte.getDescripcionParte().getDescripcion());
		return parte;
	}
	
}