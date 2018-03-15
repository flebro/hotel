package com.iia.webservices.groupa.hotel;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.iia.webservices.groupa.hotel.data.DataAccess;
import com.iia.webservices.groupa.hotel.model.Hotel;

@Path("/hotels")
public class HotelService {		
	
	@Inject
	private DataAccess dataAccess;
	
	@GET 
	@Path("/")
	@Produces("application/json")
	public Response listeHotel(){
		dataAccess.listHotels();
		return Response.ok().build();
	}
}
