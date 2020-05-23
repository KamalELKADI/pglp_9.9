package fr.uvsq.pglp_9_9.Dessin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class FormeGroupe extends Forme implements Iterable<Forme>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	

	

    public void affiche() {
        super.affiche();
        System.out.println("Groupe (");
        for (Forme f : formes) {
            f.affiche();
        }
        System.out.println(")");
    }
    
    public void add(final Forme f) {
        if (!formes.contains(f) && f != this) {
            formes.add(f);
        }
    }
    
    
    public void remove(Forme f) {
        formes.remove(f);
    }
    
    
    //Afficher la liste des formes et groupes du groupe
    @SuppressWarnings("unchecked")
    public ArrayList<Forme> getList() {
        return (ArrayList<Forme>) formes.clone();
    }
    
    
    //afficher les éléments du groupe
    public Iterator<Forme> iterator() {
        return formes.iterator();
    }
	
	
	

	
	
	
}
