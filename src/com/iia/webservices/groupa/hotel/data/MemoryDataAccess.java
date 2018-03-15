package com.iia.webservices.groupa.hotel.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.iia.webservices.groupa.hotel.model.Hotel;
import com.iia.webservices.groupa.hotel.model.Reservation;

public final class MemoryDataAccess implements DataAccess {

	private static DataAccess _instance;

	private final Set<Hotel> _hotels;
	private final Set<Reservation> _reservations;

	private MemoryDataAccess() {
		// Init
		_hotels = new HashSet<>();
		_reservations = new HashSet<>();
		// Creation des donnÃ©es de base
		load();
	}

	private void load() {
		_hotels.add(new Hotel("Au bon accueil",1));
		_hotels.add(new Hotel("Le Ritz",2));
		_hotels.add(new Hotel("Chez flo",3));
		_hotels.add(new Hotel("L'auberge rouge",4));
		_hotels.add(new Hotel("Le Carlton",5));
		_hotels.add(new Hotel("Sofitel",6));
	}

	public static DataAccess getInstance() {
		if (_instance == null) {
			_instance = new MemoryDataAccess();
		}
		return _instance;
	}

	@Override
	public void addReservation(Reservation reservation) {
		_reservations.add(reservation);
	}

	@Override
	public List<Hotel> listHotels() {
		return new ArrayList<>(_hotels);
	}

	@Override
	public List<Hotel> listHotelsDisponibles(LocalDate dateDebut, LocalDate dateFin) {
		Set<Hotel> nonDispos = _reservations.stream()
				.filter(r -> r.getDateDebut().isBefore(dateFin == null ? dateDebut : dateFin) && r.getDateFin().isAfter(dateDebut))
				.map(Reservation::getHotel)
				.collect(Collectors.toSet());

		return _hotels.stream().filter(h -> !nonDispos.contains(h)).collect(Collectors.toList());
	}

	@Override
	public List<Reservation> listReservations() {
		return new ArrayList<>(_reservations);
	}

	@Override
	public Hotel getHotel(int idHotel) {
		 Hotel resultat = null;
		//boucle avec iterator pour parcourir la Hashlist;
		Iterator<Hotel> it = _hotels.iterator();
		while(it.next() != null) {
			// Comparaison dans la boucle if de l'hotel actuel (accéder à l'id puis la comparer avec la valeur de idHotel)
			if(it.next().getId()==idHotel)
				resultat=it.next();
		}
		return resultat;

	}
}
