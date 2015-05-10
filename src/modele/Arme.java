package modele;



public class Arme extends Item{

	public static final int BOIS_ATK = 100;
	public static final int METAL_ATK = 200;
	public static final int EXCALIBUR_ATK = 500;
	public static final int MURASAME_ATK = 500;
	
	
	private int attaqueBuff;
	private int defenseBuff;
	private int agiliteBuff;
	private ArmeEnum type;
	
	public Arme(ArmeEnum type) {
		this.type = type;
		switch(type){
		case EpeeDeBois : 
			this.attaqueBuff = BOIS_ATK;
		break;
		case EpeeDeMetal :
			this.attaqueBuff = METAL_ATK;
			break;
		case Excalibur :
			this.attaqueBuff = EXCALIBUR_ATK;
			break;
		case Murasame :
			this.attaqueBuff = MURASAME_ATK;
			break;
		}
		// TODO Auto-generated constructor stub
	}
	
	public Arme(int ligne, int colonne) {
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
		case EpeeDeBois : 
			retour = "Epee de bois";
		break;
		case EpeeDeMetal :
			retour = "Epee de métal";
			break;
		case Excalibur :
			retour = "Excalibur";
			break;
		case Murasame :
			retour = "Murasame";
			break;
		}
		return retour;
	}
	

}
