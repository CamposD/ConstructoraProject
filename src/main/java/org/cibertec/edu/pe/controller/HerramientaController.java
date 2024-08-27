package org.cibertec.edu.pe.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import java.time.LocalDate;

import org.cibertec.edu.pe.model.Herramienta;
import org.cibertec.edu.pe.model.Adquisicion;
import org.cibertec.edu.pe.model.Categoria;
import org.cibertec.edu.pe.model.Detalle;
import org.cibertec.edu.pe.model.Venta;
import org.cibertec.edu.pe.repository.IHerramientaRepository;
import org.cibertec.edu.pe.repository.IAdquisicionRepository;
import org.cibertec.edu.pe.repository.ICategoriaRepository;
import org.cibertec.edu.pe.repository.IDetalleRepository;
import org.cibertec.edu.pe.repository.IVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"carrito", "total"})
public class HerramientaController {
	
	
	

	@Autowired
	private IVentaRepository ventaRepository;
	@Autowired
	private IDetalleRepository detalleRepository;

	////////listado categorias//////////
	@GetMapping("/constructora")
	public String listado(Model model) {
		List<Categoria> lista = new ArrayList<>();
		lista = categoriaRepository.findAll();
		model.addAttribute("categorias", lista);
		return "constructora";
	}
	
	
	//////////listado herramientas por categoria/////////////
	
	@Autowired
    private IHerramientaRepository herramientaRepository;
	
	@GetMapping("/herramientas/{idCategoria}")
	public String listadoHerramientas(@PathVariable("idCategoria") int idCategoria, Model model) {
	    List<Herramienta> herramientas = herramientaRepository.findByCategoriaId(idCategoria);
	    model.addAttribute("herramientas", herramientas);
	    return "herramientas";
	}
	

	////////////Add Carrito/////////
	@GetMapping("/agregar/{idHerramienta}")
	public String agregar(Model model, HttpSession session, @PathVariable(name = "idHerramienta", required = true) int idHerramienta) {
	    Herramienta herramienta = herramientaRepository.findById(idHerramienta).orElse(null);

	    if (herramienta != null) {
	        // Obtener o inicializar el carrito de la sesión
	        List<Detalle> carrito = (List<Detalle>) session.getAttribute("carrito");
	        if (carrito == null) {
	            carrito = new ArrayList<>();
	        }

	        // Verificar si la herramienta ya está en el carrito
	        boolean herramientaExistente = carrito.stream()
	                .anyMatch(detalle -> detalle.getHerramienta().getIdHerramienta() == idHerramienta);

	        if (herramientaExistente) {
	            // Si la herramienta ya está en el carrito, aumentar la cantidad
	            carrito.stream()
	                    .filter(detalle -> detalle.getHerramienta().getIdHerramienta() == idHerramienta)
	                    .findFirst()
	                    .ifPresent(detalle -> {
	                        detalle.setCantidad(detalle.getCantidad() + 1);
	                        detalle.setSubtotal(detalle.getHerramienta().getPrecio() * detalle.getCantidad());
	                    });
	        } else {
	            // Si la herramienta no está en el carrito, agregarla con cantidad 1
	            Detalle detalle = new Detalle();
	            detalle.setHerramienta(herramienta);
	            detalle.setCantidad(1);
	            detalle.setSubtotal(detalle.getHerramienta().getPrecio());
	            carrito.add(detalle);
	        }

	        // Actualizar el modelo con el carrito modificado
	        session.setAttribute("carrito", carrito);
	    }
	    
	    // Obtener la categoría de la herramienta
        Categoria categoria = herramienta.getCategoria();
        int idCategoria = categoria.getIdCategoria();

	    // Agregar la categoría al modelo
	    model.addAttribute("idCategoria", idCategoria);


	    // Redirigir a la página del carrito
	    return "redirect:/herramientas/" + idCategoria;
	}


	
	    
	
	
