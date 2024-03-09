package com.jacaranda.apiPalmaAlejandro.exception;

public class ExceptionDuplicateEmail extends RuntimeException {
	
	private static final long serialVersionUID = -409743470779314218L;
	
	public ExceptionDuplicateEmail(String  exception) {
		super(exception);
	}

}
