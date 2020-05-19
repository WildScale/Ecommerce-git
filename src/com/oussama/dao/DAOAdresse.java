package com.oussama.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.oussama.interfaces.IAdresse;
import com.oussama.models.Adresse;
import com.oussama.models.SingletonConnexion;

public class DAOAdresse implements IAdresse{

	@Override
	public Adresse ajouterAdresse(Adresse adresse) {
		Connection connexion = SingletonConnexion.getConnection();
		try {
			PreparedStatement statement = connexion.prepareStatement("INSERT INTO Adresse(ville,queartier,rue) VALUES (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, adresse.getVille());
			statement.setString(2, adresse.getQuartier());
			statement.setString(3, adresse.getRue());
			
			int affectedRows = statement.executeUpdate();

			try (ResultSet rs = statement.getGeneratedKeys()) {
			    if (rs.next()) {
			        adresse.setId(rs.getInt(1));
			    }
			    rs.close();
			}
			
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return adresse;
		
	}

	@Override
	public void modifierAdresse(Adresse adresse, int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void supprimerAdresse(int id) {
		// TODO Auto-generated method stub
		
	}

}
