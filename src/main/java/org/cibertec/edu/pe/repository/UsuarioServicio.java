package org.cibertec.edu.pe.repository;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.cibertec.edu.pe.model.Usuario;
import org.cibertec.edu.pe.model.UsuarioRegistroDTO;


public interface UsuarioServicio extends UserDetailsService{

	public Usuario guardar(UsuarioRegistroDTO registroDTO);
	
	public List<Usuario> listarUsuarios();
	
}
