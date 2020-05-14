package com.oussama.interfaces;

import com.oussama.models.Adresse;

public interface IAdresse {

	Adresse ajouterAdresse(Adresse adresse);
	void modifierAdresse(Adresse adresse, int id);
	void supprimerAdresse(int id);
}
