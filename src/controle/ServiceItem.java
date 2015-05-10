package controle;

import modele.Arme;
import modele.Armure;
import modele.Item;
import modele.Joueur;

public class ServiceItem {
	private static ServiceItem serviceItem;
	private Item[] inventaire;
	private Menu menu;
	
	private ServiceItem()
	{
		
	}
	
	public static ServiceItem getInstance()//permet d'avoir un seul et unique inventaire pendant tout le long du jeu
	{
		if (serviceItem == null)
		{
			serviceItem = new ServiceItem();
		}
		return serviceItem;
	}
	
	public void initialiseServiceItem(Item[] inventaire)//initialise un inventaire
	{
		this.inventaire=inventaire;
		menu = new Menu("");
	}
	
	public Item[] getInventaire() {
		return inventaire;
	}

	public void setInventaire(Item[] inventaire) {
		this.inventaire = inventaire;
	}
	
	public void addItemInventaire(Item i)//ajouter un item à l'inventaire
	{
		if (setItemInventaire(i)==true)
		{
			System.out.println("Vous avez ajouté "+ i + " a votre inventaire");
		}
		else
		{
			System.out.println("Votre inventaire est plein");//devra choisir de jeter un item ou pas si un son inventaire est plein
			int indiceItem = menu.menuDropItem();
			if (indiceItem >=0)
			{
				dropItem(indiceItem);
				this.inventaire[indiceItem-1]= i;
			}
			
		}
	}
	
	public boolean setItemInventaire(Item i)//met un item à l'inventaire, si l'item est mis, renvoie true, sinon false
	{
		int i1=0;

		while (i1<5 && this.inventaire[i1]!=null)
		{
			i1++;
		}
		
		System.out.println(i1);
		if (i1==5 && this.inventaire[i1-1]!=null)
			return false;
		else 
		{
			this.inventaire[i1]= i;
			return true;
		}
		
	}

	public void dropItem(int i)//supprime un item de l'inventaire
	{
		this.inventaire[i-1]=null;
	}
	
	public void equipeArme(Arme arme, int indiceItem)//le joueur équipe son arme
	{
		Arme tmp = ServiceJoueur.getInstance().getJoueur().getArme();//variable temporaire qui stock l'arme du joueur
		
		ServiceJoueur.getInstance().getJoueur().setArme(arme);//met à jour son arme
		this.inventaire[indiceItem-1]=null;//vide la place de l'arme equiper
		addItemInventaire(tmp);//rajoute l'arme qui était équipé dans l'inventaire
	}
	
	public void equipeArmure(Armure armure, int indiceItem)//le joueur équipe son armure
	{
		Armure tmp = ServiceJoueur.getInstance().getJoueur().getArmure();
		ServiceJoueur.getInstance().getJoueur().setArmure(armure);
		this.inventaire[indiceItem-1]=null;
		addItemInventaire(tmp);
	}
	
	public void desequipeArme()//le joueur retire son arme
	{
		addItemInventaire(ServiceJoueur.getInstance().getJoueur().getArme());
		ServiceJoueur.getInstance().getJoueur().setArme(null);
	}
	
	public void desequipeArmure()//le joueur retire son armure
	{
		addItemInventaire(ServiceJoueur.getInstance().getJoueur().getArmure());
		ServiceJoueur.getInstance().getJoueur().setArmure(null);
	}
}
