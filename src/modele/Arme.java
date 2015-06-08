package modele;

import controle.ServiceJoueur;



public interface Arme extends Item{
	int getAttaqueBuff();
	ArmeType getType();
	/*public static final int BONUS_CLASS = 100;
	
	public static final int BOIS_ATK = 100;
	public static final int METAL_ATK = 200;
	public static final int EXCALIBUR_ATK = 500;
	public static final int MURASAME_ATK = 500;
	public static final int ARC_BOIS_ATK = 100;*/
	
	/*private ArmeType type;
	private ArmeEnum id;
	private ServiceJoueur serviceJoueur = ServiceJoueur.getInstance();
	
	public Arme(ArmeEnum id) {
		this.id = id;
		switch(id){
		case EpeeDeBois : 
			this.type = ArmeType.Epee;
			this.attaqueBuff = BOIS_ATK;
		break;
		case EpeeDeMetal :
			this.type = ArmeType.Epee;
			this.attaqueBuff = METAL_ATK;
			break;
		case Excalibur :
			this.type = ArmeType.Epee;
			this.attaqueBuff = EXCALIBUR_ATK;
			break;
		case Murasame :
			this.type = ArmeType.Epee;
			this.attaqueBuff = MURASAME_ATK;

			break;
		case ArcDeBois :
			this.type = ArmeType.Arc;
			this.attaqueBuff= ARC_BOIS_ATK;
		}
		// TODO Auto-generated constructor stub
	}
	
	
	public ArmeType getType() {
		return type;
	}

	public void setType(ArmeType type) {
		this.type = type;
	}

	public ArmeEnum getId() {
		return id;
	}

	public void setId(ArmeEnum id) {
		this.id = id;
	}

	public String toString()
	{
		String retour = new String("");
		switch(id){
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
		case ArcDeBois :
			retour = "Arc de Bois";
			break;
		}
		return retour;
	}
	
*/
}
