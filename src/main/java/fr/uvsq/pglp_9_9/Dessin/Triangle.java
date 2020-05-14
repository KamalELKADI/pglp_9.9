package fr.uvsq.pglp_9_9.Dessin;

public class Triangle extends Forme{

	//pour un triangle on a un ensemble des 3 points triangle
	    private Point[] points;
	    
	    //constructeur
	    public Triangle(String nomVar, Point point1, Point point2, Point point3) {
	        super(nomVar);
	        int trois = 3;
	        points = new Point[trois];
	        points[0] = point1.clone();
	        points[1] = point2.clone();
	        points[2] = point3.clone();
	    }
	    
	    // deplacer les 3 points du triangle
	    public void deplace(final int x, final int y) {
	        final int trois = 3;
	        for (int i = 0; i < trois; i++) {
	            points[i].deplace(x, y);
	        }
	    }
	    
	    
	    //affichage de la forme
	    public void affiche() {
	        super.affiche();
	        System.out.println("Triangle ("
	                + "position des points = " + points[0] + ", "
	                + points[1] + ", " + points[2] + ")");
	    }
	    
	    
	    //getPoint du triangle
	    public Point getPosition(final int index) {
	        if (index < 0 || index > 2) {
	            throw new IndexOutOfBoundsException();
	        } else {
	            return points[index].clone();
	        }
	    }

}
