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
import com.iia.webservices.groupa.hotel.model.Hotel;
import com.iia.webservices.groupa.hotel.model.Utilisateur;
import com.iia.webservices.groupa.hotel.security.SecurityService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Path("/login")
@Api(value = "/login", description = "Méthodes pour le service Login")
public class LoginService {
	
	@Inject
	private DataAccess dataAccess;
	@Inject
	private SecurityService securityService;
	/**
	 * Permet d'authentifier un utilisateur
	 * @param credentials objet credentials (composé du username et password)
	 * @return Code réponse ok et un token
	 */
	@POST 
	@Path("/")
	@Consumes("application/json")
	@Produces("text/plain")
	@ApiOperation(value = "Méthode pour l'authentification auprès de l'API",
    notes = "Retourne un token",
    response = Utilisateur.class)
	/**
	 * Cette methode parmet l'authentification auprès de l'API
	 * @param credentials objet contenant le username et le password de l'utilisateur
	 * @return token
	 */
	public Response authenticate(@ApiParam(value = "Credentials objet contenant le username et le password de l'utilisateur.'groupb','1234'", required = true) Credentials credentials){
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
