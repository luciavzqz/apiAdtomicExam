package com.exam.apiAdtomic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.apiAdtomic.service.InitService;
import com.exam.apiAdtomic.utils.JsonResponse;

@RestController
@RequestMapping("/apiAdtomic") 
public class InitRestController {

	@Autowired
	private InitService initService;
	/**
	 * POST
	 * url: http://127.0.0.1:8080/apiAdtomic/init
	 * 
	 * Con motivos de este examen.
	 * @param -
	 * @return -
	 */
	@PostMapping("/init")
	public JsonResponse configuracionInicial(){
		
		initService.init();

		return (new JsonResponse("Ejecución exitosa", "Se han inicializado con éxito los valores iniciales")).setSuccess();
	}
	
}
