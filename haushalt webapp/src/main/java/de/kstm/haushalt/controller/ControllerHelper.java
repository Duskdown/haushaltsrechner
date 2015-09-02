package de.kstm.haushalt.controller;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class ControllerHelper {
	public static HttpHeaders buildHttpHeaderForNewResource(String pathVariable, Object... pathValue) {
		HttpHeaders httpHeaders = null;
		try {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest()
					.path(pathVariable).buildAndExpand(pathValue).toUri();
			httpHeaders = new HttpHeaders();
			httpHeaders.setLocation(location);
		} catch (IllegalStateException e) {
			//TODO: Logging
		}
		
		return httpHeaders;
	}
}
