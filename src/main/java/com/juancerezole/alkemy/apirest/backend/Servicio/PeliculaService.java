package com.juancerezole.alkemy.apirest.backend.Servicio;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juancerezole.alkemy.apirest.backend.DTO.PeliculaDTO;
import com.juancerezole.alkemy.apirest.backend.Modelo.Genero;
import com.juancerezole.alkemy.apirest.backend.Modelo.Pelicula;
import com.juancerezole.alkemy.apirest.backend.Repositorio.PeliculaRepositorio;

@Service
public class PeliculaService implements IPeliculaService{

	@Autowired
	private PeliculaRepositorio peliculaRepositorio;
	
	@Override
	@Transactional(readOnly = true)
	public List<Pelicula> findAll() {

		return (List<Pelicula>) peliculaRepositorio.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Pelicula findById(Long id) {
		return peliculaRepositorio.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Pelicula save(Pelicula pelicula) {
		return peliculaRepositorio.save(pelicula);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		peliculaRepositorio.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Genero> findAllGeneros() {
		return peliculaRepositorio.findAllGeneros();
	}

	@Override
	public List<PeliculaDTO> listarPeliculas() {

		return peliculaRepositorio.listarPeliculas();
	}

	@Override
	public List<Pelicula> findByTituloOrIdOrderByFechaCreacionDesc(String titulo, Long genero_id) {
		return peliculaRepositorio.findByTituloOrIdOrderByFechaCreacionDesc(titulo, genero_id);
	}

	@Override
	public List<Pelicula> findByTituloOrIdOrderByFechaCreacionAsc(String titulo, Long genero_id) {
		return peliculaRepositorio.findByTituloOrIdOrderByFechaCreacionAsc(titulo, genero_id);
	}

}
