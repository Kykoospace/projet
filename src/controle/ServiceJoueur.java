package controle;

import java.util.Random;

import modele.Arme;
import modele.ArmeType;
import modele.Armure;
import modele.Bouclier;
import modele.Inventaire;
import modele.Joueur;
import modele.Monstre;

public class ServiceJoueur {
	private Random rand = new Random();
	private static ServiceJoueur serviceJoueur;
	private int expTotale = 0;
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
		
		if (classe.equals("Saber") )
		{
			joueur.setHpMax(500);
			joueur.setHp(500);
			joueur.setManaMax(200);
			joueur.setMana(200);
			joueur.setExp(0);
			joueur.setPallier(1000);
			joueur.setLv(1);
			joueur.setPa(0);
			joueur.setPaRegen(5);
			joueur.setForce(20);
			joueur.setResistance(20);
			joueur.setAgilite(20);
		}
		else if (classe.equals("Archer"))
		{
			joueur.setHpMax(400);
			joueur.setHp(400);
			joueur.setManaMax(300);
			joueur.setMana(300);
			joueur.setExp(0);
			joueur.setPallier(1000);
			joueur.setLv(1);
			joueur.setPa(0);
			joueur.setPaRegen(6);
			joueur.setForce(15);
			joueur.setResistance(10);
			joueur.setAgilite(30);
		}
		
	}
	
	public void initialiseJoueurComplement(Joueur joueur, int ligne, int colonne, Arme arme, Armure armure)
	{
		joueur.setLigne(ligne);
		joueur.setColonne(colonne);
		Inventaire sac = new Inventaire();//cree un inventaire pour le joueur
		joueur.setInventaire(sac);
		joueur.setArmeDroite(arme);
		joueur.setArmeGauche(null);
		joueur.setArmure(armure);
		
	}
	
	public void expUp(Joueur joueur, Monstre m)
	{
		joueur.setExp(joueur.getExp()+m.getHpMax()/4);
		levelUp(joueur);
	}
	
	public void levelUp(Joueur joueur)
	{
		
		if (joueur.getExp()>joueur.getPallier())
		{
			joueur.setExp(joueur.getExp()-joueur.getPallier());
			joueur.setPallier(joueur.getPallier()*2);
			joueur.setLv(joueur.getLv()+1);
		}
		joueur.setPx(joueur.getPx()+3);
	}
	
	public void usePx(Joueur joueur, int px, int stat)
	{
		joueur.setPx(joueur.getPx()-px);
		if (stat == 1)
			upAtk(joueur, px);
		else if (stat == 2)
			upDef(joueur, px);
		else if (stat == 3)
			upAgi(joueur, px);
	}
	
	public void upAtk(Joueur joueur, int pts)
	{
		joueur.setForce(joueur.getForce()+pts);
	}
	
	public void upDef(Joueur joueur, int pts)
	{
		joueur.setResistance(joueur.getResistance()+pts);
	}
	
	public void upAgi(Joueur joueur, int pts)
	{
		joueur.setAgilite(joueur.getAgilite()+pts);
	}
	
	public int calculDe(int atk)
	{
		int de=0;
		de = atk/3;
		return de;
	}
	public int calculReste(int atk)
	{
		int reste=0;
		reste = atk%3;
		
		return reste;
	}
	
	public int calculAttaque(Joueur joueur, int main)
	{
		int atk=0;
		int de=0;
		int reste=0;
		int dmg = 0;
		//formule de dommage
		if (main == 1)
		{
			if (joueur.getArmeGauche()==null)
			{
				atk = joueur.getForce();
			}
			else
			atk = joueur.getForce()+serviceJoueur.setBonusAttaqueGauche(joueur);
		}
		else if (main==2)
		{
			if (joueur.getArmeDroite()==null)
			{
				atk = joueur.getForce();
			}
		else
			atk = joueur.getForce()+serviceJoueur.setBonusAttaqueDroite(joueur);
		}
		
		de = calculDe(atk);
		reste = calculReste(atk);
		
		for (int i=0; i<de; i++)
		{
			dmg = dmg + rand.nextInt(6);
		}
		dmg = dmg+reste;
		
		return dmg;
	}
	
	public int setBonusClasse(Joueur joueur, Arme arme)
	{
		if (joueur.getClasse().equals("Saber"))
		{
			if (arme.getType()==ArmeType.Epee)
				return 100;
		}
		if (joueur.getClasse().equals("Archer"))
		{
			if (arme.getType()==ArmeType.Arc)
				return 100;
		}
		return 0;
	}
	
	public int setBonusAttaqueGauche(Joueur joueur)
	{
		int bonus=0;
		try {
			bonus = joueur.getArmeGauche().getAttaqueBuff();
			bonus = bonus + setBonusClasse(joueur, joueur.getArmeGauche());
		} catch (NullPointerException e) {
			
		}
		return bonus;
	}
	
	public int setBonusAttaqueDroite(Joueur joueur)
	{
		int bonus=0;
		try {
			bonus = joueur.getArmeDroite().getAttaqueBuff();
			bonus = bonus + setBonusClasse(joueur, joueur.getArmeDroite());
		} catch (NullPointerException e) {
			
		}
		return bonus;
	}
	
	public int setBonusDefense(Joueur joueur)
	{
		int bonus=0;
		try {
			bonus = joueur.getArmure().getDefenseBuff();
		} catch (NullPointerException e) {
			
		}
		if (joueur.getArmeGauche()!=null && joueur.getArmeGauche() instanceof Bouclier )
		{
			Bouclier bouclier;
			bouclier = (Bouclier)joueur.getArmeGauche();
			bonus = bonus + bouclier.getDefenseBuff();
		}
		if (joueur.getArmeDroite()!=null && joueur.getArmeDroite() instanceof Bouclier)
		{
			Bouclier bouclier;
			bouclier = (Bouclier)joueur.getArmeDroite();
			bonus = bonus + bouclier.getDefenseBuff();
		}
		return bonus;
	}
	
	public void afficheEtat(Joueur joueur)
	{
		int bonusArmeGauche=0;
		int bonusArmeDroite=0;
		int bonusArmure=0;
		
		bonusArmeGauche = setBonusAttaqueGauche(joueur);
		bonusArmeDroite = setBonusAttaqueDroite(joueur);
		bonusArmure = setBonusDefense(joueur);
		System.out.print(joueur.getNom() + 
				"\nArme Gauche :" + joueur.getArmeGauche() +
				"\nArme Droite :" + joueur.getArmeDroite() +
				"\nArmure :" + joueur.getArmure() +
				"\nLv :" + joueur.getLv() +
				"\nExperience :" + joueur.getExp() +
				"/" + joueur.getPallier() +
				"\nPa :" + joueur.getPa()+
				"/20\nHP :" + joueur.getHp()+ "/" + joueur.getHpMax()+
				"\nMana :" + joueur.getMana() + "/" + joueur.getManaMax() +
				"\nForce Gauche :" + joueur.getForce() + "( +" + setBonusAttaqueGauche(joueur)+ ")" + " ( " + calculDe(joueur.getForce()+setBonusAttaqueGauche(joueur))  + "D + " + calculReste(joueur.getForce()+setBonusAttaqueGauche(joueur)) +
				"\nForce Droite :" + joueur.getForce() + "( +" + setBonusAttaqueDroite(joueur)+ ")" + " ( " + calculDe(joueur.getForce()+setBonusAttaqueDroite(joueur))  + "D + " + calculReste(joueur.getForce()+setBonusAttaqueDroite(joueur)) +
				"\nResistance :"+ joueur.getResistance() + "( +" + setBonusDefense(joueur)+ ")" +
				"\nAgilite :" + joueur.getAgilite());
		
		
	}
	
}
