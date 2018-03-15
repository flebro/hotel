package com.iia.webservices.groupa.hotel.model;

import java.time.LocalDate;

import com.iia.webservices.groupa.hotel.utils.LocalDateUtil;

public class Reservation {
	private LocalDate dateDebut;
	private LocalDate dateFin;
	private Hotel hotel;
	// cr�ation d'une variable hotel ne contenant que l'id
	private int  hotelId;

	public LocalDate getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}
	public LocalDate getDateFin() {
		return dateFin;
	}
	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	
	public Reservation(String dateDebut, String dateFin, int id) {
		this.dateDebut= LocalDateUtil.parse(dateDebut);
	    this.dateFin=LocalDateUtil.parse(dateFin);
		hotelId=id;			
	}

}
