package com.oussama.interfaces;

import java.util.ArrayList;

import com.oussama.models.Commande;
import com.oussama.models.Produit_Commande;

public interface ICommande {

	ArrayList<Commande> listerCommandesParIdClient(int id);
	Commande ajouterCommande(Commande commande);
	void ajouterProduit_Commande(Produit_Commande produit_commande);
}
