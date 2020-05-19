package com.oussama.models;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;

public class Commande {

	private int id;
	private Date date;
	private double prix;
	private int client;
	
	public Commande(Date date, double prix, int client) {
		super();
		this.date = date;
		this.prix = prix;
		this.client = client;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public int getClient() {
		return client;
	}
	public void setClient(int client) {
		this.client = client;
	}
	
	
}
