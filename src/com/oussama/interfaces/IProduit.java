package com.oussama.interfaces;

import java.util.ArrayList;

import com.oussama.models.Famille;
import com.oussama.models.Produit;

public interface IProduit {

	void ajouterProduit(Produit produit);
	void modifierProduit(Produit produit, int id);
	ArrayList<Produit> listerProduits();
	Famille getFamilleParId(int id);
	Produit getProduitParId(int id);
	ArrayList<Produit> getProduitParFamille(int id);
	void supprimerProduit(int id);
}
