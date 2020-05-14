package com.oussama.models;

public class Famille {

	private int id;
	private String nom;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Famille(String nom) {
		super();
		this.nom = nom;
	}
	
	public Famille() {
		
	}
	@Override
	public String toString() {
		return "Famille [id=" + id + ", nom=" + nom + "]";
	}
	

	
}
