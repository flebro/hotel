package com.iia.webservices.groupa.hotel.model;

public class Utilisateur {
	
	private String userName;
	private String password;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Utilisateur() {
		super();
	}
	public Utilisateur(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	


}
