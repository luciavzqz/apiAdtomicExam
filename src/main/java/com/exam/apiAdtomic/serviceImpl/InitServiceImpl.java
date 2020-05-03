package com.exam.apiAdtomic.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.apiAdtomic.dao.ParteDAO;
import com.exam.apiAdtomic.entity.enums.DescripcionParte;
import com.exam.apiAdtomic.entity.enums.TipoParte;
import com.exam.apiAdtomic.entity.model.Parte;
import com.exam.apiAdtomic.exceptionHandling.ServerErrorException;
import com.exam.apiAdtomic.service.InitService;

@Service
public class InitServiceImpl implements InitService {

	@Autowired
	private ParteDAO parteDAO;
	
	@Override
	public void init() {
		
		try {
			List<Parte> partes = parteDAO.findAll();
			
			for (Parte parte : partes) {
				parteDAO.deleteById(parte.getId());
			}
			
			parteDAO.save( new Parte(DescripcionParte.DELANTERA_DERECHA, TipoParte.OPTICA, 6100));
			parteDAO.save( new Parte(DescripcionParte.PARRILLA_FRONTAL, TipoParte.CARROCERIA, 5200));
			parteDAO.save( new Parte(DescripcionParte.PARAGOLPE_DELANTERO, TipoParte.CARROCERIA, 7600));
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServerErrorException("Falló al inicializar los datos");
		}
		
		
		
	}

}
