package application.model.DAO;

import java.sql.*;

import application.model.beans.Aliment;

public class AlimentDAO extends DAO<Aliment> {

	@Override
	public Aliment find(int id) {
		// POouvoir créer un objet Aliment à partir d'un enregistrement en BDD
		
		//Je commence par créer un objet
		Aliment aliment = new Aliment();
		
		try {
			
			Statement statement = connect.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM Aliment WHERE Id = " + id);
			
			while (result.next()) {
				int bd_id = result.getInt("Id");
				String bd_nom = result.getString("Nom");
				int bd_quantite = result.getInt("Quantité");
				Date bd_dateAchat = result.getDate("DateAchat");
				Date bd_datePeremption = result.getDate("DatePeremption");
				
				aliment.setId(bd_id);
				aliment.setNom(bd_nom);
				aliment.setQuantite(bd_quantite);
				aliment.setDateAchat(bd_dateAchat);
				aliment.setDatePeremption(bd_datePeremption);
				
			}
			return aliment;
			
		} catch (Exception e) {
			System.out.println("AlimentDAO: find() failed: " + e);
		}
		
		return null;
	}

	@Override
	public Aliment create(Aliment obj) {
		
		String nom = obj.getNom();
		int quantite = obj.getQuantite();
		Date dateAchat = obj.getDateAchat();
		Date datePeremption = obj.getDatePeremption();

		try {
			
		Statement statement = connect.createStatement();
		statement.execute("INSERT INTO Aliment (Nom, Quantite, DateAchat, DatePeremption) VALUES"
				+ "('" + nom + "', " + quantite + ", '" + dateAchat + "', '" + datePeremption + "')");
		
		} catch (Exception e) {
			System.out.println("AlimentDAO: create() failed: " + e);
		}
		
		return null;
	}

	@Override
	public Aliment update(Aliment obj) {
		
		int id = obj.getId();
		String nom = obj.getNom();
		int quantite = obj.getQuantite();
		Date dateAchat = obj.getDateAchat();
		Date datePeremption = obj.getDatePeremption();

		try {
			
		Statement statement = connect.createStatement();
		statement.execute("UPDATE Aliment SET Nom = '" + nom + "', "
				+ "Quantité = " + quantite + ", "
				+ "DateAchat = '" + dateAchat + "', "
				+ "DatePeremption = '" + datePeremption + "', "
				+ "WHERE Id = " + id + ")");
		
		} catch (Exception e) {
			System.out.println("AlimentDAO: update() failed: " + e);
		}
		
		return null;
	}

	@Override
	public void delete(Aliment obj) {

		int id = obj.getId();

		try {
			
		Statement statement = connect.createStatement();
		statement.execute("DELETE FROM Aliment WHERE Id = " + id + ")");
		
		} catch (Exception e) {
			System.out.println("AlimentDAO: delete() failed: " + e);
		}
		
	}

}
