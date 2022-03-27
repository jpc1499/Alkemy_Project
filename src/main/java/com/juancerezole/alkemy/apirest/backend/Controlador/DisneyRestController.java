package com.juancerezole.alkemy.apirest.backend.Controlador;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.juancerezole.alkemy.apirest.backend.DTO.PeliculaDTO;
import com.juancerezole.alkemy.apirest.backend.DTO.PersonajeDTO;
import com.juancerezole.alkemy.apirest.backend.Modelo.Pelicula;
import com.juancerezole.alkemy.apirest.backend.Modelo.Personaje;
import com.juancerezole.alkemy.apirest.backend.Servicio.IPeliculaService;
import com.juancerezole.alkemy.apirest.backend.Servicio.IPersonajeService;
import com.juancerezole.alkemy.apirest.backend.Servicio.IUploadFileService;



@CrossOrigin(origins = {"http://localhost:4200"}) //En caso de realizar frontend en Angular
@RestController
@RequestMapping("/api")
public class DisneyRestController {

	@Autowired
	private IPersonajeService personajeService;
	
	@Autowired
	private IPeliculaService peliculaService;

	@Autowired
	private IUploadFileService uploadService;
	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@GetMapping("/characters/details")
	public List<Personaje> detallePersonajes(){	
		return personajeService.findAll();
	}
	
	@RequestMapping( method = RequestMethod.GET, params = {"nombre", "edad", "peso", "pelicula_id"}, value="/characters")
	public List<Personaje> FiltrarPersonajes (@RequestParam(name = "nombre")String nombre, @RequestParam(name = "edad")int edad,@RequestParam(name = "peso")Double peso,@RequestParam(name = "pelicula_id")Long pelicula_id){	
		return personajeService.findByNombreOrEdadOrPesoOrId(nombre, edad, peso, pelicula_id);
	}
	
	@GetMapping("/characters")
	public List<PersonajeDTO> listarPersonajes(){
		return personajeService.listarPersonajes();
	}
	
