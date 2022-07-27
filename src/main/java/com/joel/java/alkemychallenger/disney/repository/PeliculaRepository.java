package com.joel.java.alkemychallenger.disney.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.joel.java.alkemychallenger.disney.bo.Pelicula;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long>{
	 
	@Query(value = "SELECT * FROM PELICULAS WHERE TITULO = ?", nativeQuery  = true)
	List<Pelicula> buscarPeliculaPorNombre(String nombre);
	
	@Query(value = "SELECT * FROM PELICULAS WHERE GENERO_ID = ?", nativeQuery  = true)
	List<Pelicula> buscarPeliculaPorIdGenero(Long id);
	
	@Query(value = "SELECT * FROM PELICULAS ORDER BY TITULO", nativeQuery  = true)
	List<Pelicula> recuperarTodasLasPeliculasPersonajes();
}
