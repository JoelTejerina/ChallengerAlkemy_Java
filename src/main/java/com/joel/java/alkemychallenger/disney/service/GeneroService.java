package com.joel.java.alkemychallenger.disney.service;

import java.util.List;

import com.joel.java.alkemychallenger.disney.bo.Genero;
import com.joel.java.alkemychallenger.disney.exception.ExceptionSql;
import com.joel.java.alkemychallenger.disney.exception.GeneroException;

public interface GeneroService {
	
	List<Genero> recuperarGeneros();
	
	Long guardarNuevoGenero(Genero genero);
	
	Genero buscarGeneroPorId(Long id) throws GeneroException, ExceptionSql;
	
	void actualizarGenero(Genero genero);

	List<Genero> buscarGeneroPorNombre(String nombre);

	//void borrarGeneroPorId(Long id);
}
