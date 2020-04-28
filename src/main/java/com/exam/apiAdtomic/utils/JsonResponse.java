package com.exam.apiAdtomic.utils;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class JsonResponse {
	
	private String title;
	private HttpStatus status;
	private String detail;
	private Date timestamp;
	
	public JsonResponse(String title, HttpStatus status, String detail, Date timestamp) {
		super();
		this.title = title;
		this.status = status;
		this.detail = detail;
		this.timestamp = timestamp;
	}
	
	
}
