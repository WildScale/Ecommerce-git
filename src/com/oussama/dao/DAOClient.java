package com.oussama.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.oussama.interfaces.IClient;
import com.oussama.models.Client;
import com.oussama.models.SingletonConnexion;

public class DAOClient implements IClient{

	@Override
	public void ajouterClient(Client client) {
		Connection connexion = SingletonConnexion.getConnection();
		try {
			PreparedStatement statement = connexion.prepareStatement("INSERT INTO Client(nom,prenom,email,mdp,adresse) VALUES (?,?,?,?,?)");
			statement.setString(1, client.getNom());
			statement.setString(2, client.getPrenom());
			statement.setString(3, client.getEmail());
			statement.setString(4, client.getMdp());
			statement.setInt(5, client.getAdresse());
			
			statement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void modifierClient(Client client, int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Client> listerClient() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
