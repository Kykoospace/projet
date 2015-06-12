package modele;

import java.io.Serializable;

public class EpeeDeBois implements Arme, Serializable{

	@Override
	public int getAttaqueBuff() {
		// TODO Auto-generated method stub
		return 100;
	}

	@Override
	public ArmeType getType() {
		// TODO Auto-generated method stub
		return ArmeType.Epee;
	}

	public String toString()
	{
		return "Epee de Bois";
	}
}
