package modele;

import grille.Grille;

public class Monstre extends Element{
	private String nom;
	private int hp;
	private int hpMax;
	
	private int force;
	private int resistance;
	private int agilite;
	
	public Monstre (int ligne, int colonne, String nom, int hp, int force, int resistance, int agilite)
	{
		super(ligne, colonne);
		this.nom = nom;
		this.hp = hp;
		this.hpMax = hp;
		this.force = force;
		this.resistance = resistance;
		this.agilite = agilite;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
		if (this.hp<0)
		{
			this.hp=0;
		}
	}

	public int getHpMax() {
		return hpMax;
	}

	public void setHpMax(int hpMax) {
		this.hpMax = hpMax;
	}

	public int getForce() {
		return force;
	}

	public void setForce(int force) {
		this.force = force;
	}

	public int getResistance() {
		return resistance;
	}

	public void setResistance(int resistance) {
		this.resistance = resistance;
	}

	public int getAgilite() {
		return agilite;
	}

	public void setAgilite(int agilite) {
		this.agilite = agilite;
	}

	public void afficheEtat()
	{
		System.out.println("Nom : " + this.getNom() + "\nHP :" + this.getHp()+ "/" + this.getHpMax()+ "\nForce :" + this.getForce() +"\nResistance :"+ this.getResistance() +"\nAgilite :" + this.getAgilite());
	}
	/*
	public void deplacer(int direction, Grille grille)
	{
		int ligne = this.getLigne();
		int colonne = this.getColonne();
		int x=0;
		int y=0;
		if (direction == 0)
		{
			//if (this.getLigne()>0 && (grille.vide(ligne-1, colonne) || grille.getGrille()[ligne-1][colonne].isPion()))
			if (this.getLigne()>0 && grille.vide(ligne-1, colonne))
			{
				x-=1;
			}
			
		}
		else if (direction == 1)
		{
			if (this.getLigne()<grille.getLigne()-1 && grille.vide(ligne+1, colonne))
			{
				x++;
			}
			
		}
		else if (direction == 2)
		{
			if (this.getColonne()>0 && grille.vide(ligne, colonne-1))
			{
				y-=1;
			}
			
		}
		else if (direction == 3)
		{
			if (this.getColonne()<grille.getColonne()-1 && grille.vide(ligne, colonne+1))
			{
				y++;
			}
			
		}
		
		grille.set(ligne, colonne, null);
		this.setLigne(ligne+x);
		this.setColonne(colonne+y);
		grille.set(this.getLigne(), this.getColonne(), this);
	}
	*/
	public String toString()
	{
		return "M";
	}
}
