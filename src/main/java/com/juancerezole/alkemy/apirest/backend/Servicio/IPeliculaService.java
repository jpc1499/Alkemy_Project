package com.juancerezole.alkemy.apirest.backend.Servicio;


import java.util.List;

import com.juancerezole.alkemy.apirest.backend.DTO.PeliculaDTO;
import com.juancerezole.alkemy.apirest.backend.Modelo.Genero;
import com.juancerezole.alkemy.apirest.backend.Modelo.Pelicula;

public interface IPeliculaService {

	public List<Pelicula> findAll();
	
	public Pelicula findById(Long id);
	
	public Pelicula save(Pelicula pelicula);
	
	public void delete(Long id);
	
	public List<Genero> findAllGeneros();
	
	public List<PeliculaDTO> listarPeliculas();
	
	public List<Pelicula> findByTituloOrIdOrderByFechaCreacionDesc(String titulo, Long genero_id);
	
	public List<Pelicula> findByTituloOrIdOrderByFechaCreacionAsc(String titulo, Long genero_id);
	
	
}
