package com.iia.webservices.groupa.hotel.security;

import com.iia.webservices.groupa.hotel.model.Utilisateur;

public interface SecurityService {
	
	String issueToken(Utilisateur utilisateur);
	
	boolean validateToken(String token);
	
}
