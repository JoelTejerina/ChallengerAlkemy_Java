package com.joel.java.alkemychallenger.disney.service;

import java.util.*;
import java.util.stream.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joel.java.alkemychallenger.disney.bo.Rol;
import com.joel.java.alkemychallenger.disney.bo.Usuario;
import com.joel.java.alkemychallenger.disney.repository.UsuarioRepository;

@Service
@Transactional(rollbackFor = Exception.class)
public class CustomUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String usernameOEmail) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByUsernameOrEmail(usernameOEmail, usernameOEmail)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con ese username o email: " + usernameOEmail));
		
		return new User(usuario.getEmail(), usuario.getPassword(), mapearRoles(usuario.getRoles()));
	}
	
	private Collection<? extends GrantedAuthority> mapearRoles(Set<Rol> set){
		return set.stream().map(rol -> new SimpleGrantedAuthority(rol.getNombre())).collect(Collectors.toList());
	}
}