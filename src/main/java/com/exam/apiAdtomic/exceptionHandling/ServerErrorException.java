package com.exam.apiAdtomic.exceptionHandling;

public class ServerErrorException extends RuntimeException{

	private static final long serialVersionUID = 1L;

    public ServerErrorException() {
    }

    public ServerErrorException(String message) {
    	super(message);
    }
    
}
