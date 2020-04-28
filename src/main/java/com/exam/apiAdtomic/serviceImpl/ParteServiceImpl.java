package com.exam.apiAdtomic.serviceImpl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.apiAdtomic.dao.ParteDAO;
import com.exam.apiAdtomic.entity.enums.DescripcionParte;
import com.exam.apiAdtomic.entity.enums.Inflacion;
import com.exam.apiAdtomic.entity.enums.Proveedor;
import com.exam.apiAdtomic.entity.enums.TipoParte;
import com.exam.apiAdtomic.entity.model.Parte;
import com.exam.apiAdtomic.service.ParteService;
import com.exam.apiAdtomic.utils.Fecha;

@Service
public class ParteServiceImpl implements ParteService {

	@Autowired
	private ParteDAO parteDAO;
	
	@Override
	public List<Parte> findAll() {
		List<Parte> listPartes= parteDAO.findAll();
		return listPartes;
	}

	@Override
	public Parte findById(int id) {
		Parte parte = parteDAO.findById(id);
		return parte;
	}

	@Override
	public Parte findByDescripcionParte(DescripcionParte descripcionParte) {
		Parte parte = parteDAO.findByDescripcionParte(descripcionParte);
		return parte;
	}
	
	@Override
	public void save(Parte parte) {
		parteDAO.save(parte);
	}

	@Override
	public void deleteById(int id) {
		parteDAO.deleteById(id);
	}

	/**
	 * @param La fecha de la que se quiere obtener la mejor opción en relación del precio para una compra futura
	 * @return La mejor opción mapeada de la siguiente forma
	 * {
	 *			"Óptica delantera derecha" : {
	 *				"proveedor": "AutosAR"
	 *				"monto": 6200.00
	 *				"metodo_pago" : "EFECTIVO"
	 *			},
	 *			"Parrilla frontal": {
	 *			...
	 *			}
	 *			"monto": 6899.00,
	 *			"fecha": 15-07-2020
	 *	}
	 *
	 */
	@Override
	public Map<String, Object> obtenerMejorOpcionCompraFutura(Date fecha) throws ParseException {
		// Obtengo las partes
		List<Parte> partes = parteDAO.findAll();
		
		// Retorno
		return obtenerMejorDescuentoParaPartes(partes, fecha);
	}
	
