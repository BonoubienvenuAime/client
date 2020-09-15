package com.client.modele;

import javafx.beans.property.StringProperty;

public class AdminDto {

	private StringProperty login;
	private StringProperty password;
	
	
	public final StringProperty loginProperty() {
		return this.login;
	}
	
	public final String getLogin() {
		return this.loginProperty().get();
	}
	
	public final void setLogin(final String login) {
		this.loginProperty().set(login);
	}
	
	public final StringProperty passwordProperty() {
		return this.password;
	}
	
	public final String getPassword() {
		return this.passwordProperty().get();
	}
	
	public final void setPassword(final String password) {
		this.passwordProperty().set(password);
	}
	
	
	
}
