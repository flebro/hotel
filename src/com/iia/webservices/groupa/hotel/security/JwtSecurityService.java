package com.iia.webservices.groupa.hotel.security;

import java.security.Key;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import com.iia.webservices.groupa.hotel.model.Utilisateur;
import com.iia.webservices.groupa.hotel.security.exception.AuthorizationException;
import com.iia.webservices.groupa.hotel.security.exception.InvalidTokenException;
import com.iia.webservices.groupa.hotel.security.exception.TokenExpiredException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
@ApplicationScoped
public class JwtSecurityService implements SecurityService {

	private static final long TOKEN_VALIDITY_IN_SECONDS = 3600l; 
	
	private Key key;

	@PostConstruct
	public void init() {
		key = MacProvider.generateKey();
	}

	@Override
	public String issueToken(Utilisateur utilisateur) {
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		Date expiration = new Date(nowMillis + (1000 * TOKEN_VALIDITY_IN_SECONDS));
		
		return Jwts.builder()
				.setSubject(utilisateur.getUserName())
				.setIssuedAt(now)
				.setExpiration(expiration)
				.signWith(SignatureAlgorithm.HS512, key)
				.compact();
	}

	@Override
	public void validateToken(String token) throws AuthorizationException {
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		
		JwtParser parser = Jwts.parser().setSigningKey(key);
		if (!parser.isSigned(token)) {
			throw new InvalidTokenException();
		}
			Claims claims = parser.parseClaimsJws(token).getBody();
			if (claims.getExpiration().before(now)) {
				throw new TokenExpiredException();
			}
	}

}
