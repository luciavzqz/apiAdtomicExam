package com.exam.apiAdtomic.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exam.apiAdtomic.exceptionHandling.ClientErrorException;
import com.exam.apiAdtomic.service.ParteService;
import com.exam.apiAdtomic.utils.Fecha;
import com.exam.apiAdtomic.utils.JsonResponse;

@RestController
@RequestMapping("/apiAdtomic") 
public class PartesRestController {
	
	@Autowired
	private ParteService parteService;

	/**
	 * GET
	 * url: http://127.0.0.1:8080/apiAdtomic/partes
	 * Ejemplo 1: http://127.0.0.1:8080/apiAdtomic/partes?date=05-2019
	 * Ejemplo 2: http://127.0.0.1:8080/apiAdtomic/partes?date=01-05-2019
	 * 
	 * @param Fecha en la que se quiere obtener la mejor opción de compra
	 * 		Formatos:
	 * 			1-	MM-yyyy
	 * 			2-	dd-MM--yyyy
	 * @return La mejor opción de compra de las partes existentes entre distintos proveedores en la fecha dada
	 */
	@GetMapping("/partes")
	public JsonResponse encontrarMejorOpcion(
			@RequestParam(value="date", required = false) String fecha) throws ParseException{
		
		String regexFullDate = "(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[012])-((19|2[0-9])[0-9]{2})";
		String regexMonthYearDate = "(0[1-9]|1[012])-((19|2[0-9])[0-9]{2})";
	
		if( fecha == null) {
			 throw new ClientErrorException("Se espera parámetro 'date' con valor de formato dd-MM-yyyy o MM-yyyy");
		}
		
		Map<String, Object> respuesta = new HashMap<String, Object>();
		
		if(fecha.matches(regexFullDate)) {
			
			Date fechaFormateada = Fecha.getDate(fecha, "dd-MM-yyyy");
			respuesta = parteService.obtenerMejorOpcionCompraFutura(fechaFormateada);		
		
		}else if(fecha.matches(regexMonthYearDate)) {
			
			int mes = Fecha.getMonth(fecha, "MM-yyyy");
			int año = Fecha.getYear(fecha, "MM-yyyy");
			respuesta = parteService.obtenerMejorOpcionCompraFutura(mes, año);		
		
		}else {
			throw new ClientErrorException("Se espera que el valor del parámetro 'date' tenga formato dd-MM-yyyy o MM-yyyy");
		}
		
		return (new JsonResponse(respuesta)).setDeaultSuccess();
	}
	
}