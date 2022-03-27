package com.juancerezole.alkemy.apirest.backend.Repositorio;

import org.springframework.data.repository.CrudRepository;
import com.juancerezole.alkemy.apirest.backend.Modelo.Usuario;

public interface UsuarioRepositorio extends CrudRepository<Usuario, Long>{

	public Usuario findByUsername(String username);

}
