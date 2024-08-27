package org.cibertec.edu.pe.services;

import org.cibertec.edu.pe.model.Producto;

import java.util.List;
import java.util.Optional;

public interface IProductoService {
	// Metodos del CRUD para la entidad Producto
	public List<Producto> Listado();
	public Optional<Producto> Buscar(int id);
	public void Insertar(Producto p);
	public void Modificar(Producto p);
	public void Suprimir(int id);

}
