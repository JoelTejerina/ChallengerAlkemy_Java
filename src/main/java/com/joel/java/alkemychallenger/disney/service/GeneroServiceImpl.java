package com.joel.java.alkemychallenger.disney.service;

import java.util.List;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joel.java.alkemychallenger.disney.bo.Genero;
import com.joel.java.alkemychallenger.disney.exception.ExceptionSql;
import com.joel.java.alkemychallenger.disney.exception.GeneroException;
import com.joel.java.alkemychallenger.disney.repository.GeneroRepository;

@Service
@Transactional(rollbackFor = Exception.class)
public class GeneroServiceImpl implements GeneroService {
	
	@Autowired
	private GeneroRepository generoRepository;

	@Override
	public List<Genero> recuperarGeneros() {
		return generoRepository.findAll();
	}

	@Override
	public Long guardarNuevoGenero(Genero genero) {
		generoRepository.save(genero);
		return genero.getIdGenero();
	}

	@Override
	public Genero buscarGeneroPorId(Long idGenero) throws GeneroException, ExceptionSql {
		Optional<Genero> generoOptional = generoRepository.findById(idGenero);
		return generoOptional.get();
	}

	@Cacheable("generoPorNombre")
	@Override
	public List<Genero> buscarGeneroPorNombre(String nombre) {
		return generoRepository.buscarGeneroPorNombre(nombre);
	}
	
	@Override
	public void actualizarGenero(Genero genero) {
		generoRepository.save(genero);
	}

	/*@Override
	public void borrarGeneroPorId(Long id) {
		generoRepository.deleteById(id);
		
	}*/
}
