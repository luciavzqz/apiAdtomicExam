package com.exam.apiAdtomic.entity.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.exam.apiAdtomic.utils.Mappable;


@Entity
@Table(name="compra")
public class Compra implements Mappable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="monto")
	private double monto;
	
	@Column(name="fecha")
	@CreationTimestamp
	private Date fechaCompra;
	
	@OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name = "compra_id", referencedColumnName="id")
	private List<ItemCompra> itemsCompra;

	// Constructores
	public Compra(double monto, List<ItemCompra> itemsCompra) {
		super();
		this.monto = monto;
		this.itemsCompra = itemsCompra;
	}

	//	Autogenerado
	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public List<ItemCompra> getItemsCompra() {
		return itemsCompra;
	}

	public void setItemsCompra(List<ItemCompra> itemsCompra) {
		this.itemsCompra = itemsCompra;
	}
	
}