	/**
	 * @param El mes y el año para los que se quiere obtener la mejor opción en relación del precio para una compra futura
	 * @return La mejor opción mapeada de la siguiente forma
	 * {
	 *			"Óptica delantera derecha" : {
	 *				"proveedor": "AutosAR"
	 *				"monto": 6200.00
	 *				"metodo_pago" : "EFECTIVO"
	 *			},
	 *			"Parrilla frontal": {
	 *			...
	 *			}
	 *			"monto": 6899.00,
	 *			"fecha": 15-07-2020
	 *	}
	 *
	 *	Mejora/ opción: que liste todos los días que se pueda hacer una buena compra (mismo monto final).
	 */
	@Override
	public Map<String, Object> obtenerMejorOpcionCompraFutura(int mes, int año) throws ParseException{
		
		// Obtengo las partes
		List<Parte> partes = parteDAO.findAll();
		
		List<MejorCompraDelDia> mejoresComprasDelDia = new ArrayList<>();
		for (int dia = 1; dia <= Fecha.getCantidadDeDias(mes, año); dia++) {
			Date fecha = Fecha.getDate(dia, mes, año);
			
			MejorCompraDelDia mejorCompraDelDia = new MejorCompraDelDia(fecha, partes);
			mejorCompraDelDia.start();
			mejoresComprasDelDia.add(mejorCompraDelDia);
		}
		
		for (MejorCompraDelDia mejorCompraDelDia : mejoresComprasDelDia) {
			try {
				mejorCompraDelDia.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		MejorCompraDelDia mejorCompraDelMes = null;
		for (MejorCompraDelDia mejorCompraDelDia : mejoresComprasDelDia) {			
			if(mejorCompraDelDia.esMejor(mejorCompraDelMes)) {
				mejorCompraDelMes = mejorCompraDelDia;
			}
		}
		
		return mejorCompraDelMes.getPropuesta();
	}
	
	/**
	 * @param La fecha de la que se quiere obtener la mejor opción en relación del precio para una compra futura y la lista de partes a comprar
	 * @return La opción de compra con mejor descuento, mapeada de la siguiente forma
	 * 
	 * {
	 *			"Óptica delantera derecha" : {
	 *				"proveedor": "AutosAR"
	 *				"monto": 6200.00
	 *				"metodo_pago" : "EFECTIVO"
	 *			},
	 *			"Parrilla frontal": {
	 *			...
	 *			}
	 *			"monto": 6899.00,
	 *			"fecha": 15-07-2020
	 *	}
	 */
	private Map<String, Object> obtenerMejorDescuentoParaPartes(List<Parte> partes, Date fecha) throws ParseException{
		Map<String, Object> respuesta = new HashMap<String, Object>();
		
		double inflacionDelDia = Inflacion.SEMESTRAL_ARG.calcularInflacionAlDia(fecha);
		double montoTotal = 0;
		
		// Por cada parte obtengo la mejor opción para el día determinado en "date"
		for (Parte parte : partes) {
			
			// Le agrego el cargo por la inflación
			double montoInflado = parte.getPrecioInicial() * inflacionDelDia;

			// Decido con qué provedor tengo menor precio
			// Obtengo un Mapeo con los datos {"proveedor":,"monto":,"metodo_pago":}
			// Cuando obtengo un mapa, accedo a su Key "monto" y la sumo en una variable llamada "monto", que tendrá el precio total de la compra
			Map<String, Object> propuesta = obtenerMejorDescuentoParaParte(fecha, parte.getTipoParte(), montoInflado);
			
			double monto = (double) propuesta.get("monto");
			montoTotal += monto;

			propuesta.put("monto", (Math.floor( monto * 100) / 100d));
			// Agrego el mapa obtenido al mapa "respuesta" bajo la key "nombre_de_la_parte"
			respuesta.put(parte.getDescripcionParte().getDescripcion(), propuesta);
		}
		
		// Agrego el price al mapa
		respuesta.put("monto", (Math.floor( montoTotal * 100) / 100d));
		
		// Agrego la fecha al mapa
		respuesta.put("fecha", Fecha.toString(fecha));
		
		// Retorno
		return respuesta;
	}
	
	/**
	 * @param La fecha de la que se quiere obtener la mejor opción en relación del precio para una compra futura,
	 * 			el tipo de parte para el que se quiera calcular y el monto de la misma
	 * @return La mejor opción de proveedor mapeada de la siguiente forma
	 * 	{
	 *		"proveedor": "AutosAR"
	 *		"monto": "6200,00"
	 *		"metodo_pago" : "efectivo"
	 *	}
	 */
	private Map<String, Object> obtenerMejorDescuentoParaParte(Date fecha, TipoParte tipoParte, double monto){
		// Consultar a todos los proveedores sus precios y formas de pago asociadas
		// y voy a compararlas para obtener la mejor opcion
		
		Map<String, Object> respuesta = new HashMap<String, Object>();
		double montoPropuesto = monto; 
		// En cada proveedor voy a tener el método correspondiente para obtener el descuento de manera cohesiva.
		for (Proveedor proveedor : Proveedor.values()) {
			Map<String, Object> propuesta = proveedor.obtenerMejorDescuento(fecha, tipoParte, monto);
			if( montoPropuesto >= (double) propuesta.get("monto")) {
				respuesta.putAll(propuesta);
				montoPropuesto = (double) propuesta.get("monto");
			}
		}
		
		return respuesta;
	}
	
	
	private class MejorCompraDelDia extends Thread {
		private Date fecha;
		private List<Parte> partes = new ArrayList<>();
		private Map<String, Object> propuesta = new HashMap<String, Object>();
		
		public MejorCompraDelDia (Date fecha, List<Parte> partes) throws ParseException {
			this.fecha = fecha;
			this.partes.addAll(partes);
		}
		
		@Override
		public void run() {
			try {
				propuesta.putAll(obtenerMejorDescuentoParaPartes(partes, fecha));
			} catch (ParseException e) {
				e.printStackTrace();
			}	
		}
		
		public Map<String, Object> getPropuesta(){
			return propuesta;
		}
		
		public boolean esMejor(MejorCompraDelDia o) {
			return (o != null)? ((double) this.propuesta.get("monto")) < ((double) o.getPropuesta().get("monto")): true;
		}
		
	}
}
