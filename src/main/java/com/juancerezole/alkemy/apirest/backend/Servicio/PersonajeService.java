package com.juancerezole.alkemy.apirest.backend.Servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juancerezole.alkemy.apirest.backend.DTO.PersonajeDTO;
import com.juancerezole.alkemy.apirest.backend.Modelo.Personaje;
import com.juancerezole.alkemy.apirest.backend.Repositorio.PersonajeRepositorio;

@Service
public class PersonajeService implements IPersonajeService {

	
	@Autowired
	private PersonajeRepositorio personajeRepositorio;
	
	@Override
	@Transactional(readOnly = true)
	public List<Personaje> findAll() {
		return (List<Personaje>) personajeRepositorio.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<PersonajeDTO> listarPersonajes() {
		return personajeRepositorio.listarPersonajes();
	}
	
	@Transactional(readOnly = true)
	public List<Personaje> findByNombreOrEdadOrPesoOrId(String nombre, int edad, double peso, Long pelicula_id) {
		return personajeRepositorio.findByNombreOrEdadOrPesoOrId(nombre, edad, peso, pelicula_id);
		}

	@Override
	@Transactional(readOnly = true)
	public Personaje findById(Long id) {
		return personajeRepositorio.findById(id).orElse(null);
	}

	@Override
	public Personaje save(Personaje personaje) {
	
		return personajeRepositorio.save(personaje);
	}

	@Override
	public void delete(Long id) {
		personajeRepositorio.deleteById(id);
		
	}


}
