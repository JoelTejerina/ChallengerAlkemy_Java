package com.joel.java.alkemychallenger.disney.rest;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.joel.java.alkemychallenger.disney.bo.Rol;
import com.joel.java.alkemychallenger.disney.bo.Usuario;
import com.joel.java.alkemychallenger.disney.repository.RolRepository;
import com.joel.java.alkemychallenger.disney.repository.UsuarioRepository;
import com.joel.java.alkemychallenger.disney.rest.dto.LoginDTO;
import com.joel.java.alkemychallenger.disney.rest.dto.RegistroDTO;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UsuarioRepository usuarioRespository;
	
	@Autowired
	private RolRepository rolRepositorio;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@PostMapping("/iniciarsesion")
	public ResponseEntity<String> autenticarUsuario(@RequestBody LoginDTO loginDTO){
		Authentication autenticacion = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(autenticacion);
		return new ResponseEntity<>("Ha iniciado sesion con exito", HttpStatus.OK);
	}
	
	@PostMapping("/registrar")
	public ResponseEntity<?> registrarUsuario(@RequestBody RegistroDTO registroDTO){
		if(usuarioRespository.existsByUsername(registroDTO.getUsername())) {
			return new ResponseEntity<>("Ese nombre de usuario ya existe",HttpStatus.BAD_REQUEST);
		}
		
		if(usuarioRespository.existsByEmail(registroDTO.getEmail())) {
			return new ResponseEntity<>("Ese email de usuario ya existe",HttpStatus.BAD_REQUEST);
		}
		
		Usuario usuario = new Usuario();
		usuario.setNombre(registroDTO.getNombre());
		usuario.setUsername(registroDTO.getUsername());
		usuario.setEmail(registroDTO.getEmail());
		usuario.setPassword(passwordEncoder.encode(registroDTO.getPassword()));
		
		Rol roles = rolRepositorio.findByNombre("ROLE_ADMIN").get();
		usuario.setRoles(Collections.singleton(roles));
		
		usuarioRespository.save(usuario);
		return new ResponseEntity<>("Usuario registrado exitosamente",HttpStatus.OK);
	}
	
	
}
