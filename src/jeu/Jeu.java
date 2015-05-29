package jeu;

import java.util.Random;
import java.util.Scanner;

import controle.ServiceInventaire;
import controle.ServiceJoueur;
import modele.Arme;
import modele.ArmeEnum;
import modele.ArmeType;
import modele.Armure;
import modele.ArmureEnum;
import modele.Direction;
import modele.Element;
import modele.Inventaire;
import modele.Item;
import modele.Joueur;
import modele.Monstre;
import modele.Obstacle;
import modele.Potion;
import modele.PotionDeSoin;
import modele.Tresor;
import grille.Grille;

public class Jeu {

	private Grille grille;
	private Obstacle o1;
	private Obstacle o2;
	private Monstre m1;
	private Monstre m2;
	private Tresor tresor1;
	private Random rand = new Random();
	private Joueur joueur = new Joueur();
	private ServiceJoueur serviceJoueur = ServiceJoueur.getInstance();
	private ServiceInventaire serviceItem = new ServiceInventaire();
	
	//crée tous les items du jeu
	
	Armure armureDeBois = new Armure(ArmureEnum.ArmureDeBois);
	Arme epeeDeBois = new Arme(ArmeEnum.EpeeDeBois);
	Armure armureDeMetal = new Armure(ArmureEnum.ArmureDeMetal);
	Arme epeeDeMetal = new Arme(ArmeEnum.EpeeDeMetal);
	Arme excalibur = new Arme(ArmeEnum.Excalibur);
	Arme murasame = new Arme(ArmeEnum.Murasame);
	Arme arcDeBois = new Arme(ArmeEnum.ArcDeBois);
	Potion potionDeSoin = new PotionDeSoin();
	
	public Jeu()
	{
		
		//initialiseJeu();
	}
	
	//getter&setter
	public Grille getGrille() {
		return grille;
	}

	public void setGrille(Grille grille) {
		this.grille = grille;
	}

	public Obstacle getO1() {
		return o1;
	}

	public void setO1(Obstacle o1) {
		this.o1 = o1;
	}

	public Obstacle getO2() {
		return o2;
	}

	public void setO2(Obstacle o2) {
		this.o2 = o2;
	}

	public Monstre getM1() {
		return m1;
	}

	public void setM1(Monstre m1) {
		this.m1 = m1;
	}

	public Monstre getM2() {
		return m2;
	}

	public void setM2(Monstre m2) {
		this.m2 = m2;
	}

	public Random getRand() {
		return rand;
	}

	public void setRand(Random rand) {
		this.rand = rand;
	}

	public ServiceInventaire getServiceItem() {
		return serviceItem;
	}

	public void setServiceItem(ServiceInventaire serviceItem) {
		this.serviceItem = serviceItem;
	}
	
