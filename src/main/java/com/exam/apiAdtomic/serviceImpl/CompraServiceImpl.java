package com.exam.apiAdtomic.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.apiAdtomic.dao.CompraDAO;
import com.exam.apiAdtomic.entity.enums.DescripcionParte;
import com.exam.apiAdtomic.entity.enums.MetodoPago;
import com.exam.apiAdtomic.entity.enums.Proveedor;
import com.exam.apiAdtomic.entity.model.Compra;
import com.exam.apiAdtomic.entity.model.ItemCompra;
import com.exam.apiAdtomic.entity.model.Parte;
import com.exam.apiAdtomic.service.CompraService;
import com.exam.apiAdtomic.service.ParteService;

@Service
public class CompraServiceImpl implements CompraService {

	@Autowired
	private CompraDAO compraDAO;
	
	@Autowired
	private ParteService parteService;
	
	@Override
	public List<Compra> findAll() {
		List<Compra> listCompras= compraDAO.findAll();
		return listCompras;
	}

	@Override
	public Compra findById(int id) {
		Compra compra = compraDAO.findById(id);
		return compra;
	}

	@Override
	public void save(Compra compra) {
		compraDAO.save(compra);

	}

	@Override
	public void deleteById(int id) {
		compraDAO.deleteById(id);
	}

	/* 
	 * @param Compra a guardar
	 * @return La compra persistida en la base de datos
	 *
	 */

	 /* Ejemplo de detalleCompra:
	
		{
		    "monto": 16980.0,
		    "partes": [
			    {
			    	"parte": "DELANTERA_DERECHA",
			        "proveedor": "BUENOS_AIRES_CARS",
			        "monto": 6100.0,
			        "metodo_pago": "TODOS"
			    },
			    {
			    	"parte": "PARAGOLPE_DELANTERO",
			        "proveedor": "AUTOS_AR",
			        "monto": 6460.0,
			        "metodo_pago": "EFECTIVO"
			    },
			    {
			    	"parte":"PARRILLA_FRONTAL",
			        "proveedor": "AUTOS_AR",
			        "monto": 4420.0,
			        "metodo_pago": "EFECTIVO"
			    }
		    ]
		}
	 */
	
	@Override
	public Compra save(Map<String, Object> detalleCompra) {
		
		double montoTotal = (double) detalleCompra.get("monto");
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> detallePartes = (List<Map<String, Object>>) detalleCompra.get("partes");
		List<ItemCompra> itemsCompra = new ArrayList<ItemCompra>();
		
		for (Map<String, Object> detalleParte : detallePartes) {
			Proveedor proveedor = Proveedor.valueOf((String) detalleParte.get("proveedor"));
			double monto = (double) detalleParte.get("monto");
			MetodoPago metodoPago = MetodoPago.valueOf((String) detalleParte.get("metodo_pago"));
			DescripcionParte descripcionParte = DescripcionParte.valueOf((String) detalleParte.get("parte"));
			
			Parte parte = parteService.findByDescripcionParte(descripcionParte);
			itemsCompra.add(new ItemCompra(proveedor, metodoPago, parte, monto));
		}
		
		Compra compra = new Compra(montoTotal, itemsCompra);
		
		//save(compra);
		
		return compra;
	}

}
