package org.cibertec.edu.pe.services.impl;

import org.cibertec.edu.pe.model.Producto;
import org.cibertec.edu.pe.repository.IProductoRepository;
import org.cibertec.edu.pe.services.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService implements IProductoService {

	@Autowired
	private IProductoRepository data;
	
	@Override
	public List<Producto> Listado() {
		// TODO Auto-generated method stub
		return (List<Producto>)data.findAll();
	}

	@Override
	public Optional<Producto> Buscar(int id) {
		// TODO Auto-generated method stub
		return data.findById(id);
	}

	@Override
	public void Insertar(Producto p) {
		// TODO Auto-generated method stub
		data.save(p);
		
	}

	@Override
	public void Modificar(Producto p) {
		// TODO Auto-generated method stub
		data.save(p);
	}

	@Override
	public void Suprimir(int id) {
		// TODO Auto-generated method stub
		data.deleteById(id);
	}
}
