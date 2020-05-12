package fr.uvsq.pglp_9_9.Dessin;

import java.util.ArrayList;

public class FormeGroupe extends Forme{

	private ArrayList<Forme> formes;
	
	public FormeGroupe(String nomVar) {
		super(nomVar);
		formes = new ArrayList<Forme>();
	}

	

	@Override
	public void deplace(int x, int y) {
		for (Forme forme : formes) {
			forme.deplace(x, y);
		}
		
	}
	
	@Override
	public void affiche() {
		// TODO Auto-generated method stub
		super.affiche();
	}
	
	
	

	
	
	
}
