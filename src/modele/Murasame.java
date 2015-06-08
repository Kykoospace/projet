package modele;


public class Murasame implements Arme{

	@Override
	public int getAttaqueBuff() {
		// TODO Auto-generated method stub
		return 500;
	}

	@Override
	public ArmeType getType() {
		// TODO Auto-generated method stub
		return ArmeType.Epee;
	}

	public String toString()
	{
		return "Murasame";
	}

}
