package com.iia.webservices.groupa.hotel.security.exception;

public abstract class AuthorizationException extends Exception {

	private static final long serialVersionUID = 6930395942768252656L;

	public AuthorizationException(String message) {
		super(message);
	}

}
