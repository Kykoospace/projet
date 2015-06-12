package modele;

import java.io.Serializable;

public class Excalibur implements Arme, Serializable{

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
		return "Excalibur";
	}


}
