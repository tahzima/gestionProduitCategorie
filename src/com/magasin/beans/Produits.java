package com.magasin.beans;

public class Produits {
	private int id;
	private String libelle;
	private int quantite;
	private int Id_Categorie;
	
	public Produits() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public int getId_Categorie() {
		return Id_Categorie;
	}

	public void setId_Categorie(int id_Categorie) {
		Id_Categorie = id_Categorie;
	}
	
}