	@GetMapping("/carrito")
	public String carrito() {
		return "carrito";
	}
	
	
	/*******pagar******/	

	@GetMapping("/pagar")
	public String pagar(HttpSession session, Model model) {
	    // Obtener el carrito de la sesión
	    List<Detalle> carrito = (List<Detalle>) session.getAttribute("carrito");

	    // Crear una nueva venta y guardarla en la base de datos
	    Venta venta = new Venta();
	    venta.setFechaRegistro(new Date(System.currentTimeMillis()));
	    venta.setMontoTotal(calcularTotal(carrito));
	    venta = ventaRepository.save(venta);

	    // Guardar los detalles de la venta en la base de datos
	    for (Detalle detalle : carrito) {
	        detalle.setVenta(venta);
	        detalleRepository.save(detalle);
	    }

	    session.removeAttribute("carrito");
	    session.removeAttribute("total");

	    model.addAttribute("mensaje", "¡Pago y registro de venta exitosos!");

	    // Limpiar el modelo de carrito y total
	    model.addAttribute("carrito", new ArrayList<Detalle>());
	    model.addAttribute("total", 0.0);

	    return "mensaje"; 
	} 

	private double calcularTotal(List<Detalle> carrito) {
	    double total = 0.0;
	    for (Detalle detalle : carrito) {
	        total += detalle.getSubtotal();
	    }
	    return total;
	} 
	
	
	/*******actualizar carrito*******/	
	
	
	
	@PostMapping("/actualizarCarrito")
	public String actualizarCarrito(Model model) {
	  

	    
	    List<Detalle> carrito = (List<Detalle>) model.getAttribute("carrito");

	    
	    double total = 0.0;
	    for (Detalle detalle : carrito) {
	        double subtotal = detalle.getHerramienta().getPrecio() * detalle.getCantidad();
	        detalle.setSubtotal(subtotal);
	        total += subtotal;
	    }

	
	    model.addAttribute("carrito", carrito);
	    model.addAttribute("total", total);

	   
	    return "redirect:/carrito";
	}
	

	@ModelAttribute("carrito")
	public List<Detalle> getCarrito() {
		return new ArrayList<Detalle>();
	}
	
	@ModelAttribute("total")
	public double getTotal() {
		return 0.0;
	}
	
	
	@GetMapping("/eliminar/{idHerramienta}")
	public String eliminarDelCarrito(@PathVariable("idHerramienta") int idHerramienta, HttpSession session) {
	    List<Detalle> carrito = (List<Detalle>) session.getAttribute("carrito");
	    if (carrito != null) {
	        // Eliminar el producto con el ID especificado
	        carrito.removeIf(detalle -> detalle.getHerramienta().getIdHerramienta() == idHerramienta);

	        // Recalcular el total del carrito
	        double total = carrito.stream().mapToDouble(Detalle::getSubtotal).sum();

	        session.setAttribute("carrito", carrito);
	        session.setAttribute("total", total);
	    }
	    return "redirect:/carrito";
	}
	
	//////////////////categoria/////////////
	
	@Autowired
    private ICategoriaRepository categoriaRepository;

	
    @GetMapping("/categoria")
    public String listaCategorias(Model model) {
        List<Categoria> categorias = categoriaRepository.findAll();
        model.addAttribute("categorias", categorias);
        return "categoria"; // Nombre del archivo HTML (categorias.html)
    }

  
	
    ///////////////////Adquisiciones//////////////////
    
    @Autowired
    private IAdquisicionRepository adquisicionRepository;

    @GetMapping("/adquisiciones")
    public String adquisiciones(Model model) {
        // Realizar la consulta SQL y obtener los datos
        List<Adquisicion> adquisiciones = adquisicionRepository.obtenerAdquisiciones();

        // Agregar los resultados al modelo
        model.addAttribute("adquisiciones", adquisiciones);

        // Devolver el nombre de la vista
        return "adquisiciones";
    }
}