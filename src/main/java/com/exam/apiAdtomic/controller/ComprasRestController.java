package com.exam.apiAdtomic.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.apiAdtomic.entity.model.Compra;
import com.exam.apiAdtomic.service.CompraService;

@RestController
@RequestMapping("/apiAdtomic") //esta sera la raiz de la url, es decir http://127.0.0.1:8080/apiAdtomic/
public class ComprasRestController {
	
	@Autowired
	private CompraService compraService;

	/*
	 * GET
	 * url: http://127.0.0.1:8080/apiAdtomic/compras
	 * 
	 * @param -
	 * @return Todas las compras
	 */
	@GetMapping("/compras")
	public List<Compra> findAll(){
		
		return compraService.findAll();
	}
	
	
	/*
	 * POST
	 * url: http://127.0.0.1:8080/apiAdtomic/compras
	 * 
	 * @param Compra a guardar
	 * @return La compra persistida en la base de datos
	 *
	 */
	 /*
	 * Ejemplo de RequestBody:
	 * 
	 *  {	
	 *  	"partes": [
	 *  		{
	 *  			"parte": "Paragolpe delantero"
	 *        	  	"proveedor": "AutosAr",
	 *         		"monto": "6460",
	 *          	"metodo_pago": "EFECTIVO"
	 *      	},
	 *     		{
	 *     			"parte":"Delantera derecha",
	 *         		"proveedor": "Good Repair",
	 *          	"monto": "6100",
	 *          	"metodo_pago": "TODOS"
	 *       	},
	 *      	{
	 *      		"parte":"Parrilla frontal",
	 *     			"proveedor": "AutosAr",
	 *         		"monto": "4420",
	 *         		"metodo_pago": "EFECTIVO"
	 *       	}
	 * 		 ],
	 *      "monto": "16980",
	 *  }
	 */
	@PostMapping("/compras")
	public Compra Compra(
			@RequestBody Map<String, Object> detalleCompra) {
		
		System.out.println(detalleCompra.toString());
		Compra compra = compraService.save(detalleCompra);
		
		return compra;
	}
}