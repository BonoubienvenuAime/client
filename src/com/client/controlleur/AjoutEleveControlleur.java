package com.client.controlleur;

import com.client.modele.EleveDto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class AjoutEleveControlleur {

	@FXML
	private TextField matriculeField;
	
	@FXML
	private TextField nomField;
	
	@FXML
	private TextField prenomField;
	
	@FXML
	private ComboBox classeCombo;
	
	@FXML
	private ComboBox typeCombo;
	
	private Stage dialogStage;
	private EleveDto eleveDto;
	private boolean okClicked=false;
	//ObservableList<String> type = FXCollections.observableArrayList("ancien","affecte","titre");
	//ObservableList<String> classe = FXCollections.observableArrayList("6e","5e","4e","3e","2nde","1ere","tle");

	
	@FXML
	private void initialize() {
		typeCombo.getItems().addAll("ancien","affecté","titre");
		classeCombo.getItems().addAll("6e","5e","4e","3e","2nde","1ere","tle");
	}
	
	public void setDialog(Stage dialogStage) {
		this.dialogStage=dialogStage;
	}
	
	public void setEleve(EleveDto eleve) {
		
		this.eleveDto=eleve; //nouveau
		 
		matriculeField.setText(Integer.toString(eleve.getMatricule()));
		nomField.setText(eleve.getNom());
		prenomField.setText(eleve.getPrenom());
		classeCombo.setPromptText(eleve.getClasse());
		
		typeCombo.setPromptText(eleve.getType());

		
	}
	
public void setAjoutEleve(EleveDto eleve) {
		
		this.eleveDto=eleve; //nouveau
		 
		

		
	}


public void setEleve2(EleveDto eleve) {
	this.eleveDto = eleve;
	
	
}
	
	public boolean isOkClicked() {
		return okClicked;
	}
	
	@FXML
	private void handleOk() {
		
		if(isInputValid()) {
			//eleveDto.setMatricule(Integer.parseInt(matriculeField.getText()));

			eleveDto.setNom(nomField.getText());
			eleveDto.setPrenom(prenomField.getText());
			eleveDto.setClasse(classeCombo.getSelectionModel().getSelectedItem().toString());
			eleveDto.setType(classeCombo.getSelectionModel().getSelectedItem().toString());
			
			okClicked=true;
			dialogStage.close();
		}
	}
	
	@FXML
	private void handleClose() {
		dialogStage.close();
	}
	
	private boolean isInputValid() {
		
		String errorMessage="";
		if(nomField.getText()==null || nomField.getText().length()==0)
			errorMessage+="le nom n'est pas valide !\n";
		if(prenomField.getText()==null || prenomField.getText().length()==0)
			errorMessage+="le prenom n'est pas valide !\n";
		if(typeCombo.getSelectionModel().getSelectedItem()==null)
			errorMessage+="le champ type est invalide ! \n";
		if(classeCombo.getSelectionModel().getSelectedItem()==null)
			errorMessage+="le champ classe est invalide ! \n";
		/*if (matriculeField.getText() == null || matriculeField.getText().length() == 0) {          ///
			errorMessage += "No valid postal code!\n";
		} else {
			// try to parse the postal code into an int.
			try {
			   Integer.parseInt(matriculeField.getText());
			} catch (NumberFormatException e) {
			errorMessage += "No valid postal code (must be an integer)!\n";
		    }
		}*/
		
		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Show the error message.
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Champs invalides ");
			alert.setHeaderText("SVP mettez des champs valides");
			alert.setContentText(errorMessage);
			alert.showAndWait();
			return false;
			}
	  }
}
