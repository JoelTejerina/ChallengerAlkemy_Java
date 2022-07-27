package com.joel.java.alkemychallenger.disney.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.joel.java.alkemychallenger.disney.bo.Personaje;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, Long>{
	
	@Query("select p from Personaje as p where p.nombre like ?1%")
	List<Personaje> buscarPersonajePorNombre(String nombre);
	
	@Query("select p from Personaje as p where p.edad = ?1")
	List<Personaje> buscarPersonajePorEdad(Long edad);
	
	@Query(value = "SELECT * FROM PERSONAJES WHERE PELICULA_ID = ?", nativeQuery  = true)
	List<Personaje> buscarPersonajePorPelicula(Long idPelicula);
}
