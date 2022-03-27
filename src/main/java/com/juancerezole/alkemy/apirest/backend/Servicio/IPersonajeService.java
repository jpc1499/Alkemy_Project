package com.juancerezole.alkemy.apirest.backend.Servicio;

import java.util.List;

import com.juancerezole.alkemy.apirest.backend.DTO.PersonajeDTO;
import com.juancerezole.alkemy.apirest.backend.Modelo.Personaje;

public interface IPersonajeService {

	public List<Personaje> findAll();
	
	public List<PersonajeDTO> listarPersonajes();
	
	public List<Personaje> findByNombreOrEdadOrPesoOrId(String nombre, int edad, double peso, Long pelicula_id);
	
	public Personaje findById(Long id);
	
	public Personaje save(Personaje personaje);
	
	public void delete(Long id);
}
