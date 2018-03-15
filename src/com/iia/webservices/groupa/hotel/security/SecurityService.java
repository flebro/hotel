package com.iia.webservices.groupa.hotel.security;

import com.iia.webservices.groupa.hotel.model.Utilisateur;
import com.iia.webservices.groupa.hotel.security.exception.AuthorizationException;

public interface SecurityService {
	
	String issueToken(Utilisateur utilisateur);
	
	void validateToken(String token) throws AuthorizationException;
	
}
