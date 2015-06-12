package modele;

import java.io.Serializable;


public class Obstacle extends Element implements Serializable{
	public Obstacle (int ligne, int colonne)
	{
		super(ligne, colonne);
	}
	
	public String toString(){
		return "O";
	}
	
}
