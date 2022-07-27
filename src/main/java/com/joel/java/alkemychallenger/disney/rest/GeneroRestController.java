package com.joel.java.alkemychallenger.disney.rest;

import java.util.ArrayList;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.joel.java.alkemychallenger.disney.bo.Genero;
import com.joel.java.alkemychallenger.disney.exception.ExceptionSql;
import com.joel.java.alkemychallenger.disney.exception.GeneroException;
import com.joel.java.alkemychallenger.disney.rest.dto.GeneroDTO;
import com.joel.java.alkemychallenger.disney.service.GeneroService;

@RestController
@RequestMapping("/api/generos")
public class GeneroRestController {
	
	@Autowired
	private GeneroService generoService;
	
	@GetMapping
	public ResponseEntity<List<GeneroDTO>> recuperarGeneros(){
		List<Genero> generos  = generoService.recuperarGeneros();
		List<GeneroDTO> generoDTO = new ArrayList<>();
		
		for(Genero genero : generos) {
			generoDTO.add(new GeneroDTO(genero));
		}

		return ResponseEntity.status(HttpStatus.OK).body(generoDTO);
	}
	
	@GetMapping(params = "nombre")
	@RequestMapping("/buscar")
	public ResponseEntity<List<GeneroDTO>> buscarGenerosPorNombre(@RequestParam(value="nombre", required = false) String nombre){
		List<Genero> generos = null;
		generos = generoService.buscarGeneroPorNombre(nombre);
		List<GeneroDTO> generoDTO = new ArrayList<GeneroDTO>();
		
		for(Genero genero : generos) {
			generoDTO.add(new GeneroDTO(genero));
		}
		
		if(generoDTO.isEmpty()) 
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No encontramos ninguna pelicula con ese nombre: " + nombre);
		
		return ResponseEntity.status(HttpStatus.OK).body(generoDTO);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<GeneroDTO> altaDeNuevoGenero(@Valid @RequestBody GeneroDTO generoDTO) {
		Genero genero = new Genero();
		modificandoDatosEntidadGenero(generoDTO, genero);
		Long idGenerado = generoService.guardarNuevoGenero(genero);
	    genero.setIdGenero(idGenerado);
		return ResponseEntity.status(HttpStatus.CREATED).eTag("La creacion fue un exito").body(generoDTO);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<GeneroDTO> actualizarGeneroPorId(@PathVariable Long id, @Valid @RequestBody GeneroDTO generoDTO) {
		Genero genero = new Genero();
		
		try {
			genero = generoService.buscarGeneroPorId(id);
		} catch (GeneroException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error al actualizar el genero: " + e.getMessage());
		} catch (ExceptionSql e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error en la base de datos al actualizar genero: " + e.getMessage());
		} finally {
			if(genero.getIdGenero() == null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error no existe el id del genero : " + id);
			}
		}
		
		modificandoDatosEntidadGenero(generoDTO, genero);
		generoService.actualizarGenero(genero);
		return ResponseEntity.status(HttpStatus.OK).eTag("La actualizacion fue un exito").body(generoDTO);
	}
	
	/*@DeleteMapping("/{id}")
	public ResponseEntity<?> borrarPersonajePorId(@PathVariable Long id){
		Genero genero = new Genero();
		try {
			genero = generoService.buscarGeneroPorId(id);
		} catch (GeneroException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error al eliminar un genero: " + e.getMessage());
		} catch (ExceptionSql e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error en la base de datos al eliminar un genero: " + e.getMessage());
		} finally {
			if(genero.getIdGenero() == null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error no existe el id del genero : " + id);
			}
		}
		generoService.borrarGeneroPorId(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).eTag("La eliminacion fue un exito").body(null);
	}*/
	
	private void modificandoDatosEntidadGenero(GeneroDTO generoDTO, Genero genero) {
		genero.setNombre(generoDTO.getNombre());
		genero.setImagen(generoDTO.getImagen());
	}
}
