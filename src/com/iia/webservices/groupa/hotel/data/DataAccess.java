package com.iia.webservices.groupa.hotel.data;

import java.time.LocalDate;
import java.util.List;

import com.iia.webservices.groupa.hotel.model.Hotel;
import com.iia.webservices.groupa.hotel.model.Reservation;
import com.iia.webservices.groupa.hotel.model.Utilisateur;

public interface DataAccess {
	
	Utilisateur getUtilisateur(String userName, String password);
	
	void addReservation(Reservation reservation);
	
	List<Hotel> listHotels();
	
	List<Hotel> listHotelsDisponibles(LocalDate dateDebut, LocalDate dateFin);
	
	List<Reservation> listReservations();
	
	Hotel getHotel(int idHotel);

}
