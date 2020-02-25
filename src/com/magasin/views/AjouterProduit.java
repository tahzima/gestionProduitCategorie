package com.magasin.views;


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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AjouterProduit extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private List<Categories> listCategorie;
	private ProduitControl produitControl;
	private int test=0;

	public AjouterProduit(Users utilisateur,List<Produits>listProduit) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 259, 216);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Libelle");
		lblNewLabel.setBounds(10, 11, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Quantite");
		lblNewLabel_1.setBounds(10, 59, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Categorie");
		lblNewLabel_2.setBounds(10, 110, 76, 14);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(149, 8, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(149, 56, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(149, 107, 86, 20);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.setBounds(10, 153, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.setBounds(146, 153, 89, 23);
		contentPane.add(btnNewButton_1);
		
		listCategorie = new ArrayList<Categories>();
		CategorieControl categorieControl = new CategorieControl();
		listCategorie=categorieControl.getAll();
		int dataSize=listCategorie.size();
		Object [][]data=new Object[dataSize][2];
		for(int k=0;k<dataSize;k++)
		{
			data[k][0]=listCategorie.get(k).getLibelle();
			data[k][1]=listCategorie.get(k).getId();
			comboBox.addItem(data[k][1]);
		}
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProduitControl produitControl = new ProduitControl();
				String libelle=textField.getText();
				int quantite = Integer.parseInt(textField_1.getText());
				int id=Integer.valueOf(comboBox.getSelectedItem().toString());
				if(libelle !=null && !libelle.isEmpty() && quantite>0) {
					boolean result = produitControl.ajouterProduit(libelle, quantite, id);
					if(result) {
						JOptionPane.showMessageDialog(null, "Produit Bien Ajouter");
						test=1;
					}else {
						JOptionPane.showMessageDialog(null, "Erreur");
					}					
				}else{
					JOptionPane.showMessageDialog(null, "Erreur de remplissage des champs");
				}
			}
		});
		
		/*btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});*/
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
	}
}
