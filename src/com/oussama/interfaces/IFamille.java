package com.oussama.interfaces;

import java.util.ArrayList;

import com.oussama.models.Famille;

public interface IFamille {

	void ajouterFamille(Famille famille);
	void modifierFamille(Famille famille, int id);
	ArrayList<Famille> listerFamille();
	void supprimerFamille(int id);
	
}
