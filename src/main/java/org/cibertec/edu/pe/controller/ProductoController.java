package org.cibertec.edu.pe.controller;

import org.cibertec.edu.pe.model.Producto;
import org.cibertec.edu.pe.services.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping
public class ProductoController {
    @Autowired
    private IProductoService servicio;

    @GetMapping("/productos")
    public String Listar(Model modelo) {
        List<Producto> productos = servicio.Listado();
        modelo.addAttribute("productos", productos);
        return "productos";
    }

    //Accion Agregar @Validated para verificar tipo de datos correctos
    @GetMapping("/new")
    public String agregar(Model modelo) {
        Producto nuevoProducto = new Producto();
        // Obtener el último ID de la lista de productos y aumentarlo en uno para el nuevo producto
        List<Producto> productos = servicio.Listado();
        int nuevoId = productos.isEmpty() ? 1 : productos.get(productos.size() - 1).getId() + 1;
        nuevoProducto.setId(nuevoId);
        modelo.addAttribute("producto", nuevoProducto);
        return "formProducto";
    }

    //Accion guardar(save) Model modelo >> parametro para pasar datos desde el controlador a la vista
    @PostMapping("/save")
    public String save(@Validated Producto p, Model modelo) {
        servicio.Insertar(p);
        return "redirect:/productos";
    }

    //Accion Editar
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model modelo) {
        Optional<Producto> optionalProducto = servicio.Buscar(id);
        if (optionalProducto.isPresent()) {
            Producto producto = optionalProducto.get();
            // Deshabilitar la edición del campo de ID
            modelo.addAttribute("producto", producto);
            return "formProducto";
        } else {
            // Manejar el caso en que no se encuentre el producto con el ID especificado
            return "redirect:/productos";
        }
    }

    //Accion Eliminar
    @GetMapping("/eliminarProducto/{id}")
    public String eliminar(@PathVariable int id, Model modelo) {
        servicio.Suprimir(id);
        return "redirect:/productos";
    }
}
