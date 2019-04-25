package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			
			//On charge le fichier FXML pour l'utiliser comme scène
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/PrincipalView.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Mon manger dans mon frigo");
			stage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
