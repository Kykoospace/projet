package controle;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;
import java.util.Scanner;

import jeu.Jeu;
import modele.Arme;
import modele.Armure;
import modele.Direction;
import modele.Joueur;
import modele.Potion;

public class Menu {
	
	private Scanner input = new Scanner(System.in);
	private Random rand = new Random();
	private Jeu jeu;
	private ServiceJoueur serviceJoueur = ServiceJoueur.getInstance();
	private ServiceInventaire serviceItem;
	
	public Menu()
	{
		//connexion
		// choix du mode & charger un personnage
		
		
		int mode= start();
		
		this.jeu = new Jeu();// cree un jeu
		
		if (menuChargement()==2)
		{
			menuCreationPersonnage();
		}
		else
			chargement();
		jeu.initialiseJeu();
		this.serviceItem = jeu.getServiceItem();//recupere le service item cree par le jeu
		System.out.println(this.serviceJoueur);
		System.out.println(this.serviceItem);
		menu(); //menu de controle du jeu
	}
	public Menu(String s)
	{
		
	}
	
	public int start()
	{
		System.out.println("1 - Solo\n2 - 1v1");
		int choix = 0;
		do
		{
			choix = input.nextInt();
			if (choix>2 || choix<1)
			{
				System.out.println("Mode non valide");
			}
		}while(choix>2 || choix<1);
		return choix;
	}
	
	public int menuChargement()
	{
		System.out.println("Charger un personnage ? 1-oui 2-non");
		int choix=0;
		do
		{
			choix = input.nextInt();
		}while(choix>2 || choix<1);
		return choix;
	}
	
	public void menuCreationPersonnage()
	{
		System.out.println("Création d'un personnage\nSaisir le nom de votre personnage :");
		input.nextLine();
		String nom = input.nextLine();
		System.out.println("Choisissez votre classe :\n1- Saber\n2- Archer");
		int choix=0;
		do
		{
			choix = input.nextInt();
			if (choix>2 || choix<1)
			{
				System.out.println("Classe non valide");
			}
		}while (choix>2 || choix<1);
		String classe = new String ("");
		if (choix ==1)
			classe = "Saber";
		else if (choix == 2)
			classe = "Archer";
		jeu.initialiseJoueur(nom, classe);	
		attribuerPoint();
	}
	
