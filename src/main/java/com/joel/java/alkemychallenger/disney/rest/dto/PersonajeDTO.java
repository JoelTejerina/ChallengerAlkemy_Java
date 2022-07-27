package com.joel.java.alkemychallenger.disney.rest.dto;

import java.util.Date;

import javax.validation.constraints.*;

import org.springframework.web.multipart.MultipartFile;

import com.joel.java.alkemychallenger.disney.bo.Personaje;

public class PersonajeDTO {

	private Long idPersonaje;
	private String imagen;
	
	@NotEmpty
	@NotBlank
	@Size(max = 100, message
	= "El maximo de caracteres perimitidos para nombre es de 100")
	private String nombre;
	
	@NotNull
	@Positive
	@Max(value = 1000000, message
		= "La edad maxima es de 1.000.000")
	private Long edad;
	
	@NotNull
	@Positive
	@Max(value = 700, message
			= "El peso maximo permitido es hasta 700kg")
	private Long peso;
	
	@NotEmpty
	@NotBlank
	@Size(min = 5, max = 2000, message
	= "Historia se permite entre 10 y 2000 caracteres")
	private	String historia;
	
	//Pelicula
	private Long peliculaIdPelicula;
	private String peliculaImagen;
	private String peliculaTitulo;
	private Date peliculaFechaDeCreacion;
	private Long peliculaCalificacion;
	
	public PersonajeDTO() {}

	public PersonajeDTO(Personaje personaje) {
		super();
		this.idPersonaje = personaje.getIdPersonaje();
		this.imagen = personaje.getImagen();
		this.nombre = personaje.getNombre();
		this.edad = personaje.getEdad();
		this.peso = personaje.getPeso();
		this.historia = personaje.getHistoria();
		if(personaje.getPelicula() != null) {
			this.peliculaIdPelicula = personaje.getPelicula().getIdPelicula();
			this.peliculaImagen = personaje.getPelicula().getImagen();
			this.peliculaTitulo = personaje.getPelicula().getTitulo();
			this.peliculaFechaDeCreacion = personaje.getPelicula().getFechaDeCreacion();
			this.peliculaCalificacion = personaje.getPelicula().getCalificacion();
		}
	}
	
	public Long getIdPersonaje() {
		return idPersonaje;
	}
	public void setIdPersonaje(Long idPersonaje) {
		this.idPersonaje = idPersonaje;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Long getEdad() {
		return edad;
	}
	public void setEdad(Long edad) {
		this.edad = edad;
	}
	public Long getPeso() {
		return peso;
	}
	public void setPeso(Long peso) {
		this.peso = peso;
	}
	public String getHistoria() {
		return historia;
	}
	public void setHistoria(String historia) {
		this.historia = historia;
	}
	public Long getPeliculaIdPelicula() {
		return peliculaIdPelicula;
	}
	public void setPeliculaIdPelicula(Long peliculaIdPelicula) {
		this.peliculaIdPelicula = peliculaIdPelicula;
	}
	public String getPeliculaImagen() {
		return peliculaImagen;
	}
	public void setPeliculaImagen(String peliculaImagen) {
		this.peliculaImagen = peliculaImagen;
	}
	public String getPeliculaTitulo() {
		return peliculaTitulo;
	}
	public void setPeliculaTitulo(String peliculaTitulo) {
		this.peliculaTitulo = peliculaTitulo;
	}
	public Date getPeliculaFechaDeCreacion() {
		return peliculaFechaDeCreacion;
	}
	public void setPeliculaFechaDeCreacion(Date peliculaFechaDeCreacion) {
		this.peliculaFechaDeCreacion = peliculaFechaDeCreacion;
	}
	public Long getPeliculaCalificacion() {
		return peliculaCalificacion;
	}
	public void setPeliculaCalificacion(Long peliculaCalificacion) {
		this.peliculaCalificacion = peliculaCalificacion;
	}
}
