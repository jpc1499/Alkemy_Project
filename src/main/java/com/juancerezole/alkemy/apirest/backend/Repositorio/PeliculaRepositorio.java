package com.juancerezole.alkemy.apirest.backend.Repositorio;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.juancerezole.alkemy.apirest.backend.DTO.PeliculaDTO;
import com.juancerezole.alkemy.apirest.backend.Modelo.Genero;
import com.juancerezole.alkemy.apirest.backend.Modelo.Pelicula;

@Repository
public interface PeliculaRepositorio extends CrudRepository<Pelicula, Long>{

	@Query("select g from Genero g")
	public List<Genero> findAllGeneros();
	
	@Query(value="select imagen, titulo, fecha_creacion from peliculas", nativeQuery = true)
	public List<PeliculaDTO> listarPeliculas();
	
	public List<Pelicula> findByTituloOrIdOrderByFechaCreacionDesc(String titulo, Long genero_id);
	
	public List<Pelicula> findByTituloOrIdOrderByFechaCreacionAsc(String titulo, Long genero_id);
}
