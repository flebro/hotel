package com.iia.webservices.groupa.hotel.data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.iia.webservices.groupa.hotel.data.exception.DateIndisponibleException;
import com.iia.webservices.groupa.hotel.model.Hotel;
import com.iia.webservices.groupa.hotel.model.Reservation;
import com.iia.webservices.groupa.hotel.model.Utilisateur;
import com.iia.webservices.groupa.hotel.utils.DateUtil;

@ApplicationScoped
public class MemoryDataAccess implements DataAccess, Serializable {

	private static final long serialVersionUID = -6844150364243779383L;

	@Inject
	private DateUtil dateUtil;
	
	private List<Hotel> _hotels;
	private List<Reservation> _reservations;
	private List<Utilisateur> _users;
	
	@PostConstruct
	public void init() {
		// Init
		_hotels = new ArrayList<>();
		_reservations = new ArrayList<>();
		_users = new ArrayList<>();
		// Creation des données de base
		load();
	}

	private void load() {
		_hotels.add(new Hotel("Au bon accueil",1));
		_hotels.add(new Hotel("Le Ritz",2));
		_hotels.add(new Hotel("Chez flo",3));
		_hotels.add(new Hotel("L'auberge rouge",4));
		_hotels.add(new Hotel("Le Carlton",5));
		_hotels.add(new Hotel("Sofitel",6));
		_reservations.add(new Reservation(dateUtil.parse("2018-01-30"),dateUtil.parse("2018-02-14"),_hotels.get(1)));
		_reservations.add(new Reservation(dateUtil.parse("2018-02-30"),dateUtil.parse("2018-03-14"),_hotels.get(1)));
		_reservations.add(new Reservation(dateUtil.parse("2018-01-15"),dateUtil.parse("2018-02-01"),_hotels.get(3)));
		_reservations.add(new Reservation(dateUtil.parse("2018-01-18"),dateUtil.parse("2018-02-19"),_hotels.get(4)));
		
		_users.add(new Utilisateur("groupb", "1234"));
	}
	
	@Override
	public Hotel getHotel(int idHotel) {
		Hotel resultat = null;
		for (Hotel hotel : _hotels) {
			if (hotel.getId() == idHotel) {
				resultat = hotel;
			}
		}
		return resultat;
	}
	
	@Override
	public List<Hotel> listHotels() {
		return new ArrayList<>(_hotels);
	}
	
	@Override
	public List<Hotel> listHotelsDisponibles(LocalDate dateDebut, LocalDate dateFin) {
		Set<Hotel> nonDispos = _reservations.stream()
				.filter(r -> r.chevauche(dateDebut, dateFin == null ? dateDebut : dateFin))
				.map(Reservation::getHotel)
				.collect(Collectors.toSet());

		return _hotels.stream().filter(h -> !nonDispos.contains(h)).collect(Collectors.toList());
	}

	@Override
	public List<Reservation> listReservations(LocalDate date, Hotel hotel)
	{
		//Par défaut on charge toutes les réservations, on allégera cette liste en fonction des crit�res en param�tre
		List<Reservation> lesReservations= new ArrayList<>(_reservations);
		// boucle pour parcourir chaque r�sa
		if (date != null) {
			for (Reservation reservation : lesReservations) {
				if(!reservation.chevauche(date, date)) {
					lesReservations.remove(reservation);
				}
			}
		}
		
		if (hotel != null) {
			for (Reservation reservation : lesReservations) {
				if(!reservation.getHotel().equals(hotel)) {
					lesReservations.remove(reservation);
				}
			}
		}
	
		return lesReservations;
	}

	@Override
	public void addReservation(Reservation reservation) throws DateIndisponibleException {
		for (Reservation dejaReserve : _reservations) {
			if (dejaReserve.getHotel().equals(reservation.getHotel()) && dejaReserve.chevauche(reservation) ) {
				throw new DateIndisponibleException();
			}
		}
		
		_reservations.add(reservation);
	}

	@Override
	public Utilisateur getUtilisateur(String userName, String password) {
		return _users.stream().filter(u -> u.getUserName().equals(userName) && u.getPassword().equals(password)).findAny().get();
	}
	
}
