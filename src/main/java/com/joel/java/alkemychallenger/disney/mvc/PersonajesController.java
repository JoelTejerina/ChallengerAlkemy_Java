package com.joel.java.alkemychallenger.disney.mvc;

import java.io.*;


import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.joel.java.alkemychallenger.disney.bo.Pelicula;
import com.joel.java.alkemychallenger.disney.bo.Personaje;
import com.joel.java.alkemychallenger.disney.exception.ExceptionSql;
import com.joel.java.alkemychallenger.disney.exception.PersonajeException;
import com.joel.java.alkemychallenger.disney.mvc.form.PersonajeForm;
import com.joel.java.alkemychallenger.disney.service.PersonajeService;

@Controller
@RequestMapping("/personajes")
public class PersonajesController {

	private static  Logger log = LoggerFactory.getLogger(PersonajesController.class);
	
	@Autowired
	private PersonajeService personajeService;
	
	@GetMapping("/{id}")
	public String ver(Model model, @PathVariable Long id) {
		Personaje personajes = new Personaje();
		try {
			personajes = personajeService.buscarPersonajePorId(id);
		} catch (ExceptionSql e) {
			log.error("Error en la base de datos al obtener un personaje", e.getMessage());
		} catch (PersonajeException e) {
			log.error("Error al obtener un personaje", e.getMessage());		}
		model.addAttribute("personajes", personajes);
		return "/personajes/ver";
	}

	@GetMapping
	public String listar(Model model) {
		List<Personaje> personajes = personajeService.recuperarPersonaje();
		model.addAttribute("personajes", personajes);
		return "/personajes/listar";
	}
	
	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		cargandoPeliculas(model);
		model.addAttribute("personajeForm", new PersonajeForm());
		return "/personajes/form";
	}
	
	@GetMapping("/{id}/editar")
	public String editar(Model model, @PathVariable Long id) {
		Personaje personaje = new Personaje();
		
		try {
			personaje = personajeService.buscarPersonajePorId(id);
		} catch (ExceptionSql e) {
			log.error("Error en la base de datos al editar un personaje", e.getMessage());
		} catch (PersonajeException e) {
			log.error("Error al editar un personaje", e.getMessage());
		}
		

		PersonajeForm personajeForm = new PersonajeForm();
		personajeForm.setIdPersonaje(personaje.getIdPersonaje());
		personajeForm.setNombre(personaje.getNombre());
		personajeForm.setEdad(personaje.getEdad());
		personajeForm.setPeso(personaje.getPeso());
		personajeForm.setHistoria(personaje.getHistoria());
		
		this.cargandoPeliculas(model);
		if(personaje.getPelicula() != null) {
			personajeForm.setPeliculaIdPelicula(personaje.getPelicula().getIdPelicula());
		}
		
		model.addAttribute("personajeForm", personajeForm);

		return "/personajes/form";
	}


	@PostMapping("/guardar")
	public String guardar(@Valid @ModelAttribute(name = "personajeForm") PersonajeForm personajeForm, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			this.cargandoPeliculas(model);
			model.addAttribute("personajeForm", personajeForm);
			
			return "/personajes/form";
		}
		
		Personaje personaje = null;
		Long idPersonaje = personajeForm.getIdPersonaje();
		if(idPersonaje == null) {
			personaje = new Personaje();
		}
		else {
			try {
				personaje = personajeService.buscarPersonajePorId(idPersonaje);
			} catch (ExceptionSql e) {
				log.error("Error en la base de datos al guardar un personaje", e.getMessage());
			} catch (PersonajeException e) {
				log.error("Error guardar un personaje", e.getMessage());
			}
		}
		personaje.setNombre(personajeForm.getNombre());
		personaje.setEdad(personajeForm.getEdad());
		personaje.setPeso(personajeForm.getPeso());
		personaje.setHistoria(personajeForm.getHistoria());	
		try {
			personajeService.guardarNuevoPersonaje(personaje, personajeForm.getPeliculaIdPelicula());
		} catch (ExceptionSql e) {
			log.error("Error en la base de datos al guardar un personaje", e.getMessage());
		} catch (PersonajeException e) {
			log.error("Error guardar un personaje", e.getMessage());
		}
		
		System.out.println(personajeForm.getImagen().getOriginalFilename() + " " + personajeForm.getImagen().getSize());

		
		File archivoImagen = new File("C:/Users/Joel/imagen/foto-" + personaje.getIdPersonaje() + ".jpg");

		try(FileOutputStream out = new FileOutputStream(archivoImagen)) {
			out.write(personajeForm.getImagen().getBytes());
			
		} catch (FileNotFoundException e) {
			log.error("Archivo no encontrado", e);
		} catch (IOException e) {
			log.error("Error al guardar el archivo", e);
		}

		return "redirect:/personajes";
	}
	
	
	@GetMapping(value = "/recuperar-imagen/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] recuperarFotoProducto(@PathVariable Long id) {
		
		Personaje personaje = new Personaje();
		try {
			personaje = personajeService.buscarPersonajePorId(id);
		} catch (ExceptionSql e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (PersonajeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if(personaje != null) {
			log.info("estoy aca y es nulo");
			File archivoImagen = new File("C:/Users/Joel/imagen/foto-" + personaje.getIdPersonaje() + ".jpg");
			if(archivoImagen.exists()) {
				log.info("El archivo existe");
				try(FileInputStream in = new FileInputStream(archivoImagen)) {
					return in.readAllBytes();					
				} catch (FileNotFoundException e) {
					log.error("Archivo no encontrado", e);
				} catch (IOException e) {
					log.error("Error al leer el archivo", e);
				}
			}
		}
		return null;
	}

	
	@GetMapping("/{id}/borrar")
	public String borrar(Model model, @PathVariable Long id) {
		personajeService.borrarPersonajePorId(id);
		return "redirect:/personajes";
	}
	
	private void cargandoPeliculas(Model model) {
		List<Pelicula> peliculas = personajeService.recuperarPeliculaPersonaje();
		model.addAttribute("peliculas", peliculas);
	}
} 