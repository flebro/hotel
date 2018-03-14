package com.iia.webservices.groupa.hotel;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.iia.webservices.groupa.hotel.data.DataAccess;
import com.iia.webservices.groupa.hotel.data.MemoryDataAccess;
import com.iia.webservices.groupa.hotel.model.Reservation;

@Path("/hotels")
public class ReservationService {

	private DataAccess dataAccess = MemoryDataAccess.getInstance();

	@POST 
	@Path("/")
	@Produces("application/json")
	public Response ReservationHotel(Reservation reservation){
		dataAccess.addReservation(reservation);
		return Response.ok().build();
	}
}
