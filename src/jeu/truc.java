/*public void deplacerJoueur(int direction, Joueur j)
	{
		int ligne = j.getLigne();
		int colonne = j.getColonne();
		int x=0;
		int y=0;
		if (direction == 0)
		{
			if (j.getLigne()>0 && (grille.vide(ligne-1, colonne) || grille.getGrille()[ligne-1][colonne].getClass()==Item.class))
				x-=1;
		}
		else if (direction == 1)
		{
			if (j.getLigne()<grille.getLigne()-1 && (grille.vide(ligne+1, colonne)|| grille.getGrille()[ligne+1][colonne].getClass()==Item.class))
				x++;
		}
		else if (direction == 2)
		{
			if (j.getColonne()>0 && (grille.vide(ligne, colonne-1)|| grille.getGrille()[ligne][colonne-1].getClass()==Item.class))
				y-=1;
		}
		else if (direction == 3)
		{
			if (j.getColonne()<grille.getColonne()-1 && (grille.vide(ligne, colonne+1)|| grille.getGrille()[ligne][colonne+1].getClass()==Item.class))

				y++;
		}
		grille.set(ligne, colonne, null);
		j.setLigne(ligne+x);
		j.setColonne(colonne+y);
		grille.set(j.getLigne(), j.getColonne(), j);
	}
	
	public void deplacerMonstre(int direction, Monstre m)
	{
		int ligne = m.getLigne();
		int colonne = m.getColonne();
		int x=0;
		int y=0;
		if (direction == 0)
		{
			if (m.getLigne()>0 && grille.vide(ligne-1, colonne))
				x-=1;
		}
		else if (direction == 1)
		{
			if (m.getLigne()<grille.getLigne()-1 && grille.vide(ligne+1, colonne))
				x++;
		}
		else if (direction == 2)
		{
			if (m.getColonne()>0 && grille.vide(ligne, colonne-1))
				y-=1;
		}
		else if (direction == 3)
		{
			if (m.getColonne()<grille.getColonne()-1 && grille.vide(ligne, colonne+1))
				y++;
		}
		
		grille.set(ligne, colonne, null);
		m.setLigne(ligne+x);
		m.setColonne(colonne+y);
		grille.set(m.getLigne(), m.getColonne(), m);
	}
	*/