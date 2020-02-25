package com.magasin.business.controller;

import java.sql.Connection;
import java.sql.ResultSet;

import com.magasin.beans.Users;
import com.magasin.business.service.Database;

public class AuthentificationControl {
	
	public Users authentifier(java.lang.String loginnn, java.lang.String password ) {
		Connection conexion = Database.getInstance().getConexion();
		Users utilisateur = new Users();
		try {
			java.sql.Statement state = conexion != null ? conexion.createStatement() : null ;
			
			ResultSet result = state.executeQuery("SELECT * FROM utilisateur where login like '"+loginnn+"'");
			while (result.next()) {
				if(password.equals(result.getString("password"))){
					utilisateur.setId(result.getInt("id"));
					utilisateur.setNom_utilisateur(result.getString("nom_utilisateur"));
					utilisateur.setPrenom_utilisateur(result.getString("prenom_utilisateur"));
					utilisateur.setLogin(result.getString("login"));
					utilisateur.setPassword(result.getString("password"));
				}
			}
			state.close();
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return utilisateur;
	}
	
}