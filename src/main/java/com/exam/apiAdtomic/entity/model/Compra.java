package com.exam.apiAdtomic.entity.model;

import java.io.Serializable;
import java.util.ArrayList;
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
public class Compra implements Mappable, Serializable {

	private static final long serialVersionUID = 1017506906048386347L;

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
    @JoinColumn(name = "compra_id", nullable=false)
	private List<ItemCompra> itemsCompra = new ArrayList<>();
	
	public Compra(double monto, List<ItemCompra> itemsCompra) {
		super();
		this.monto = monto;
		this.itemsCompra.addAll(itemsCompra);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public double getMonto() {
		return monto;
	}

	public Compra setMonto(double monto) {
		this.monto = monto;
		return this;
	}

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public List<ItemCompra> getItemsCompra() {
		return itemsCompra;
	}

	public Compra setItemsCompra(List<ItemCompra> itemsCompra) {
		this.itemsCompra = itemsCompra;
		return this;
	}
	
}
