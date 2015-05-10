package modele;

import java.util.Arrays;

public class Inventaire {
	private Item[] inventaire;

	public Inventaire() {
			this.inventaire = new Item[5];
	}

	public Item[] getInventaire() {
		return inventaire;
	}

	
	
	@Override
	public String toString() {
		return "Inventaire [inventaire=" + Arrays.toString(inventaire) + "]";
	}
	
	
}
