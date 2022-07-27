package com.joel.java.alkemychallenger.disney.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.joel.java.alkemychallenger.disney.bo.Pelicula;
import com.joel.java.alkemychallenger.disney.bo.Personaje;
import com.joel.java.alkemychallenger.disney.exception.ExceptionSql;
import com.joel.java.alkemychallenger.disney.exception.PersonajeException;
import com.joel.java.alkemychallenger.disney.repository.PeliculaRepository;
import com.joel.java.alkemychallenger.disney.repository.PersonajeRepository;

@Service
@Transactional(rollbackFor = Exception.class)
public class PersonajeServiceImpl implements PersonajeService{
	
	@Autowired
	private PersonajeRepository personajeRepository;
	
	@Autowired
	private PeliculaRepository peliculaRepository;
	
	@Override
	public List<Personaje> recuperarPersonaje() {
		return personajeRepository.findAll();
	}

	@Override
	public Long guardarNuevoPersonaje(Personaje personaje) {
		personajeRepository.save(personaje);
		return personaje.getIdPersonaje();
	}
	
	@Override
	public Long guardarNuevoPersonaje(Personaje personaje, Long idPelicula) throws PersonajeException, ExceptionSql {
		Pelicula pelicula = peliculaRepository.getReferenceById(idPelicula);
		personaje.setPelicula(pelicula);
		personajeRepository.save(personaje);
		return personaje.getIdPersonaje();
	}

	@Override
	public void borrarPersonajePorId(Long id) {
		personajeRepository.deleteById(id);;
	}

	@Override
	public void actualizarPersonaje(Personaje personaje) {
		personajeRepository.save(personaje);
	}

	@Override
	public Personaje buscarPersonajePorId(Long id) throws PersonajeException, ExceptionSql  {
		Optional<Personaje> personajeOptional = personajeRepository.findById(id);
		return personajeOptional.get();
	}

	@Cacheable("personajesPorNombre")
	@Override
	public List<Personaje> buscarPersonajePorNombre(String nombre) {
		return personajeRepository.buscarPersonajePorNombre(nombre);
	}
	
	@Cacheable("personajePorEdad")
	@Override
	public List<Personaje> buscarPersonajePorEdad(Long edad) {
		return personajeRepository.buscarPersonajePorEdad(edad);
	}
	
	@Cacheable("personajePorPelicula")
	@Override
	public List<Personaje> buscarPersonajePorPelicula(Long idPelicula) {
		return personajeRepository.buscarPersonajePorPelicula(idPelicula);
	}

	@Override
	public List<Pelicula> recuperarPeliculaPersonaje() {
		return peliculaRepository.recuperarTodasLasPeliculasPersonajes();
	}
}
