package modele;

import controle.ServiceJoueur;

public class PotionDeSoin implements Potion{

	public void heal(Joueur joueur)
	{
		joueur.setHp(joueur.getHp()+100);
	}
	
	public String toString()
	{
		return "Potion de soin";
	}
}
