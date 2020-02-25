package com.magasin.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.magasin.beans.Categories;
import com.magasin.beans.Produits;
import com.magasin.beans.Users;
import com.magasin.business.controller.CategorieControl;
import com.magasin.business.controller.ProduitControl;

public class Main extends JFrame {

	private JPanel contentPane;
	private List<Categories> listeCategorie;
	private String [] colonnesCategorie={"Categorie"};
	private Object [][]dataCategorie;
	private TableModel tablemodelCategorie;
	private String [] colonnesProduit={"Libelle","Quantite","Categorie"};
	private Object [][]dataProduit;
	private TableModel tablemodelProduit;
	private JTable table;
	private JTable table_1;

	public Main(Users utilisateur,List<Produits> listeProduit, List<Categories>listCategorie) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 765, 346);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Categorie");
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_2 = new JMenu("Produit");
		menuBar.add(mnNewMenu_2);
		
		JSeparator separator = new JSeparator();
		menuBar.add(separator);
		
		String log= "vous devez passer par l authentification";
		
		JMenu mnNewMenu_1 = new JMenu(log);
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Authentification();
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 11, 414, 218));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 357, 269);
		contentPane.add(panel);
		panel.setLayout(null);
		panel.setVisible(false);
		
		JLabel lblNewLabel = new JLabel("Categorie   :");
		lblNewLabel.setBounds(10, 11, 76, 14);
		panel.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 337, 192);
		panel.add(scrollPane);
		
		
		
		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.setBounds(10, 239, 89, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Modifier");
		btnNewButton_1.setBounds(136, 239, 89, 23);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Supprimer");
		btnNewButton_2.setBounds(258, 239, 89, 23);
		panel.add(btnNewButton_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(382, 11, 357, 269);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		panel_1.setVisible(false);
		
		JLabel lblNewLabel_1 = new JLabel("Produit   :");
		lblNewLabel_1.setBounds(10, 11, 68, 14);
		panel_1.add(lblNewLabel_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 36, 337, 190);
		panel_1.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		JButton btnNewButton_3 = new JButton("Ajouter");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AjouterProduit ajouterProduit = new AjouterProduit(utilisateur,listeProduit);
				ajouterProduit.setVisible(true);
				dispose();
			}
		});
		btnNewButton_3.setBounds(10, 237, 89, 23);
		panel_1.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Modifier");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModifierProduit modifierProduit = new ModifierProduit(utilisateur,listeProduit);
				modifierProduit.setVisible(true);
				dispose();
			}
		});
		btnNewButton_4.setBounds(141, 237, 89, 23);
		panel_1.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Supprimer");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SupprimerProduit supprimerProduit = new SupprimerProduit(utilisateur,listeProduit);
				supprimerProduit.setVisible(true);
				dispose();
			}
		});
		btnNewButton_5.setBounds(258, 237, 89, 23);
		panel_1.add(btnNewButton_5);
		
		if(utilisateur.getLogin()!=null) {
			log=utilisateur.getNom_utilisateur()+" "+utilisateur.getPrenom_utilisateur();
			mnNewMenu_1.setText(log);
			mnNewMenu.setVisible(true);
			mntmNewMenuItem_2.setText("LogOut");
			
		}else {
			mnNewMenu.setVisible(false);
			mntmNewMenuItem_2.setText("LogIn");
		}

		mnNewMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel.setVisible(true);
				panel_1.setVisible(false);
				setBounds(100, 100, 386, 346);
			}
		});

		mnNewMenu_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel.setVisible(false);
				panel_1.setVisible(true);
				setBounds(100, 100, 386, 346);
				panel_1.setBounds(10, 11, 357, 269);
			}
		});
		

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AjouterCategorie ajouterCategorie = new AjouterCategorie(utilisateur,listeProduit,listCategorie);
				ajouterCategorie.setVisible(true);
				dispose();
			}
		});
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModifierCategorie modifierCategorie = new ModifierCategorie(utilisateur,listeProduit,listCategorie);
				modifierCategorie.setVisible(true);
				dispose();
			}
		});
		
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SupprimerCategorie supprimerCategorie = new SupprimerCategorie(utilisateur,listeProduit,listCategorie);
				supprimerCategorie.setVisible(true);
				dispose();
			}
		});
		
		CategorieControl categorieControl = new CategorieControl();
		listeCategorie=categorieControl.getAll();
		int dataSizeCategorie=listeCategorie.size();
		dataCategorie=new Object[dataSizeCategorie][2];
		for(int k=0;k<dataSizeCategorie;k++)
		{
			dataCategorie[k][0]=listeCategorie.get(k).getLibelle();
			dataCategorie[k][1]=listeCategorie.get(k).getId();
		}
		tablemodelCategorie = new DefaultTableModel(dataCategorie,colonnesCategorie);
		table= new JTable(tablemodelCategorie);
		scrollPane.add(table);
		scrollPane.setViewportView(table);
		
		//ProduitControl produitControl = new ProduitControl();
		//listeProduit=produitControl.getAllProduits();
		//listProduit=listeProduit;
		int dataSizeProduit=listeProduit.size();
		dataProduit=new Object[dataSizeProduit][4];
		for(int k=0;k<dataSizeProduit;k++)
		{
			dataProduit[k][0]=listeProduit.get(k).getLibelle();
			dataProduit[k][1]=listeProduit.get(k).getQuantite();
			int id_cate=listeProduit.get(k).getId_Categorie();
			String libelle = categorieControl.getById(id_cate);
			dataProduit[k][2]=libelle;
			dataProduit[k][3]=listeProduit.get(k).getId();
		}
		tablemodelProduit = new DefaultTableModel(dataProduit,colonnesProduit);
		table_1= new JTable(tablemodelProduit);
		
		scrollPane_1.add(table_1);
		scrollPane_1.setViewportView(table_1);
		
	}
}