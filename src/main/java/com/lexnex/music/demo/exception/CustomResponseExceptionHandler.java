package com.lexnex.music.demo.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import com.lexnex.music.demo.artists.ArtistNotFoundException;

public class CustomResponseExceptionHandler {

	@ExceptionHandler(ArtistNotFoundException.class)
	public final ResponseEntity<Object> handleArtistNotFoundException(ArtistNotFoundException ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
}
