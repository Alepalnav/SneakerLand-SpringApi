package com.jacaranda.apiPalmaAlejandro.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ElementNotFoundException extends RuntimeException {
	
	public ElementNotFoundException(Integer id) {
		super("No se puede encontrar el elemento con id=" + id);
		}

}
