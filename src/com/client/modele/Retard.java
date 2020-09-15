package com.client.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Retard {

	private IntegerProperty matricule;
	private StringProperty nom;
	private StringProperty prenom;
	
	private StringProperty classe;
	
	//private IntegerProperty paye;
	//private IntegerProperty total;
	private DoubleProperty paye;
	private DoubleProperty total;
	
	
	
	
	
	
	public Retard(int matricule, String nom, String prenom, String classe, double montantPaye, double montantTotal) {
		super();
		this.matricule =new SimpleIntegerProperty(matricule);
		this.nom = new SimpleStringProperty(nom);
		this.prenom = new SimpleStringProperty(prenom);
		this.classe = new SimpleStringProperty(classe);
		this.paye = new SimpleDoubleProperty(montantPaye) ;
		this.total = new SimpleDoubleProperty(montantTotal);
	}






	public final IntegerProperty matriculeProperty() {
		return this.matricule;
	}
	

	public final int getMatricule() {
		return this.matriculeProperty().get();
	}
	

	public final void setMatricule(final int matricule) {
		this.matriculeProperty().set(matricule);
	}


	public final StringProperty nomProperty() {
		return this.nom;
	}
	

	public final String getNom() {
		return this.nomProperty().get();
	}
	
	public final void setNom(final String nom) {
		this.nomProperty().set(nom);
	}
	
	public final StringProperty prenomProperty() {
		return this.prenom;
	}
	

	public final String getPrenom() {
		return this.prenomProperty().get();
	}


	public final void setPrenom(final String prenom) {
		this.prenomProperty().set(prenom);
	}
	

	public final StringProperty classeProperty() {
		return this.classe;
	}
	
	public final String getClasse() {
		return this.classeProperty().get();
	}
	
	public final void setClasse(final String classe) {
		this.classeProperty().set(classe);
	}
	

	public final DoubleProperty payeProperty() {
		return this.paye;
	}
	
	
	public final double getPaye() {
		return this.payeProperty().get();
	}
	
	public final void setPaye(final double paye) {
		this.payeProperty().set(paye);
	}
	

	public final DoubleProperty totalProperty() {
		return this.total;
	}
	
	public final double getTotal() {
		return this.totalProperty().get();
	}
	
	public final void setTotal(final double total) {
		this.totalProperty().set(total);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
