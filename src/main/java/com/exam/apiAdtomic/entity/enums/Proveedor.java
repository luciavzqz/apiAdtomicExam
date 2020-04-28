package com.exam.apiAdtomic.entity.enums;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.exam.apiAdtomic.utils.Fecha;

public enum Proveedor {
	AUTOS_AR("AutosAr"){

		@Override
		public Map<String, Object> obtenerMejorDescuento(Date fecha, TipoParte tipoParte, double monto) {
			Map<String, Object> respuesta = new HashMap<String, Object>();

			Calendar fechaCalendar = Fecha.getCalendar(fecha);

			double porcentajeDeDescuento = 1;
			String metodoPago = MetodoPago.TODOS.name();
			
			if(tipoParte.equals(TipoParte.CARROCERIA)){

				// Carrocería - 15% off - Efectivo
				// Condición: Tercer sabado de cada mes
				if( fechaCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY && (fechaCalendar.get(Calendar.WEEK_OF_MONTH) == 3)) { 
					porcentajeDeDescuento = 0.85;
					metodoPago = MetodoPago.EFECTIVO.name();
				}
			}
			
			respuesta.put("monto", monto * porcentajeDeDescuento);
			respuesta.put("metodo_pago", metodoPago);
			respuesta.put("proveedor", getNombre());
			
			return respuesta;
		}
		
	},
	BUENOS_AIRES_CARS("Buenos Aires Cars"){

		@Override
		public Map<String, Object> obtenerMejorDescuento(Date fecha, TipoParte tipoParte, double monto) {
			Map<String, Object> respuesta = new HashMap<String, Object>();

			Calendar fechaCalendar = Fecha.getCalendar(fecha);

			double porcentajeDeDescuento = 1;
			String metodoPago = MetodoPago.TODOS.name();
			
			if(tipoParte.equals(TipoParte.OPTICA)) {
				// Óptica - 11% off - Tarjeta de crédito
				// Condición: A partir del 1er día de cada mes cada 5 días.
				if((fechaCalendar.get(Calendar.DAY_OF_MONTH) - 1) % 5 == 0) { // El día del mes menos uno, ¿Es múltiplo de 5?
					porcentajeDeDescuento = 0.89;
					metodoPago = MetodoPago.TARJETA_CREDITO.name();
				}
				 
			}
			
			respuesta.put("monto", monto * porcentajeDeDescuento);
			respuesta.put("metodo_pago", metodoPago);
			respuesta.put("proveedor", getNombre());
			
			return respuesta;
		}
	},
	GOOD_REPAIR("Good Repair"){

		@Override
		public Map<String, Object> obtenerMejorDescuento(Date fecha, TipoParte tipoParte, double monto) {
			Map<String, Object> respuesta = new HashMap<String, Object>();

			Calendar fechaCalendar = Fecha.getCalendar(fecha);

			double porcentajeDeDescuento = 1;
			String metodoPago = MetodoPago.TODOS.name();

			int dia = fechaCalendar.get(Calendar.DAY_OF_WEEK);
			
			if(tipoParte.equals(TipoParte.OPTICA)) {
				// Óptica - 20% off - Tarjeta de crédito
				// Condición: Lunes a miercoles de todos los meses.
				if(dia >= Calendar.MONDAY &&  dia <= Calendar.WEDNESDAY) { 
					porcentajeDeDescuento = 0.8;
					metodoPago = MetodoPago.TARJETA_CREDITO.name();
				}
				
			}else if(tipoParte.equals(TipoParte.CARROCERIA)){
				// Carrocería - 6% off - Efectivo
				// Condición: Jueves a Viernes de todos los meses.
				if(dia == Calendar.THURSDAY || dia == Calendar.FRIDAY) { 
					porcentajeDeDescuento = 0.94;
					metodoPago = MetodoPago.EFECTIVO.name();
				}
			}
			
			respuesta.put("monto", monto * porcentajeDeDescuento);
			respuesta.put("metodo_pago", metodoPago);
			respuesta.put("proveedor", getNombre());
			
			return respuesta;
		}
	};
	
	private final  String nombre;
	
	private Proveedor(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
        return this.nombre;
    }
	
	public abstract Map<String, Object> obtenerMejorDescuento(Date fecha, TipoParte tipoParte, double monto);
}
