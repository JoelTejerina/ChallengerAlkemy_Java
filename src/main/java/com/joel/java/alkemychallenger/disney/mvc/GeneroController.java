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
import com.joel.java.alkemychallenger.disney.exception.ExceptionSql;
import com.joel.java.alkemychallenger.disney.exception.GeneroException;
import com.joel.java.alkemychallenger.disney.mvc.form.GeneroForm;
import com.joel.java.alkemychallenger.disney.service.GeneroService;

@Controller
@RequestMapping("/generos")
public class GeneroController {

	private static  Logger log = LoggerFactory.getLogger(PersonajesController.class);

	@Autowired
	private GeneroService generoService;
	
	@GetMapping("/{id}")
	public String ver(Model model, @PathVariable Long id) {
		Genero generos = new Genero();
		try {
			generos = generoService.buscarGeneroPorId(id);
		} catch (ExceptionSql e) {
			log.error("Error en la base de datos al obtener un genero", e.getMessage());
		} catch (GeneroException e) {
			log.error("Error al obtener un genero", e.getMessage());
		} 
		model.addAttribute("generos", generos);
		return "/generos/ver";
	}

	@GetMapping
	public String listar(Model model) {
		List<Genero> generos = generoService.recuperarGeneros();
		model.addAttribute("generos", generos);
		return "/generos/listar";
	}
	
	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		model.addAttribute("generoForm", new GeneroForm());
		return "/generos/form";
	}
	
	@GetMapping("/{id}/editar")
	public String editar(Model model, @PathVariable Long id) {
		Genero genero = new Genero();
		
		try {
			genero = generoService.buscarGeneroPorId(id);
		} catch (ExceptionSql e) {
			e.printStackTrace();
		} catch (GeneroException e) {
			e.printStackTrace();
		} 

		GeneroForm generoForm = new GeneroForm();
		generoForm.setIdGenero(genero.getIdGenero());
		generoForm.setImagen(genero.getImagen());
		generoForm.setNombre(genero.getNombre());
		
		model.addAttribute("generoForm", generoForm);
		
		return "/generos/form";
	}


	@PostMapping("/guardar")
	public String guardar(@Valid @ModelAttribute(name = "generoForm") GeneroForm generoForm, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			log.info("estoy aca   " + bindingResult );
			model.addAttribute("generoForm", generoForm);
			return "/generos/form";
		}
		
		Genero genero = new Genero();
		Long idGenero = generoForm.getIdGenero();
		if(idGenero == null) {
			genero = new Genero();
		}
		else {
			try {
				genero = generoService.buscarGeneroPorId(idGenero);
			} catch (ExceptionSql e) {
				log.error("Error en la base de datos al guardar un genero", e.getMessage());
			} catch (GeneroException e) {
				log.error("Error al guardar un genero", e.getMessage());
			}
		}
		
		genero.setImagen(generoForm.getImagen());
		genero.setNombre(generoForm.getNombre());
		generoService.guardarNuevoGenero(genero);
		return "redirect:/generos";
	}
} 