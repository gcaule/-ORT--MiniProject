package application.view;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.stream.IntStream;

import application.model.DAO.AlimentDAO;
import application.model.beans.Aliment;
import javafx.collections.ObservableList;
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

	//Cette liste et ce constructeur nous permettra de mettre à jour l'autre scène
	private ObservableList<Aliment> data ;

	public void setAlimentData(ObservableList<Aliment> data) {
		this.data = data ;
	}

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

			//try {

				//Oui, on récupère toutes les infos
				String nomAliment = foodNameTF.getText();
				int quantite = (int) quantityComboBox.getValue();
				LocalDate dateAchat = dateAchatDP.getValue();
				System.out.println(dateAchat);
				LocalDate datePeremption = datePeremptionDP.getValue();
				
				//DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				
				Date dateAchatDB = java.sql.Date.valueOf(String.valueOf(dateAchatDP.getValue()));
				Date datePeremptionDB = java.sql.Date.valueOf(datePeremption);

				/*DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				String dateAchatS = dateAchat.format(format);
				System.out.println(dateAchatS);
				String datePeremptionS = datePeremption.format(format);

				DateFormat inputFormat = new SimpleDateFormat("yyyyMM-dd");
				DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");

				Date dateAchatBuff = inputFormat.parse(dateAchatS);
				System.out.println(dateAchatBuff);
				Date datePerempBuff = inputFormat.parse(datePeremptionS);

				String achatBuff = outputFormat.format(dateAchatBuff);
				System.out.println(achatBuff);
				String perempBuff = outputFormat.format(datePerempBuff);

				Date dateAchatDB = outputFormat.parse(achatBuff);
				System.out.println(dateAchatDB);
				Date datePeremptionDB = new SimpleDateFormat("dd/MM/yyyy").parse(perempBuff);*/

				//On met les infos dans un objet
				Aliment aliment = new Aliment();
				aliment.setNom(nomAliment);
				aliment.setQuantite(quantite);
				aliment.setDateAchat(dateAchatDB);
				aliment.setDatePeremption(datePeremptionDB);

				//On met l'objet dans la BDD et dans le Tableview
				AlimentDAO createAliment = new AlimentDAO();
				createAliment.create(aliment);
				data.add(aliment);

			/*} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/

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