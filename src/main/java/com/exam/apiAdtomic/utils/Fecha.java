package com.exam.apiAdtomic.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Fecha {
	public static String toString(Date fecha) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(fecha);
		String dia = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)); 
		String mes = String.valueOf(calendar.get(Calendar.MONTH) + 1); 
		String año = String.valueOf(calendar.get(Calendar.YEAR)); 
		return (dia.length() == 1? "0" + dia: dia) + "-" + (mes.length() == 1? "0" + mes: mes) + "-" + año;
	}
	
	public static Calendar getCalendar(Date fecha) {
		Calendar fechaCalendar = new GregorianCalendar();
		fechaCalendar.setTime(fecha);
		return fechaCalendar;
	}
	
	public static Date getDate(String fecha, String parseFormat) throws ParseException {
		SimpleDateFormat sdformat = new SimpleDateFormat(parseFormat);
		Date date = sdformat.parse(fecha);
		return date;
	}
	
	public static Date getDate(int dia, int mes, int año) throws ParseException {
		Calendar calendar =  new GregorianCalendar(año, mes-1, dia);
		return calendar.getTime();
	}

	public static int getMonth(String fecha, String parseFormat) {
		int begin = parseFormat.indexOf("M");
		int end = begin + 2;
		return Integer.valueOf(fecha.substring(begin, end));
	}

	public static int getYear(String fecha, String parseFormat) {
		int begin = parseFormat.indexOf("y");
		int end = begin + 4;
		return Integer.valueOf(fecha.substring(begin, end));
	}
	
	public static int getCantidadDeDias(int mes, int año) {
		Calendar fecha = Calendar.getInstance();
	    fecha.set(año, mes, 0);
	    return fecha.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	public static Timestamp getCurrentTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}
}
	
