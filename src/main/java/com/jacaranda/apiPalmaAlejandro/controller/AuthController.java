package com.jacaranda.apiPalmaAlejandro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jacaranda.apiPalmaAlejandro.dto.LoginCredential;
import com.jacaranda.apiPalmaAlejandro.dto.UserDTO;
import com.jacaranda.apiPalmaAlejandro.exception.ExceptionCredentialNotValid;
import com.jacaranda.apiPalmaAlejandro.exception.ExceptionDuplicateEmail;
import com.jacaranda.apiPalmaAlejandro.model.User;
import com.jacaranda.apiPalmaAlejandro.service.UserService;
import com.jacaranda.apiPalmaAlejandro.utility.TokenUtils;

@RestController
@CrossOrigin(origins = "https://sneaker-land.vercel.app")
public class AuthController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
//	@PostMapping("/login")
//	public String login(UserDTO userDTO) {
//		
//		
//	}
	
	@PostMapping("/auth/register")
	public ResponseEntity<?> register(@RequestBody User user, BindingResult bindingResult) {
		user.setRole("user");
		User newUserDTO = userService.addUser(user);
		
		if(newUserDTO==null) {
			throw new ExceptionDuplicateEmail("Email duplicado");
		}else {
    		return ResponseEntity.status(201).body(newUserDTO);
    	}
	}

	@PostMapping("/auth/login")
	public ResponseEntity<?> login(@RequestBody LoginCredential loginRequest) {
		Authentication authentication;
		try {
			authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
	
		} catch (Exception e) {
			throw new ExceptionCredentialNotValid(e.getMessage());
		}
		
		User user = (User)authentication.getPrincipal();
		String jwt = TokenUtils.generateToken(loginRequest.getUsername(), user.getEmail(), user.getRole());

		return ResponseEntity.ok(jwt);
		
	}

}
