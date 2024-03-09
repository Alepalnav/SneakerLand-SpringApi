package com.jacaranda.apiPalmaAlejandro.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jacaranda.apiPalmaAlejandro.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	AuthEntryPoint authEntryPoint;
	
	@Autowired
	RequestFilter requestFilter;
	
    @Bean
    UserService userDetailsService() {
		return new UserService();
	}
    
	// Método que nos suministrará la codificación
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Método que autentifica
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	 @Bean
	 AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
	    return authConfig.getAuthenticationManager();
	  }
	
	
	// Aquí es donde podemos especificar qué es lo que hace y lo que no
	// según el rol del usuario 
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
			.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        .exceptionHandling((exceptionHandling) -> exceptionHandling.authenticationEntryPoint(authEntryPoint))
	        .authorizeHttpRequests((requests) -> {
			requests
				.requestMatchers("/signin").permitAll()
				.requestMatchers("/auth/**").permitAll()
				.requestMatchers("/error").permitAll()
				.requestMatchers("/products/**").permitAll()
				.requestMatchers("/product/**").permitAll()
				.requestMatchers("/users").permitAll()
				.requestMatchers("/swagger-ui/**").permitAll()
				.requestMatchers("/v3/api-docs/**").permitAll()
				.anyRequest().denyAll();
	        })
	        .formLogin((form) -> form.permitAll())
	        .logout((logout) -> logout.permitAll().logoutSuccessUrl("/"));
		
        http.addFilterBefore(requestFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}
