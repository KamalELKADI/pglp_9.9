package fr.uvsq.pglp_9_9.Dessin;

public abstract class Forme {
	
    private String nomVar;
    
        
    public Forme(String nomVar) {

		this.nomVar = nomVar;
	}
	public String getNomVar() {
		return nomVar + "";
	}
	public void setNomVar(String nomVar) {
		this.nomVar = nomVar + "";
	}

    //deplacer une forme
    public abstract void deplace(int x, int y);
    
    
    //afficher la forme
    public void affiche() {
        System.out.print(nomVar + " : ");
    }
    

}
