package com.joel.java.alkemychallenger.disney.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.joel.java.alkemychallenger.disney.bo.Genero;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long> {

	@Query(value = "SELECT * FROM GENEROS ORDER BY NOMBRE", nativeQuery  = true)
	List<Genero> recuperarTodasLosGeneroPeliculas();
	
	@Query(value = "SELECT * FROM GENEROS WHERE NOMBRE = ?", nativeQuery  = true)
	List<Genero> buscarGeneroPorNombre(String nombre);

}
