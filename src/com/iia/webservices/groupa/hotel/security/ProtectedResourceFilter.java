package com.iia.webservices.groupa.hotel.security;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.iia.webservices.groupa.hotel.security.exception.AuthorizationException;

@Provider
@ProtectedResource
public class ProtectedResourceFilter implements ContainerRequestFilter {

	private static final boolean ACTIVE_SECURITY = false;
	private static final String AUTHENTICATION_SCHEME = "Bearer";

	@Inject
	private SecurityService securityService;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		if (!ACTIVE_SECURITY) {
			String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

			// Validate the Authorization header
			if (!isTokenBasedAuthentication(authorizationHeader)) {
				abortWithUnauthorized(requestContext, "No authorization token");
				return;
			}

			// Extract the token from the Authorization header
			String token = authorizationHeader.substring(AUTHENTICATION_SCHEME.length()).trim();

			try {
				securityService.validateToken(token);
			} catch (AuthorizationException e) {
				abortWithUnauthorized(requestContext, e.getMessage());
			}
		}
	}

	private void abortWithUnauthorized(ContainerRequestContext requestContext, String message) {
		// Abort the filter chain with a 401 status code response
		// The WWW-Authenticate header is sent along with the response
		requestContext.abortWith(
				Response.status(Response.Status.UNAUTHORIZED)
				.entity(message)
				.build());
	}

	private boolean isTokenBasedAuthentication(String authorizationHeader) {

		// Check if the Authorization header is valid
		// It must not be null and must be prefixed with "Bearer" plus a whitespace
		// The authentication scheme comparison must be case-insensitive
		return authorizationHeader != null && authorizationHeader.toLowerCase()
				.startsWith(AUTHENTICATION_SCHEME.toLowerCase() + " ");
	}

}
