package com.client.modele;

import java.util.Date;

public class PaiementDto {

	private int reference;
	private double montant;
	private Date datePaiement;
	
	
	
	
	public PaiementDto(int reference, double montant, Date datePaiement) {
		super();
		this.reference = reference;
		this.montant = montant;
		this.datePaiement = datePaiement;
	}
	public int getReference() {
		return reference;
	}
	public void setReference(int reference) {
		this.reference = reference;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public Date getDatePaiement() {
		return datePaiement;
	}
	public void setDatePaiement(Date datePaiement) {
		this.datePaiement = datePaiement;
	}
	
	
	
	

	/*public PaiementDto(int reference, double montant, String date) {
       this.reference=new SimpleIntegerProperty(reference);
       this.montant= new SimpleDoubleProperty(montant);
       this.datePaiement=new SimpleStringProperty(date);
	}

	private IntegerProperty SimpleIntegerProperty(int reference2) {
		// TODO Auto-generated method stub
		return null;
	}

	public final IntegerProperty referenceProperty() {
		return this.reference;
	}
	
	public final int getReference() {
		return this.referenceProperty().get();
	}
	
	public final void setReference(final int reference) {
		this.referenceProperty().set(reference);
	}
	
	public final DoubleProperty montantProperty() {
		return this.montant;
	}
	
	public final double getMontant() {
		return this.montantProperty().get();
	}
	
	public final void setMontant(final double montant) {
		this.montantProperty().set(montant);
	}

	public final StringProperty datePaiementProperty() {
		return this.datePaiement;
	}
	

	public final String getDatePaiement() {
		return this.datePaiementProperty().get();
	}
	

	public final void setDatePaiement(final String datePaiement) {
		this.datePaiementProperty().set(datePaiement);
	}
	*/
	
	
	
}
