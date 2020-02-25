package com.magasin.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.magasin.beans.Categories;
import com.magasin.beans.Produits;
import com.magasin.beans.Users;
import com.magasin.business.controller.CategorieControl;
import com.magasin.business.controller.ProduitControl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class ModifierProduit extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private List<Categories> listCategorie;
	//private List<Produits> listProduit;
	private ProduitControl produitControl;
	private int test = 0;
	
	public ModifierProduit(Users utilisateur,List<Produits> listProduit) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 239, 217);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Libelle");
		lblNewLabel.setBounds(10, 47, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Quantite");
		lblNewLabel_1.setBounds(10, 75, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Categorie");
		lblNewLabel_2.setBounds(10, 116, 68, 14);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(130, 44, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(130, 72, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(130, 113, 86, 20);
		contentPane.add(comboBox);
		
		listCategorie = new ArrayList<Categories>();
		CategorieControl categorieControl = new CategorieControl();
		listCategorie=categorieControl.getAll();
		int dataSize=listCategorie.size();
		Object [][]data=new Object[dataSize][2];
		for(int k=0;k<dataSize;k++)
		{
			data[k][0]=listCategorie.get(k).getId();
			data[k][1]=listCategorie.get(k).getLibelle();
			comboBox.addItem(data[k][0]);
		}
		
		JButton btnNewButton = new JButton("Modifier");
		btnNewButton.setBounds(10, 152, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(test==1) {
					produitControl = new ProduitControl();
					List<Produits> newListProduit = new ArrayList<Produits>();
					newListProduit=produitControl.getAllProduits();
					Main menu = new Main(utilisateur,newListProduit,null);
					menu.setVisible(true);
					dispose();
				}else {
					Main menu = new Main(utilisateur,listProduit,null);
					menu.setVisible(true);
					dispose();
				}
			}
		});
		btnNewButton_1.setBounds(127, 152, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_3 = new JLabel("Produit");
		lblNewLabel_3.setBounds(10, 11, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(130, 8, 86, 20);
		contentPane.add(comboBox_1);

		//listProduit = new ArrayList<Produits>();
		//ProduitControl produitcontrol = new ProduitControl();
		//listProduit=produitcontrol.getAllProduits();
		int dataSizeProduit=listProduit.size();
		Object [][]dataProduit=new Object[dataSizeProduit][4];
		for(int k=0;k<dataSizeProduit;k++)
		{
			dataProduit[k][0]=listProduit.get(k).getId();
			dataProduit[k][1]=listProduit.get(k).getLibelle();
			dataProduit[k][2]=listProduit.get(k).getQuantite();
			dataProduit[k][3]=listProduit.get(k).getId_Categorie();
			comboBox_1.addItem(dataProduit[k][0]);
		}
		
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idProduit = Integer.valueOf(comboBox_1.getSelectedItem().toString());
				/*textField.setText(listProduit.get(idProduit).getLibelle());
				String quantite = String.valueOf(listProduit.get(idProduit).getQuantite());
				textField_1.setText(quantite);
				comboBox.setSelectedItem(listProduit.get(idProduit).getId_Categorie());*/
				ProduitControl produitControl = new ProduitControl();
				Produits produit = new Produits();
				produit =produitControl.getById(idProduit);
				textField.setText(String.valueOf(produit.getLibelle()));
				textField_1.setText(String.valueOf(produit.getQuantite()));
				comboBox.setSelectedItem(produit.getId_Categorie());
			}
		});
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProduitControl produitControl = new ProduitControl();
				String libelle=textField.getText();
				int quantite = Integer.parseInt(textField_1.getText());
				int id=Integer.valueOf(comboBox_1.getSelectedItem().toString());
				int id_Categorie=Integer.valueOf(comboBox.getSelectedItem().toString());
				if(libelle !=null && !libelle.isEmpty() && quantite>0) {
					boolean result = produitControl.modifierProduit(id, libelle, quantite, id_Categorie);
					if(result) {
						JOptionPane.showMessageDialog(null, "Produit Bien Modifier");
						test=1;
					}else {
						JOptionPane.showMessageDialog(null, "Erreur");
					}					
				}else{
					JOptionPane.showMessageDialog(null, "Erreur de remplissage des champs");
				}
			}
		});
		
	}
}
