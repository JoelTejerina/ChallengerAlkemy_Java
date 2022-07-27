package com.joel.java.alkemychallenger.disney.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class InicioController {
	
	@GetMapping("/inicio")
	public String login(Model model) {
		return "/iniciopagina/inicio";
	}
}
