package modele;

public class ArcDeBois implements Arme{

	@Override
	public int getAttaqueBuff() {
		// TODO Auto-generated method stub
		return 100;
	}

	@Override
	public ArmeType getType() {
		// TODO Auto-generated method stub
		return ArmeType.Arc;
	}

	public String toString()
	{
		return "Arc de Bois";
	}
}
