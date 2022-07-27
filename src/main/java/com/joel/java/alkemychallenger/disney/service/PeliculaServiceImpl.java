package com.joel.java.alkemychallenger.disney.service;

import java.util.Date;
import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joel.java.alkemychallenger.disney.bo.Genero;
import com.joel.java.alkemychallenger.disney.bo.Pelicula;
import com.joel.java.alkemychallenger.disney.exception.ExceptionSql;
import com.joel.java.alkemychallenger.disney.exception.PeliculaException;
import com.joel.java.alkemychallenger.disney.repository.GeneroRepository;
import com.joel.java.alkemychallenger.disney.repository.PeliculaRepository;

@Service
@Transactional(rollbackFor = Exception.class)
public class PeliculaServiceImpl implements PeliculaService {
	
	@Autowired
	PeliculaRepository peliculaRepository;
	
	@Autowired
	GeneroRepository generoRepository;
	
	@Override
	public List<Pelicula> recuperarPelicula() {
		return peliculaRepository.findAll();
	}

	@Override
	public Long guardarNuevaPelicula(Pelicula pelicula) {
		if(pelicula.getFechaDeCreacion() == null)
			pelicula.setFechaDeCreacion(new Date());
		peliculaRepository.save(pelicula);
		return pelicula.getIdPelicula();
	}
	
	@Override
	public Long guardarNuevaPelicula(Pelicula pelicula, Long idGenero) throws PeliculaException, ExceptionSql {
		Genero genero = generoRepository.getReferenceById(idGenero);
		
		if(pelicula.getFechaDeCreacion() == null)
			pelicula.setFechaDeCreacion(new Date());
		
		pelicula.setGenero(genero);
		peliculaRepository.save(pelicula);
		return pelicula.getIdPelicula();
	}


	@Override
	public void actualizarPelicula(Pelicula pelicula) {
		peliculaRepository.save(pelicula);
	}
	
	@Override
	public void borrarPeliculaPorId(Long id) {
		peliculaRepository.deleteById(id);
	}

	@Override
	public Pelicula buscarPeliculaPorId(Long id) throws PeliculaException, ExceptionSql {
		Optional<Pelicula> peliculaOptional = peliculaRepository.findById(id);
		return peliculaOptional.get();
	}

	@Cacheable("peliculasPorNombre")
	@Override
	public List<Pelicula> buscarPeliculaPorNombre(String nombre) {
		return peliculaRepository.buscarPeliculaPorNombre(nombre);
	}

	@Cacheable("peliculasPorIdGenero")
	@Override
	public List<Pelicula> buscarPeliculaPorIdGenero(Long id) {
		return peliculaRepository.buscarPeliculaPorIdGenero(id);
	}

	@Override
	public List<Genero> recuperarPeliculaGenero() {
		return generoRepository.recuperarTodasLosGeneroPeliculas();
	}
}
