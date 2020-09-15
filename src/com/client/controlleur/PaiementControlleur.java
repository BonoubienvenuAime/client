package com.client.controlleur;

import java.io.IOException;
import java.rmi.RemoteException;

import com.client.Client;
import com.scolarite.metier.Eleve;
import com.scolarite.metier.Paiement;
import com.scolarite.rmi.IEcoleRemote;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class PaiementControlleur {
	
	
	private IEcoleRemote stub;
	private Eleve eleve;
	private Paiement paiement;
	@FXML
	private TextField matriculeField;
	
	@FXML
	private Label label;
	
	//@FXML
	//private AnchorPane anchorPane;
	
	@FXML
	private Label matriculeLabel;
	
	@FXML
	private Label nomLabel;
	
	@FXML
	private Label prenomLabel;
	
	@FXML
	private Label classeLabel;
	
	@FXML
	private Label typeLabel;
	
	@FXML
	private Label totalLabel;
	
	@FXML
	private Label payeLabel;
	
	
	@FXML
	private TextField versementField;
	
	@FXML
	private Button payement;
	
	@FXML
	private void initialize() {
		label.setText("");
		refresh();
	}
	
	private double montant;
	private double scolarite;
	
	
	@FXML
	private void handleValider() {
		// faire une condition dans la recuperation du matricule si le matricule est present on affiche sinon on affiche un
		//message d'erreur
	//	double montant;
		//double scolarite; // c'est le montant de la scolarite que l'eleve doit payer
		eleve=null;
		if(isValide()) {
			stub=Client.getStub();
			try {
				//montant=stub.montantTotalPaye(Integer.parseInt(matriculeField.getText()));
				//payeLabel.setText(Double.toString(montant));
				
				eleve=stub.rechercherEleve(Integer.parseInt(matriculeField.getText()));
				if(eleve!=null) {
					matriculeLabel.setText(Integer.toString(eleve.getMatricule()) );
					nomLabel.setText(eleve.getNom());
					prenomLabel.setText(eleve.getPrenom());
					classeLabel.setText(eleve.getClasse());
					typeLabel.setText(eleve.getScolarite().getCategorie());
					montant=stub.montantTotalPaye(Integer.parseInt(matriculeField.getText()));
					
					//scolarite=stub.montantScolarite(Integer.parseInt(matriculeField.getText()));
					//total.setText(Double.toString(scolarite));
					
					payeLabel.setText(Double.toString(montant));
					//payement.setDisable(false);
				}else {
					refresh();
					Alert alert=new Alert(AlertType.INFORMATION);
					alert.setTitle("recherche de matricule");
					alert.setHeaderText("");
					alert.setContentText("le matricule n'existe pas");
					alert.showAndWait();
				}
				
				
				
				
			} catch (NumberFormatException | RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			/*try {
				BorderPane pane = FXMLLoader.load(getClass().getResource("/com/client/vue/LayoutPaiement.fxml"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}/*else{
			Alert dialog = new Alert(AlertType.INFORMATION);
			dialog.setTitle("An information dialog-box");
			dialog.setHeaderText("An information dialog with header");
			dialog.setContentText("Le matricule n'existe pas" );
			dialog.showAndWait();
	      }*/
		
		
	}
	
	private boolean isValide() {
		String errorMessage="";
		if (matriculeField.getText() == null || matriculeField.getText().length() == 0) {
			errorMessage = "Le numero matricule est invalide \n";
		} else {
			// try to parse the postal code into an int.
			try {
			   Integer.parseInt(matriculeField.getText());
			} catch (NumberFormatException e) {
			errorMessage = "Le numero matricule est invalide (entrer un entier)!\n";
		    }
		}
		if (errorMessage.length() == 0){
			return true;
		} else {
			// Show the error message.
			refresh();
			Alert alert = new Alert(AlertType.ERROR);
// a revoir			alert.initOwner(dialogStage);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);
			alert.showAndWait();
			return false;
			}
	}
	
	private void refresh() {
		matriculeLabel.setText("");
		nomLabel.setText("");
		prenomLabel.setText("");
		classeLabel.setText("");
		totalLabel.setText("");
		payeLabel.setText("");
		typeLabel.setText("");
		versementField.setText("");
	}
	
	@FXML
	private void handlePayement() {
		stub=Client.getStub();
		if(valide()) {
			// recuperer la valeur de champ versement l'ajouter au montant paye et le comparer au montant total que
			// l'eleve doit payer 
			paiement=null;paiement=new Paiement();
			//eleve=null;eleve=new Eleve();
			paiement.setEleve(new Eleve()); // tester apres paiement.setEleve(eleve)
			paiement.setMontantPaye(Double.parseDouble(versementField.getText()));
			paiement.getEleve().setMatricule(eleve.getMatricule());
			try {
				if(Double.parseDouble(versementField.getText())+montant<scolarite) {
					stub.enregistrerPaiement(paiement);
				}else {
					Alert alert = new Alert(AlertType.ERROR);
				
								alert.setTitle("Paiement de scolarite");
								alert.setHeaderText("Paiement invalide");
								alert.setContentText("le montant saisi est incorrect");
								alert.showAndWait();
				}
				
				refresh();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
				
		
	}
	
	
	private boolean valide() {
		
		String errorMessage="";
		if (versementField.getText() == null || versementField.getText().length() == 0) {
			errorMessage = "Le numero matricule est invalide \n";
		} else {
			// try to parse the postal code into an int.
			try {
			   Double.parseDouble(matriculeField.getText());
			} catch (NumberFormatException e) {
			errorMessage = "Le numero matricule est invalide (entrer un entier)!\n";
		    }
		}
		if (errorMessage.length() == 0){
			return true;
		} else {
			// Show the error message.
			refresh();
			Alert alert = new Alert(AlertType.ERROR);
// a revoir			alert.initOwner(dialogStage);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);
			alert.showAndWait();
			return false;
			}
	}

}
