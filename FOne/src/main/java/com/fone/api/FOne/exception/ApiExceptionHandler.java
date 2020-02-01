package com.fone.api.FOne.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = {ApiRequestException.class})
	public ResponseEntity<Object> handleApiException(ApiRequestException exception) {
		ApiException apiException;
		HttpStatus status;
		
		status = HttpStatus.BAD_REQUEST;
		
		apiException = new ApiException(exception.getMessage(),
										new Date(),
										status);
		
		return new ResponseEntity<Object>(apiException, status);	
	}
	
}
