package com.juancerezole.alkemy.apirest.backend.Repositorio;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.juancerezole.alkemy.apirest.backend.DTO.PersonajeDTO;
import com.juancerezole.alkemy.apirest.backend.Modelo.Personaje;


@Repository
public interface PersonajeRepositorio extends CrudRepository<Personaje, Long>{
	
	public List<Personaje> findByNombreOrEdadOrPesoOrId(String nombre, int edad, double peso, Long pelicula_id);
	
	@Query(value="select nombre, imagen from personajes", nativeQuery = true)
	public List<PersonajeDTO> listarPersonajes();

}
