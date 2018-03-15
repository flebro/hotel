package com.iia.webservices.groupa.hotel;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.iia.webservices.groupa.hotel.data.DataAccess;
import com.iia.webservices.groupa.hotel.dos.ReservationDemande;
import com.iia.webservices.groupa.hotel.model.Reservation;
import com.iia.webservices.groupa.hotel.security.ProtectedResource;

@Path("/reservations")
@ProtectedResource
public class ReservationService {

	@Inject
	private DataAccess dataAccess;

	@POST 
	@Path("/")
	@Consumes("application/json")
	@Produces("application/json")
	public Response ReservationHotel(ReservationDemande reservationDemande){
		Reservation reservation=new Reservation(reservationDemande.getDateDebDemande(),
		reservationDemande.getDateFinDemande(),
		reservationDemande.getHotelIDDemande());
		dataAccess.addReservation(reservation);
		return Response.ok().build();
	}
}
