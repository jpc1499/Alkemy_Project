package com.juancerezole.alkemy.apirest.backend.Servicio;

import com.juancerezole.alkemy.apirest.backend.Modelo.Usuario;

public interface IUsuarioService {

	public Usuario findByUsername(String username);
	
	public Usuario findById(Long id);
	
	public Usuario save(Usuario usuario);
	
	public void delete(Long id);
}
