package grille;

import modele.Element;
import modele.Joueur;
import modele.Monstre;

public class Grille {
	private Element [][]grille;
	private int ligne;
	private int colonne;
	
	public Grille(int i, int j)
	{
		ligne=i;
		colonne=j;
		grille = new Element[i][j];
	}
	
	//getters et setters
	public Element[][] getGrille() {
		return grille;
	}

	public void setGrille(Element[][] grille) {
		this.grille = grille;
	}

	public int getLigne() {
		return ligne;
	}

	public void setLigne(int ligne) {
		this.ligne = ligne;
	}

	public int getColonne() {
		return colonne;
	}

	public void setColonne(int colonne) {
		this.colonne = colonne;
	}
	
	//methode
	public void set(int i, int j, Element e)//met un element dans une case
	{
		grille[i][j]= e;
	}
	
	public boolean vide(int i, int j)//verifie si une case est vide
	{
		if (grille[i][j]==null)
		{
			return true;
		}
		else 
			return false;
			
	}
	
	public void clearScreen()//fais sauter des lignes pour avoir un affichage séparé à chaque action
	{
		for (int i=0; i<5; i++)
		{
			System.out.println();
		}
	}
	
	public void afficher()//affiche la grille
	{
		clearScreen();
		for (int i=0; i<ligne; i++)
		{
			System.out.print("|");
			for (int j=0; j<colonne; j++)
			{
				
				if (vide(i,j))
				{
					System.out.print(" ");
				}
				else
					System.out.print(grille[i][j]);
				
				System.out.print("|");
			}
			System.out.println();
		}
	}
	
	
	
}

