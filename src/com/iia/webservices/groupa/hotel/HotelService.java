package com.iia.webservices.groupa.hotel;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/hotels")
public class HotelService {

	@GET @Path("/")
	public Response testHotel(){
		System.out.println("test ok");

		return Response.ok().build();
	}
}
