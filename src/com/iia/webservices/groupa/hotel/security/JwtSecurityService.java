package com.iia.webservices.groupa.hotel.security;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import com.iia.webservices.groupa.hotel.model.Utilisateur;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;
@ApplicationScoped
public class JwtSecurityService implements SecurityService {

	private Key key;
	
	@PostConstruct
	public void init() {
		key = MacProvider.generateKey();
	}
	
	@Override
	public String issueToken(Utilisateur utilisateur) {
		return Jwts.builder()
		  .setSubject(utilisateur.getUserName())
		  .signWith(SignatureAlgorithm.HS512, key)
		  .compact();
	}

	@Override
	public boolean validateToken(String token) {
		return Jwts.parser().setSigningKey(key).isSigned(token);
	}
	
}
