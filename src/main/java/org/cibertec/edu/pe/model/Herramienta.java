package org.cibertec.edu.pe.model;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "herramientas")
public class Herramienta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idHerramienta;
	private String descripcion;
	private double precio;
	private int stock;
	private String imagen;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;
	
	
	
	public int getIdHerramienta() {
		return idHerramienta;
	}
	public void setIdHerramienta(int idHerramienta) {
		this.idHerramienta = idHerramienta;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	@Override
	public String toString() {
		return "Producto [idHerramienta=" + idHerramienta + ", descripcion=" + descripcion + ", categoria=" + categoria + ", precio=" + precio + ", stock="
				+ stock + "]";
	}
	
}