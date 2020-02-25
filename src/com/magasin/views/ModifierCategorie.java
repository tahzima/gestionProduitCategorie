package com.magasin.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.magasin.beans.Categories;
import com.magasin.beans.Produits;
import com.magasin.beans.Users;
import com.magasin.business.controller.CategorieControl;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ModifierCategorie extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private List<Categories> listCategorie = new ArrayList<Categories>();
	private int test = 0;
	private CategorieControl categoriControl;

	public ModifierCategorie(Users utilisateur,List<Produits> listeProduit,List<Categories> listeCategorie) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 195);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		CategorieControl categorieControl = new CategorieControl();
		
		JButton btnNewButton = new JButton("Modifier");
		btnNewButton.setBounds(28, 129, 89, 23);
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
		btnNewButton_1.setBounds(306, 129, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Id");
		lblNewLabel.setBounds(50, 39, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Libelle");
		lblNewLabel_1.setBounds(50, 80, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(276, 77, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index=comboBox.getSelectedIndex();
				textField.setText(listeCategorie.get(index).getLibelle());
			}
		});
		comboBox.setBounds(276, 36, 119, 20);
		
		int dataSize=listeCategorie.size();
		Object [][]data=new Object[dataSize][2];
		for(int k=0;k<dataSize;k++)
		{
			data[k][0]=listeCategorie.get(k).getId();
			comboBox.addItem(data[k][0]);
		}
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int indexVirtuelle=comboBox.getSelectedIndex();
				int indexReel=listeCategorie.get(indexVirtuelle).getId();
				String libelle=textField.getText();
				CategorieControl categorieControl = new CategorieControl();
				boolean result=categorieControl.modifierCategorie(indexReel, libelle);
				if(result) {
					JOptionPane.showMessageDialog(null, "Categorie Bien Modifie");
					test=1;
				}else {
					JOptionPane.showMessageDialog(null, "Error");
				}
			}
		});
		contentPane.add(comboBox);
	}
}
