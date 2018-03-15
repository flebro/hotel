package com.iia.webservices.groupa.hotel.data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import com.iia.webservices.groupa.hotel.model.Hotel;
import com.iia.webservices.groupa.hotel.model.Reservation;
import com.iia.webservices.groupa.hotel.model.Utilisateur;

@ApplicationScoped
public class MemoryDataAccess implements DataAccess, Serializable {

	private static final long serialVersionUID = -6844150364243779383L;
	
	private Set<Hotel> _hotels;
	private Set<Reservation> _reservations;
	private Set<Utilisateur> _users;
	
	@PostConstruct
	public void init() {
		// Init
		_hotels = new HashSet<>();
		_reservations = new HashSet<>();
		_users = new HashSet<>();
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
		
		_users.add(new Utilisateur("groupb", "1234"));
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
			// Comparaison dans la boucle if de l'hotel actuel (acc�der � l'id puis la comparer avec la valeur de idHotel)
			if(it.next().getId()==idHotel)
				resultat=it.next();
		}
		return resultat;

	}

	@Override
	public Utilisateur getUtilisateur(String userName, String password) {
		return _users.stream().filter(u -> u.getUserName().equals(userName) && u.getPassword().equals(password)).findAny().get();
	}
}
