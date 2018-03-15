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

import org.jboss.resteasy.util.HttpResponseCodes;

import com.iia.webservices.groupa.hotel.data.DataAccess;
import com.iia.webservices.groupa.hotel.data.exception.DateIndisponibleException;
import com.iia.webservices.groupa.hotel.dos.ReservationDemande;
import com.iia.webservices.groupa.hotel.model.Hotel;
import com.iia.webservices.groupa.hotel.model.Reservation;
import com.iia.webservices.groupa.hotel.security.ProtectedResource;
import com.iia.webservices.groupa.hotel.utils.DateUtil;

@Path("/reservations") @ProtectedResource
public class ReservationService {
	
	@Inject
	private DataAccess dataAccess;
	@Inject
	private DateUtil dateUtil;

	@POST 
	@Path("/")
	@Consumes("application/json")
	@Produces("application/json")
	public Response ReservationHotel(ReservationDemande reservationDemande){
		// on test chaque param�tre avant de construire notre r�sa
		if(reservationDemande.getDateDebDemande() == null || 
				reservationDemande.getDateFinDemande() == null || 
				reservationDemande.getHotelIDDemande() == null) {
			Response.status(HttpResponseCodes.SC_BAD_REQUEST).entity("You must provide all parameters").build();
		}
		
		LocalDate dateDebutParsed = null;
		LocalDate dateFinParsed = null;
		try {
			dateDebutParsed = dateUtil.parse(reservationDemande.getDateDebDemande());
			dateFinParsed = dateUtil.parse(reservationDemande.getDateFinDemande());
		} catch (Exception e) {
			Response.status(HttpResponseCodes.SC_BAD_REQUEST).entity("Dates must be formatted as such : yyyy-MM-dd").build();
		}
		
		Hotel hotel = dataAccess.getHotel(reservationDemande.getHotelIDDemande());
		if (hotel == null) {
			return Response.status(HttpResponseCodes.SC_NOT_FOUND).build();
		}
		
		Reservation reservation=new Reservation(dateDebutParsed, dateFinParsed, hotel);

		try {
			dataAccess.addReservation(reservation);
		} catch (DateIndisponibleException e) {
			Response.status(HttpResponseCodes.SC_BAD_REQUEST).entity("This dates are not available").build();
		}
		
		return Response.ok().build();
	}
	
	@GET 
	@Path("/")
	@Produces("application/json")
	public Response listeReservation(@QueryParam("hotelId") Integer hotelId, @QueryParam("date") String dateStr ){
		LocalDate date = null;
		Hotel hotel = null;
		
		if (hotelId != null) {
			hotel = dataAccess.getHotel(hotelId);
			if (hotel == null) {
				return Response.status(HttpResponseCodes.SC_NOT_FOUND).build();
			}
		}
		
		if (dateStr != null) {
			try {
				date = dateUtil.parse(dateStr);
			} catch (Exception e) {
				Response.status(HttpResponseCodes.SC_BAD_REQUEST).entity("Dates must be formatted as such : yyyy-MM-dd").build();
			}
		}
			
		return Response.ok().entity(dataAccess.listReservations(date, hotel)).build();
	}
}
