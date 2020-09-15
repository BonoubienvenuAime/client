package com.client.controlleur;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

import com.client.Client;
import com.client.modele.EleveDto;
import com.client.modele.Retard;
import com.scolarite.metier.Eleve;
import com.scolarite.rmi.IEcoleRemote;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;



public class ListeDesRetardsControlleur implements Initializable{
	
	private IEcoleRemote stub;
//	ObservableList<EleveDto> listeRetard=FXCollections.observableArrayList();
	List<Eleve> liste;
	@FXML
	private TableView<EleveDto> eleveTableRetard;
	
	@FXML
	private TableColumn<EleveDto, Integer> matricule;
	
	@FXML
	private TableColumn<EleveDto, String> nom;
	
	@FXML
	private TableColumn<EleveDto, String> prenom;
	
	/*@FXML
	private TableColumn<Retard, String> classe;
	
	@FXML
	private TableColumn<Retard, Double> paye;
	
	@FXML
	private TableColumn<Retard, Double> total;*/

	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<EleveDto> listeRetard=FXCollections.observableArrayList();
		matricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
		 nom.setCellValueFactory(cellData->cellData.getValue().nomProperty());
	        
	     prenom.setCellValueFactory(cellData->cellData.getValue().prenomProperty());
		//listeRetard=null;
		liste=null;
		
		stub=Client.getStub();
		try {
			liste=stub.listeEleveAjourne();
			for (Eleve eleves : liste) {
				System.out.print(eleves.getNom());System.out.print(" "+eleves.getMatricule()); System.out.print("  "+eleves.getMontantPaye());
				System.out.print(" "+eleves.getScolarite().getMontant());
				System.out.println();
				listeRetard.add(new EleveDto(eleves.getMatricule(),eleves.getNom(),eleves.getPrenom()));
			}
			eleveTableRetard.setItems(listeRetard);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	
	    // classe.setCellValueFactory(cellData->cellData.getValue().classeProperty());
	    // paye.setCellValueFactory(new PropertyValueFactory<>("paye"));
	    // paye.setCellValueFactory(new PropertyValueFactory<>("total"));

	}
	
	
	

}
