package modele;

import java.io.Serializable;

public class ArcDeBois implements Arme, Serializable{

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
