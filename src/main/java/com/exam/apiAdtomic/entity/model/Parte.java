package com.exam.apiAdtomic.entity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.exam.apiAdtomic.entity.enums.DescripcionParte;
import com.exam.apiAdtomic.entity.enums.TipoParte;

@Entity
@Table(name="parte")
public class Parte {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Enumerated(EnumType.STRING)
	private DescripcionParte descripcionParte;
	
	@Enumerated(EnumType.STRING)
	private TipoParte tipoParte;
	
	@Column(name="precio_inicial")
	private float precioInicial;

	// Constructores 
	
	public Parte() {
		super();
	}
	
	public Parte(DescripcionParte descripcionParte, TipoParte tipoParte, float precioInicial) {
		super();
		this.descripcionParte = descripcionParte;
		this.tipoParte = tipoParte;
		this.precioInicial = precioInicial;
	}

	// Logica
	
	// Autogenerados
	
	public DescripcionParte getDescripcionParte() {
		return descripcionParte;
	}

	public void setDescripcionParte(DescripcionParte descripcionParte) {
		this.descripcionParte = descripcionParte;
	}

	public TipoParte getTipoParte() {
		return tipoParte;
	}

	public void setTipoParte(TipoParte tipoParte) {
		this.tipoParte = tipoParte;
	}

	public float getPrecioInicial() {
		return precioInicial;
	}

	public void setPrecioInicial(float precioInicial) {
		this.precioInicial = precioInicial;
	}
	
	
}
