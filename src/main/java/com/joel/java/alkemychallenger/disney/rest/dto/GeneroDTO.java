package com.joel.java.alkemychallenger.disney.rest.dto;

import javax.validation.constraints.*;

import com.joel.java.alkemychallenger.disney.bo.Genero;

public class GeneroDTO {
	
	private Long idGenero;
	
	@NotEmpty
	@NotBlank
	@Size(max = 15, message
	= "El maximo de caracteres perimitidos para nombre es de 15")
	private String nombre;
	private String imagen;

	public GeneroDTO() {}

	public GeneroDTO(Genero genero) {
		super();
		this.idGenero = genero.getIdGenero();
		this.nombre = genero.getNombre();
		this.imagen = genero.getImagen();
	}

	public Long getIdGenero() {
		return idGenero;
	}

	public void setIdGenero(Long idGenero) {
		this.idGenero = idGenero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
}
