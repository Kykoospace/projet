package jeu;

import java.util.Random;
import java.util.Scanner;

import controle.ServiceItem;
import controle.ServiceJoueur;
import modele.Arme;
import modele.ArmeEnum;
import modele.Armure;
import modele.ArmureEnum;
import modele.Element;
import modele.Inventaire;
import modele.Item;
import modele.Joueur;
import modele.Monstre;
import modele.Obstacle;
import grille.Grille;

public class Jeu {

	private Grille grille;
	private Obstacle o1;
	private Obstacle o2;
	private Monstre m1;
	private Monstre m2;
	private Item item1;
	private Item item2;
	private Random rand = new Random();
	private ServiceJoueur serviceJoueur = ServiceJoueur.getInstance();
	private ServiceItem serviceItem = ServiceItem.getInstance();
	
	public Jeu()
	{
		
		initialiseJeu();
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

	public ServiceItem getServiceItem() {
		return serviceItem;
	}

	public void setServiceItem(ServiceItem serviceItem) {
		this.serviceItem = serviceItem;
	}
	
	//fin getter&setter

	
	public void initialiseJeu()//place et crée tout ce qu'il faut
	{
		grille = new Grille(5,5);//crée la grille
		
		//crée tous les items du jeu
		Armure armureDeBois = new Armure(ArmureEnum.ArmureDeBois);
		Arme epeeDeBois = new Arme(ArmeEnum.EpeeDeBois);
		Armure armureDeMetal = new Armure(ArmureEnum.ArmureDeMetal);
		Arme epeeDeMetal = new Arme(ArmeEnum.EpeeDeMetal);
		Arme excalibur = new Arme(ArmeEnum.Excalibur);
		Arme murasame = new Arme(ArmeEnum.Murasame);
		
		
		
		Inventaire sac = new Inventaire();//crée un inventaire pour le joueur
		
		//crée un joueur et l'initialise
		serviceJoueur.initialiseJoueur(2,2, "Baka", 100, 100,  5, 10, 10 ,10, epeeDeBois, armureDeMetal, sac);
		//récupère l'inventaire du joueur pour pouvoir l'appeller de n'importe où
		serviceItem.initialiseServiceItem(serviceJoueur.getJoueur().getInventaire().getInventaire());
		
		serviceItem.addItemInventaire(excalibur);
		serviceItem.addItemInventaire(epeeDeMetal);
		serviceItem.addItemInventaire(murasame);
		serviceItem.addItemInventaire(armureDeMetal);
		grille.set(2, 2, serviceJoueur.getJoueur());
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
		//Placement des trésors
		/*while (!grille.vide(ligne, colonne))
		{
			ligne = rand.nextInt(grille.getLigne());
			colonne = rand.nextInt(grille.getColonne());
		}
		
		grille.set(ligne, colonne, item1);*/
		
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
			if (e.getLigne()>0 && (grille.vide(ligne-1, colonne) || grille.getGrille()[ligne-1][colonne] instanceof Item))
				x-=1;
		}
		else if (direction == 1)
		{
			if (e.getLigne()<grille.getLigne()-1 && (grille.vide(ligne+1, colonne)|| grille.getGrille()[ligne+1][colonne] instanceof Item))
				x++;
		}
		else if (direction == 2)
		{
			if (e.getColonne()>0 && (grille.vide(ligne, colonne-1)|| grille.getGrille()[ligne][colonne-1] instanceof Item))
				y-=1;
		}
		else if (direction == 3)
		{
			if (e.getColonne()<grille.getColonne()-1 && (grille.vide(ligne, colonne+1)|| grille.getGrille()[ligne][colonne+1] instanceof Item))

				y++;
		}
		grille.set(ligne, colonne, null);
		serviceJoueur.getJoueur().setLigne(ligne+x);
		serviceJoueur.getJoueur().setColonne(colonne+y);
		grille.set(serviceJoueur.getJoueur().getLigne(), e.getColonne(), e);
		usePA(1);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public Element rechercheCombatPossible(int direction)
	{
		if (direction == 0)
		{
			if (serviceJoueur.getJoueur().getLigne()>0 && !grille.vide(serviceJoueur.getJoueur().getLigne()-1, serviceJoueur.getJoueur().getColonne()) && grille.getGrille()[serviceJoueur.getJoueur().getLigne()-1][serviceJoueur.getJoueur().getColonne()] instanceof Monstre)
				return grille.getGrille()[serviceJoueur.getJoueur().getLigne()-1][serviceJoueur.getJoueur().getColonne()];
		}
		else if (direction == 1)
		{
			if (serviceJoueur.getJoueur().getLigne()<grille.getLigne()-1 && !grille.vide(serviceJoueur.getJoueur().getLigne()+1, serviceJoueur.getJoueur().getColonne()) && grille.getGrille()[serviceJoueur.getJoueur().getLigne()+1][serviceJoueur.getJoueur().getColonne()] instanceof Monstre)
				return grille.getGrille()[serviceJoueur.getJoueur().getLigne()+1][serviceJoueur.getJoueur().getColonne()];
		}
		else if (direction == 2)
		{
			if (serviceJoueur.getJoueur().getColonne()>0 && !grille.vide(serviceJoueur.getJoueur().getLigne(), serviceJoueur.getJoueur().getColonne()-1)&& grille.getGrille()[serviceJoueur.getJoueur().getLigne()][serviceJoueur.getJoueur().getColonne()-1] instanceof Monstre)
				return grille.getGrille()[serviceJoueur.getJoueur().getLigne()][serviceJoueur.getJoueur().getColonne()-1];
		}
		else if (direction == 3)
		{
			if (serviceJoueur.getJoueur().getColonne()<grille.getColonne()-1 &&!grille.vide(serviceJoueur.getJoueur().getLigne(), serviceJoueur.getJoueur().getColonne()+1) && grille.getGrille()[serviceJoueur.getJoueur().getLigne()][serviceJoueur.getJoueur().getColonne()+1] instanceof Monstre)
				return grille.getGrille()[serviceJoueur.getJoueur().getLigne()][serviceJoueur.getJoueur().getColonne()+1];
		}
		return grille.getGrille()[serviceJoueur.getJoueur().getLigne()][serviceJoueur.getJoueur().getColonne()];
		
	}
	
	public void attaquerMonstre(int direction, Monstre m)
	{
		int ligne =0;
		int colonne = 0;
		int atk =1;
		int def =1;
		int dmg=0;
		//formule de dommage (atk = (atkBase + atkBonus) + Rand((1/4)*atk)-def
		if (serviceJoueur.getJoueur().getArme()==null)
		{
			atk = serviceJoueur.getJoueur().getForce();
		}
		else
		atk = serviceJoueur.getJoueur().getForce()+serviceJoueur.getJoueur().getArme().getAttaqueBuff();
		def = m.getResistance()/2;
		def = def +rand.nextInt(def);
		atk = atk + rand.nextInt(atk/4);
		dmg= atk-def;
		m.setHp(m.getHp()-dmg);
		usePA(1);
		serviceJoueur.getJoueur().afficheEtat();
		System.out.println(""+serviceJoueur.getJoueur().getNom()+ " a infligé "+dmg+" dommages");
		System.out.println();
		m.afficheEtat();
		
		if (m.getHp()<=0)
		{
			System.out.println("Monstre vaincu");
			ligne = m.getLigne();
			colonne =  m.getColonne();
			
			
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
			
			grille.set(ligne, colonne, null);
			
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
		return new Monstre(ligne, colonne, "Pikachu", 20, 10, 10, 10);
	}
	
	public void usePA(int pa)
	{
		serviceJoueur.getJoueur().setPa(serviceJoueur.getJoueur().getPa()-pa);
	}
	
	
}