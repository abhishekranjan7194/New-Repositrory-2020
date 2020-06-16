package com.microservices.dthchannelsubscriptions.exceptions;

public class InvalidChannelException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	
	public InvalidChannelException() {};
	

	public InvalidChannelException(String message) {
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
