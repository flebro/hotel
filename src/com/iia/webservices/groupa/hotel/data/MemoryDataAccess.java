package com.iia.webservices.groupa.hotel.data;

import java.io.Serializable;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Singleton;

import com.iia.webservices.groupa.hotel.model.Hotel;
import com.iia.webservices.groupa.hotel.model.Reservation;
import com.iia.webservices.groupa.hotel.utils.LocalDateUtil;

@ApplicationScoped
public class MemoryDataAccess implements DataAccess, Serializable {

	private static final long serialVersionUID = -6844150364243779383L;

	private final List<Hotel> _hotels;
	private final List<Reservation> _reservations;

	public MemoryDataAccess() {
		// Init
		_hotels = new ArrayList<>();
		_reservations = new ArrayList<>();
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
		_reservations.add(new Reservation(LocalDateUtil.parse("2018-01-30"),LocalDateUtil.parse("2018-02-14"),_hotels.get(1)));
		_reservations.add(new Reservation(LocalDateUtil.parse("2018-02-30"),LocalDateUtil.parse("2018-03-14"),_hotels.get(1)));
		_reservations.add(new Reservation(LocalDateUtil.parse("2018-01-15"),LocalDateUtil.parse("2018-02-01"),_hotels.get(3)));
		_reservations.add(new Reservation(LocalDateUtil.parse("2018-01-18"),LocalDateUtil.parse("2018-02-19"),_hotels.get(4)));
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
	public List<Reservation> listReservations(LocalDate Date,Hotel hotel)
	{

		//Par défaut on charge toutes les réservations, on allégera cette liste en fonction des critères en paramètre
		List<Reservation> lesReservations= new ArrayList<Reservation>(_reservations);
		// boucle pour parcourir chaque résa
		for (Reservation reservation : _reservations) 
		{
			//si l'hôtel est renseigné
			if(hotel!=null)
			{
				if(!reservation.getHotel().equals(hotel)) 
				{
					lesReservations.remove(reservation);
				} 
			}
			for (Reservation reservation2 : _reservations) 
			{
				//si la date est renseignée
				if(Date!=null)
				{ 
					if(reservation2.getDateDebut().isBefore(Date)||reservation2.getDateFin().isBefore(Date)) 
					{
						lesReservations.remove(reservation2);
					} 
				}
			}

		}
		return lesReservations;
	}

	@Override
	public Hotel getHotel(int idHotel) {
		Hotel resultat = null;
		//boucle avec iterator pour parcourir la Hashlist;
		Iterator<Hotel> it = _hotels.iterator();
		while(it.next() != null) {
			// Comparaison dans la boucle if de l'hotel actuel avec celui de l'hotel - id en argument
			if(it.next().getId()==idHotel)
				resultat=it.next();
		}
		return resultat;

	}
}
