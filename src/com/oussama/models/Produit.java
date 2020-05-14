package com.oussama.models;

public class Produit {

	private int id;
	private String nom;
	private double prix;
	private Famille famille;
	
	public Produit(String nom, double prix, Famille famille) {
		super();
		this.nom = nom;
		this.prix = prix;
		this.famille = famille;
	}
	
	public Produit() {
		
	}
	
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
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}

	public Famille getFamille() {
		return famille;
	}

	public void setFamille(Famille famille) {
		this.famille = famille;
	}

	@Override
	public String toString() {
		return "Produit [id=" + id + ", nom=" + nom + ", prix=" + prix + ", famille=" + famille + "]";
	}
	
	
	
	
}
