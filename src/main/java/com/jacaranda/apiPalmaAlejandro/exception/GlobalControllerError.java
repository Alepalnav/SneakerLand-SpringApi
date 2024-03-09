package com.jacaranda.apiPalmaAlejandro.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerError {

	@ExceptionHandler(ExceptionPageNotFound.class)
	public ResponseEntity<ApiError> handleExceptionPageNotFound(ExceptionPageNotFound e){
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
		
	}
	
	@ExceptionHandler(ExceptionValueNotRight.class)
	public ResponseEntity<ApiError> handleExceptionValueNotRight(ExceptionValueNotRight e){
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
		
	}
	
	@ExceptionHandler(ExceptionCredentialNotValid.class)
	public ResponseEntity<ApiError> handleExceptionCredentialNotValid(ExceptionCredentialNotValid e){
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
		
	}

	@ExceptionHandler(ExceptionTokenNotValid.class)
	public ResponseEntity<ApiError> handleExceptionTokenNotValid(ExceptionTokenNotValid e){
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
		
	}
	
	@ExceptionHandler(ElementNotFoundException.class)
	public ResponseEntity<ApiError> handleElementNotFoundException(ElementNotFoundException e){
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
		
	}
	
	@ExceptionHandler(ExceptionDuplicateEmail.class)
	public ResponseEntity<ApiError> handleExceptionDuplicateEmail(ExceptionDuplicateEmail e){
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
		
	}
	
	
}
