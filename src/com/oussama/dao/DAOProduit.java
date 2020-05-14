package com.oussama.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;
import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;
import com.oussama.interfaces.IProduit;
import com.oussama.models.Famille;
import com.oussama.models.Produit;
import com.oussama.models.SingletonConnexion;

public class DAOProduit implements IProduit {

	@Override
	public void ajouterProduit(Produit produit) {
		Connection connexion = SingletonConnexion.getConnection();

		try {
			PreparedStatement statement = connexion
					.prepareStatement("INSERT INTO Produit(nom,prix,famille) VALUES (?,?,?);");
			statement.setString(1, produit.getNom());
			statement.setDouble(2, produit.getPrix());
			statement.setInt(3, produit.getFamille().getId());

			statement.executeUpdate();
			
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void modifierProduit(Produit produit, int id) {
		
		Connection connexion = SingletonConnexion.getConnection();
		try {
			PreparedStatement statement = connexion.prepareStatement("UPDATE Produit SET nom = ? , prix = ? , famille = ? WHERE id= ?");
			statement.setString(1, produit.getNom());
			statement.setDouble(2, produit.getPrix());
			statement.setInt(3,produit.getFamille().getId());
			statement.setInt(4, id);
			
			statement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public ArrayList<Produit> listerProduits() {
		ArrayList<Produit> produits = new ArrayList<Produit>();
		Connection connexion = SingletonConnexion.getConnection();
		
		try {
			PreparedStatement statement = connexion.prepareStatement("SELECT * FROM Produit");
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				String nom = result.getString("nom");
				double prix = result.getDouble("prix");
				int id_famille = result.getInt("famille");
				Famille famille = getFamilleParId(id_famille);
				Produit produit = new Produit(nom,prix, famille);
				produit.setId(result.getInt("id"));
				produits.add(produit);
			}
			
			statement.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return produits;
	}

	@Override
	public Famille getFamilleParId(int id) {
		
		Connection connexion = SingletonConnexion.getConnection();
		Famille famille = new Famille();
		try {
			PreparedStatement statement = connexion.prepareStatement("SELECT * FROM Famille WHERE id = ?");
			statement.setInt(1, id);
			
			ResultSet result = statement.executeQuery();
			
			result.next();
			
			String nom = result.getString("nom");
			
			famille.setNom(nom);
			famille.setId(id);

			statement.close();
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return famille;
	}

	@Override
	public Produit getProduitParId(int id) {
		Connection connexion = SingletonConnexion.getConnection();
		Produit produit = new Produit();
		
		try {
			PreparedStatement statement = connexion.prepareStatement("SELECT * FROM Produit WHERE id = ?");
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			
			result.next();
			
			produit.setId(id);
			produit.setNom(result.getString("nom"));
			produit.setPrix(result.getDouble("prix"));
			
			Famille famille = getFamilleParId(result.getInt("famille"));
			
			produit.setFamille(famille);
			
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return produit;
	}

	@Override
	public ArrayList<Produit> getProduitParFamille(int id) {
		
		Connection connexion = SingletonConnexion.getConnection();
		ArrayList<Produit> produits = new ArrayList<Produit>();
		
		try {
			PreparedStatement statement = connexion.prepareStatement("SELECT * FROM Produit WHERE famille = ?");
			statement.setInt(1, id);
			System.out.println(statement.toString());
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				Produit produit = new Produit();
				
				String nom = result.getString("nom");
				double prix = result.getDouble("prix");
				Famille famille = getFamilleParId(id);
				
				produit.setNom(nom);
				produit.setPrix(prix);
				produit.setFamille(famille);
				produit.setId(result.getInt("id"));
				
				produits.add(produit);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("PRODUITS DAO : "+ produits);
		return produits;
	}

	@Override
	public void supprimerProduit(int id) {
		Connection connexion = SingletonConnexion.getConnection();
		try {
			PreparedStatement statement = connexion.prepareStatement("DELETE FROM Produit WHERE id = ?");
			statement.setInt(1, id);
			
			statement.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	

}
