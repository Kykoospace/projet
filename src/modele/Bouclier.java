package modele;

public class Bouclier implements Arme, Armure{

	@Override
	public int getDefenseBuff() {
		// TODO Auto-generated method stub
		return 200;
	}

	@Override
	public int getAttaqueBuff() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public ArmeType getType() {
		// TODO Auto-generated method stub
		return ArmeType.Bouclier;
	}

	public String toString()
	{
		return "Bouclier";
	}

}
