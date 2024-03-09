package com.jacaranda.apiPalmaAlejandro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jacaranda.apiPalmaAlejandro.dto.UserDTO;
import com.jacaranda.apiPalmaAlejandro.service.UserService;

@RestController
@CrossOrigin(origins = "https://sneaker-land.vercel.app")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/users")
	public List<UserDTO> listUsers(){
		return userService.getAll();
	}

}
