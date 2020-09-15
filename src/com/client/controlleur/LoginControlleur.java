package com.client.controlleur;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.client.Client;

import com.client.modele.AdminDto;
import com.client.modele.EleveDto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginControlleur implements Initializable {
	
	private AdminDto admin;
	private Client client;
	private Stage loginStage;
	private boolean okClicked=false;
	private BorderPane root;
	private Stage primaryStage;
	
	@FXML
	private TextField login;
	
	@FXML
	private PasswordField password;
	
	@FXML
	private Label loginLabel;
	
	@FXML
	private Label passwordLabel;
	
	@FXML
	private Label label;
	
	
	/*@FXML
	private void handleConnecter(ActionEvent event) {
		
		if(login.getText().equals("admin") && password.getText().equals("admin"))
			okClicked=true;
		loginStage.close();
		//System.out.print(login.getText());
	}*/
	
	@FXML
	private void handleConnecter(ActionEvent event) {
		
		if(login.getText().equals("admin") && password.getText().equals("admin"))
		{
			 try {
				 okClicked=true;
				root=FXMLLoader.load(getClass().getResource("/com/client/vue/FenetrePrincipale.fxml"));
				Scene fenetrePrincipale= new Scene(root); 
				primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				primaryStage.setScene(fenetrePrincipale);
				
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Client.class.getResource("/com/client/vue/Menu.fxml"));
				
				BorderPane menu =(BorderPane) loader.load();
				root.setCenter(menu);
				MenuControlleur controller = loader.getController();
				controller.setLoginControlleur(this);
				primaryStage.setFullScreen(true);
				primaryStage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			label.setText("Connexion refusee");
		}
			
		//loginStage.close();
		//System.out.print(login.getText());
	}

	@FXML
	private void handleAnnuler() {
		loginStage.close();
	}
	
	public void setLoginStage(Stage loginStage) {
		this.loginStage= loginStage;
	}
	
	
	public void setClient(Client client) {
		this.client=client;
	}
	
	public BorderPane getBorderPane() {
		return root;
	}
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	
	public boolean validConexion() {
		
		return okClicked;
	
	}
	
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

		
		//MenuControlleur.action="ajout"; //nouveau --------------------------------------------------
		
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
		 controller.setEleve2(eleve);
		
		dialogStage.showAndWait();
		return controller.isOkClicked();
	} catch (IOException e) {
		
		e.printStackTrace();
		return false;
	}
}

@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	// TODO Auto-generated method stub
	label.setText("");
}

	
	
	
	
}
