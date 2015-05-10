package controle;

import modele.Arme;
import modele.Armure;
import modele.Inventaire;
import modele.Joueur;

public class ServiceJoueur {
	
	private static ServiceJoueur serviceJoueur;
	private ServiceJoueur(){
		
	}
	
	public static ServiceJoueur getInstance()
	{
		if (serviceJoueur == null)
		{
			serviceJoueur = new ServiceJoueur();
		}
		return serviceJoueur;
	}
	
	public void initialiseJoueur(int ligne, int colonne, String nom, int hp, int mana,  int pa, int force, int defense ,int agilite, Arme arme, Armure armure, Inventaire sac)
	{
		Joueur joueur = Joueur.getInstance();
		joueur.setLigne(ligne);
		joueur.setColonne(colonne);
		joueur.setNom(nom);
		joueur.setHp(hp);
		joueur.setHpMax(hp);
		joueur.setMana(mana);
		joueur.setManaMax(mana);
		joueur.setExp(0);
		joueur.setLv(1);
		joueur.setPa(pa);
		joueur.setPaRegen(pa);
		joueur.setForce(force);
		joueur.setResistance(defense);
		joueur.setAgilite(agilite);
		joueur.setArme(arme);
		joueur.setArmure(armure);
		joueur.setInventaire(sac);
	}
	
	public Joueur getJoueur()
	{
		return Joueur.getInstance();
	}
	
}
