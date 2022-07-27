package com.joel.java.alkemychallenger.disney.rest.dto;

import java.util.Date;

import javax.validation.constraints.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.joel.java.alkemychallenger.disney.bo.Pelicula;

public class PeliculaDTO {

	private Long idPelicula;
	private String imagen;
	
	@NotBlank
	@NotEmpty
	@Size(min = 1, max = 30, message
	= "Titulo se permite entre 1 y 30 caracteres")
	private String titulo;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date fechaDeCreacion;
	
	@NotNull
	@Range(min = 0,max = 5, message
			= "Valor permitido en calificaciones entre 0 y 5")
	private Long calificacion;
	
	//Genero
	private Long generoIdGenero;
	private String generoNombre;
	private String generoImagen;

	public PeliculaDTO() {}

	public PeliculaDTO(Pelicula pelicula) {
		super();
		this.idPelicula = pelicula.getIdPelicula();
		this.imagen = pelicula.getImagen();
		this.titulo = pelicula.getTitulo();
		this.fechaDeCreacion = pelicula.getFechaDeCreacion();
		this.calificacion = pelicula.getCalificacion();
		if(pelicula.getGenero() != null) {
			this.generoIdGenero = pelicula.getGenero().getIdGenero();
			this.generoNombre = pelicula.getGenero().getNombre();
			this.generoImagen = pelicula.getGenero().getImagen();
		}
	}

	public Long getIdPelicula() {
		return idPelicula;
	}

	public void setIdPelicula(Long idPelicula) {
		this.idPelicula = idPelicula;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getFechaDeCreacion() {
		return fechaDeCreacion;
	}

	public void setFechaDeCreacion(Date fechaDeCreacion) {
		this.fechaDeCreacion = fechaDeCreacion;
	}

	public Long getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(Long calificacion) {
		this.calificacion = calificacion;
	}

	public Long getGeneroIdGenero() {
		return generoIdGenero;
	}

	public void setGeneroIdGenero(Long generoIdGenero) {
		this.generoIdGenero = generoIdGenero;
	}

	public String getGeneroNombre() {
		return generoNombre;
	}

	public void setGeneroNombre(String generoNombre) {
		this.generoNombre = generoNombre;
	}

	public String getGeneroImagen() {
		return generoImagen;
	}

	public void setGeneroImagen(String generoImagen) {
		this.generoImagen = generoImagen;
	}
}