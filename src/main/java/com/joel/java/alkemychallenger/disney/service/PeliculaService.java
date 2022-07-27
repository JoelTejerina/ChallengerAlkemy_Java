package com.joel.java.alkemychallenger.disney.service;

import java.util.List;

import com.joel.java.alkemychallenger.disney.bo.Genero;
import com.joel.java.alkemychallenger.disney.bo.Pelicula;
import com.joel.java.alkemychallenger.disney.exception.ExceptionSql;
import com.joel.java.alkemychallenger.disney.exception.PeliculaException;
import com.joel.java.alkemychallenger.disney.exception.PersonajeException;

public interface PeliculaService {
	
	List<Pelicula> recuperarPelicula();
	
	Long guardarNuevaPelicula(Pelicula pelicula);
	
	Long guardarNuevaPelicula(Pelicula pelicula, Long idGenero) throws PeliculaException, ExceptionSql;;
	
	void borrarPeliculaPorId(Long id);
	
	void actualizarPelicula(Pelicula personaje);
	
	Pelicula buscarPeliculaPorId(Long id) throws PeliculaException, ExceptionSql;
	
	List<Pelicula> buscarPeliculaPorNombre(String nombre);
	
	List<Pelicula> buscarPeliculaPorIdGenero(Long id);

	List<Genero> recuperarPeliculaGenero();
}
