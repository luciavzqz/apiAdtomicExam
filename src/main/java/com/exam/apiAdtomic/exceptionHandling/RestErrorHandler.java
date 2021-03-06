package com.exam.apiAdtomic.exceptionHandling;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.exam.apiAdtomic.utils.JsonResponse;


@RestControllerAdvice
public class RestErrorHandler {
	
		@ExceptionHandler(ClientErrorException.class)
		@ResponseStatus(HttpStatus.BAD_REQUEST)
	    public JsonResponse  processValidationError(ClientErrorException ex) {
			JsonResponse response = new JsonResponse("Petición rechazada", HttpStatus.BAD_REQUEST.value(), ex.getMessage());
	        return response;
	    }
		
		@ExceptionHandler(ServerErrorException.class)
		@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	    public JsonResponse  processValidationError(ServerErrorException ex) {
			JsonResponse response = new JsonResponse("Petición rechazada", HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
	        return response;
	    }
		 
		@ExceptionHandler(IOException.class)
		@ResponseStatus(HttpStatus.BAD_REQUEST)
	    public JsonResponse  processValidationError(IOException ex) {
			JsonResponse response = new JsonResponse("Petición rechazada", HttpStatus.BAD_REQUEST.value(), ex.getMessage());
	        return response;
	    }
		
		@ExceptionHandler(HttpMessageNotReadableException.class)
		@ResponseStatus(HttpStatus.BAD_REQUEST)
	    public JsonResponse  processValidationError(HttpMessageNotReadableException ex) {
			JsonResponse response = new JsonResponse("Petición rechazada", HttpStatus.BAD_REQUEST.value(), ex.getMessage());
	        return response;
	    }
}
