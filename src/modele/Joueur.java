package modele;

import grille.Grille;

public class Joueur extends Element{
	private static Joueur joueur;
	
	private String nom;
	private int hp;
	private int hpMax;
	private int mana;
	private int manaMax;
	private int exp;
	private int lv;
	private int pa;
	private int paRegen;
	
	private int force;
	private int resistance;
	private int agilite;
	
	private Arme arme;
	private Armure armure;
	private Inventaire inventaire;
	
	
	private Joueur(){}
	
	private Joueur(int ligne, int colonne, String nom, int hp, int mana, int pa, int force, int resistance, int agilite, Arme arme, Armure armure, Inventaire sac)
	{
		super(ligne, colonne);
		this.nom = nom;
		this.hp = hp;
		this.hpMax = hp;
		this.mana = mana;
		this.manaMax = mana;
		this.exp = 0;
		this.lv = 1;
		this.pa = pa;
		this.paRegen = pa;
		this.force = force;
		this.resistance = resistance;
		this.agilite = agilite;
		this.arme = arme;
		this.armure = armure;
		this.inventaire= sac;
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





	public int getMana() {
		return mana;
	}





	public void setMana(int mana) {
		this.mana = mana;
	}





	public int getManaMax() {
		return manaMax;
	}





	public void setManaMax(int manaMax) {
		this.manaMax = manaMax;
	}





	public int getExp() {
		return exp;
	}





	public void setExp(int exp) {
		this.exp = exp;
	}





	public int getLv() {
		return lv;
	}





	public void setLv(int lv) {
		this.lv = lv;
	}





	public int getPa() {
		return pa;
	}





	public void setPa(int pa) {
		this.pa = pa;
	}





	public int getPaRegen() {
		return paRegen;
	}





	public void setPaRegen(int paRegen) {
		this.paRegen = paRegen;
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
	
	
	
	public String getNom() {
		return nom;
	}





	public void setNom(String nom) {
		this.nom = nom;
	}





	public Arme getArme() {
		return arme;
	}





	public void setArme(Arme arme) {
		this.arme = arme;
	}





	public Armure getArmure() {
		return armure;
	}





	public void setArmure(Armure armure) {
		this.armure = armure;
	}





	public Inventaire getInventaire() {
		return inventaire;
	}





	public void setInventaire(Inventaire inventaire) {
		this.inventaire = inventaire;
	}





	public void afficheEtat()
	{
		System.out.print(this.nom + 
		"\nArme :" + this.getArme() +
		"\nArmure :" + this.getArmure() +
		"\nLv :" + this.lv +
		"\nExperience :" + this.exp +
		"\nPa :" + this.pa +
		"/20\nHP :" + this.getHp()+ "/" + this.getHpMax());
		if (this.arme == null && this.armure!=null)
			System.out.println(
					"\nForce :" + this.getForce() + "( +0)" +
					"\nResistance :"+ this.getResistance() + "( +" + armure.getDefenseBuff()+ ")" +
					"\nAgilite :" + this.getAgilite());
		else
			if (this.armure == null && this.arme !=null)
				System.out.println(
						"\nForce :" + this.getForce() + "( +" + arme.getAttaqueBuff()+ ")" +
						"\nResistance :"+ this.getResistance() + "( +0)" +
						"\nAgilite :" + this.getAgilite());
			else if (this.armure == null && this.arme ==null)
				System.out.println(
						"\nForce :" + this.getForce() + "( +0)" +
						"\nResistance :"+ this.getResistance() + "( +0)" +
						"\nAgilite :" + this.getAgilite());
			else
			System.out.println(
					"\nForce :" + this.getForce() + "( +" + arme.getAttaqueBuff()+ ")" +
					"\nResistance :"+ this.getResistance() + "( +" + armure.getDefenseBuff()+ ")" +
					"\nAgilite :" + this.getAgilite());
		
	}
	
	public String toString()
	{
		return "J";
	}

	public static Joueur getInstance()
	{
		if (joueur==null)
		{
			joueur = new Joueur();
		}
		return joueur;
	}

		
	
}