package com.exam.apiAdtomic.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exam.apiAdtomic.entity.enums.DescripcionParte;
import com.exam.apiAdtomic.entity.enums.TipoParte;
import com.exam.apiAdtomic.entity.model.Compra;
import com.exam.apiAdtomic.entity.model.Parte;
import com.exam.apiAdtomic.service.ParteService;
import com.exam.apiAdtomic.utils.Fecha;

@RestController
@RequestMapping("/apiAdtomic") 
public class PartesRestController {
	
	@Autowired
	private ParteService parteService;

	/*
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
	public ResponseEntity<Object> encontrarMejorOpcion(
			@RequestParam(value="date", required = false) String fecha) throws ParseException{
		
		String regexFullDate = "(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[012])-((19|2[0-9])[0-9]{2})";
		String regexMonthYearDate = "(0[1-9]|1[012])-((19|2[0-9])[0-9]{2})";
		
		if( fecha == null) {
			return new ResponseEntity<>("Es null", HttpStatus.BAD_REQUEST);
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
			System.out.println("La regex no cumple");
			return new ResponseEntity<>("La regex no cumple", HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(respuesta, HttpStatus.OK);
	}
	
	
	/*
	 * GET
	 * url: http://127.0.0.1:8080/apiAdtomic/configuracionInicial
	 * 
	 * Con motivos de este examen.
	 * @param -
	 * @return -
	 */
	@GetMapping("/partes/configInicial")
	public void configInicial(){
		
		parteService.save( new Parte(DescripcionParte.DELANTERA_DERECHA, TipoParte.OPTICA, 6100));
		parteService.save( new Parte(DescripcionParte.PARRILLA_FRONTAL, TipoParte.CARROCERIA, 5200));
		parteService.save( new Parte(DescripcionParte.PARAGOLPE_DELANTERO, TipoParte.CARROCERIA, 7600));
		
	}
	@PostMapping("/ConfiguraciónInicial")
	public void configuracionInicial() {
		
	}
}