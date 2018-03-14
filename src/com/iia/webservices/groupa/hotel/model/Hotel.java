package com.iia.webservices.groupa.hotel.model;

public class Hotel {
	
	public Hotel() {
		super();
	}

	
	public Hotel(String nom) {
		super();
		this.nom = nom;
	}

	private String nom;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
}
