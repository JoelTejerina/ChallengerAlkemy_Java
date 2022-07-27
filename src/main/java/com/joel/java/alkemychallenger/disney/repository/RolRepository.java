package com.joel.java.alkemychallenger.disney.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joel.java.alkemychallenger.disney.bo.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long>{

	public Optional<Rol> findByNombre(String nombre);
}
