package com.exam.apiAdtomic.exceptionHandling;

public class ClientErrorException extends RuntimeException{

	private static final long serialVersionUID = 1L;

    public ClientErrorException() {
    }

    public ClientErrorException(String message) {
    	super(message);
    }
    
}
