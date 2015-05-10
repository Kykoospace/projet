package modele;



public abstract class Item extends Element{
	
	public Item ()
	{
		
	}
	public Item (int ligne, int colonne)
	{
		super(ligne, colonne);
	}
	
	public String toString()
	{
		return "i";
	}
	
}
