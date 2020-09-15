package com.client.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EleveDto {

	private IntegerProperty matricule;
	private StringProperty nom;
	private StringProperty prenom;
	private StringProperty type;
	private StringProperty classe;
	
	//private PaiementDto paiement;
	
	
	/*public PaiementDto getPaiement() {
		return paiement;
	}

	public void setPaiement(PaiementDto paiement) {
		this.paiement = paiement;
	}*/

	public EleveDto() {
		
		this(null,null,null,null,null);
		
	}

	public EleveDto(int matricule, String nom, String prenom) {
	
		this.matricule = new SimpleIntegerProperty(matricule);
		this.nom = new SimpleStringProperty(nom);
		this.prenom = new SimpleStringProperty(prenom);
	}

	
	public EleveDto(int matricule, String nom, String prenom, String type,String classe) {
		
		this.matricule = new SimpleIntegerProperty(matricule);
		this.nom = new SimpleStringProperty(nom);
		this.prenom = new SimpleStringProperty(prenom);;
		this.type = new SimpleStringProperty(type);
		this.classe = new SimpleStringProperty(classe);
	}

	
	public EleveDto(Object object, Object object2, Object object3, Object object4, Object object5) {
		// TODO Auto-generated constructor stub
	}

	public void setClasse(StringProperty classe) {
		this.classe = classe;
	}

	public final IntegerProperty matriculeProperty() {
		return this.matricule;
	}
	
	public final int getMatricule() {
		return this.matricule.get();
	}
	
	public final void setMatricule(final int matricule) {
		this.matricule.set(matricule);
	}
	
	public final StringProperty nomProperty() {
		return this.nom;
	}
	
	public final String getNom() {
		return this.nom.get();
	}
	
	public final void setNom(final String nom) {
		this.nom.set(nom);
	}
	
	public final StringProperty prenomProperty() {
		return this.prenom;
	}
	
	public final String getPrenom() {
		return this.prenom.get();
	}
	
	public final void setPrenom(final String prenom) {
		this.prenom.set(prenom);
	}
	
	public final StringProperty typeProperty() {
		return this.type;
	}
	
	public final String getType() {
		return this.type.get();
	}
	
	public final void setType(final String type) {
		this.type.set(type);
	}

	public final StringProperty classeProperty() {
		return this.classe;
	}
	

	public final String getClasse() {
		return this.classe.get();
	}
	

	public final void setClasse(final String classe) {
		this.classe.set(classe);
	}
	
	
	
	
	
}
