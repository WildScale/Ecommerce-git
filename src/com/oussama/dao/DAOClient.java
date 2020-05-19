package com.oussama.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.oussama.interfaces.IClient;
import com.oussama.models.Adresse;
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
		Connection connexion = SingletonConnexion.getConnection();
		try {
			PreparedStatement statement = connexion.prepareStatement("UPDATE Client SET nom = ?, prenom = ?, adresse = ?, email = ?, mdp = ?");
			statement.setString(1, client.getNom());
			statement.setString(2, client.getPrenom());
			statement.setInt(3, client.getAdresse());
			statement.setString(4, client.getEmail());
			statement.setString(5, client.getMdp());
			
			statement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public ArrayList<Client> listerClient() {
		Connection connexion = SingletonConnexion.getConnection();
		ArrayList<Client> clients = new ArrayList<Client>();
		try {
			PreparedStatement statement = connexion.prepareStatement("SELECT * FROM Client;");
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				String nom = result.getString("nom");
				String prenom = result.getString("prenom");
				int adresse = result.getInt("adresse");
				String email = result.getString("email");
				String mdp = result.getString("mdp");
				
				Client client = new Client(nom, prenom, adresse, email, mdp);
				client.setId(result.getInt("id"));
				clients.add(client);
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return clients;
	}

	@Override
	public Client login(String email, String password) {
		Connection connexion = SingletonConnexion.getConnection();
		Client client = new Client();
		try {
			PreparedStatement statement = connexion.prepareStatement("SELECT * FROM Client WHERE email= ? AND mdp = ?");
			statement.setString(1, email);
			statement.setString(2, password);
			
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				
				client.setEmail(email);
				client.setMdp(password);
				client.setNom(result.getString("nom"));
				client.setPrenom(result.getString("prenom"));
				client.setId(result.getInt("id"));
				client.setAdresse(result.getInt("adresse"));
				return client;
			}
		
		}
		catch(SQLException e ) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Adresse getAdresseParId(int id) {
		Connection connexion = SingletonConnexion.getConnection();
		Adresse adresse = new Adresse();
		try {
			PreparedStatement statement = connexion.prepareStatement("SELECT * FROM Adresse WHERE id = ?");
			statement.setInt(1, id);
			
			ResultSet result = statement.executeQuery();
			
			result.next();
			
			String ville = result.getString("ville");
			String quartier = result.getString("quartier");
			String rue = result.getString("rue");
			
			adresse.setQuartier(quartier);
			adresse.setId(id);
			adresse.setRue(rue);
			adresse.setVille(ville);
			
			statement.close();
		}
		catch(SQLException e ) {
			e.printStackTrace();
		}
		return adresse;
	}

	@Override
	public Client getClientParId(int id) {
		Connection connexion = SingletonConnexion.getConnection();
		Client client = new Client();
		try {
			PreparedStatement statement = connexion.prepareStatement("SELECT * FROM Client WHERE id = ?");
			statement.setInt(1, id);
			
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				String nom = result.getString("nom");
				String prenom = result.getString("prenom");
				int adresse = result.getInt("adresse");
				String email = result.getString("email");
				String mdp = result.getString("mdp");
				
				client = new Client(nom, prenom, adresse, email, mdp);
				client.setId(id);
				return client;
			}
		}
		catch(SQLException e ) {
			e.printStackTrace();
		}
		return null;
	}
	
	

	
	
	
}
