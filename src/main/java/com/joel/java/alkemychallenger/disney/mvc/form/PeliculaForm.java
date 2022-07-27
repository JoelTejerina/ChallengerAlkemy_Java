package com.joel.java.alkemychallenger.disney.mvc.form;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class PeliculaForm {
	
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

	public Long generoIdGenero;

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
}
