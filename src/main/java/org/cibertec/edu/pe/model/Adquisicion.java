package org.cibertec.edu.pe.model;

public class Adquisicion {

    private String categoria;
    private String nombreHerramienta;
    private int cantidad;
    private String herramienta;
    private double subtotal;
    private String fechaVenta;
    private int idVenta;
    private double precioUnidad; 

    public Adquisicion() {
    }

    // Constructor
    public Adquisicion(String categoria, String nombreHerramienta, int cantidad, String herramienta, double subtotal, String fechaVenta, int idVenta, double precioUnidad) {
        this.categoria = categoria;
        this.nombreHerramienta = nombreHerramienta;
        this.cantidad = cantidad;
        this.herramienta = herramienta;
        this.subtotal = subtotal;
        this.fechaVenta = fechaVenta;
        this.idVenta = idVenta;
        this.precioUnidad = precioUnidad;
    }

    // Getters y Setters
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNombreHerramienta() {
        return nombreHerramienta;
    }

    public void setNombreHerramienta(String nombreHerramienta) {
        this.nombreHerramienta = nombreHerramienta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getHerramienta() {
        return herramienta;
    }

    public void setHerramienta(String herramienta) {
        this.herramienta = herramienta;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public double getPrecioUnidad() {
        return precioUnidad;
    }

    public void setPrecioUnidad(double precioUnidad) {
        this.precioUnidad = precioUnidad;
    }

    @Override
    public String toString() {
        return "Adquisicion{" +
                "categoria='" + categoria + '\'' +
                ", nombreHerramienta='" + nombreHerramienta + '\'' +
                ", cantidad=" + cantidad +
                ", herramienta='" + herramienta + '\'' +
                ", subtotal=" + subtotal +
                ", fechaVenta='" + fechaVenta + '\'' +
                ", idVenta=" + idVenta +
                ", precioUnidad=" + precioUnidad +
                '}';
    }
}