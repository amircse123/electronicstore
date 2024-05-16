package com.electronicstore.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.electronicstore.exceptions.BadApiRequest;
import com.electronicstore.exceptions.ResourceNotFoundException;
import com.electronicstore.utility.ApiResponseMessage;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponseMessage> getException(ResourceNotFoundException ex) {

		ApiResponseMessage resp = ApiResponseMessage.builder().message(ex.getMessage()).status(HttpStatus.NOT_FOUND)
				.success(false).build();

		return new ResponseEntity<ApiResponseMessage>(resp, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BadApiRequest.class)
	public ResponseEntity<ApiResponseMessage> getBadApiRequestException(BadApiRequest ex) {

		ApiResponseMessage resp = ApiResponseMessage.builder().message(ex.getMessage()).status(HttpStatus.NOT_FOUND)
				.success(false).build();

		return new ResponseEntity<ApiResponseMessage>(resp, HttpStatus.NOT_FOUND);
	}
}
