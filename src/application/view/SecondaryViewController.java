package application.view;

import java.time.LocalDate;
import java.util.stream.IntStream;

import application.model.DAO.AlimentDAO;
import application.model.beans.Aliment;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SecondaryViewController {

	@FXML private TextField foodNameTF;
	@FXML private ComboBox quantityComboBox;
	@FXML private DatePicker dateAchatDP;
	@FXML private DatePicker datePeremptionDP;
	@FXML private Button validateButton;
	@FXML private Button cancelButton;

	public void initialize() {

		//On initialise les datepicker à la date d'aujourd'hui
		dateAchatDP.setValue(LocalDate.now());
		datePeremptionDP.setValue(LocalDate.now());

		//On initialise le ComboBox avec les chiffres de 1 à 25, et on sélectionne 1 par défaut
		IntStream.rangeClosed(1,25).boxed().forEach(quantityComboBox.getItems()::add);
		quantityComboBox.getSelectionModel().selectFirst();
	}

	public void handleValidateButton() {

		//On vérifie que l'utilisateur ait bien renseigné tous les champs renseignables
		if ( foodNameTF.getText() == null || foodNameTF.getText().trim().isEmpty() ) {

			//Nope, message d'alerte
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Vous devez entrer un nom !");
			alert.show();

		} else {

			//Oui, on récupère toutes les infos
			String nomAliment = foodNameTF.getText();
			int quantite = (int) quantityComboBox.getValue();
			LocalDate dateAchat = dateAchatDP.getValue();
			LocalDate datePeremption = datePeremptionDP.getValue();
			
			//On met les infos dans un objet
			Aliment aliment = new Aliment();
			aliment.setNom(nomAliment);
			aliment.setQuantite(quantite);
			aliment.setDateAchat(dateAchat);
			aliment.setDatePeremption(datePeremption);
			
			//On met l'objet dans la BDD
			AlimentDAO createAliment = new AlimentDAO();
			createAliment.create(aliment);

			//On récupère le Stage et on le ferme
			Stage stage = (Stage) validateButton.getScene().getWindow();
			stage.close();

		}
	}

	public void handleCancelButton() {

		//On récupère le Stage et on le ferme
		Stage stage = (Stage) validateButton.getScene().getWindow();
		stage.close();

	}
}