package com.iia.webservices.groupa.hotel.model;

public class Hotel {
	
	public Hotel() {
		super();
	}

	
	public Hotel(String nom, int id) {
		super();
		this.nom = nom;
		this.id=id;
	}

	private String nom;
	private int id;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
}
