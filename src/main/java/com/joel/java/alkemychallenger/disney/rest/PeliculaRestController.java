package com.joel.java.alkemychallenger.disney.rest;

import java.util.ArrayList;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.joel.java.alkemychallenger.disney.bo.Pelicula;
import com.joel.java.alkemychallenger.disney.bo.Personaje;
import com.joel.java.alkemychallenger.disney.exception.ExceptionSql;
import com.joel.java.alkemychallenger.disney.exception.PeliculaException;
import com.joel.java.alkemychallenger.disney.exception.PersonajeException;
import com.joel.java.alkemychallenger.disney.rest.dto.PeliculaDTO;
import com.joel.java.alkemychallenger.disney.service.PeliculaService;
import com.joel.java.alkemychallenger.disney.service.PersonajeService;

@RestController
@RequestMapping("/api/peliculas")
public class PeliculaRestController {
	
	private static Logger log = LoggerFactory.getLogger(PeliculaRestController.class);
	
	@Autowired
	private PeliculaService peliculaService;
	
	@Autowired
	private PersonajeService personajeService;
	
	@GetMapping("/recuperar")
	public ResponseEntity<List<PeliculaDTO>> recuperarTodasLasPeliculas() {
		List<Pelicula> peliculas = personajeService.recuperarPeliculaPersonaje();
		List<PeliculaDTO> peliculaDTO = new ArrayList<PeliculaDTO>();
		
		for(Pelicula pelicula : peliculas) {
			peliculaDTO.add(new PeliculaDTO(pelicula));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(peliculaDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<PeliculaDTO>> recuperarPeliculas(){
		List<Pelicula> peliculas = peliculaService.recuperarPelicula();
		List<PeliculaDTO> peliculaDTO = new ArrayList<PeliculaDTO>();
		
		for(Pelicula pelicula : peliculas) {
			peliculaDTO.add(new PeliculaDTO(pelicula));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(peliculaDTO);
	}
	
	@GetMapping(params = "nombre")
	@RequestMapping("/buscar")
	public ResponseEntity<List<PeliculaDTO>> buscarPeliculasPorNombre(@RequestParam(value="nombre", required = false) String nombre){
		List<Pelicula> peliculas = null;
		peliculas = peliculaService.buscarPeliculaPorNombre(nombre);
		List<PeliculaDTO> peliculaDTO = new ArrayList<PeliculaDTO>();
		
		for(Pelicula pelicula : peliculas) {
			peliculaDTO.add(new PeliculaDTO(pelicula));
		}
		
		if(peliculaDTO.isEmpty()) 
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No encontramos ninguna pelicula con ese nombre: " + nombre);
		
		return ResponseEntity.status(HttpStatus.OK).body(peliculaDTO);
	}
	
	@GetMapping(params = "genre")
	public ResponseEntity<List<PeliculaDTO>> buscarPeliculaPorNombre(@RequestParam(value="genre", required = false) Long idGenero){
		List<Pelicula> peliculas = null;
		peliculas = peliculaService.buscarPeliculaPorIdGenero(idGenero);
		List<PeliculaDTO> peliculaDTO = new ArrayList<PeliculaDTO>();
		
		for(Pelicula pelicula : peliculas) {
			peliculaDTO.add(new PeliculaDTO(pelicula));
		}
		
		if(peliculaDTO.isEmpty()) 
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No encontramos ninguna pelicula relacionado con el id de genero: " + idGenero);
		
		return ResponseEntity.status(HttpStatus.OK).body(peliculaDTO);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<PeliculaDTO> altaDeNuevaPelicula(@Valid @RequestBody PeliculaDTO peliculaDTO) {
		Pelicula pelicula = new Pelicula();
		modificandoDatosEntidadPelicula(peliculaDTO, pelicula);
		Long idGenerado;
		if(peliculaDTO.getGeneroIdGenero() == null) {
			idGenerado = peliculaService.guardarNuevaPelicula(pelicula);;
		}
		else {
			try {
				idGenerado = peliculaService.guardarNuevaPelicula(pelicula, peliculaDTO.getGeneroIdGenero());
			} catch (PeliculaException e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error al crear una pelicula: " + e.getMessage());
			} catch (ExceptionSql e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error en la base de datos al crear una pelicula: " + e.getMessage());
			} finally {
				if(pelicula.getIdPelicula() == null) {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error no existe el id del genero : " + peliculaDTO.getGeneroIdGenero());
				}
			}
		}
		
		peliculaDTO.setIdPelicula(idGenerado);
		return ResponseEntity.status(HttpStatus.CREATED).eTag("La creacion fue un exito").body(peliculaDTO);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<PeliculaDTO> actualizarPeliculaPorId(@PathVariable Long id, @Valid @RequestBody PeliculaDTO peliculaDTO) {
		Pelicula pelicula = new Pelicula();
		
		try {
			pelicula = peliculaService.buscarPeliculaPorId(id);
		} catch (PeliculaException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error al actualizar una pelicula: " + e.getMessage());
		} catch (ExceptionSql e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error en la base de datos al actualizar una pelicula: " + e.getMessage());
		} finally {
			if(pelicula.getIdPelicula() == null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error no existe el id de la pelicula : " + id);
			}
		}
		modificandoDatosEntidadPelicula(peliculaDTO, pelicula);
		peliculaService.actualizarPelicula(pelicula);
		return ResponseEntity.status(HttpStatus.OK).eTag("La actualizacion fue un exito").body(peliculaDTO);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> borrarPeliculaPorId(@PathVariable Long id){
		Pelicula pelicula = new Pelicula();
		
		try {
			pelicula = peliculaService.buscarPeliculaPorId(id);
		} catch (PeliculaException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error al eliminar una pelicula: " + e.getMessage());
		} catch (ExceptionSql e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error en la base de datos al eliminar una pelicula: " + e.getMessage());
		} finally {
			if(pelicula.getIdPelicula() == null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error no existe el id de la pelicula : " + id);
			}
		}
		peliculaService.borrarPeliculaPorId(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).eTag("La eliminacion fue un exito").body(null);
	}
	
	private void modificandoDatosEntidadPelicula(PeliculaDTO peliculaDTO, Pelicula pelicula) {
		pelicula.setImagen(peliculaDTO.getImagen());
		pelicula.setTitulo(peliculaDTO.getTitulo());
		pelicula.setFechaDeCreacion(peliculaDTO.getFechaDeCreacion());
		pelicula.setCalificacion(peliculaDTO.getCalificacion());
		pelicula.setFechaDeCreacion(peliculaDTO.getFechaDeCreacion());
	}
}
