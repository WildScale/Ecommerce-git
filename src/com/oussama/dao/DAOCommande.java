package com.oussama.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import com.oussama.interfaces.ICommande;
import com.oussama.models.Client;
import com.oussama.models.Commande;
import com.oussama.models.Produit_Commande;
import com.oussama.models.SingletonConnexion;

public class DAOCommande implements ICommande {

	@Override
	public ArrayList<Commande> listerCommandesParIdClient(int id) {
		ArrayList<Commande> commandes = new ArrayList<Commande>();

		Connection connexion = SingletonConnexion.getConnection();
		try {
			PreparedStatement statement = connexion.prepareStatement("SELECT * FROM Commande WHERE client = ?");
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				Date date = result.getDate("dateCd");
				double prix = result.getDouble("prix");
				int id_commande = result.getInt("id");

				Commande commande = new Commande(date, prix, id);
				commande.setId(id_commande);
				commandes.add(commande);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return commandes;
	}

	@Override
	public Commande ajouterCommande(Commande commande) {
		Connection connexion = SingletonConnexion.getConnection();
		try {
			PreparedStatement statement = connexion.prepareStatement(
					"INSERT INTO Commande(dateCd,prix,client) VALUES (?,?,?);",
					PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setDate(1, commande.getDate());
			statement.setDouble(2, commande.getPrix());
			statement.setInt(3, commande.getClient());

			int affectedRows = statement.executeUpdate();
			try (ResultSet rs = statement.getGeneratedKeys()) {
				if (rs.next()) {
					commande.setId(rs.getInt(1));
				}
				rs.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return commande;
	}

	@Override
	public void ajouterProduit_Commande(Produit_Commande produit_commande) {
		Connection connexion = SingletonConnexion.getConnection();

		try {
			PreparedStatement statement = connexion
					.prepareStatement("INSERT INTO produit_commande(id_produit,id_commande,quantite) VALUES (?,?,?);");

			statement.setInt(1, produit_commande.getId_produit());
			statement.setInt(2, produit_commande.getId_commande());
			statement.setInt(3, produit_commande.getQuantite());

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
