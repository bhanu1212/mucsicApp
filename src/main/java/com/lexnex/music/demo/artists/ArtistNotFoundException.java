package com.lexnex.music.demo.artists;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * 
 * @author BHANU PRATAP SINGH
 *
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ArtistNotFoundException extends RuntimeException {
	public ArtistNotFoundException(String message) {
		super(message);
	}
}