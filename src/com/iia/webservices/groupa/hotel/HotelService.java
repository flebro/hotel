package com.iia.webservices.groupa.hotel;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.iia.webservices.groupa.hotel.model.Hotel;

@Path("/hotels")
public class HotelService {		

	@GET 
	@Path("/")
	@Produces("application/json")
	public Response listeHotel(){
		return Response.ok().build();
	}
}
