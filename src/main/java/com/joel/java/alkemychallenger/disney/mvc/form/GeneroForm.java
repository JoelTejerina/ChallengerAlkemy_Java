package com.joel.java.alkemychallenger.disney.mvc.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.joel.java.alkemychallenger.disney.bo.Genero;

public class GeneroForm {

	public Long idGenero;

	@NotEmpty
	@NotBlank
	@Size(max = 15, message
	= "El maximo de caracteres perimitidos para nombre es de 15")
	public String nombre;
	public String imagen;

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