	public void chargement()
	{
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(
		              new BufferedInputStream(
		                new FileInputStream(
		                  new File("save.txt"))));
			Joueur joueur = (Joueur) ois.readObject();
			jeu.setJoueur(joueur);
			ois.close();
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void attribuerPoint()
	{
		System.out.println("Vous avez 18 points a répartir entre la force, la resistance et l'agilité");
		int atk=0;
		int def =0;
		int agi = 0;
		String confirmation;
		boolean validation = false;
		do
		{
			System.out.println("Force : ");
			atk = input.nextInt();
			if (atk>18)
				System.out.println("Vous avez depasse le nombre de points autorises");
			else
			{
				System.out.println("Resistance : ");
				def = input.nextInt();
				if (atk+def>18)
					System.out.println("Vous avez depasse le nombre de points autorises");
				else
				{
					agi = 18-atk-def;
					System.out.println("Agilite : "+agi);
					System.out.println("En êtes-vous sûr? (oui/non)");
					input.nextLine();
					confirmation = input.nextLine();
					if (confirmation.equals("oui"))
					{
						jeu.getJoueur().setForce(jeu.getJoueur().getForce()+atk);
						jeu.getJoueur().setResistance(jeu.getJoueur().getResistance()+def);
						jeu.getJoueur().setAgilite(jeu.getJoueur().getAgilite()+agi);
						validation = true;
					}
					else 
						validation=false;
				}
				
			}
		}while(atk + def + agi !=18 || validation==false);
	}
	
	public void menu()//menu qui permet de choisir ce qu'on veut faire
	{
		int choix=0;
		jeu.getGrille().afficher();	//affiche la grille
		do
		{
			jeu.getJoueur().setPa(jeu.getJoueur().getPa()+jeu.getJoueur().getPaRegen());//fais gagner des pa au joueur
			do
			{
				System.out.println("Que voulez-vous faire ?\n1- Deplacement\n2- Attaquer\n3- Voir Inventaire\n4- Voir Etat\n5- Utiliser Px\n6- Sauvegarde du personnage\n7 - Fin du tour");
				do
				{
					choix = input.nextInt();
					if (choix<1 || choix>7)
					{
						System.out.println("Mauvaise saisie");
					}
				}while (choix<1 || choix>6);
				
				if (choix == 1)
				{
					menuDeplacement();//permet au joueur de se deplacer
				}
				
				else if(choix == 2)
				{
					menuAttaque();//permet au joueur d'attaquer
				}
				else if(choix==3)
				{
					menuInventaire();//permet au joueur de gerer son equipement
				}
				else if(choix==4)
				{
					serviceJoueur.afficheEtat(jeu.getJoueur());
					//jeu.getJoueur().afficheEtat();//permet au joueur de voir son statut
				}
				else if (choix==5)
				{
					menuUsePx();
				}
				else if(choix==6)
				{
					//sauvegarde
				    ObjectOutputStream oout;
					try {
						oout = new ObjectOutputStream(
					              new BufferedOutputStream(
					                new FileOutputStream(
					                  new File("save.txt"))));
						
						oout.writeObject(jeu.getJoueur());
						oout.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				}
				//Action des mostres
				jeu.tourMonstre();
				System.out.println("Fin tour monstres");
				//Fin Action des monstres
				jeu.getGrille().afficher();	//affiche la grille
			}while(/*jeu.getJoueur().getPa()>0 && */choix!=6);//tant qu'il ne decide pas de finir son tour
			
		}while (jeu.getJoueur().getHp()>0);
		
	}
	
	public void menuDeplacement()//menu qui gere les deplacements
	{
		
		int choix=-1;
			do
			{
				System.out.println("8 - Haut\n2 - Bas\n4 - Gauche\n6 - Droite\n5 - Stop\n");
				choix = input.nextInt();
				if (choix!=8 && choix!=2 && choix!=4 && choix!=6 && choix!=5)
					System.out.println("Mauvaise saisie");
				
			}while (choix!=8 && choix!=2 && choix!=4 && choix!=6 && choix!=5);
			
			switch (choix) {
			case 8:
				jeu.deplacerElement(0, jeu.getJoueur());//haut
				break;
			case 2:
				jeu.deplacerElement(1, jeu.getJoueur());//bas
				break;
			case 4:
				jeu.deplacerElement(2, jeu.getJoueur());//gauche
				break;
			case 6:
				jeu.deplacerElement(3, jeu.getJoueur());//droite
				break;
			case 0:
				
				break;
			default:
				break;
		}
	}
	
	public void menuAttaque()
	{
		int choix=-1;
		
		do
		{
			System.out.println("8 - Haut\n2 - Bas\n4 - Gauche\n6 - Droite\n5 - Stop\n");
			choix = input.nextInt();
			if (choix!=8 && choix!=2 && choix!=4 && choix!=6 && choix!=5)
				System.out.println("Mauvaise saisie");
			
		}while (choix!=8 && choix!=2 && choix!=4 && choix!=6 && choix!=5);
		System.out.println("Choisissez la main a utiliser pour attaquer : 1-G / 2-D");
		int main;
		do//choisit la main
		{
			main = input.nextInt();
			if (main!=1 && main!=2)
				System.out.println("Mauvaise saisie");
			
		}while (main!=1 && main!=2);
		int indiceMain=0;
		if (main==1)
		{
			indiceMain = 1;
		}
		else if (main==2)
		{
			indiceMain=2;
		}
		
		switch (choix) {
		case 8:
			if (jeu.rechercheCombatPossible(Direction.HAUT, indiceMain)==jeu.getM1())//verifie si un monstre est present et s'il est egal à m1
			{
				jeu.attaquerMonstre(0, jeu.getM1(), indiceMain);//si true, il l'attaque
			}
			else if(jeu.rechercheCombatPossible(Direction.HAUT, indiceMain)==jeu.getM2())//verifie si un monstre est present et s'il est egal à m2
			{
				jeu.attaquerMonstre(0, jeu.getM2(), indiceMain);//si true, il l'attaque
			}
				
			else
				System.out.println("Combat pas possible");
			break;
		case 2:
			if (jeu.rechercheCombatPossible(Direction.BAS, indiceMain)==jeu.getM1())//verifie si un monstre est present et s'il est egal à m1
			{
				jeu.attaquerMonstre(0, jeu.getM1(), indiceMain);//si true, il l'attaque
			}
			else if(jeu.rechercheCombatPossible(Direction.BAS, indiceMain)==jeu.getM2())//verifie si un monstre est present et s'il est egal à m2
			{
				jeu.attaquerMonstre(0, jeu.getM2(), indiceMain);//si true, il l'attaque
			}
			else
				System.out.println("Combat pas possible");
			break;
		case 4:
			if (jeu.rechercheCombatPossible(Direction.GAUCHE, indiceMain)==jeu.getM1())//verifie si un monstre est present et s'il est egal à m1
			{
				jeu.attaquerMonstre(0, jeu.getM1(), indiceMain);//si true, il l'attaque
			}
			else if(jeu.rechercheCombatPossible(Direction.GAUCHE, indiceMain)==jeu.getM2())//verifie si un monstre est present et s'il est egal à m2
			{
				jeu.attaquerMonstre(0, jeu.getM2(), indiceMain);//si true, il l'attaque
			}
			else
				System.out.println("Combat pas possible");
			break;
		case 6:
			if (jeu.rechercheCombatPossible(Direction.DROITE, indiceMain)==jeu.getM1())//verifie si un monstre est present et s'il est egal à m1
			{
				jeu.attaquerMonstre(3, jeu.getM1(), indiceMain);//si true, il l'attaque
			}
			else if(jeu.rechercheCombatPossible(Direction.DROITE, indiceMain)==jeu.getM2())//verifie si un monstre est present et s'il est egal à m2
			{
				jeu.attaquerMonstre(3, jeu.getM2(), indiceMain);//si true, il l'attaque
			}
			
			else
				System.out.println("Combat pas possible");
			break;
		case 0:
			
			break;
		default:
			break;
	}
		jeu.getGrille().afficher();
	}
	
	
	public void menuInventaire()
	{
		System.out.println(jeu.getJoueur().getInventaire());
		System.out.println("Que voulez-vous faire?");
		int choix=0;
		do//saisie controle
		{
			System.out.println("1 - Equiper\n2 - Desequiper\n3 - Drop\n4 - Utiliser\n5 - Quitter");
			choix = input.nextInt();
			if (choix<1 || choix>5)
				System.out.println("Mauvaise saisie");
			
		}while (choix<1 || choix>5);
		
		if (choix == 1)//equipe
		{
			int indiceItem=0;
			System.out.println("Choisissez l'objet a equiper?");
			System.out.println(jeu.getJoueur().getInventaire());
			do//choisit un item
			{
				indiceItem = input.nextInt();
				if (indiceItem<0 && indiceItem>5)
					System.out.println("Mauvaise saisie");
				
			}while (indiceItem<0 && indiceItem>=5);
			
			
			if (jeu.getJoueur().getInventaire().getInventaire()[indiceItem-1] instanceof Arme)
			{
				System.out.println("Choisissez la main a equiper? 1-G / 2-D");
				int main;
				do//choisit la main
				{
					main = input.nextInt();
					if (main!=1 && main!=2)
						System.out.println("Mauvaise saisie");
					
				}while (main!=1 && main!=2);
				Arme arme = (Arme) jeu.getJoueur().getInventaire().getInventaire()[indiceItem-1];
				if (main==1)
				{
					serviceItem.equipeArmeGauche(jeu.getJoueur(), arme, indiceItem);
				}
				else if (main==2)
				{
					serviceItem.equipeArmeDroite(jeu.getJoueur(), arme, indiceItem);
				}
				
				
			}
			else if (jeu.getJoueur().getInventaire().getInventaire()[indiceItem-1] instanceof Armure)
			{
				Armure armure = (Armure) jeu.getJoueur().getInventaire().getInventaire()[indiceItem-1];
				serviceItem.equipeArmure(jeu.getJoueur(), armure, indiceItem);
			}
			else
			{
				System.out.println("Objet non equipable");
			}
		}
		else if(choix==2)//desequipe
		{
			System.out.println("0 - Arme\n1 - Armure");
			do//choisit son arme ou son armure
			{
				choix = input.nextInt();
				if (choix<0 && choix>2)
					System.out.println("Mauvaise saisie");
				
			}while (choix<0 && choix>2);
			if (choix ==0)
			{
				System.out.println("Choisissez la main a desequiper? 1-G / 2-D");
				int main;
				do//choisit un item
				{
					main = input.nextInt();
					if (main!=1 && main!=2)
						System.out.println("Mauvaise saisie");
					
				}while (main!=1 && main!=2);
				
				if (main==2 && jeu.getJoueur().getArmeDroite()!=null)
				{
					serviceItem.desequipeArmeDroite(jeu.getJoueur());
				}
				else if (main==1 && jeu.getJoueur().getArmeGauche()!=null)
				{
					serviceItem.desequipeArmeGauche(jeu.getJoueur());
				}
				else
				{
					System.out.println("Vous n'avez rien a desequiper");
				}
				
			}
			else if(choix==1 && jeu.getJoueur().getArmure()!=null)
			{
				serviceItem.desequipeArmure(jeu.getJoueur());
			}
			else
			{
				System.out.println("Vous n'avez rien a desequiper");
			}
		}
		else if (choix==3)//jete un item de son inventaire
		{
			int indiceItem = menuDropItem();
			serviceItem.dropItem(indiceItem, jeu.getJoueur());
		}
		else if (choix==4)//utilise un item
		{
			int indiceItem=0;
			System.out.println("Choisissez l'objet a utiliser");
			System.out.println(jeu.getJoueur().getInventaire());
			do//choisit un item
			{
				indiceItem = input.nextInt();
				if (indiceItem<0 && indiceItem>5)
					System.out.println("Mauvaise saisie");
				
			}while (indiceItem<0 && indiceItem>=5);
			
			if (jeu.getJoueur().getInventaire().getInventaire()[indiceItem-1] instanceof Potion)
			{
				Potion potion = (Potion)jeu.getJoueur().getInventaire().getInventaire()[indiceItem-1];
				serviceItem.utiliserPotion(jeu.getJoueur(), potion, indiceItem);
				System.out.println("Vous avez utilise "+potion);
			}
			else
			{
				System.out.println("Objet non utilisable");
			}
		}
	}
	
	public int menuDropItem()
	{
		System.out.println("Que voulez-vous faire?");
		int choix=0;
		do
		{
			System.out.println("0 - Retirer un item de son inventaire\n1 - Ignorer");
			choix = input.nextInt();
			if (choix!=0 && choix!=1)
				System.out.println("Mauvaise saisie");
			
		}while (choix!=0 && choix!=1);
		if (choix == 0)
		{
			System.out.println("Choisissez l'item a abandonner");
			System.out.println(jeu.getJoueur().getInventaire());
			do//tape le numéro de l'emplacement de l'item
			{
				choix = input.nextInt();
				if (choix<0 && choix>5)
					System.out.println("Mauvaise saisie");
				
			}while (choix<0 && choix>=5);
		}
		else if (choix == 1)
		{
			choix = -1;
		}
		return choix;
	}
	
	
	public void menuUsePx()
	{
		if (jeu.getJoueur().getPx()>0)
		{
			System.out.println("Vous disposez de "+jeu.getJoueur().getPx()+" px, combien de px voulez-vous depenser ?");
			int choix=0;
			int pts=0;
			do
			{
				pts = input.nextInt();
				if (pts<1 || pts>jeu.getJoueur().getPx())
					System.out.println("Montant voulue superieur au nombre de px disponible, recommencez");
				
			}while (pts<1 || pts>jeu.getJoueur().getPx());
			
			System.out.println("Quelle caracteristique augmente ? 1 - force / 2 - resistance / 3 - agilite");
			
			do
			{
				choix = input.nextInt();
				if (choix<1 || choix>3)
					System.out.println("Mauvaise saisie");
				
			}while (choix<1 || choix>3);
			
			if (choix==1)
				serviceJoueur.usePx(jeu.getJoueur(), pts, 1);
			if (choix==2)
				serviceJoueur.usePx(jeu.getJoueur(), pts, 2);
			if (choix==3)
				serviceJoueur.usePx(jeu.getJoueur(), pts, 3);
		}
		
		
	}
	
}
