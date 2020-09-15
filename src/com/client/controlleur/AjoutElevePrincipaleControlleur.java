package com.client.controlleur;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import com.client.Client;
import com.client.modele.EleveDto;
import com.scolarite.metier.Eleve;
import com.scolarite.metier.Scolarite;
import com.scolarite.rmi.IEcoleRemote;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class AjoutElevePrincipaleControlleur implements Initializable{

	private EleveDto eleveDto;
	private IEcoleRemote stub;
	private Eleve eleve;
	private Scolarite scolarite;
	
	@FXML
	private TextField nomField;
	
	@FXML
	private TextField prenomField;
	
	@FXML
	private ComboBox<String> classeCombo;
	
	@FXML
	private ComboBox<String> typeCombo;
	
	
	
	
	@FXML
    private void handleEnregistrer() {
		if(isInputValid()) {
			eleve=null;
			Eleve eleveDao=null;
    		 scolarite=null;                                                 
    	    eleve = new Eleve();	scolarite=new Scolarite();						
    		eleve.setScolarite(scolarite);
    	    eleve.setClasse(classeCombo.getSelectionModel().getSelectedItem().toString());
    		eleve.setNom(nomField.getText());
    		eleve.setPrenom(prenomField.getText());
    		//eleve.setMatricule(tempEleve.getMatricule());
    		eleve.getScolarite().setCategorie(typeCombo.getSelectionModel().getSelectedItem().toString());
			
			
            stub=Client.getStub();
            try {
            	//System.out.println(eleve.getNom());System.out.println(eleve.getPrenom());
            	//System.out.println(eleve.getClasse());System.out.println(eleve.getScolarite().getCategorie());
				eleveDao=stub.ajouterEleve(eleve);
			
					Alert dialog = new Alert(AlertType.INFORMATION);
					dialog.setTitle("Enregistrement d'un etudiant");
					dialog.setHeaderText("Enregistrement reussi");
					dialog.setContentText("Matricule : " +eleveDao.getMatricule());
					dialog.showAndWait();
			        refresh();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		/*	eleveDto.setNom(nomField.getText());
			eleveDto.setPrenom(prenomField.getText());
			eleveDto.setClasse(classeCombo.getSelectionModel().getSelectedItem().toString());
			eleveDto.setType(classeCombo.getSelectionModel().getSelectedItem().toString());*/
			
		}
	}
	
	@FXML
	private void handleAnnuler() {
		refresh();
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
		
		
		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Show the error message.
			Alert alert = new Alert(AlertType.ERROR);
			//alert.initOwner(dialogStage);
			alert.setTitle("Champs invalides ");
			alert.setHeaderText("SVP mettez des champs valides");
			alert.setContentText(errorMessage);
			alert.showAndWait();
			return false;
			}
	  }

	
	
	
	
	
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		//ObservableList<String> type = FXCollections.observableArrayList("ancien","affecte","titre");
		//ObservableList<String> classe = FXCollections.observableArrayList("6e","5e","4e","3e","2nde","1ere","tle");
		typeCombo.getItems().addAll("ancien","affecté","titre");
		classeCombo.getItems().addAll("6e","5e","4e","3e","2nde","1ere","tle");
	}
	
    
	private void refresh() {
		nomField.setText("");
		prenomField.setText("");
		classeCombo.setPromptText("Precisez la classe");
		typeCombo.setPromptText("Precisez le type");
	}


}
