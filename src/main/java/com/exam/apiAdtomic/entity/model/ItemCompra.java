package com.exam.apiAdtomic.entity.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.exam.apiAdtomic.entity.enums.MetodoPago;
import com.exam.apiAdtomic.entity.enums.Proveedor;

@Entity
@Table(name="item_compra")
public class ItemCompra implements Serializable{

	private static final long serialVersionUID = -5233679753720475196L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Enumerated(EnumType.STRING)
	private Proveedor proveedor;
	
	@Enumerated(EnumType.STRING)
	private MetodoPago metodoPago;

	@ManyToOne
    @JoinColumn(name = "parte_id", referencedColumnName="id")
	private Parte parte;
	
	@Column(name="monto")
	private double monto;

	// Constructores 
	
	public ItemCompra(Proveedor proveedor, MetodoPago metodoPago, Parte parte, double monto) {
		super();
		this.proveedor = proveedor;
		this.metodoPago = metodoPago;
		this.parte = parte;
		this.monto = monto;
	}

	// Logica
	
	// Autogenerados
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public MetodoPago getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(MetodoPago metodoPago) {
		this.metodoPago = metodoPago;
	}

	public Parte getParte() {
		return parte;
	}

	public void setParte(Parte parte) {
		this.parte = parte;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}
	
	
	
}
