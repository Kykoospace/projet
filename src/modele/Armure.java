package modele;



public class Armure extends Item{

	public static final int BOIS_DEF = 100;
	public static final int METAL_DEF = 200;
	

	
	private int attaqueBuff;
	private int defenseBuff;
	private int agiliteBuff;
	private ArmureEnum type;
	
	public Armure (ArmureEnum type)
	{
		this.type = type;
		
		switch(type){
		case ArmureDeBois : 
			this.defenseBuff = BOIS_DEF;
		break;
		case ArmureDeMetal :
			this.defenseBuff = METAL_DEF;
			break;
	
		
		
		}
	}
	
	public Armure(int ligne, int colonne) {
		super(ligne, colonne);
		// TODO Auto-generated constructor stub
	}
	
	
	
	 public int getAttaqueBuff() {
		return attaqueBuff;
	}



	public void setAttaqueBuff(int attaqueBuff) {
		this.attaqueBuff = attaqueBuff;
	}



	public int getDefenseBuff() {
		return defenseBuff;
	}



	public void setDefenseBuff(int defenseBuff) {
		this.defenseBuff = defenseBuff;
	}



	public int getAgiliteBuff() {
		return agiliteBuff;
	}



	public void setAgiliteBuff(int agiliteBuff) {
		this.agiliteBuff = agiliteBuff;
	}

	
	public String toString()
	{
		String retour = new String("");
		switch(type){
		case ArmureDeBois : 
			retour = "Armure de bois";
		break;
		case ArmureDeMetal :
			retour = "Armure de m�tal";
			break;
		}
		return retour;
	}
	
}
