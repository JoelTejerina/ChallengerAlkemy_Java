package com.joel.java.alkemychallenger.disney.rest;

import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.joel.java.alkemychallenger.disney.bo.Personaje;
import com.joel.java.alkemychallenger.disney.exception.ExceptionSql;
import com.joel.java.alkemychallenger.disney.exception.PersonajeException;
import com.joel.java.alkemychallenger.disney.rest.dto.PersonajeDTO;
import com.joel.java.alkemychallenger.disney.service.PersonajeService;

@RestController
@RequestMapping("api/personajes")
public class PersonajeRestController {
	
	private static Logger log = LoggerFactory.getLogger(PersonajeRestController.class);
	
	@Autowired
	private PersonajeService personajeService;
	
	@GetMapping
	public ResponseEntity<List<PersonajeDTO>> recuperarPersonaje(){
		List<Personaje> personajes = personajeService.recuperarPersonaje();
		List<PersonajeDTO> personajesDTO = new ArrayList<PersonajeDTO>();
		
		for(Personaje personaje : personajes) {
			personajesDTO.add(new PersonajeDTO(personaje));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(personajesDTO);
	}
	
	@RequestMapping("/buscar")
	@GetMapping(params = "nombre")
	public ResponseEntity<List<PersonajeDTO>> buscarPersonajePorNombre(@RequestParam(value="nombre", required = false) String nombre){
		List<Personaje> personajes = null;
		personajes = personajeService.buscarPersonajePorNombre(nombre);
		List<PersonajeDTO> personajesDTO = new ArrayList<PersonajeDTO>();
		
		for(Personaje personaje : personajes) {
			personajesDTO.add(new PersonajeDTO(personaje));
		}
		
		if(personajesDTO.isEmpty()) 
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No encontramos ningun personaje con ese nombre: " + nombre);
		if(nombre == "") {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se a podido realizar su peticion, no se indico un nombre:" + nombre);
		}
		return ResponseEntity.status(HttpStatus.OK).body(personajesDTO);
	}
	
	@GetMapping(params = "edad")
	public ResponseEntity<List<PersonajeDTO>> buscarPersonajePorEdad(@RequestParam(value="edad", required = false) Long edad){
		List<Personaje> personajes = null;
		personajes = personajeService.buscarPersonajePorEdad(edad);
		List<PersonajeDTO> personajesDTO = new ArrayList<PersonajeDTO>();
		
		for(Personaje personaje : personajes) {
			personajesDTO.add(new PersonajeDTO(personaje));
		}
		
		if(personajesDTO.isEmpty()) 
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No encontramos ningun personaje con esa edad: " + edad);
		
		return ResponseEntity.status(HttpStatus.OK).body(personajesDTO);
	}
	
	@GetMapping(params = "pelicula")
	public ResponseEntity<List<PersonajeDTO>> buscarPersonajePorPelicula(@RequestParam(value="pelicula", required = false) Long idPelicula){
		List<Personaje> personajes = null;
		personajes = personajeService.buscarPersonajePorPelicula(idPelicula);
		
		List<PersonajeDTO> personajesDTO = new ArrayList<PersonajeDTO>();
		
		for(Personaje personaje : personajes) {
			personajesDTO.add(new PersonajeDTO(personaje));
		}
		
		if(personajesDTO.isEmpty()) 
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No encontramos ningun personaje relacionado con el id de la pelicula: " + idPelicula);
		
		return ResponseEntity.status(HttpStatus.OK).body(personajesDTO);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<PersonajeDTO> altaDeNuevoPersonaje(@Valid @RequestBody PersonajeDTO personajeDTO){
		Personaje personaje = new Personaje();
		modificandoDatosEntidadPersonaje(personajeDTO, personaje);
		Long idGenerado = null;
		
		if(personajeDTO.getPeliculaIdPelicula() == null) {
			idGenerado = personajeService.guardarNuevoPersonaje(personaje);
		}
		else {
			try {
				idGenerado = personajeService.guardarNuevoPersonaje(personaje, personajeDTO.getPeliculaIdPelicula());
			} catch (PersonajeException e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error crear un personaje: " + e.getMessage());
			} catch (ExceptionSql e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error en la base de datos al dar de alta un personaje: " + e.getMessage());
			} finally {				
				if(idGenerado == null) {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error no existe el id de pelicula : " + personajeDTO.getPeliculaIdPelicula());
				}
			}
		}
		
		personaje.setIdPersonaje(idGenerado);
		return ResponseEntity.status(HttpStatus.CREATED).eTag("La creacion fue un exito").body(personajeDTO);
	}	
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<PersonajeDTO> actualizarPersonajePorId(@PathVariable Long id, @Valid @RequestBody PersonajeDTO personajeDTO) {
		Personaje personaje = new Personaje();
		try {
			personaje = personajeService.buscarPersonajePorId(id);
		} catch (PersonajeException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error al actualizar un personaje: " + e.getMessage());
		} catch (ExceptionSql e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error en la base de datos al actualizar un personaje: " + e.getMessage());
		} finally {
			if(personaje.getIdPersonaje() == null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error no existe el id del personaje : " + id);
			}
		}
		modificandoDatosEntidadPersonaje(personajeDTO, personaje);
		personajeService.actualizarPersonaje(personaje);
		return ResponseEntity.status(HttpStatus.OK).eTag("La actualizacion fue un exito").body(personajeDTO);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> borrarPersonajePorId(@PathVariable Long id){
		Personaje personaje = new Personaje();
		try {
			personaje = personajeService.buscarPersonajePorId(id);
		} catch (PersonajeException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error al eliminar un personaje: " + e.getMessage());
		} catch (ExceptionSql e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error en la base de datos al eliminar un personaje: " + e.getMessage());
		} finally {
			if(personaje.getIdPersonaje() == null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error no existe el id del personaje : " + id);
			}
		}
		personajeService.borrarPersonajePorId(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).eTag("La eliminacion fue un exito").body(null);
	}
	
	private void modificandoDatosEntidadPersonaje(PersonajeDTO personajeDTO, Personaje personaje) {
		personaje.setImagen(personajeDTO.getImagen());
		personaje.setNombre(personajeDTO.getNombre());
		personaje.setEdad(personajeDTO.getEdad());
		personaje.setPeso(personajeDTO.getPeso());
		personaje.setHistoria(personajeDTO.getHistoria());
	}
}
