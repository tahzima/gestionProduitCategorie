package com.magasin.business.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.magasin.beans.Produits;
import com.magasin.business.service.Database;

public class ProduitControl {
	public List<Produits> getAllProduits() {
			Connection conexion = Database.getInstance().getConexion();
			List<Produits> lists = new ArrayList<Produits>();
			try {
				Statement state = conexion != null ? conexion.createStatement() : null ;
				ResultSet resultProduit = state.executeQuery("SELECT * FROM produits");
				while (resultProduit.next()) {
					Produits produit = new Produits();
					produit.setId(resultProduit.getInt("id"));
					produit.setLibelle(resultProduit.getString("libelle"));
					produit.setQuantite(resultProduit.getInt("quantite"));
					produit.setId_Categorie(resultProduit.getInt("id_categorie"));
					lists.add(produit);
					
				}
				state.close();
				resultProduit.close();
			} catch (Exception e) {
				e.printStackTrace();
			} 
			return lists;
	}
	public boolean ajouterProduit(String libelle,int quantite,int id_Categorie) {
		
		Connection conexion = Database.getInstance().getConexion();
		boolean pass = false;
		try {
			java.sql.Statement state = conexion != null ? conexion.createStatement() : null ;
			int res = state.executeUpdate("insert into produits(libelle,quantite,id_categorie) values('"+libelle+"',"+quantite+","+id_Categorie+")");
			if(res>0) {
				pass=true;
			}
			state.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return pass;
	}
	public boolean supprimerProduit(int id) {
		
		Connection conexion = Database.getInstance().getConexion();
		boolean pass = false;
		try {
			java.sql.Statement state = conexion != null ? conexion.createStatement() : null ;
			int res= state.executeUpdate("delete from produits where id="+id+"");
			if(res>0) {
				pass=true;
			}
			state.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return pass;
	}
	public boolean modifierProduit(int id,String libelle,int quantite,int id_categorie) {
		
		Connection conexion = Database.getInstance().getConexion();
		boolean pass = false;
		try {
			java.sql.Statement state = conexion != null ? conexion.createStatement() : null ;
			int res = state.executeUpdate("UPDATE produits SET libelle='"+libelle+"',quantite="+quantite+",id_categorie="+id_categorie+" WHERE id="+id+"");
			if(res>0) {
				pass=true;
			}
			state.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return pass;
	}

	public Produits getById(int idProduit) {
		Connection conexion = Database.getInstance().getConexion();
		Produits produit = new Produits();
		try {
			Statement state = conexion != null ? conexion.createStatement() : null;
			ResultSet resultProduit = state.executeQuery("select * from produits where id="+idProduit+"");
			while (resultProduit.next()) {
				produit.setId(resultProduit.getInt("id"));
				produit.setQuantite(resultProduit.getInt("quantite"));
				produit.setLibelle(resultProduit.getString("libelle"));
				produit.setId_Categorie(resultProduit.getInt("id_categorie"));
			}
			state.close();
			resultProduit.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return produit;
	}
}