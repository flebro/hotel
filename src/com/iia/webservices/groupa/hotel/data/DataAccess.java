package com.iia.webservices.groupa.hotel.data;

import java.time.LocalDate;
import java.util.List;

import com.iia.webservices.groupa.hotel.model.Hotel;
import com.iia.webservices.groupa.hotel.model.Reservation;

public interface DataAccess {
	
	void addReservation(Reservation reservation);
	
	List<Hotel> listHotels();
	
	List<Hotel> listHotelsDisponibles(LocalDate dateDebut, LocalDate dateFin);
	
	List<Reservation> listReservations();

}
