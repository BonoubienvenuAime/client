package com.client;

import java.io.IOException;
import java.rmi.Naming;

import com.client.controlleur.AjoutEleveControlleur;
import com.client.controlleur.FenetrePrincipaleControlleur;
import com.client.controlleur.LoginControlleur;
import com.client.controlleur.MenuControlleur;
import com.client.modele.EleveDto;
import com.scolarite.rmi.IEcoleRemote;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Client extends Application {

	
	private BorderPane root;
	private Stage loginStage;
	private Stage primaryStage;
	private ObservableList<EleveDto> data= FXCollections.observableArrayList();
	private AnchorPane changeInterface;
	
	private static IEcoleRemote stub;




	public Client() {
		/*data.add(new EleveDto(1,"Hans", "Muster","ancien","6e"));
		data.add(new EleveDto(2,"Ruth", "Mueller","titre","4e"));
		data.add(new EleveDto(3,"Heinz", "Kurz","nouveau","6e"));
		data.add(new EleveDto(4,"Cornelia", "Meier","titre","2nd"));
		data.add(new EleveDto(5,"Werner", "Meyer","nouveau","1ere"));
		data.add(new EleveDto(6,"Lydia", "Kunz","ancien","tle"));
		data.add(new EleveDto(7,"Anna", "Best","nouveau","5e"));
		data.add(new EleveDto(8,"Stefan", "Meier","nouveau","4e"));
		data.add(new EleveDto(9,"Martin", "Mueller","ancien","5e"));*/
	}
	
	
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
	    stub = (IEcoleRemote) Naming.lookup("rmi://localhost:6789/scolarite");
		this.primaryStage=primaryStage;
		this.primaryStage.setTitle("GESTION DE SCOLARITE");
		/*fenetrePrincipale();
    	menuView();
		//loginStage=primaryStage;
	    boolean okClicked = this.loginView();
	    //System.out.print(okClicked);
	    if(okClicked) {
	    	 
	    	primaryStage.show();

	    }
		/*FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ClientRmi.class.getResource("vue/Login.fxml"));
		BorderPane pane=loader.load();
		Scene scene = new Scene(pane);
    	primaryStage.setScene(scene);
		primaryStage.setTitle("Authentification");
	    primaryStage.show();*/
		//borderPan*/
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Client.class.getResource("vue/Login.fxml"));
		
		Parent root=loader.load();
		Scene scene = new Scene(root);
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		LoginControlleur controller=loader.getController();
		controller.setLoginStage(primaryStage);
		
	}
	
	public static IEcoleRemote getStub() {
		return stub;
	}
	
	
	
	
	
	
	public void fenetrePrincipale() {
		
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Client.class.getResource("vue/FenetrePrincipale.fxml"));
			root=loader.load();
			FenetrePrincipaleControlleur controlleur=loader.getController();        //nouveau
			//changeInterface=controlleur.getChangeInterface();  //nouveau
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Stage getPrimaryStage() {
		  return this.primaryStage;
		}
	
	public boolean loginView() {
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Client.class.getResource("vue/Login.fxml"));
			BorderPane pane=loader.load();
			Scene scene = new Scene(pane);
		    loginStage = new Stage();
			loginStage.setTitle("Authentification");
			//loginStage.initModality(Modality.WINDOW_MODAL);
			//loginStage.initOwner(primaryStage);
			loginStage.setScene(scene);
			
			LoginControlleur controller=loader.getController();
			
	
			controller.setLoginStage(loginStage);
			loginStage.showAndWait();
            return controller.validConexion();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	

	/*public void menuView() {
		
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Client.class.getResource("vue/Menu.fxml"));
			//AnchorPane menu =(AnchorPane) loader.load();
			BorderPane menu =(BorderPane) loader.load();

			// Set person overview into the center of root layout.
			root.setCenter(menu);
			//changeInterface.getChildren().setAll(menu);
			
			MenuControlleur controller = loader.getController();
		 	controller.setClient(this);
		} catch (Exception e) {
			e.printStackTrace();
			}
	}*/
	
	
public boolean showEleveEditDialog(EleveDto eleve) {
		
		try {

			MenuControlleur.action="edition";
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Client.class.getResource("vue/AjoutEleve.fxml"));
			BorderPane page = (BorderPane) loader.load();
			Scene scene = new Scene(page);
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Editer un Eleve");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			dialogStage.setScene(scene);
			
			AjoutEleveControlleur controller = loader.getController();
			controller.setDialog(dialogStage);
           // boolean ajout=controller.
			 controller.setEleve(eleve);
			
			dialogStage.showAndWait();
			return controller.isOkClicked();
		} catch (IOException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}

public boolean ajouterEleve(EleveDto eleve)  {
	try {

		
		MenuControlleur.action="ajout"; //nouveau --------------------------------------------------
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Client.class.getResource("vue/AjoutEleve.fxml"));
		BorderPane page = (BorderPane) loader.load();
		Scene scene = new Scene(page);
		Stage dialogStage = new Stage();
		dialogStage.setTitle("Editer un Eleve");
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(primaryStage);
		dialogStage.setScene(scene);
		
		AjoutEleveControlleur controller = loader.getController();
		controller.setDialog(dialogStage);
       // boolean ajout=controller.
		 controller.setAjoutEleve(eleve);
		
		dialogStage.show();
		return controller.isOkClicked();
	} catch (IOException e) {
		
		e.printStackTrace();
		return false;
	}
}

	public ObservableList<EleveDto> getData() {
		return data;
	}
	
public static void main(String[] args) {
		
		launch(args);
		

	}


}
