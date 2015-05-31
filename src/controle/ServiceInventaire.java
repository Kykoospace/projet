package controle;

import modele.Arme;
import modele.ArmeType;
import modele.Armure;
import modele.Item;
import modele.Joueur;
import modele.Potion;
import modele.PotionDeSoin;

public class ServiceInventaire {
	private static ServiceInventaire serviceItem;
	//private Item[] inventaire;
	private Menu menu;
	
	public ServiceInventaire()
	{
		menu = new Menu("");
	}
	
	/*public Item[] getInventaire() {
		return inventaire;
	}

	public void setInventaire(Item[] inventaire) {
		this.inventaire = inventaire;
	}
	*/
	public void addItemInventaire(Item i, Joueur joueur)//ajouter un item à l'inventaire
	{
		if (i != null && setItemInventaire(i, joueur)==true)
		{
			System.out.println("Vous avez ajouté "+ i + " a votre inventaire");
		}
		else if (i == null)
		{
			
		}
		else
		{
			System.out.println("Votre inventaire est plein");//devra choisir de jeter un item ou pas si un son inventaire est plein
			int indiceItem = menu.menuDropItem();
			if (indiceItem >=0)
			{
				dropItem(indiceItem,joueur);
				joueur.getInventaire().getInventaire()[indiceItem-1]= i;
				System.out.println("Vous avez ajouté "+ i + " a votre inventaire");
			}
			
		}
	}
	
	public boolean setItemInventaire(Item i, Joueur joueur)//met un item à l'inventaire, si l'item est mis, renvoie true, sinon false
	{
		int i1=0;

		while (i1<5 && joueur.getInventaire().getInventaire()[i1]!=null)
		{
			i1++;
		}
		
		System.out.println(i1);
		if (i1==5 && joueur.getInventaire().getInventaire()[i1-1]!=null)
			return false;
		else 
		{
			joueur.getInventaire().getInventaire()[i1]= i;
			return true;
		}
		
	}

	public void dropItem(int i, Joueur joueur)//supprime un item de l'inventaire
	{
		if (i>=0 && i<=5)
			joueur.getInventaire().getInventaire()[i-1]=null;
	}
	
	public void equipeArmeGauche(Joueur joueur, Arme arme, int indiceItem)//le joueur équipe son arme
	{
		Arme tmp = joueur.getArmeGauche();//variable temporaire qui stock l'arme du joueur
		joueur.setArmeGauche(arme);//met à jour son arme
		joueur.getInventaire().getInventaire()[indiceItem-1]=null;//vide la place de l'arme equiper
		addItemInventaire(tmp, joueur);//rajoute l'arme qui était équipé dans l'inventaire
		if (arme.getType()==ArmeType.Arc && joueur.getArmeGauche()!= null)
		{
			desequipeArmeDroite(joueur);
		}
		else if (arme.getType()==ArmeType.Epee && joueur.getArmeGauche().getType()==ArmeType.Epee)
		{
			desequipeArmeDroite(joueur);
		}
	}
	
	public void equipeArmeDroite(Joueur joueur, Arme arme, int indiceItem)//le joueur équipe son arme
	{
		Arme tmp = joueur.getArmeDroite();//variable temporaire qui stock l'arme du joueur	
		joueur.setArmeDroite(arme);//met à jour son arme
		joueur.getInventaire().getInventaire()[indiceItem-1]=null;//vide la place de l'arme equiper
		addItemInventaire(tmp, joueur);//rajoute l'arme qui était équipé dans l'inventaire
		if (arme.getType()==ArmeType.Arc && joueur.getArmeGauche()!= null)
		{
			desequipeArmeGauche(joueur);
		}
		else if (arme.getType()==ArmeType.Epee && joueur.getArmeGauche().getType()==ArmeType.Epee)
		{
			desequipeArmeGauche(joueur);
		}
	}
	
	public void equipeArmure(Joueur joueur, Armure armure, int indiceItem)//le joueur équipe son armure
	{
		Armure tmp = joueur.getArmure();
		joueur.setArmure(armure);
		joueur.getInventaire().getInventaire()[indiceItem-1]=null;
		addItemInventaire(tmp, joueur);
	}
	
	public void desequipeArmeGauche(Joueur joueur)//le joueur retire son arme
	{
		addItemInventaire(joueur.getArmeGauche(), joueur);
		joueur.setArmeGauche(null);
	}
	
	public void desequipeArmeDroite(Joueur joueur)//le joueur retire son arme
	{
		addItemInventaire(joueur.getArmeDroite(), joueur);
		joueur.setArmeDroite(null);
	}
	
	public void desequipeArmure(Joueur joueur)//le joueur retire son armure
	{
		addItemInventaire(joueur.getArmure(), joueur);
		joueur.setArmure(null);
	}
	
	public void utiliserPotion(Joueur joueur, Potion p, int indiceItem)
	{
		if (p instanceof PotionDeSoin)
			p.heal(joueur);
		dropItem(indiceItem, joueur);
	}
	
	
	
	
	/**
	 * 
	 */
	
	
	
	
	
	
	
	
	
	
	
}
