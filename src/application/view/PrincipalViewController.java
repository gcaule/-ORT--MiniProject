package application.view;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PrincipalViewController {

	@FXML private Button addItem;
	@FXML private Button quitButton;

	public void handleAddItem() {
		
		try {
			//On charge le fichier FXML pour l'utiliser comme scène
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/SecondaryView.fxml"));
			AnchorPane root = (AnchorPane) loader.load();

			Stage stage = new Stage();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Ajouter du manger");
			stage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void handleQuitButton() {
		
		//On récupère le Stage et on le ferme
		Stage stage = (Stage) quitButton.getScene().getWindow();
		stage.close();
		
	}

}