	@GetMapping("/characters/{id}")
	public ResponseEntity<?> BuscarPersonajePorId(@PathVariable Long id) {
		Personaje personaje = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			personaje = personajeService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar consulta a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (personaje == null) {
			response.put("mensaje", "El personaje ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Personaje>(personaje, HttpStatus.OK);
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/characters")
	@Transactional
	public ResponseEntity<?> crearPersonaje(@Valid @RequestBody Personaje personaje, BindingResult result){
		Personaje personajeNew = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
		personajeNew = personajeService.save(personaje);
		} catch(DataAccessException e){
			response.put("mensaje", "Error al realizar un insert a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El personaje ha sido creado con éxito!");
		response.put("personaje", personajeNew);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured("ROLE_ADMIN")
	@PutMapping("/characters/{id}")
	@Transactional
	public ResponseEntity<?> actualizarPersonaje(@Valid @RequestBody Personaje personaje, BindingResult result, @PathVariable Long id) {
		Personaje personajeActual = personajeService.findById(id);
		Personaje personajeActualizado = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (personajeActual == null) {
			response.put("mensaje", "Error: No se pudo editar, el personaje ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			personajeActual.setNombre(personaje.getNombre());
			personajeActual.setEdad(personaje.getEdad());
			personajeActual.setPeso(personaje.getPeso());
			personajeActual.setHistoria(personaje.getHistoria());
			personajeActualizado = personajeService.save(personajeActual);
		} catch(DataAccessException e){
			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El personaje ha sido actualizado con éxito!");
		response.put("personaje", personajeActualizado);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/characters/{id}")
	public ResponseEntity<?> eliminarPersonaje(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		
		try {
			Personaje personaje = personajeService.findById(id);
			String nombreFotoAnterior = personaje.getImagen();
			
			uploadService.eliminar(nombreFotoAnterior);
			
			personajeService.delete(id);
		} catch(DataAccessException e){
			response.put("mensaje", "Error al eliminar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El personaje ha sido eliminado con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/characters/upload")
	public ResponseEntity<?> cargarImagenPersonaje(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){
		Map<String, Object> response = new HashMap<>();
		
		Personaje personaje = personajeService.findById(id);
		
		if(!archivo.isEmpty()) {
			
			String nombreArchivo = null;
			try {
				nombreArchivo = uploadService.copiar(archivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen:");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			String nombreFotoAnterior = personaje.getImagen();
			
			uploadService.eliminar(nombreFotoAnterior);
			
			personaje.setImagen(nombreArchivo);
			personajeService.save(personaje);
			response.put("personaje", personaje);
			response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verImagen(@PathVariable String nombreFoto){
		
		Resource recurso = null;
		
		try {
			recurso = uploadService.upload(nombreFoto);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}
	
	@GetMapping("/movies")
	@Transactional(readOnly = true)
	public List<PeliculaDTO> ListarPeliculas(){
		return peliculaService.listarPeliculas();
	}
	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@GetMapping("/movies/details")
	@Transactional(readOnly = true)
	public List<Pelicula> DetallePeliculas(){
		return peliculaService.findAll();
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/movies")
	@Transactional
	public ResponseEntity<?> crearPelicula(@Valid @RequestBody Pelicula pelicula, BindingResult result){
		Pelicula peliculaNew = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
		peliculaNew = peliculaService.save(pelicula);
		} catch(DataAccessException e){
			response.put("mensaje", "Error al realizar un insert a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La película ha sido creado con éxito!");
		response.put("pelicula", peliculaNew);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/movies/{id}")
	@Transactional
	public ResponseEntity<?> actualizarPelicula(@Valid @RequestBody Pelicula pelicula, BindingResult result, @PathVariable Long id) {
		Pelicula peliculaActual = peliculaService.findById(id);
		Pelicula peliculaActualizada = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (peliculaActual == null) {
			response.put("mensaje", "Error: No se pudo editar, la película ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			peliculaActual.setTitulo(pelicula.getTitulo());
			peliculaActual.setFechaCreacion(pelicula.getFechaCreacion());
//			peliculaActual.setCalificacion(pelicula.getCalificacion());
			peliculaActualizada = peliculaService.save(peliculaActual);
		} catch(DataAccessException e){
			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La película ha sido actualizada con éxito!");
		response.put("pelicula", peliculaActualizada);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/movies/{id}")
	public ResponseEntity<?> eliminarPelicula(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		
		try {
			Pelicula pelicula = peliculaService.findById(id);
			String nombreFotoAnterior = pelicula.getImagen();
			
			uploadService.eliminar(nombreFotoAnterior);
			
			peliculaService.delete(id);
		} catch(DataAccessException e){
			response.put("mensaje", "Error al eliminar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La pelicula ha sido eliminada con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/movies/upload")
	public ResponseEntity<?> cargarImagenPelicula(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){
		Map<String, Object> response = new HashMap<>();
		
		Pelicula pelicula = peliculaService.findById(id);
		
		if(!archivo.isEmpty()) {
			
			String nombreArchivo = null;
			try {
				nombreArchivo = uploadService.copiar(archivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen:");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			String nombreFotoAnterior = pelicula.getImagen();
			
			uploadService.eliminar(nombreFotoAnterior);
			
			pelicula.setImagen(nombreArchivo);
			peliculaService.save(pelicula);
			response.put("pelicula", pelicula);
			response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	@RequestMapping( method = RequestMethod.GET, params = {"titulo", "genero_id"}, value="/moviesAsc")
	public List<Pelicula> FiltrarPeliculasAsc (@RequestParam(name = "titulo")String titulo, @RequestParam(name = "genero_id")Long id_genero){	
		
		return peliculaService.findByTituloOrIdOrderByFechaCreacionAsc(titulo, id_genero);
	}
	
	@RequestMapping( method = RequestMethod.GET, params = {"titulo", "genero_id"}, value="/moviesDesc")
	public List<Pelicula> FiltrarPeliculasDesc (@RequestParam(name = "titulo")String titulo, @RequestParam(name = "genero_id")Long id_genero){	
		
		return peliculaService.findByTituloOrIdOrderByFechaCreacionDesc(titulo, id_genero);
	}
	

	
}
