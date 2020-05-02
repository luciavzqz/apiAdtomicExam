package com.exam.apiAdtomic.utils;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

public class JsonResponse {

	private Timestamp timestamp = Fecha.getCurrentTimestamp();
	private int status;
	private String title = null;
	private String message = null;
	private Map<String, Object> data = new HashMap<>();
	
	public JsonResponse() {
	}
	
	public JsonResponse(String title, String message) {
		super();
		this.title = title;
		this.message = message;
		this.status = HttpStatus.OK.value();
	}
	
	public JsonResponse(String title, int status, String message) {
		super();
		this.title = title;
		this.status = status;
		this.message = message;
	}
	
	public JsonResponse(Map<String, Object> data) {
		super();
		this.data.putAll(data);
	}
	
	public JsonResponse(String message, Map<String, Object> data) {
		super();
		this.message = message;
		this.data.putAll(data);
	}
	
	public JsonResponse(String title, String message, Map<String, Object> data) {
		super();
		this.title = title;
		this.message = message;
		this.data.putAll(data);
	}
	
	public JsonResponse(String title, int status, String message, Map<String, Object> data) {
		super();
		this.title = title;
		this.status = status;
		this.message = message;
		this.data.putAll(data);
	}
	

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public JsonResponse setMessage(String message) {
		this.message = message;
		return this;
	}

	public int getStatus() {
		return status;
	}

	public JsonResponse setStatus(int status) {
		this.status = status;
		return this;
	}
	
	public JsonResponse setDeaultSuccess() {
		this.status = HttpStatus.OK.value();
		this.title = "Petición exitosa";
		return this;
	}
	
	public JsonResponse setSuccess() {
		this.status = HttpStatus.OK.value();
		return this;
	}
}
