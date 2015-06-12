package modele;

import java.io.Serializable;

public interface Potion extends Item, Serializable{

	
	
	public void heal(Joueur joueur);
}
