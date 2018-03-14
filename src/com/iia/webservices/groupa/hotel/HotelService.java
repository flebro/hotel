package com.iia.webservices.groupa.hotel;

import java.time.LocalDate;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.iia.webservices.groupa.hotel.data.DataAccess;
import com.iia.webservices.groupa.hotel.data.MemoryDataAccess;

@Path("/hotels")
public class HotelService {		
	private DataAccess dataAccess = MemoryDataAccess.getInstance();

	@GET 
	@Path("/")
	@Produces("application/json")
	public Response listeHotel(){


		return Response.ok().entity(dataAccess.listHotels()).build();
	}

	@GET 
	@Path("/")
	@Produces("application/json")
	public Response listeHotelDisponible(@QueryParam("dateDeb") LocalDate DateDeb,@QueryParam("dateFin")LocalDate DateFin){
		return Response.ok().entity(dataAccess.listHotelsDisponibles(DateDeb, DateFin)).build();
	}
}
