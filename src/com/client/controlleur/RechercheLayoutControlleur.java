package com.client.controlleur;

import java.net.URL;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.client.Client;
import com.client.modele.PaiementDto;
import com.scolarite.metier.Eleve;
import com.scolarite.metier.Paiement;
import com.scolarite.rmi.IEcoleRemote;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class RechercheLayoutControlleur implements Initializable {

	//private Client client;
	private Eleve eleve;
	private IEcoleRemote stub;
	private List<Paiement> listePaiements;
	//private ObservableList<Paiement> paiements=FXCollections.observableArrayList();
	@FXML
	private AnchorPane anchorPane;
	
	@FXML
	private Label matriculeLabel;
	
	@FXML
	private Label nomLabel;
	
	
	@FXML
	private TableView<PaiementDto> payementTable;
	
	@FXML
	private TableColumn<PaiementDto, Integer> reference;
	
	@FXML
	private TableColumn<PaiementDto, Double> montant;
	
	@FXML
	private TableColumn<PaiementDto, Date> datePaiement;
	
	
	@FXML
	private Label prenomLabel;
	
	@FXML
	private Label classeLabel;
	
	@FXML
	private Label typeLabel;
	
	@FXML
	private Label label;
	
	@FXML
	private TextField rechercheField;
	
	@FXML
	private void handleRechercher() {
		if(isValide()) {
			eleve=null;
		  stub=Client.getStub();
		  try {
			eleve=stub.rechercherEleve(Integer.parseInt(rechercheField.getText()));
			if(eleve!=null) {
				matriculeLabel.setText(Integer.toString(eleve.getMatricule()) );
				nomLabel.setText(eleve.getNom());
				prenomLabel.setText(eleve.getPrenom());
				classeLabel.setText(eleve.getClasse());
				typeLabel.setText(eleve.getScolarite().getCategorie());
			}else {
				label.setText("Echec ce matricule n'exite pas ");
			}
			
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
	@FXML
	private void handleModifier() {
		
	}
	
	

	@FXML
	private void handleHistorique() {
		if(isValide()) {
			ObservableList<PaiementDto> paiements=FXCollections.observableArrayList();
			stub=Client.getStub();
			listePaiements=null;//paiements=null;
			try {
				listePaiements=stub.listePaiementEleve(Integer.parseInt(rechercheField.getText()));
				for (Paiement paiement : listePaiements) {
					paiements.addAll(new PaiementDto(paiement.getNumPaiement(), paiement.getMontantPaye(), paiement.getDate()));
				}
				reference.setCellValueFactory(new PropertyValueFactory<PaiementDto,Integer>("reference"));
				montant.setCellValueFactory(new PropertyValueFactory<PaiementDto,Double>("montant"));
				datePaiement.setCellValueFactory(new PropertyValueFactory<PaiementDto,Date>("datePaiement"));
				
				
				payementTable.setItems(paiements);
				
			} catch (NumberFormatException | RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	
	
	private boolean isValide() {
		String errorMessage="";
		if (rechercheField.getText() == null || rechercheField.getText().length() == 0) {
			errorMessage = "Le numero matricule est invalide \n";
		} else {
			// try to parse the postal code into an int.
			try {
			   Integer.parseInt(rechercheField.getText());
			} catch (NumberFormatException e) {
			errorMessage = "Le numero matricule est invalide (entrer un entier)!\n";
		    }
		}
		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Show the error message.
			Alert alert = new Alert(AlertType.ERROR);
// a revoir			alert.initOwner(dialogStage);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);
			alert.showAndWait();
			return false;
			}
	}
	
	private void remplirInformation(Eleve eleve) {  // Eleve est issu du serveur c'est different de EleveDto
		//matriculeLabel.setText(Integer.toString(eleve.getMatricule()));
		matriculeLabel.setText(""+(eleve.getMatricule()));
		nomLabel.setText(eleve.getNom());
		prenomLabel.setText(eleve.getPrenom());
		//typeLabel.setText(eleve.getType());
		classeLabel.setText(eleve.getClasse());

	}
	
	@FXML
	private void initialize() {
		label.setText("");
		matriculeLabel.setText("");
		nomLabel.setText("");
		prenomLabel.setText("");
		classeLabel.setText("");
		typeLabel.setText("");
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		refresh();
		
		
	}
    
	private void refresh() {
		matriculeLabel.setText("");
		nomLabel.setText("");
		prenomLabel.setText("");
		classeLabel.setText("");
		typeLabel.setText("");
		label.setText("");
	}
	
	
}
