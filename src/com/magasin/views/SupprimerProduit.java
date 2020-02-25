package com.magasin.views;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.magasin.beans.Produits;
import com.magasin.beans.Users;
import com.magasin.business.controller.CategorieControl;
import com.magasin.business.controller.ProduitControl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SupprimerProduit extends JFrame {

	private JPanel contentPane;
	private ProduitControl produitControl;
	private int test=0;
	
	public SupprimerProduit(Users utilisateur,List<Produits> listProduit) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 262, 127);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Produit");
		lblNewLabel.setBounds(10, 11, 46, 14);
		contentPane.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(140, 8, 96, 20);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("Supprimer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int indexVirtuelle=comboBox.getSelectedIndex();
				int indexReel=listProduit.get(indexVirtuelle).getId();
				ProduitControl produitControl = new ProduitControl();
				boolean result= produitControl.supprimerProduit(indexReel);
				if(result) {
					test=1;
					JOptionPane.showMessageDialog(null, "Produit Bien supprimer");
				}else {
					JOptionPane.showMessageDialog(null, "Error");
				}
			}
		});
		btnNewButton.setBounds(10, 60, 89, 23);
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
		btnNewButton_1.setBounds(149, 60, 89, 23);
		contentPane.add(btnNewButton_1);
		
		//listProduit = new ArrayList<Produits>();
		ProduitControl produitcontrol = new ProduitControl();
		//listProduit=produitcontrol.getAllProduits();
		int dataSizeProduit=listProduit.size();
		Object [][]dataProduit=new Object[dataSizeProduit][2];
		for(int k=0;k<dataSizeProduit;k++)
		{
			dataProduit[k][0]=listProduit.get(k).getLibelle();
			dataProduit[k][1]=listProduit.get(k).getId();
			comboBox.addItem(dataProduit[k][0]);
		}
		
	}
}
