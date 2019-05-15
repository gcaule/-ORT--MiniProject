package application.model.DAO;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import application.model.beans.Aliment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AlimentDAO extends DAO<Aliment> {

	@Override
	public Aliment createDB() {

		// Création du schéma et de la table
		try {

			//On commence par créer le schéma
			Statement statement = connect.createStatement();
			statement.executeUpdate("CREATE SCHEMA IF NOT EXISTS `foodInFridge` DEFAULT CHARACTER SET utf8 ;");
			statement.executeUpdate("USE `foodInFridge` ;");

			//Puis on créé la table
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS `foodInFridge`.`Aliment` (" + 
					" `Id` INT NOT NULL AUTO_INCREMENT," + 
					" `Nom` VARCHAR(45) NOT NULL," + 
					" `Quantité` INT NOT NULL," + 
					" `DateAchat` DATE NOT NULL," + 
					" `DatePeremption` DATE NOT NULL," + 
					" PRIMARY KEY (`id`))" + 
					" ENGINE = InnoDB;");

		} catch (Exception e) {
			System.out.println("AlimentDAO: createDB() failed: " + e);
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public ObservableList<Aliment> fetchEntries() {

		// On récupère les tuples de la BDD
		try {

			//On va les stocker dans une liste
			ObservableList<Aliment> aliment = FXCollections.observableArrayList();

			Statement statement = connect.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM Aliment ORDER BY DatePeremption;");

			while (result.next()) {

				Aliment currentItem = new Aliment(result.getInt("Id"),
						result.getString("Nom"),
						result.getInt("Quantité"),
						result.getDate("DateAchat").toString(),
						result.getDate("DatePeremption").toString());

				//On met les dates au format européen
				String dateAchatBuff = currentItem.getDateAchat();
				String datePeremptionBuff = currentItem.getDatePeremption();
				
			    SimpleDateFormat oldFormat = new SimpleDateFormat("yyyy-MM-dd");
			    SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
			    
			    String dateAchatObj = newFormat.format(oldFormat.parse(dateAchatBuff));
			    String datePeremptionObj = newFormat.format(oldFormat.parse(datePeremptionBuff));
				
			    //On met les nouvelles dates dans l'objet
				currentItem.setDateAchat(dateAchatObj);
				currentItem.setDatePeremption(datePeremptionObj);

				aliment.add(currentItem);

			}

			return aliment;

		} catch (Exception e) {
			System.out.println("AlimentDAO: fetchEntries() failed: " + e);
			e.printStackTrace();
		}

		return null;
	}


	@Override
	public Aliment find(int id) {

		// Pouvoir créer un objet Aliment à partir d'un enregistrement en BDD
		//Je commence par créer un objet
		Aliment aliment = new Aliment();

		try {

			String query = "SELECT * FROM Aliment WHERE Id = ?;";
			PreparedStatement preparedStatement = connect.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.execute();

			ResultSet result = (ResultSet) preparedStatement;

			while (result.next()) {
				int bd_id = result.getInt("Id");
				String bd_nom = result.getString("Nom");
				int bd_quantite = result.getInt("Quantité");
				Date bd_dateAchat = result.getDate("DateAchat");
				Date bd_datePeremption = result.getDate("DatePeremption");

				//On met la date au format européen
				DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
				
				String dateAchatBuff = format.format(bd_dateAchat);
				String datePeremptionBuff = format.format(bd_datePeremption);
				
				Date dateAchatObj = format.parse(dateAchatBuff);
				Date datePeremptionObj = format.parse(datePeremptionBuff);

				aliment.setId(bd_id);
				aliment.setNom(bd_nom);
				aliment.setQuantite(bd_quantite);
				aliment.setDateAchat(dateAchatObj.toString());
				aliment.setDatePeremption(datePeremptionObj.toString());
			}
			
			return aliment;

		} catch (Exception e) {
			System.out.println("AlimentDAO: find() failed: " + e);
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Aliment create(Aliment obj) {

		//On stocke les données de obj dans des variables		
		String nom = obj.getNom();
		int quantite = obj.getQuantite();
		String dateAchat = obj.getDateAchat();
		String datePeremption = obj.getDatePeremption();

		try {
			//On met la date au format européen, et au format SQL
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			
		    Date parseDateAchat = format.parse(dateAchat);
		    Date parseDatePeremption = format.parse(datePeremption);
		    
		    java.sql.Date dateAchatDB = new java.sql.Date(parseDateAchat.getTime());
		    java.sql.Date datePeremptionDB = new java.sql.Date(parseDatePeremption.getTime());
			
			//On stocke les variables dans la BDD
			String query = "INSERT INTO Aliment (Nom, Quantité, DateAchat, DatePeremption) VALUES (?, ?, ?, ?);";
			PreparedStatement preparedStatement = connect.prepareStatement(query);
			preparedStatement.setString(1, nom);
			preparedStatement.setInt(2, quantite);
			preparedStatement.setDate(3, (java.sql.Date) dateAchatDB);
			preparedStatement.setDate(4, (java.sql.Date) datePeremptionDB);
			preparedStatement.execute();

		} catch (Exception e) {
			System.out.println("AlimentDAO: create() failed: " + e);
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void delete(Aliment obj) {

		int id = obj.getId();

		try {

			String query = "DELETE FROM Aliment WHERE Id = ?;";
			PreparedStatement preparedStatement = connect.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.execute();

		} catch (Exception e) {
			System.out.println("AlimentDAO: delete() failed: " + e);
			e.printStackTrace();
		}

	}

	@Override
	public Aliment update(Aliment obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
