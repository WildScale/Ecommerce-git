package com.oussama.models;

public class Produit {

	private int id;
	private String nom;
	private double prix;
	private Famille famille;
	private String description;
	private String image;
	
	public Produit(String nom, double prix, Famille famille, String description, String image) {
		super();
		this.nom = nom;
		this.prix = prix;
		this.famille = famille;
		this.image = image;
		this.description = description;
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
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Produit [id=" + id + ", nom=" + nom + ", prix=" + prix + ", famille=" + famille + "]";
	}
	
	
	
	
}
