package com.fone.api.FOne.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class ApiException {

	private final String message;
	private final Date currentMoment;
	private final HttpStatus httpStatus; 

	
	public ApiException(String message, Date currentMoment, HttpStatus httpStatus) {
		super();
		this.message = message;
		this.currentMoment = currentMoment;
		this.httpStatus = httpStatus;
	}


	public String getMessage() {
		return message;
	}

	public Date getCurrentMoment() {
		return currentMoment;
	}


	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	
}
