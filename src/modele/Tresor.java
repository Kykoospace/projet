package modele;

import java.io.Serializable;


public class Tresor extends Element implements Serializable{

	public Tresor (int ligne, int colonne)
	{
		super(ligne, colonne);
	}
	
	public String toString()
	{
		return "T";
	}
	
	
}
