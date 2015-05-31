package modele;

import grille.Grille;

public class Joueur extends Element{
	private static Joueur joueur;
	
	private String nom;
	private String classe;
	private int hp;
	private int hpMax;
	private int mana;
	private int manaMax;
	private int exp;
	private int pallier;
	private int lv;
	private int pa;
	private int paRegen;
	
	private int force;
	private int resistance;
	private int agilite;
	
	private Arme armeGauche;
	private Arme armeDroite;
	private Armure armure;
	private Inventaire inventaire;
	
	
	public Joueur(){}
	
	/*private Joueur(int ligne, int colonne, String nom, int hp, int mana, int pa, int force, int resistance, int agilite, Arme arme, Armure armure, Inventaire sac)
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
	}*/
	
	
	
	
	
	public int getHp() {
		return hp;
	}





	public void setHp(int hp) {
		this.hp = hp;
		if (this.hp>=this.hpMax)
		{
			this.hp = this.hpMax;
		}
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
		if (this.mana>=this.manaMax)
		{
			this.mana = this.manaMax;
		}
		if (this.mana<0)
		{
			this.mana=0;
		}
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


	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public int getPallier()
	{
		return pallier;
	}
	
	public void setPallier(int pallier)
	{
		this.pallier=pallier;
	}

	public Arme getArmeGauche() {
		return armeGauche;
	}

	public Arme getArmeDroite(){
		return armeDroite;
	}



	public void setArmeGauche(Arme arme) {
		this.armeGauche = arme;
	}

	public void setArmeDroite(Arme arme){
		this.armeDroite = arme;
	}



	public Armure getArmure() {
		if (this.armure == null)
			return null;
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
		int bonus_ClassG = 0;
		int bonus_ClassD = 0;
		if (this.classe=="Saber")
		{
			if (this.armeGauche!=null && this.armeGauche.getType()==ArmeType.Epee)
				bonus_ClassG = 100;
			if (this.armeDroite!=null && this.armeDroite.getType()==ArmeType.Epee)
				bonus_ClassD = 100;
		}
		if (this.classe=="Archer")
		{
			if (this.armeGauche!=null && this.armeGauche.getType()==ArmeType.Arc)
				bonus_ClassG = 100;
			if (this.armeDroite!=null && this.armeDroite.getType()==ArmeType.Arc)
				bonus_ClassD = 100;
		}
		System.out.print(this.nom + 
		"\nArme Gauche :" + this.getArmeGauche() +
		"\nArme Droite :" + this.getArmeDroite() +
		"\nArmure :" + this.getArmure() +
		"\nLv :" + this.lv +
		"\nExperience :" + this.exp +
		"\nPa :" + this.pa +
		"/20\nHP :" + this.getHp()+ "/" + this.getHpMax()+
		"\nMana :" + this.getMana() + "/" + this.getManaMax());
		
		if (this.armure!=null)
		{
			if (this.armeGauche !=null && this.armeDroite == null)
				System.out.println(
					"\nForce Gauche :" + this.getForce() + "( +" + (armeGauche.getAttaqueBuff()+bonus_ClassG)+ ")" +
					"\nForce Droite :" + this.getForce() + "( +0)" +
					"\nResistance :"+ this.getResistance() + "( +" + armure.getDefenseBuff()+ ")" +
					"\nAgilite :" + this.getAgilite());
			else if (this.armeGauche ==null && this.armeDroite != null)
				System.out.println(
						"\nForce Gauche :" + this.getForce() + "( +0)" +
						"\nForce Droite :" + this.getForce() + "( +" + (armeDroite.getAttaqueBuff()+bonus_ClassD)+ ")" +
						"\nResistance :"+ this.getResistance() + "( +" + armure.getDefenseBuff()+ ")" +
						"\nAgilite :" + this.getAgilite());
			else if (this.armeDroite == null && this.armeGauche == null)
				System.out.println(
						"\nForce Gauche :" + this.getForce() + "( +0)" +
						"\nForce Droite :" + this.getForce() + "( +0)" +
						"\nResistance :"+ this.getResistance() +  "( +" + armure.getDefenseBuff()+ ")" +
						"\nAgilite :" + this.getAgilite());
			else
				System.out.println(
						"\nForce Gauche :" + this.getForce() + "( +" + (armeGauche.getAttaqueBuff()+bonus_ClassG)+ ")" +
						"\nForce Droite :" + this.getForce() + "( +" + (armeDroite.getAttaqueBuff()+bonus_ClassD)+ ")" +
						"\nResistance :"+ this.getResistance() + "( +" + armure.getDefenseBuff()+ ")" +
						"\nAgilite :" + this.getAgilite());
		}
		else if (this.armure==null)
		{
			if (this.armeGauche == null && this.armeDroite == null)
				System.out.println(
						"\nForce Gauche :" + this.getForce() + "( +0)" +
						"\nForce Droite :" + this.getForce() + "( +0)" +
						"\nResistance :"+ this.getResistance() + "( +0)" +
						"\nAgilite :" + this.getAgilite());
			else if (this.armeGauche ==null && this.armeDroite != null)
				System.out.println(
					"\nForce Gauche :" + this.getForce() + "( +0)" +
					"\nForce Droite :" + this.getForce() + "( +" + (armeDroite.getAttaqueBuff()+bonus_ClassD)+")" +
					"\nResistance :"+ this.getResistance() + "( +0)" +
					"\nAgilite :" + this.getAgilite());
			else if (this.armeGauche !=null && this.armeDroite ==null)
				System.out.println(
						"\nForce Gauche :" + this.getForce() + "( +" + (armeGauche.getAttaqueBuff()+bonus_ClassG)+")" +
						"\nForce Droite :" + this.getForce() + "( +0)" +
						"\nResistance :"+ this.getResistance() + "( +0)" +
						"\nAgilite :" + this.getAgilite());
			else
				System.out.println(
						"\nForce Gauche :" + this.getForce() + "( +" + (armeGauche.getAttaqueBuff()+bonus_ClassG)+")" +
						"\nForce Droite :" + this.getForce() + "( +" + (armeDroite.getAttaqueBuff()+bonus_ClassD)+")" +
						"\nResistance :"+ this.getResistance() + "( +0)" +
						"\nAgilite :" + this.getAgilite());
			
		}
		
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