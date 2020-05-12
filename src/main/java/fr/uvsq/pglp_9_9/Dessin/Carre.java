package fr.uvsq.pglp_9_9.Dessin;

public class Carre extends Forme{

	/**
     * position du coin en haut à gauche du carré.
     */
    private Point topLeft;
    /**
     * longueur des côtés du carré.
     */
    private int longueur;
    /**
     * constructeur d'un carré avec position du coin en haut à gauche.
     * @param nomVariable nom de variable pour créer le carré
     * @param topLeftPosition position du coin en haut à gauche du carré
     * @param longueurCarre longueur du carré > 0
     * @throws Exception longueur invalide
     */
    public Carre(final String nomVar,
            final int longueurCarre) throws Exception {
        super(nomVar);
       
        this.setLongueur(longueurCarre);
    }

    /**
     * affiche un carré.
     */
    public void affiche() {
        super.affiche();
        System.out.println("Carre (longueur = "
                + longueur
                + ", position du coin en haut à gauche = " + topLeft + ")");
    }
   
    
    //Get longeur
    public int getLongueur() {
        return longueur;
    }
    
    //Set longeur
    public void setLongueur(final int longueurCarre) throws Exception {
        if (longueurCarre > 0) {
            this.longueur = longueurCarre;
        } else {
            throw new Exception();
        }
    }
	//Deplacer un carre
	public void deplace(int x, int y) {
		topLeft.deplace(x, y);
		
	}

}
