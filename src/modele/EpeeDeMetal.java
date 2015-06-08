package modele;

public class EpeeDeMetal implements Arme{

	@Override
	public int getAttaqueBuff() {
		// TODO Auto-generated method stub
		return 200;
	}

	@Override
	public ArmeType getType() {
		// TODO Auto-generated method stub
		return ArmeType.Epee;
	}

	public String toString()
	{
		return "Epee de Metal";
	}
}
