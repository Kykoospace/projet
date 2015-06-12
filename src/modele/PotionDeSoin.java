package modele;

import java.io.Serializable;

public class PotionDeSoin implements Potion, Serializable{

	public void heal(Joueur joueur)
	{
		joueur.setHp(joueur.getHp()+100);
	}
	
	public String toString()
	{
		return "Potion de soin";
	}
}
