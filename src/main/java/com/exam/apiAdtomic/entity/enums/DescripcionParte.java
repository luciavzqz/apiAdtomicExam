package com.exam.apiAdtomic.entity.enums;

public enum DescripcionParte {
	DELANTERA_DERECHA("Óptica delantera derecha"),
	PARRILLA_FRONTAL("Parrilla frontal"),
	PARAGOLPE_DELANTERO("Paragolpe delantero");
	
	private String descripcion;
	
	private DescripcionParte(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getDescripcion() {
        return this.descripcion;
    }
}
