package com.magasin.views;


import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.magasin.beans.Categories;
import com.magasin.beans.Produits;
import com.magasin.beans.Users;
import com.magasin.business.controller.CategorieControl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SupprimerCategorie extends JFrame {

	private JPanel contentPane;
	private List<Categories> listCategorie= new ArrayList<Categories>();
	private int test=0;
	private CategorieControl categoriControl;

	public SupprimerCategorie(Users utilisateur,List<Produits> listeProduit,List<Categories> listeCategorie) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 323, 157);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//CategorieControl categorieControl = new CategorieControl();
		//listCategorie=categorieControl.getAll();
		
		JLabel lblNewLabel = new JLabel("Categories");
		lblNewLabel.setBounds(37, 44, 68, 14);
		contentPane.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(177, 41, 90, 20);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("Supprimer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int indexVirtuelle=comboBox.getSelectedIndex();
				int indexReel=listeCategorie.get(indexVirtuelle).getId();
				CategorieControl categorieControl = new CategorieControl();
				boolean result= categorieControl.supprimerCategorie(indexReel);
				if(result) {
					JOptionPane.showMessageDialog(null, "Categorie Bien supprimer");
					test=1;
				}else {
					JOptionPane.showMessageDialog(null, "Error");
				}
			}
		});
		btnNewButton.setBounds(37, 84, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(test==1) {
					categoriControl = new CategorieControl();
					List<Categories> newListCategorie = new ArrayList<Categories>();
					newListCategorie=categoriControl.getAll();
					Main menu = new Main(utilisateur,listeProduit,newListCategorie);
					menu.setVisible(true);
					dispose();
				}else {
					Main menu = new Main(utilisateur,listeProduit,listeCategorie);
					menu.setVisible(true);
					dispose();
				}
			}
		});
		btnNewButton_1.setBounds(178, 84, 89, 23);
		contentPane.add(btnNewButton_1);
		
		int dataSize=listeCategorie.size();
		Object [][]data=new Object[dataSize][2];
		for(int k=0;k<dataSize;k++)
		{
			data[k][0]=listeCategorie.get(k).getLibelle();
			comboBox.addItem(data[k][0]);
		}
		
	}
}
