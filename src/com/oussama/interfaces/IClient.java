package com.oussama.interfaces;

import java.util.ArrayList;

import com.oussama.models.Client;

public interface IClient {
	void ajouterClient(Client client);
	void modifierClient(Client client,int id);
	ArrayList<Client> listerClient();
}