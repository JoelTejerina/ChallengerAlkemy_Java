package com.joel.java.alkemychallenger.disney.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.joel.java.alkemychallenger.disney.service.CustomUserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
		
	@Autowired
	private CustomUserDetailsServiceImpl userDetailsService;
	
	@Bean
	public UserDetailsService jdbcUserDetailsService(DataSource dataSource) {

	  JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
	  
	  return users;
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
		.antMatchers(HttpMethod.GET, "/api/**").authenticated()
		.antMatchers("/personajes/**").authenticated()
		.antMatchers("/peliculas/**").authenticated()
		.antMatchers("/generos/**").authenticated()
		.antMatchers("/**").permitAll()
		.antMatchers("/api/auth/**").permitAll().and()
		.formLogin(form -> {
			form.loginPage("/login");
			form.loginProcessingUrl("/validarusuario");
			form.failureUrl("/login?error=true");
			form.defaultSuccessUrl("/inicio", true);
		}).httpBasic().and().csrf().disable()
		.logout().logoutUrl("/logout").logoutSuccessUrl("/login")
		.invalidateHttpSession(true)
		.deleteCookies("JSESSIONID");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
	}

}
