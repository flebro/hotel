package com.iia.webservices.groupa.hotel.dos;

import com.iia.webservices.groupa.hotel.model.Hotel;

public class ReservationDO {
	private String dateDebut;
	private String dateFin;
	private Hotel hotel;
	public String getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}
	public String getDateFin() {
		return dateFin;
	}
	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	public ReservationDO() {
		super();
	}
	public ReservationDO(String dateDebut, String dateFin, Hotel hotel) {
		super();
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.hotel = hotel;
	}
}
