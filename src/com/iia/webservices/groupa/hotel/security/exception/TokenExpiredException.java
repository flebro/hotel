package com.iia.webservices.groupa.hotel.security.exception;

public class TokenExpiredException extends AuthorizationException {

	private static final long serialVersionUID = -1265460388555027269L;

	public TokenExpiredException() {
		super("Authorization token expired");
	}

}
