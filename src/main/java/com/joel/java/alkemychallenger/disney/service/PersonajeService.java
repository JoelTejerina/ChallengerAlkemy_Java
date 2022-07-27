package com.joel.java.alkemychallenger.disney.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.joel.java.alkemychallenger.disney.bo.Pelicula;
import com.joel.java.alkemychallenger.disney.bo.Personaje;
import com.joel.java.alkemychallenger.disney.exception.ExceptionSql;
import com.joel.java.alkemychallenger.disney.exception.PersonajeException;

public interface PersonajeService {
	
	List<Personaje> recuperarPersonaje();
		
	Long guardarNuevoPersonaje(Personaje personaje, Long idPelicula) throws PersonajeException, ExceptionSql;
	
	Long guardarNuevoPersonaje(Personaje personaje);
	
	void borrarPersonajePorId(Long id);
	
	void actualizarPersonaje(Personaje personaje);
	
	Personaje buscarPersonajePorId(Long id) throws PersonajeException, ExceptionSql;
	
	List<Personaje> buscarPersonajePorNombre(String nombre);
	
	List<Personaje> buscarPersonajePorEdad(Long edad);
	
	List<Personaje> buscarPersonajePorPelicula(Long idPelicula);
	
	List<Pelicula> recuperarPeliculaPersonaje();
}
