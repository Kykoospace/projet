package modele;

import java.io.Serializable;


public class ArmureDeBois implements Armure, Serializable{

	@Override
	public int getDefenseBuff() {
		// TODO Auto-generated method stub
		return 100;
	}
	
	public String toString()
	{
		return "Armure de Bois";
	}

}
