package application.view;

import java.util.stream.IntStream;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class SecondaryViewController {

	@FXML private ComboBox quantityComboBox;
	@FXML private Button validateButton;

	@FXML public void initialize() {
		//On initialise le ComboBox avec les chiffres de 1 à 25, et on sélectionne 1 par défaut
		IntStream.rangeClosed(1,25).boxed().forEach(quantityComboBox.getItems()::add);
		quantityComboBox.getSelectionModel().selectFirst();
	}

	public void handleValidateButton() {
		//On récupère le Stage et on le ferme
		Stage stage = (Stage) validateButton.getScene().getWindow();
		stage.close();
	}

}
