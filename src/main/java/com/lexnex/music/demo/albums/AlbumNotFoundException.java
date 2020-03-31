package com.lexnex.music.demo.albums;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author BHANU PRATAP SINGH
 *
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AlbumNotFoundException extends RuntimeException {
	public AlbumNotFoundException(String message) {
		super(message);
	}
}