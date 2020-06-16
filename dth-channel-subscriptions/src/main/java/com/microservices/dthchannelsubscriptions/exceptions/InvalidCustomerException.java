package com.microservices.dthchannelsubscriptions.exceptions;

public class InvalidCustomerException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String message;
	
	public InvalidCustomerException() {};
	

	public InvalidCustomerException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
