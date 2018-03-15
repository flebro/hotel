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
import com.iia.webservices.groupa.hotel.security.ProtectedResource;

@Path("/hotels") @ProtectedResource
public class HotelService {		
	
	@Inject
	private DataAccess dataAccess;
	@Inject
	private DateUtil dateUtil;
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Response getHotel(@PathParam("id") int id) {
		Hotel hotel = dataAccess.getHotel(id);
		if (hotel == null) {
			return Response.status(HttpResponseCodes.SC_NOT_FOUND).build();
		} else {
			return Response.ok().entity(hotel).build();
		}
	}
	
	@GET 
	@Path("/")
	@Produces("application/json")
	public Response listeHotelDisponible(@QueryParam("dateDeb") String dateDeb,@QueryParam("dateFin") String dateFin){
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
