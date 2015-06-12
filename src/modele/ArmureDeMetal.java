package modele;

import java.io.Serializable;


public class ArmureDeMetal implements Armure, Serializable{

	@Override
	public int getDefenseBuff() {
		// TODO Auto-generated method stub
		return 200;
	}
	
	public String toString()
	{
		return "Armure de Bois";
	}

}
