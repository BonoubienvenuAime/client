package com.client.controlleur;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;

public class FenetrePrincipaleControlleur {

	@FXML
	private BorderPane rootPane;
	
	
	@FXML
	private void handleRechercher() {
		
		
		try {
			BorderPane pane = FXMLLoader.load(getClass().getResource("/com/client/vue/RechercheLayout.fxml"));
			
			//FXMLLoader loader = new FXMLLoader();
			//loader.setLocation(getClass().getResource("/com/client/vue/RechercherLayout.fxml"));
			//BorderPane pane = (BorderPane) loader.load();
			rootPane.setCenter(pane);	
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*public AnchorPane getChangeInterface() {
		
		return changeInterface;
	}*/
	
	@FXML
	private void handleListerEleve() {
		
	}
	
	
	@FXML
	private void handleListeRetard() {
		try {
			TitledPane pane = FXMLLoader.load(getClass().getResource("/com/client/vue/Retard.fxml"));
			rootPane.setCenter(pane);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@FXML
	private void handleRecu() {
		
	}
	
	@FXML
	private void handlePayer() {
		try {
			TitledPane pane = FXMLLoader.load(getClass().getResource("/com/client/vue/Paiement.fxml"));
			
			rootPane.setCenter(pane);	
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	private void handleAjouter() {
		try {
			BorderPane pane = FXMLLoader.load(getClass().getResource("/com/client/vue/AjoutElevePrincipale.fxml"));
			rootPane.setCenter(pane);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@FXML
	private void handleListe() {
		try {
			BorderPane pane = FXMLLoader.load(getClass().getResource("/com/client/vue/Menu.fxml"));
			
			rootPane.setCenter(pane);	
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	private void handleAjouterEleve() {
		try {
			TitledPane pane = FXMLLoader.load(getClass().getResource("/com/client/vue/AjoutElevePrincipale.fxml"));
			rootPane.setCenter(pane);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
