package application.view;

import application.Main;
import application.model.DAO.AlimentDAO;
import application.model.beans.Aliment;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PrincipalViewController {

	@FXML private TableView alimentTV;
	@FXML private TableColumn alimentColumn;
	@FXML private TableColumn quantiteColumn;
	@FXML private TableColumn dateAchatColumn;
	@FXML private TableColumn datePeremptionColumn;
	@FXML private Button addItem;
	@FXML private Button quitButton;
	
	public void initialize() {

		try {
		//On initialise le tableview avec les entrées de la BDD
		
		//On récupère la liste d'aliments en BDD
		AlimentDAO listeAliment = new AlimentDAO();
		ObservableList<Aliment> aliment = listeAliment.fetchEntries();
		
		//On prépare les colonnes du tableview et on le kpeuple avec les objets
		ObservableList<TableColumn> columns = alimentTV.getColumns();		
		
		columns.get(0).setCellValueFactory(new PropertyValueFactory("nom"));
		columns.get(1).setCellValueFactory(new PropertyValueFactory("quantite"));
		columns.get(2).setCellValueFactory(new PropertyValueFactory("dateAchat"));
		columns.get(3).setCellValueFactory(new PropertyValueFactory("datePeremption"));
		alimentTV.setItems(aliment);

		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public void handleAddItem() {		
		
		try {
			//On charge le fichier FXML pour l'utiliser comme scène
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/SecondaryView.fxml"));
			Parent root = (Parent) loader.load();
			
			//On insère la liste des aliments dans le controller pour
			//pouvoir mettre la vue à jour à partir de l'autre controller
			SecondaryViewController svc = loader.getController();
			svc.setAlimentData(alimentTV.getItems());

			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
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
