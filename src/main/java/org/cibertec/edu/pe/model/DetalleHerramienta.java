package org.cibertec.edu.pe.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "detalle")
public class DetalleHerramienta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDetalle;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idHerramienta")
	private Herramienta herramienta;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idVenta")
	private Venta venta;
	
	private int cantidad;
	private double subtotal;
	
	public int getIdDetalle() {
		return idDetalle;
	}
	public void setIdDetalle(int idDetalle) {
		this.idDetalle = idDetalle;
	}
	public Herramienta getHerramienta() {
		return herramienta;
	}
	public void setHerramienta(Herramienta herramienta) {
		this.herramienta = herramienta;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public Venta getVenta() {
		return venta;
	}
	public void setVenta(Venta venta) {
		this.venta = venta;
	}
	
	@Override
	public String toString() {
		return "Detalle [idDetalle=" + idDetalle + ", herramienta=" + herramienta + ", cantidad=" + cantidad + ", subtotal="
				+ subtotal + "]";
	}
	
}