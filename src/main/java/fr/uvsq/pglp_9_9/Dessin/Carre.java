package fr.uvsq.pglp_9_9.Dessin;

public class Carre extends Forme{

	//position du coin en haut à gauche du carré
    private Point topLeft;
   
    //longeur des cotes du Carre
    private int longueur;
   
    
    
    public Carre(String nomVariable, Point topLeftPosition,int longueurCarre){
        super(nomVariable);
        try {
			 topLeft = topLeftPosition.clone();
             setLongueur(longueurCarre);
		} catch (Exception e) {
			System.out.println("Erreur : " + e.getMessage());
		}
       
    }

    //Afficher le carre
    public void affiche() {
        super.affiche();
        System.out.println("Carre (longueur = "
                + longueur
                + ", position en haut à gauche = " + topLeft + ")");
    }
   
    
    //Get longeur
    public int getLongueur() {
        return longueur;
    }
    
    //Set longeur
    public void setLongueur(int longueurCarre) throws Exception {
        if (longueurCarre > 0) {
            longueur = longueurCarre;
        } else {
            throw new Exception();
        }
    }
    
	//Deplacer un carre
	public void deplace(int x, int y) {
		topLeft.deplace(x, y);
		
	}
	
	// Obtenir la position du coin en haut à gauche du carré.
	public Point getTopLeft() {
        return topLeft.clone();
    }

}