	public Joueur getJoueur() {
		return joueur;
	}

	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}
	
	//fin getter&setter

	public void initialiseJoueur(String nom, String classe)
	{
		serviceJoueur.initialiseJoueur(joueur, nom, classe);
	}
	
	public void initialiseJeu()//place et crée tout ce qu'il faut
	{
		grille = new Grille(5,5);//crée la grille
		
		//crée un joueur et l'initialise
		if (joueur.getClasse()=="Saber")
			serviceJoueur.initialiseJoueurComplement(joueur, 2, 2, epeeDeBois, armureDeBois);
		else if (joueur.getClasse()=="Archer")
			serviceJoueur.initialiseJoueurComplement(joueur, 2, 2, arcDeBois, armureDeBois);
		else if (joueur.getClasse()=="Caster")
			serviceJoueur.initialiseJoueurComplement(joueur, 2, 2, epeeDeBois, armureDeBois);
		//récupère l'inventaire du joueur pour pouvoir l'appeller de n'importe où
		//serviceItem.initialiseServiceItem(joueur.getInventaire().getInventaire());
		
		serviceItem.addItemInventaire(excalibur, joueur);
		serviceItem.addItemInventaire(arcDeBois, joueur);
		serviceItem.addItemInventaire(murasame, joueur);
		serviceItem.addItemInventaire(potionDeSoin, joueur);
		grille.set(2, 2, joueur);
		//debut du placement des obstacles aléatoirement pour l'instant
		int i =0;
		int ligne=0;
		int colonne=0;
		while (i<2)
		{
			ligne = rand.nextInt(grille.getLigne());
			colonne = rand.nextInt(grille.getColonne());
			
			if (i==0)
			{
				if (grille.vide(ligne, colonne))
				{
					o1 = new Obstacle(ligne, colonne);
					grille.set(ligne, colonne, o1);
					i++;
				}
				
			}
			else if(i==1)
			{
				if (grille.vide(ligne, colonne))
				{
					o2 = new Obstacle(ligne, colonne);
					grille.set(ligne,  colonne, o2);
					i++;
				}
				
			}
		}//////fin du placement des obstacles
		//Placement monstre1
		while (!grille.vide(ligne, colonne))
		{
			ligne = rand.nextInt(grille.getLigne());
			colonne = rand.nextInt(grille.getColonne());
		}
		
		m1 = new Monstre(ligne, colonne, "Neko", 1500, 10, 10, 10);
		grille.set(ligne, colonne, m1);
		//Fin placement du monstre1
		//Placement du monstre2
		while (!grille.vide(ligne, colonne))
		{
			ligne = rand.nextInt(grille.getLigne());
			colonne = rand.nextInt(grille.getColonne());
		}
		
		m2 = new Monstre(ligne, colonne, "Dragon", 2000, 100, 100, 100);
		grille.set(ligne, colonne, m2);
		//Fin du placement du monstre2
		grille.afficher();
	}
	
	
	public void deplacerElement(int direction, Element e)
	{
		int ligne = e.getLigne();
		int colonne = e.getColonne();
		int x=0;
		int y=0;
		if (direction == 0)
		{
			if (e.getLigne()>0 && (grille.vide(ligne-1, colonne) || grille.getGrille()[ligne-1][colonne] instanceof Tresor))
				x-=1;
		}
		else if (direction == 1)
		{
			if (e.getLigne()<grille.getLigne()-1 && (grille.vide(ligne+1, colonne)|| grille.getGrille()[ligne+1][colonne] instanceof Tresor))
				x++;
		}
		else if (direction == 2)
		{
			if (e.getColonne()>0 && (grille.vide(ligne, colonne-1)|| grille.getGrille()[ligne][colonne-1] instanceof Tresor))
				y-=1;
		}
		else if (direction == 3)
		{
			if (e.getColonne()<grille.getColonne()-1 && (grille.vide(ligne, colonne+1)|| grille.getGrille()[ligne][colonne+1] instanceof Tresor))

				y++;
		}
		if (grille.getGrille()[ligne+x][colonne+y] instanceof Tresor)
		{
			Item itemAleatoire = randomItem();
			System.out.println("Le coffre contient "+ itemAleatoire);
			serviceItem.addItemInventaire(itemAleatoire, joueur);
		}
		
		grille.set(ligne, colonne, null);
		joueur.setLigne(ligne+x);
		joueur.setColonne(colonne+y);
		grille.set(joueur.getLigne(), e.getColonne(), e);
		if (joueur.getColonne() != colonne || joueur.getLigne() != ligne)
		{
			usePA(1);
		}
		
		
	}
	
	
	
	
	
	public Item randomItem()
	{
		int typeItemAleatoire = rand.nextInt(2);
		if (typeItemAleatoire == 0)
		{
			return this.excalibur;
		}
		else if (typeItemAleatoire == 1)
		{
			return this.armureDeBois;
		}
		else if (typeItemAleatoire == 2)
		{
			return this.potionDeSoin;
		}
		else 
			return null;
	}
	
	
	
	
	
	
	
	public Element rechercheCombatPossible(Direction direction, int indiceMain)
	{
		int x=1;
		if ( (indiceMain==1 && joueur.getArmeGauche()!=null && joueur.getArmeGauche().getType()==ArmeType.Epee) ||
			 (indiceMain==1 && joueur.getArmeGauche()==null) ||
			 (indiceMain==2 && joueur.getArmeDroite()==null) ||
			 (indiceMain==2 && joueur.getArmeDroite()!=null && joueur.getArmeDroite().getType()==ArmeType.Epee))
		{
			
			switch (direction) {
			case HAUT : 
				if (joueur.getLigne()-x>=0 && !grille.vide(joueur.getLigne()-x, joueur.getColonne()) && grille.getGrille()[joueur.getLigne()-x][joueur.getColonne()] instanceof Monstre)
					return grille.getGrille()[joueur.getLigne()-x][joueur.getColonne()];
			break;
			case BAS :
				if (joueur.getLigne()+x<=grille.getLigne()-1 && !grille.vide(joueur.getLigne()+x, joueur.getColonne()) && grille.getGrille()[joueur.getLigne()+x][joueur.getColonne()] instanceof Monstre)
					return grille.getGrille()[joueur.getLigne()+x][joueur.getColonne()];
			break;
			case GAUCHE :
				if (joueur.getColonne()-x>=0 && !grille.vide(joueur.getLigne(), joueur.getColonne()-x)&& grille.getGrille()[joueur.getLigne()][joueur.getColonne()-x] instanceof Monstre)
					return grille.getGrille()[joueur.getLigne()][joueur.getColonne()-x];
			break;
			case DROITE : 
				if (joueur.getColonne()+x<=grille.getColonne()-1 &&!grille.vide(joueur.getLigne(), joueur.getColonne()+x) && grille.getGrille()[joueur.getLigne()][joueur.getColonne()+x] instanceof Monstre)
					return grille.getGrille()[joueur.getLigne()][joueur.getColonne()+x];
			break;
		}
		}
		else if ((indiceMain==2 && joueur.getArmeGauche()==null && joueur.getArmeDroite()!=null && joueur.getArmeDroite().getType()==ArmeType.Arc) ||
				(indiceMain==1 && joueur.getArmeGauche()!=null && joueur.getArmeDroite()==null && joueur.getArmeGauche().getType()==ArmeType.Arc))
		{
			
			while (x<3)
			{
				switch (direction) {
					case HAUT : 
						if (joueur.getLigne()-x>=0 && !grille.vide(joueur.getLigne()-x, joueur.getColonne()) && grille.getGrille()[joueur.getLigne()-x][joueur.getColonne()] instanceof Monstre)
							return grille.getGrille()[joueur.getLigne()-x][joueur.getColonne()];
					break;
					case BAS :
						if (joueur.getLigne()+x<=grille.getLigne()-1 && !grille.vide(joueur.getLigne()+x, joueur.getColonne()) && grille.getGrille()[joueur.getLigne()+x][joueur.getColonne()] instanceof Monstre)
							return grille.getGrille()[joueur.getLigne()+x][joueur.getColonne()];
					break;
					case GAUCHE :
						if (joueur.getColonne()-x>=0 && !grille.vide(joueur.getLigne(), joueur.getColonne()-x)&& grille.getGrille()[joueur.getLigne()][joueur.getColonne()-x] instanceof Monstre)
							return grille.getGrille()[joueur.getLigne()][joueur.getColonne()-x];
					break;
					case DROITE : 
						if (joueur.getColonne()+x<=grille.getColonne()-1 &&!grille.vide(joueur.getLigne(), joueur.getColonne()+x) && grille.getGrille()[joueur.getLigne()][joueur.getColonne()+x] instanceof Monstre)
							return grille.getGrille()[joueur.getLigne()][joueur.getColonne()+x];
					break;
				}
				x++;
			}
		}
		
		
		return grille.getGrille()[joueur.getLigne()][joueur.getColonne()];
		
	}
	
	public void attaquerMonstre(int direction, Monstre m, int main)
	{
		int bonus_ClassG = 0;
		int bonus_ClassD = 0;
		if (joueur.getClasse()=="Saber")
		{
			if (joueur.getArmeGauche()!=null && joueur.getArmeGauche().getType()==ArmeType.Epee)
				bonus_ClassG = 100;
			if (joueur.getArmeDroite()!=null && joueur.getArmeDroite().getType()==ArmeType.Epee)
				bonus_ClassD = 100;
		}
		if (joueur.getClasse()=="Archer")
		{
			if (joueur.getArmeGauche()!=null && joueur.getArmeGauche().getType()==ArmeType.Arc)
				bonus_ClassG = 100;
			if (joueur.getArmeDroite()!=null && joueur.getArmeDroite().getType()==ArmeType.Arc)
				bonus_ClassD = 100;
		}
		int ligne =0;
		int colonne = 0;
		int atk =1;
		int def =1;
		int dmg=0;
		//formule de dommage (atk = (atkBase + atkBonus) + Rand((1/4)*atk)-def
		if (main == 1)
		{
			if (joueur.getArmeGauche()==null)
			{
				atk = joueur.getForce();
			}
			else
			atk = joueur.getForce()+joueur.getArmeGauche().getAttaqueBuff()+bonus_ClassG;
		}
		else if (main==2)
		{
			if (joueur.getArmeDroite()==null)
			{
				atk = joueur.getForce();
			}
			else
			atk = joueur.getForce()+joueur.getArmeDroite().getAttaqueBuff()+bonus_ClassD;
		}
		
		def = m.getResistance()/2;
		def = def +rand.nextInt(def);
		atk = atk + rand.nextInt(atk/4);
		dmg= atk-def;
		if (dmg<=0)
			dmg=1;
		m.setHp(m.getHp()-dmg);
		usePA(1);
		joueur.afficheEtat();
		System.out.println(""+joueur.getNom()+ " a infligé "+dmg+" dommages");
		System.out.println();
		m.afficheEtat();
		
		if (m.getHp()<=0)
		{
			serviceJoueur.expUp(joueur, m);
			System.out.println("Monstre vaincu");
			ligne = m.getLigne();
			colonne =  m.getColonne();
			this.tresor1 = new Tresor(ligne, colonne);
			
			if (m==this.m1)
			{
				this.m1=respawnMonstre();
				grille.set(this.m1.getLigne(), this.m1.getColonne(), m1);
			}
			else if(m==this.m2)
			{
				this.m2=respawnMonstre();
				grille.set(this.m2.getLigne(), this.m2.getColonne(), m2);
			}
			grille.set(this.tresor1.getLigne(), this.tresor1.getColonne(), tresor1);
			//grille.set(ligne, colonne, null);
			
		}
	}
	
	public Monstre respawnMonstre()
	{
		int ligne= 0;
		int colonne = 0;
		int i=0;
		while (i<1)
		{
			ligne = rand.nextInt(grille.getLigne());
			colonne = rand.nextInt(grille.getColonne());
				if (grille.vide(ligne, colonne))
				{
					i++;
				}
				
		}
		return new Monstre(ligne, colonne, "Pikachu", 600, 10, 500, 10);
	}
	
	public void usePA(int pa)
	{
		joueur.setPa(joueur.getPa()-pa);
	}
	
	public void tourMonstre()
	{
		
		if (rechercheCombatPossible(Direction.HAUT, 0) == m1 || rechercheCombatPossible(Direction.BAS, 0) == m1 || rechercheCombatPossible(Direction.GAUCHE, 0) == m1 || rechercheCombatPossible(Direction.DROITE, 0) == m1)
			{
			attaquerJoueur(m1);
		}
			
		if (rechercheCombatPossible(Direction.HAUT, 0) == m2 || rechercheCombatPossible(Direction.BAS, 0) == m2 || rechercheCombatPossible(Direction.GAUCHE, 0) == m2 || rechercheCombatPossible(Direction.DROITE, 0) == m2)
		{
			attaquerJoueur(m2);
		}
		
		
	}
	
	public void attaquerJoueur(Monstre m)
	{
		joueur.setHp(joueur.getHp() - m.getForce());
		System.out.println("Vous avez été attaqué");
	}
	
	
}