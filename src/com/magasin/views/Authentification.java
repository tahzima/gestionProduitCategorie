package com.magasin.views;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.magasin.beans.Categories;
import com.magasin.beans.Produits;
import com.magasin.beans.Users;
import com.magasin.business.controller.CategorieControl;
import com.magasin.business.controller.ProduitControl;

import javax.swing.JPasswordField;

public class Authentification extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Authentification frame = new Authentification();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Authentification() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 283, 164);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField("tahzimaIL");
		textField.setBounds(169, 11, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("login");
		lblNewLabel.setBounds(21, 14, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("password");
		lblNewLabel_1.setBounds(21, 60, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Connecter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String loginnn = textField.getText();
				String password = passwordField.getText();
				com.magasin.business.controller.AuthentificationControl auth = new com.magasin.business.controller.AuthentificationControl();
				if((loginnn!=null && password!=null) && (!loginnn.isEmpty()) && (!password.isEmpty())) {
					Users utilisateur =new Users();
					utilisateur=auth.authentifier(loginnn, password);
					if(utilisateur.getId()>0) {
						List<Produits> listProduit = new ArrayList<Produits>();
						List<Categories> listCategorie= new ArrayList<Categories>();
						ProduitControl produitControl = new ProduitControl();
						CategorieControl categorieControl =new CategorieControl();
						listProduit=produitControl.getAllProduits();
						listCategorie=categorieControl.getAll();
						Main menu= new Main(utilisateur,listProduit,listCategorie);
						menu.setVisible(true);
						dispose();
						
						
					}
					else {
						JOptionPane.showMessageDialog(null, "Erreur d'authentification");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Remplire les deux champs");
				}

			}
		});
		btnNewButton.setBounds(83, 88, 89, 23);
		contentPane.add(btnNewButton);
		
		passwordField = new JPasswordField("123456");
		passwordField.setBounds(169, 54, 86, 20);
		contentPane.add(passwordField);
	}
}
