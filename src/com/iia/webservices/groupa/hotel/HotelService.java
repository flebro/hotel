package com.iia.webservices.groupa.hotel;

import java.time.LocalDate;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.util.HttpResponseCodes;

import com.iia.webservices.groupa.hotel.data.DataAccess;
import com.iia.webservices.groupa.hotel.model.Hotel;
import com.iia.webservices.groupa.hotel.utils.DateUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.*;

import com.iia.webservices.groupa.hotel.security.ProtectedResource;

@Path("/hotels") @ProtectedResource
@Produces("application/json")
@Api(value = "/hotels")


//@OpenAPIDefinition (info = 
//@Info(
//          title = "the title",
//          version = "0.0",
//          description = "My API",
//          license = @License(name = "Apache 2.0", url = "http://foo.bar"),
//          contact = @Contact(url = "http://gigantic-server.com", name = "Fred", email = "Fred@gigagantic-server.com")
//  )
//)

public class HotelService  {		
	
	@Inject
	private DataAccess dataAccess;
	@Inject
	private DateUtil dateUtil;
	
	@GET
	@Path("/{id}")
	@ApiOperation(value = "Obtenir un h�tel � partir de son id.",
	    notes = "Si l'id est inconnu retourne un code erreur http",
	    response = Hotel.class)
	/**
	 * Obtenir un h�tel � partir de son id.
	 * @param id Id est un entier correspondant au code de l'h�tel.
	 * @return 	L'objet h�tel de l'id sp�cifi�.
	 */
	public Response getHotel(@ApiParam(value = "id de l'h�tel", required = true) @PathParam("id") int id) {
		Hotel hotel = dataAccess.getHotel(id);
		if (hotel == null) {
			return Response.status(HttpResponseCodes.SC_NOT_FOUND).build();
		} else {
			return Response.ok().entity(hotel).build();
		}
	}
	/**
	 * Fournit la liste des h�tels disponibles aux dates sp�cifi�es
	 * @param dateDeb Date de d�but de s�jour au format AAAA-MM-JJ [String]
	 * @param dateFin Date de fin de s�jour au format AAAA-MM-JJ [String]
	 * @return
	 */
	@GET 
	@Path("/")
	@ApiOperation(value = "Fournit la liste des h�tels disponibles aux dates sp�cifi�es",
    notes = "Si les param�tres sont nuls, fournit la liste compl�te des h�tels disponibles",
    response = Hotel.class,
    nickname="listeHotelDisponible")
	public Response listeHotelDisponible(@ApiParam(value = "Date d�but", required = false) @QueryParam("dateDeb") String dateDeb,@ApiParam(value = "Date Fin", required = false) @QueryParam("dateFin") String dateFin){
		if (dateDeb == null || dateFin== null) {
			return Response.ok().entity(dataAccess.listHotels()).build();
		} else {
			LocalDate dateDebutParsed = null;
			LocalDate dateFinParsed = null;
			try {
				dateDebutParsed = dateUtil.parse(dateDeb);
				dateFinParsed = dateUtil.parse(dateFin);
			} catch (Exception e) {
				Response.status(HttpResponseCodes.SC_BAD_REQUEST).entity("Dates must be formatted as such : yyyy-MM-dd").build();
			}
			return Response.ok().entity(dataAccess.listHotelsDisponibles(dateDebutParsed, dateFinParsed)).build();
		}
	}

}
