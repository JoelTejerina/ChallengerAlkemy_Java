package com.joel.java.alkemychallenger.disney.mvc;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.joel.java.alkemychallenger.disney.bo.Genero;
import com.joel.java.alkemychallenger.disney.bo.Pelicula;
import com.joel.java.alkemychallenger.disney.exception.ExceptionSql;
import com.joel.java.alkemychallenger.disney.exception.PeliculaException;
import com.joel.java.alkemychallenger.disney.exception.PersonajeException;
import com.joel.java.alkemychallenger.disney.mvc.form.PeliculaForm;
import com.joel.java.alkemychallenger.disney.service.PeliculaService;
import com.joel.java.alkemychallenger.disney.service.PersonajeService;

@Controller
@RequestMapping("/peliculas")
public class PeliculaController {

	private static  Logger log = LoggerFactory.getLogger(PersonajesController.class);

	@Autowired
	private PeliculaService peliculaService;
	

	@GetMapping("/{id}")
	public String ver(Model model, @PathVariable Long id) {
		Pelicula peliculas = new Pelicula();
			try {
				peliculas = peliculaService.buscarPeliculaPorId(id);
			} catch (ExceptionSql e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (PeliculaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		model.addAttribute("peliculas", peliculas);
		return "/peliculas/ver";
	}

	@GetMapping
	public String listar(Model model) {
		List<Pelicula> peliculas = peliculaService.recuperarPelicula();
		model.addAttribute("peliculas", peliculas);
		return "/peliculas/listar";
	}

	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		cargandoGeneros(model);
		model.addAttribute("peliculaForm", new PeliculaForm());
		return "/peliculas/form";
	}

	@GetMapping("/{id}/editar")
	public String editar(Model model, @PathVariable Long id) {
		Pelicula pelicula = new Pelicula();

			try {
				pelicula = peliculaService.buscarPeliculaPorId(id);
			} catch (ExceptionSql e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (PeliculaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		PeliculaForm peliculaForm = new PeliculaForm();
		 peliculaForm.setIdPelicula(pelicula.getIdPelicula());
		 peliculaForm.setImagen(pelicula.getImagen());
		 peliculaForm.setTitulo(pelicula.getTitulo());
		 peliculaForm.setFechaDeCreacion(pelicula.getFechaDeCreacion());
		 peliculaForm.setCalificacion(pelicula.getCalificacion());

		this.cargandoGeneros(model);
		if(pelicula.getGenero() != null) {
			peliculaForm.setGeneroIdGenero(pelicula.getGenero().getIdGenero());
		}

		model.addAttribute("peliculaForm", peliculaForm);

		return "/peliculas/form";
	}


	@PostMapping("/guardar")
	public String guardar(@Valid @ModelAttribute(name = "peliculaForm") PeliculaForm peliculaForm, BindingResult bindingResult, Model model) {

		if(bindingResult.hasErrors()) {
			this.cargandoGeneros(model);
			model.addAttribute("peliculaForm", peliculaForm);

			return "/peliculas/form";
		}

		Pelicula pelicula = null;
		Long idPelicula = peliculaForm.getIdPelicula();
		if(idPelicula == null) {
			pelicula = new Pelicula();
		}
		else {
			try {
				pelicula = peliculaService.buscarPeliculaPorId(idPelicula);
			} catch (ExceptionSql e) {
				e.printStackTrace();
			} catch (PeliculaException e) {
				e.printStackTrace();
			}
		}

		pelicula.setImagen(peliculaForm.getImagen());
		pelicula.setTitulo(peliculaForm.getTitulo());
		pelicula.setFechaDeCreacion(peliculaForm.getFechaDeCreacion());
		pelicula.setCalificacion(peliculaForm.getCalificacion());
		try {
			peliculaService.guardarNuevaPelicula(pelicula, peliculaForm.getGeneroIdGenero());
		} catch (ExceptionSql e) {
			e.printStackTrace();
		} catch (PeliculaException e) {
			e.printStackTrace();
		}
		return "redirect:/peliculas";
	}


	@GetMapping("/{id}/borrar")
	public String borrar(Model model, @PathVariable Long id) {
		peliculaService.borrarPeliculaPorId(id);
		return "redirect:/peliculas";
	}

	private void cargandoGeneros(Model model) {
		List<Genero> generos = peliculaService.recuperarPeliculaGenero();
		model.addAttribute("generos", generos);
	}
}  