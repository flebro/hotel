package com.iia.webservices.groupa.hotel.model;

import java.time.LocalDate;

public class Reservation { 
	private LocalDate dateDebut;
	private LocalDate dateFin;
	private Hotel hotel;

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
	
	public Reservation(LocalDate dateDebut, LocalDate dateFin,Hotel hotel) {
		this.dateDebut= dateDebut;
	    this.dateFin=dateFin;
		this.hotel=hotel;	
	}
	
	public boolean chevauche(Reservation autreReservation) {
		return chevauche(autreReservation.getDateDebut(), autreReservation.getDateFin());
	}

	public boolean chevauche(LocalDate dateDebut, LocalDate dateFin) {
		return dateFin.isAfter(this.dateDebut) && dateDebut.isBefore(this.dateFin);
	}
	
}
