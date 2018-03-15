package com.iia.webservices.groupa.hotel;

import java.time.LocalDate;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.deser.DeserializerCache;
import com.iia.webservices.groupa.hotel.data.DataAccess;
import com.iia.webservices.groupa.hotel.data.MemoryDataAccess;
import com.iia.webservices.groupa.hotel.dos.ReservationDemande;
import com.iia.webservices.groupa.hotel.model.Hotel;
import com.iia.webservices.groupa.hotel.model.Reservation;
import com.iia.webservices.groupa.hotel.serialization.CustomLocalDateDeserialization;
import com.iia.webservices.groupa.hotel.utils.LocalDateUtil;

@Path("/reservations")
public class ReservationService {

	private DataAccess dataAccess = MemoryDataAccess.getInstance();

	@POST 
	@Path("/")
	@Consumes("application/json")
	@Produces("application/json")
	public Response ReservationHotel(ReservationDemande reservationDemande){
		
		//mapping de resado vers resa
		
		Reservation reservation=new Reservation(reservationDemande.getDateDebDemande(),
		reservationDemande.getDateFinDemande(),
		reservationDemande.getHotelIDDemande());

		dataAccess.addReservation(reservation);
		return Response.ok().build();
	}
}
