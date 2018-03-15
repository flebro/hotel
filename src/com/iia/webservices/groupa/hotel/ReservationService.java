package com.iia.webservices.groupa.hotel;

import java.time.LocalDate;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.iia.webservices.groupa.hotel.data.DataAccess;
import com.iia.webservices.groupa.hotel.dos.ReservationDemande;
import com.iia.webservices.groupa.hotel.model.Reservation;
import com.iia.webservices.groupa.hotel.utils.LocalDateUtil;

@Path("/reservations") @ProtectedResource
public class ReservationService {
	
	@Inject
	private DataAccess dataAccess;

	@POST 
	@Path("/")
	@Consumes("application/json")
	@Produces("application/json")
	public Response ReservationHotel(ReservationDemande reservationDemande){
		// on test chaque paramètre avant de construire notre résa
		if(reservationDemande.getDateDebDemande()!=null || reservationDemande.getDateFinDemande()!=null || dataAccess.getHotel(reservationDemande.getHotelIDDemande())!=null) {
			
		}
		
		//mapping de resado vers resa
		
		Reservation reservation=new Reservation(reservationDemande.getDateDebDemande(),
		reservationDemande.getDateFinDemande(),
		dataAccess.getHotel(reservationDemande.getHotelIDDemande()));

		dataAccess.addReservation(reservation);
		return Response.ok().build();
	}
	
	@GET 
	@Path("/")
	@Produces("application/json")
	public Response listeReservation(){
		
			return Response.ok().entity(dataAccess.listReservations()).build();
	}
}
