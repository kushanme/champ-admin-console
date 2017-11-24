package com.champ.admin.console.exception;

public class ServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7238550336070591529L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

}
