package com.iia.webservices.groupa.hotel.security.exception;

public class InvalidTokenException extends AuthorizationException {

	private static final long serialVersionUID = 1087395308823696975L;

	public InvalidTokenException() {
		super("Authorization token invalid");
	}

}
