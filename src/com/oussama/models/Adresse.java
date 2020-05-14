package com.oussama.models;

public class Adresse {
	private int id;
	private String ville;
	private String quartier;
	private String rue;
	
	public Adresse(String ville, String quartier, String rue) {
		super();
		this.ville = ville;	
		this.quartier = quartier;
		this.rue = rue;
	}
	
	public Adresse() {
		
	}
	
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getQuartier() {
		return quartier;
	}
	public void setQuartier(String quartier) {
		this.quartier = quartier;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
