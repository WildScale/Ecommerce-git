package com.oussama.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.oussama.interfaces.IFamille;
import com.oussama.models.Famille;
import com.oussama.models.SingletonConnexion;

public class DAOFamille implements IFamille{
	
	@Override
	public void ajouterFamille(Famille famille) {
		Connection connexion = SingletonConnexion.getConnection();
		
		try {
			PreparedStatement statement = connexion.prepareStatement("INSERT INTO Famille(nom) VALUES (?);");
			statement.setString(1, famille.getNom());
			statement.executeUpdate();
			statement.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void modifierFamille(Famille famille, int id) {
		
		
	}

	@Override
	public ArrayList<Famille> listerFamille() {
		Connection connexion = SingletonConnexion.getConnection();
		ArrayList<Famille> familles = new ArrayList();
		try {
			PreparedStatement statement = connexion.prepareStatement("SELECT * FROM Famille");
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				Famille famille = new Famille(result.getString("nom"));
				famille.setId(result.getInt("id"));
				familles.add(famille);
			}
			
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return familles;
	}

	@Override
	public void supprimerFamille(int id) {
		// TODO Auto-generated method stub
		
	}
	

}
