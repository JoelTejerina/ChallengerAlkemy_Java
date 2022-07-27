package com.joel.java.alkemychallenger.disney.mvc.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class PersonajeForm {
	
	private Long idPersonaje;
	private MultipartFile imagen;
	
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
	
	private Long peliculaIdPelicula;

	public Long getIdPersonaje() {
		return idPersonaje;
	}

	public void setIdPersonaje(Long idPersonaje) {
		this.idPersonaje = idPersonaje;
	}

	public MultipartFile getImagen() {
		return imagen;
	}

	public void setImagen(MultipartFile imagen) {
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
}