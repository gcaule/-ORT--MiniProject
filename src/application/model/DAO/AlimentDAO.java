package application.model.DAO;

import java.sql.*;
import java.time.LocalDate;

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
			ResultSet result = statement.executeQuery("SELECT * FROM Aliment ;");

			while (result.next()) {
				aliment.add(new Aliment(result.getInt("Id"),
						result.getString("Nom"),
						result.getInt("Quantité"),
						result.getDate("DateAchat").toLocalDate(),
						result.getDate("DatePeremption").toLocalDate()));
			}
			return aliment;

		} catch (Exception e) {
			System.out.println("AlimentDAO: fetchEntries() failed: " + e);
		}

		return null;
	}


	@Override
	public Aliment find(int id) {

		// Pouvoir créer un objet Aliment à partir d'un enregistrement en BDD
		//Je commence par créer un objet
		Aliment aliment = new Aliment();

		try {

			Statement statement = connect.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM Aliment WHERE Id = " + id);

			while (result.next()) {
				int bd_id = result.getInt("Id");
				String bd_nom = result.getString("Nom");
				int bd_quantite = result.getInt("Quantité");
				LocalDate bd_dateAchat = result.getDate("DateAchat").toLocalDate();
				LocalDate bd_datePeremption = result.getDate("DatePeremption").toLocalDate();

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

		//On stocke les données de obj dans des variables		
		String nom = obj.getNom();
		int quantite = obj.getQuantite();
		LocalDate dateAchat = obj.getDateAchat();
		LocalDate datePeremption = obj.getDatePeremption();

		try {

			//On stocke les variables dans la BDD
			Statement statement = connect.createStatement();
			statement.execute("INSERT INTO Aliment (Nom, Quantité, DateAchat, DatePeremption) VALUES"
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
		LocalDate dateAchat = obj.getDateAchat();
		LocalDate datePeremption = obj.getDatePeremption();

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
