package com.client.controlleur;

import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.List;

import com.client.Client;
import com.client.modele.EleveDto;
import com.scolarite.metier.Eleve;
import com.scolarite.metier.Scolarite;
import com.scolarite.rmi.IEcoleRemote;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class MenuControlleur {

	
	private LoginControlleur loginControlleur;
	private boolean ajout=false;
	private IEcoleRemote stub;
	private Eleve eleve;
	public static String  action;
	private Scolarite scolarite;
	private ObservableList<EleveDto> data= FXCollections.observableArrayList(); 
	
	@FXML
	private TableView<EleveDto> eleveTable;
	
	@FXML
	private TableColumn<EleveDto, Integer> matricule;
	
	@FXML
	private TableColumn<EleveDto, String> nom;
	
	@FXML
	private TableColumn<EleveDto, String> prenom;
	
	@FXML
	private Label matriculeLabel;
	
	@FXML
	private Label nomLabel;
	
	@FXML
	private Label prenomLabel;
	
	@FXML
	private Label typeLabel;
	
	@FXML
	private Label classeLabel;
	
	
	@FXML
	private void initialize() {
		
		//matricule.setCellValueFactory(cellData->cellData.getValue().matriculeProperty());

		matricule.setCellValueFactory(new PropertyValueFactory<EleveDto,Integer>("matricule"));
        nom.setCellValueFactory(cellData->cellData.getValue().nomProperty());
        
        prenom.setCellValueFactory(cellData->cellData.getValue().prenomProperty());

        
        showEleveDetails(null);
        
        eleveTable.getSelectionModel().selectedItemProperty().addListener(
        		(observable,oldValue,newValue)->showEleveDetails(newValue)
        		
        		);
        
        this.stub=Client.getStub(); //nouveau-------------------------------------------------------------
        List<Eleve> liste=null;
         try {
			liste=stub.listeEleves();
			for (Eleve eleve : liste) {
				data.add(new EleveDto(eleve.getMatricule(),eleve.getNom(),eleve.getPrenom(),eleve.
						getScolarite().getCategorie(),eleve.getClasse()));
			}
			eleveTable.setItems(data);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	 
   /* public void setClient(Client client){
        this.client= client;
        this.stub=client.getStub(); //nouveau-------------------------------------------------------------
        List<Eleve> liste=null;
         try {
			liste=stub.listeEleves();
			for (Eleve eleve : liste) {
				data.add(new EleveDto(eleve.getMatricule(),eleve.getNom(),eleve.getPrenom(),eleve.
						getScolarite().getCategorie(),eleve.getClasse()));
			}
			eleveTable.setItems(data);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
        
        
        
    }*/
    
    public void setLoginControlleur(LoginControlleur loginControlleur) {
    	this.loginControlleur=loginControlleur;
    }
    
    private void showEleveDetails(EleveDto eleve) {
    	
    	if(eleve!=null) {
    		matriculeLabel.setText(Integer.toString(eleve.getMatricule()));
    		nomLabel.setText(eleve.getNom());
    		prenomLabel.setText(eleve.getPrenom());
    		typeLabel.setText(eleve.getType());
    		classeLabel.setText(eleve.getClasse());
    	} else {
    		matriculeLabel.setText("");
    		nomLabel.setText("");
    		prenomLabel.setText("");
    		typeLabel.setText("");
    		classeLabel.setText("");
    	}
    	
    	
    }
    
    @FXML
    private void handleAjouter() {
    	//ajout=true;
    	EleveDto tempEleve = new EleveDto();
    	boolean okClicked=loginControlleur.ajouterEleve(tempEleve);
    	if(okClicked) {
    		Eleve eleveDao=null;
    		eleve=null;  scolarite=null;                                                 // nouveau
    	    eleve = new Eleve();	scolarite=new Scolarite();						//il faut rendre le champ matricule non editable
    		eleve.setScolarite(scolarite);
    	    eleve.setClasse(tempEleve.getClasse());
    		eleve.setNom(tempEleve.getNom());
    		eleve.setMatricule(tempEleve.getMatricule());
    		eleve.getScolarite().setCategorie(tempEleve.getType());
    		try {
				eleveDao = stub.ajouterEleve(eleve);
				tempEleve.setMatricule(eleveDao.getMatricule());
				
				data.add(tempEleve);                         //ancien
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		
    	}
    		
    }
	
	/*public Client getClient() {
		return client;
	}*/



	public boolean isAjout() {
		return ajout;
	}



	public TableView<EleveDto> getEleveTable() {
		return eleveTable;
	}





	@FXML
	private void handleEditer() {
		EleveDto selectedEleve = eleveTable.getSelectionModel().getSelectedItem();
    	if(selectedEleve!=null) {
        	boolean okClicked=loginControlleur.showEleveEditDialog(selectedEleve);
        	if (okClicked) {
        		eleve=null;												//
        		eleve=new Eleve(); eleve.setClasse(selectedEleve.getClasse());		//
        		Scolarite scolarite = new Scolarite();eleve.setScolarite(scolarite);
        		eleve.setMatricule(selectedEleve.getMatricule());
        		eleve.setNom(selectedEleve.getNom());eleve.setPrenom(selectedEleve.getPrenom());	//
        		eleve.getScolarite().setCategorie(selectedEleve.getType());  //
        		try {
					stub.modifierEleve(eleve);//
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		showEleveDetails(selectedEleve);
        	}
    	}else {
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.initOwner(loginControlleur.getPrimaryStage());
    		alert.setTitle("Edition Invalide");
    		alert.setHeaderText("Aucune personne selectionne");
    		alert.setContentText(" Selectionnez une personne dans la table");
    		alert.showAndWait();
    	}
	}
	
	
	
	/*----------------------bon*/
	@FXML
	private void handleSupprimer() {
		int selectedIndex = eleveTable.getSelectionModel().getSelectedIndex();
    	if(selectedIndex >= 0) {
    		eleveTable.getItems().remove(selectedIndex);
    	}else {
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.initOwner(loginControlleur.getPrimaryStage());
    		alert.setTitle("Suppression invalide");
    		alert.setHeaderText("Aucune personne selectionnee");
    		alert.setContentText("Selectionnez une personne dans la table");
    		alert.showAndWait();
    	}
	}
}
