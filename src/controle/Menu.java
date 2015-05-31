package controle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

import jeu.Jeu;
import modele.Arme;
import modele.Armure;
import modele.Direction;
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
		
		this.jeu = new Jeu();// crée un jeu
		
		if (menuChargement()==2)
		{
			menuCreationPersonnage();
		}
		else
			chargement();
		jeu.initialiseJeu();
		this.serviceItem = jeu.getServiceItem();//récupère le service item crée par le jeu
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
			if (choix>3 && choix<0)
			{
				System.out.println("Mode non valide");
			}
		}while(choix>3 && choix<0);
		return choix;
	}
	
	public int menuChargement()
	{
		System.out.println("Charger un personnage ? 1-oui 2-non");
		int choix=0;
		do
		{
			choix = input.nextInt();
		}while(choix!=1 && choix !=2);
		return choix;
	}
	
	public void menuCreationPersonnage()
	{
		System.out.println("Création d'un personnage\nSaisir le nom de votre personnage :");
		input.nextLine();
		String nom = input.nextLine();
		System.out.println("Choisissez votre classe :\n1- Saber\n2- Archer\n3- Caster");
		int choix=0;
		do
		{
			choix = input.nextInt();
			if (choix>4 && choix<0)
			{
				System.out.println("Classe non valide");
			}
		}while (choix>4 && choix<0);
		String classe = new String ("");
		if (choix ==1)
			classe = "Saber";
		else if (choix == 2)
			classe = "Archer";
		else if (choix ==3)
			classe = "Caster";
		jeu.initialiseJoueur(nom, classe);	
		attribuerPoint();
	}
	
	public void chargement()
	{
		try {
			String flux;
			BufferedReader load = new BufferedReader(new FileReader("save.txt"));
			
			
			flux=load.readLine();
			int ligne = Integer.parseInt(flux);
			flux=load.readLine();
			int colonne = Integer.parseInt(flux);
			String nom=load.readLine();
			
			String classe=load.readLine();
			
			flux=load.readLine();
			int lv = Integer.parseInt(flux);
			flux=load.readLine();
			int exp = Integer.parseInt(flux);
			flux=load.readLine();
			int pallier = Integer.parseInt(flux);
			flux=load.readLine();
			int pa = Integer.parseInt(flux);
			flux=load.readLine();
			int paRegen = Integer.parseInt(flux);
			flux=load.readLine();
			int hp = Integer.parseInt(flux);
			flux=load.readLine();
			int hpMax = Integer.parseInt(flux);
			flux=load.readLine();
			int mana = Integer.parseInt(flux);
			flux=load.readLine();
			int manaMax = 	Integer.parseInt(flux);
			flux=load.readLine();
			int force = Integer.parseInt(flux);
			flux=load.readLine();
			int resistance =Integer.parseInt(flux);
			flux=load.readLine();
			int agilite = Integer.parseInt(flux);
			String armeGauche = load.readLine();
			String armeDroite = load.readLine();
			String armure = load.readLine();
			String item1 = load.readLine();
			String item2 = load.readLine();
			String item3 = load.readLine();
			String item4 = load.readLine();
			String item5 = load.readLine();
			/*while ((flux = load.readLine()) != null) {
				System.out.println(flux);
				}*/
			
			
			jeu.chargementJoueur(nom, classe, ligne, colonne, lv, exp, pallier, pa, paRegen, hp, hpMax, mana, manaMax, force, resistance, agilite, armeGauche, armeDroite, armure, item1, item2, item3, item4, item5);
			load.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void attribuerPoint()
	{
		System.out.println("Vous avez 18 points à répartir entre la force, la resistance et l'agilité");
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
				System.out.println("Vous avez dépassé le nombre de points autorisés");
			else
			{
				System.out.println("Resistance : ");
				def = input.nextInt();
				if (atk+def>18)
					System.out.println("Vous avez dépassé le nombre de points autorisés");
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
				System.out.println("Que voulez-vous faire ?\n1- Deplacement\n2- Attaquer\n3- Voir Inventaire\n4- Voir Etat\n5- Sauvegarde du personnage\n6 - Fin du tour");
				do
				{
					choix = input.nextInt();
					if (choix<0 && choix>7)
					{
						System.out.println("Mauvaise saisie");
					}
				}while (choix<0 && choix>6);
				
				if (choix == 1)
				{
					menuDeplacement();//permet au joueur de se déplacer
				}
				
				else if(choix == 2)
				{
					menuAttaque();//permet au joueur d'attaquer
				}
				else if(choix==3)
				{
					menuInventaire();//permet au joueur de gérer son équipement
				}
				else if(choix==4)
				{
					jeu.getJoueur().afficheEtat();//permet au joueur de voir son statut
				}
				else if(choix==5)
				{
					//sauvegarde
					try {
						PrintWriter save = new PrintWriter(new FileWriter("save.txt", false));
						String flux;
						flux = ""+jeu.getJoueur().getLigne()+"\n"+jeu.getJoueur().getColonne()+"\n"+jeu.getJoueur().getNom()+"\n"
						+jeu.getJoueur().getClasse()+"\n"+jeu.getJoueur().getLv()+"\n"+jeu.getJoueur().getExp()+"\n"+
						jeu.getJoueur().getPallier()+"\n"+jeu.getJoueur().getPa()+"\n"+jeu.getJoueur().getPaRegen()+"\n"
						+jeu.getJoueur().getHp()+"\n"+jeu.getJoueur().getHpMax()+"\n"+
						jeu.getJoueur().getMana()+"\n"+jeu.getJoueur().getManaMax()+"\n"+jeu.getJoueur().getForce()+"\n"+
						jeu.getJoueur().getResistance()+"\n"+jeu.getJoueur().getAgilite()+"\n"+jeu.getJoueur().getArmeGauche()
						+"\n"+jeu.getJoueur().getArmeDroite()+"\n"+jeu.getJoueur().getArmure()+"\n"+jeu.getJoueur().getInventaire().getInventaire()[0]
						+"\n"+jeu.getJoueur().getInventaire().getInventaire()[1]+"\n"+jeu.getJoueur().getInventaire().getInventaire()[2]
						+"\n"+jeu.getJoueur().getInventaire().getInventaire()[3]+"\n"+jeu.getJoueur().getInventaire().getInventaire()[4];
						save.println(flux);
						save.close();
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
			}while(/*jeu.getJoueur().getPa()>0 && */choix!=6);//tant qu'il ne décide pas de finir son tour
			
		}while (jeu.getJoueur().getHp()>0);
		
	}
	
	public void menuDeplacement()//menu qui gère les déplacements
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
		System.out.println("Choisissez la main à utiliser pour attaquer : 1-G / 2-D");
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
			if (jeu.rechercheCombatPossible(Direction.HAUT, indiceMain)==jeu.getM1())//verifie si un monstre est présent et s'il est égal à m1
			{
				jeu.attaquerMonstre(0, jeu.getM1(), indiceMain);//si true, il l'attaque
			}
			else if(jeu.rechercheCombatPossible(Direction.HAUT, indiceMain)==jeu.getM2())//verifie si un monstre est présent et s'il est égal à m2
			{
				jeu.attaquerMonstre(0, jeu.getM2(), indiceMain);//si true, il l'attaque
			}
				
			else
				System.out.println("Combat pas possible");
			break;
		case 2:
			if (jeu.rechercheCombatPossible(Direction.BAS, indiceMain)==jeu.getM1())//verifie si un monstre est présent et s'il est égal à m1
			{
				jeu.attaquerMonstre(0, jeu.getM1(), indiceMain);//si true, il l'attaque
			}
			else if(jeu.rechercheCombatPossible(Direction.BAS, indiceMain)==jeu.getM2())//verifie si un monstre est présent et s'il est égal à m2
			{
				jeu.attaquerMonstre(0, jeu.getM2(), indiceMain);//si true, il l'attaque
			}
			else
				System.out.println("Combat pas possible");
			break;
		case 4:
			if (jeu.rechercheCombatPossible(Direction.GAUCHE, indiceMain)==jeu.getM1())//verifie si un monstre est présent et s'il est égal à m1
			{
				jeu.attaquerMonstre(0, jeu.getM1(), indiceMain);//si true, il l'attaque
			}
			else if(jeu.rechercheCombatPossible(Direction.GAUCHE, indiceMain)==jeu.getM2())//verifie si un monstre est présent et s'il est égal à m2
			{
				jeu.attaquerMonstre(0, jeu.getM2(), indiceMain);//si true, il l'attaque
			}
			else
				System.out.println("Combat pas possible");
			break;
		case 6:
			if (jeu.rechercheCombatPossible(Direction.DROITE, indiceMain)==jeu.getM1())//verifie si un monstre est présent et s'il est égal à m1
			{
				jeu.attaquerMonstre(3, jeu.getM1(), indiceMain);//si true, il l'attaque
			}
			else if(jeu.rechercheCombatPossible(Direction.DROITE, indiceMain)==jeu.getM2())//verifie si un monstre est présent et s'il est égal à m2
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
		do//saisie controlé
		{
			System.out.println("1 - Equiper\n2 - Desequiper\n3 - Drop\n4 - Utiliser\n5 - Quitter");
			choix = input.nextInt();
			if (choix<0 && choix>3)
				System.out.println("Mauvaise saisie");
			
		}while (choix<1 && choix>5);
		
		if (choix == 1)//equipe
		{
			int indiceItem=0;
			System.out.println("Choisissez l'objet à équiper?");
			System.out.println(jeu.getJoueur().getInventaire());
			do//choisit un item
			{
				indiceItem = input.nextInt();
				if (indiceItem<0 && indiceItem>5)
					System.out.println("Mauvaise saisie");
				
			}while (indiceItem<0 && indiceItem>=5);
			
			
			if (jeu.getJoueur().getInventaire().getInventaire()[indiceItem-1] instanceof Arme)
			{
				System.out.println("Choisissez la main à équiper? 1-G / 2-D");
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
				System.out.println("Objet non équipable");
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
				System.out.println("Choisissez la main à desequiper? 1-G / 2-D");
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
					System.out.println("Vous n'avez rien à desequiper");
				}
				
			}
			else if(choix==1 && jeu.getJoueur().getArmure()!=null)
			{
				serviceItem.desequipeArmure(jeu.getJoueur());
			}
			else
			{
				System.out.println("Vous n'avez rien à desequiper");
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
			System.out.println("Choisissez l'objet à utiliser");
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
				System.out.println("Vous avez utilisé "+potion);
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
			System.out.println("Choisissez l'item à abandonner");
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
	
}
