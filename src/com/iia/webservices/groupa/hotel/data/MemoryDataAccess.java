package com.iia.webservices.groupa.hotel.data;

import java.io.Serializable;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Singleton;

import com.iia.webservices.groupa.hotel.model.Hotel;
import com.iia.webservices.groupa.hotel.model.Reservation;

@ApplicationScoped
public class MemoryDataAccess implements DataAccess, Serializable {

	private static final long serialVersionUID = -6844150364243779383L;
	
	private final Set<Hotel> _hotels;
	private final Set<Reservation> _reservations;
	
	public MemoryDataAccess() {
		// Init
		_hotels = new HashSet<>();
		_reservations = new HashSet<>();
		// Creation des données de base
		load();
	}
	
	private void load() {
		_hotels.add(new Hotel("Au bon accueil"));
		_hotels.add(new Hotel("Le Ritz"));
		_hotels.add(new Hotel("Chez flo"));
		_hotels.add(new Hotel("L'auberge rouge"));
		_hotels.add(new Hotel("Le Carlton"));
		_hotels.add(new Hotel("Sofitel"));
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
		
}
