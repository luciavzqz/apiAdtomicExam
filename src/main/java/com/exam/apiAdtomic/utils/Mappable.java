package com.exam.apiAdtomic.utils;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public interface Mappable {

	@SuppressWarnings("unchecked")
	default Map<String, Object> toMap(){
		ObjectMapper objectMapper = new ObjectMapper();
		return ( Map<String, Object> ) objectMapper.convertValue(this, Map.class);
	}
}
