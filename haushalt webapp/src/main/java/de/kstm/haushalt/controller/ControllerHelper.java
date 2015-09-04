package de.kstm.haushalt.controller;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class ControllerHelper {
	public static HttpHeaders buildHttpHeaderForNewResource(String pathVariable, Object... pathValues) {
		HttpHeaders httpHeaders = null;
		try {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest()
					.path(pathVariable).buildAndExpand(pathVariable).toUri();
			httpHeaders = new HttpHeaders();
			httpHeaders.setLocation(location);
		} catch (IllegalStateException e) {
			//TODO: Logging
		}
		
		return httpHeaders;
	}
	
	public static <T> ResponseEntity<T> getNewlyCreatedRequestEntity(T object, String pathVariable, Object... pathValues) {
		HttpHeaders httpHeaders = buildHttpHeaderForNewResource(pathVariable, pathValues);
	
		return new ResponseEntity<T>(object, httpHeaders, HttpStatus.CREATED);
	}
}
