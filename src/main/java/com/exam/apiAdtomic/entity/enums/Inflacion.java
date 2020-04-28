package com.exam.apiAdtomic.entity.enums;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public enum Inflacion {
	SEMESTRAL_ARG(0.25, 180, 1, 7, 2019);
	
	private double interes;
	private int periodoEnDias;
	private int inicioPeriodoDay;
	private int inicioPeriodoMonth;
	private int inicioPeriodoYear;
	

	private Inflacion(double interes, int periodoEnDias, int inicioPeriodoDay, int inicioPeriodoMonth,
			int inicioPeriodoYear) {
		this.interes = interes;
		this.periodoEnDias = periodoEnDias;
		this.inicioPeriodoDay = inicioPeriodoDay;
		this.inicioPeriodoMonth = inicioPeriodoMonth;
		this.inicioPeriodoYear = inicioPeriodoYear;
	}

	// Lógica
	
	/*
	 * @param La fecha sobre la que se quiera saber la inflación.
	 * @return Inflación total al día de la fecha.
	 */
	public double calcularInflacionAlDia(Date fecha) throws ParseException {
		
		Calendar inicioPeriodoCalendar = new GregorianCalendar(inicioPeriodoYear, inicioPeriodoMonth-1, inicioPeriodoDay);
		Calendar fechaCalendar = new GregorianCalendar();
		fechaCalendar.setTime(fecha);

		// Si la fecha es anterior al inicioPeriodo, la inflación es 1 - no hay inflación
		if(fechaCalendar.compareTo(inicioPeriodoCalendar) < 0 ) return 1;
		
		// Si no retorna, es mayor, o igual
		int semestresCompletos = 	(fechaCalendar.get(Calendar.YEAR) == inicioPeriodoCalendar.get(Calendar.YEAR))
									? 0
									: (fechaCalendar.get(Calendar.YEAR) - inicioPeriodoCalendar.get(Calendar.YEAR) - 1) * 2
									  + fechaCalendar.get(Calendar.MONTH) / 6 + 1;
		 
		int cantidadDeDias;
		
		if(fechaCalendar.get(Calendar.MONTH) >= Calendar.JULY) {
			Calendar calendarAuxiliar = new GregorianCalendar(fechaCalendar.get(Calendar.YEAR), 6, 1);
			cantidadDeDias = fechaCalendar.get(Calendar.DAY_OF_YEAR) - calendarAuxiliar.get(Calendar.DAY_OF_YEAR);
		}else {
			cantidadDeDias = fechaCalendar.get(Calendar.DAY_OF_YEAR);
		}
	
		return calcularInflacionEnSemestresCompletos(semestresCompletos) * calcularInflacionEnCantidadDeDias(cantidadDeDias);
	}
	
	/*
	 * Para semestres completos transcurridos entre el 1/7/2019 y la fecha
	 * @param Cantidad de semestres sobre los que se quiera calcular la inflación acumulada
	 * @return Inflación acumulada en los semestres completos
	 */
	private double calcularInflacionEnSemestresCompletos(int semestresCompletos) {
		return Math.pow(1 + interes, semestresCompletos);
	}
	
	/*
	 * Para días pertenecientes a un semestre que no llegó a completarse
	 * @param Cantidad de días sobre los que se quiera calcular la inlación acumulada
	 * @return Inflación acumulada en la cantidad
	 */
	private double calcularInflacionEnCantidadDeDias(int cantidadDeDias) {
		return 1 + (interes / periodoEnDias ) * cantidadDeDias;
	}
}
