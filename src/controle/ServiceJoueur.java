package controle;

import modele.Arme;
import modele.Armure;
import modele.Inventaire;
import modele.Joueur;
import modele.Monstre;

public class ServiceJoueur {
	
	private static ServiceJoueur serviceJoueur;
	private int expTotale = 0;
	private int pallier = 1000;
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
	
	public void initialiseJoueur(Joueur joueur, String nom, String classe)
	{
		joueur.setNom(nom);
		joueur.setClasse(classe);
		
		if (classe == "Saber")
		{
			joueur.setHpMax(500);
			joueur.setHp(500);
			joueur.setManaMax(200);
			joueur.setMana(200);
			joueur.setExp(0);
			joueur.setLv(1);
			joueur.setPa(0);
			joueur.setPaRegen(5);
			joueur.setForce(20);
			joueur.setResistance(20);
			joueur.setAgilite(20);
		}
		else if (classe == "Archer")
		{
			joueur.setHpMax(400);
			joueur.setHp(400);
			joueur.setManaMax(300);
			joueur.setMana(300);
			joueur.setExp(0);
			joueur.setLv(1);
			joueur.setPa(0);
			joueur.setPaRegen(6);
			joueur.setForce(15);
			joueur.setResistance(10);
			joueur.setAgilite(30);
		}
		else if (classe == "Caster")
		{
			joueur.setHpMax(300);
			joueur.setHp(300);
			joueur.setManaMax(500);
			joueur.setMana(500);
			joueur.setExp(0);
			joueur.setLv(1);
			joueur.setPa(0);
			joueur.setPaRegen(5);
			joueur.setForce(5);
			joueur.setResistance(10);
			joueur.setAgilite(15);
		}
		
	}
	
	public void initialiseJoueurComplement(Joueur joueur, int ligne, int colonne, Arme arme, Armure armure)
	{
		joueur.setLigne(ligne);
		joueur.setColonne(colonne);
		Inventaire sac = new Inventaire();//crée un inventaire pour le joueur
		joueur.setInventaire(sac);
		joueur.setArmeDroite(arme);
		joueur.setArmeGauche(arme);
		joueur.setArmure(armure);
		
	}
	
	
	
	public void expUp(Joueur joueur, Monstre m)
	{
		joueur.setExp(joueur.getExp()+m.getHpMax()/4);
		levelUp(joueur);
	}
	
	public void levelUp(Joueur joueur)
	{
		
		if (joueur.getExp()>pallier)
		{
			joueur.setExp(joueur.getExp()-this.pallier);
			this.pallier = this.pallier * 2;
			joueur.setLv(joueur.getLv()+1);
		}
	}
	
	
}
