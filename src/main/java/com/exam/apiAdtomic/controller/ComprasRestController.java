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
import com.exam.apiAdtomic.exceptionHandling.ClientErrorException;
import com.exam.apiAdtomic.exceptionHandling.ServerErrorException;
import com.exam.apiAdtomic.service.CompraService;
import com.exam.apiAdtomic.utils.JsonResponse;

@RestController
@RequestMapping("/apiAdtomic") //esta sera la raiz de la url, es decir http://127.0.0.1:8080/apiAdtomic/
public class ComprasRestController {
	
	@Autowired
	private CompraService compraService;

	/**
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
	
	
	/**
	 * POST
	 * url: http://127.0.0.1:8080/apiAdtomic/compras
	 * 
	 * @param detalle de compra a guardar
	 * @return La compra persistida en la base de datos
	 * @throws ClientErrorException 
	 *
	 */
	
	 /* Ejemplo de RequestBody:
	
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
	
	@PostMapping("/compras")
	public JsonResponse Compra(
			@RequestBody(required = false) Map<String, Object> detalleCompra) throws ClientErrorException {

		if(detalleCompra == null)
			 throw new ClientErrorException("Se espera RequestBody. Verifique el formato del RequestBody.");	
		
		Compra compra = null;
		try {
			compra = compraService.save(detalleCompra);
		} catch (ClientErrorException e) {
			e.printStackTrace();
			 throw new ClientErrorException(e.getMessage() != null? e.getMessage() : "Parámetros inválidos. Verifique el RequestBody.");	
		} catch (ServerErrorException e) {
			e.printStackTrace();
			 throw new ServerErrorException(e.getMessage());	
		}catch (Exception e) {
			e.printStackTrace();
			 throw new ClientErrorException("Parámetros inválidos. Verifique el RequestBody.");	
		}
		
		return (new JsonResponse(compra.toMap())).setDeaultSuccess();
	}
}