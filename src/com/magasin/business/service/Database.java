package com.magasin.business.service;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
	
	private static Database instance = new Database();
	public static Database getInstance() {
		return instance;
	}
	private java.lang.String url = "jdbc:mysql://localhost:3306/gestionmagasin?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
	private java.lang.String user = "root";
	private java.lang.String passwd = "";
	private Connection conexion;
	private Database() {
		if (conexion == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conexion = DriverManager.getConnection(url, user, passwd);
 
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public Connection getConexion() {
		return conexion;
	}
}