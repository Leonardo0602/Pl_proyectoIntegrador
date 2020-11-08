package com.facaaprende.PI.exception;

public class NotFoundModelException  extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public NotFoundModelException(String mensaje) {		
		super(mensaje);
	}

}
