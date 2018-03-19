package com.iia.webservices.groupa.hotel;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.util.HttpResponseCodes;

import com.iia.webservices.groupa.hotel.data.DataAccess;
import com.iia.webservices.groupa.hotel.dos.Credentials;
import com.iia.webservices.groupa.hotel.model.Utilisateur;
import com.iia.webservices.groupa.hotel.security.SecurityService;

@Path("/login")
public class LoginService {
	
	@Inject
	private DataAccess dataAccess;
	@Inject
	private SecurityService securityService;
	
	@POST 
	@Path("/")
	@Consumes("application/json")
	@Produces("text/plain")
	/**
	 * Cette methode parmet l'authentification auprès de l'API
	 * @param credentials objet contenant le username et le password de l'utilisateur
	 * @return token
	 */
	public Response authenticate(Credentials credentials){
		Utilisateur user = dataAccess.getUtilisateur(credentials.getUsername(), credentials.getPassword());
		if (user == null) {
			Response.status(HttpResponseCodes.SC_UNAUTHORIZED).entity("Wrong credentials").build();
		} 
		
		// Issue a token for the user
        String token = securityService.issueToken(user);

        // Return the token on the response
        return Response.ok(token).build();
	}

}
