package com.joel.java.alkemychallenger.disney.bo;

import javax.persistence.*;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="PERSONAJES")
public class Personaje {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPersonaje;
	private String imagen;
	private String nombre;
	private Long edad;
	private Long peso;
	private	String historia;
	
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pelicula_id")
	private Pelicula pelicula;
	
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

	public Pelicula getPelicula() {
		return pelicula;
	}
	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}
}
