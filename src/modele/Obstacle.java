package modele;


public class Obstacle extends Element{
	public Obstacle (int ligne, int colonne)
	{
		super(ligne, colonne);
	}
	
	public String toString(){
		return "O";
	}
	
}
