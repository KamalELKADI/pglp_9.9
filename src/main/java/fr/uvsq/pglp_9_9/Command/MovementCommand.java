package fr.uvsq.pglp_9_9.Command;

import fr.uvsq.pglp_9_9.Dao.Dao;
import fr.uvsq.pglp_9_9.Dao.JdbcDaoFactory;
import fr.uvsq.pglp_9_9.Dessin.Carre;
import fr.uvsq.pglp_9_9.Dessin.Cercle;
import fr.uvsq.pglp_9_9.Dessin.Forme;
import fr.uvsq.pglp_9_9.Dessin.FormeGroupe;
import fr.uvsq.pglp_9_9.Dessin.Point;
import fr.uvsq.pglp_9_9.Dessin.Rectangle;
import fr.uvsq.pglp_9_9.Dessin.Triangle;

public class MovementCommand implements Command{

	private Forme forme;
    
    private Point vecteur;
    
    public MovementCommand(Forme f, Point vecteurDeplacement) {
        this.vecteur = vecteurDeplacement;
        forme = f;
    }
	@Override
	public void execute() {
		forme.deplace(vecteur.getX(), vecteur.getY());
        JdbcDaoFactory factory = new JdbcDaoFactory();
        if (forme.getClass() == Cercle.class) {
            Dao<Cercle> dao = factory.CreateCercleDao();
            dao.update((Cercle) forme);
        } else if (forme.getClass() == Carre.class) {
            Dao<Carre> dao = factory.CreateCarreDAO();
            dao.update((Carre) forme);
        } else if (forme.getClass() == Rectangle.class) {
            Dao<Rectangle> dao = factory.CreateRectangleDao();
            dao.update((Rectangle) forme);
        } else if (forme.getClass() == Triangle.class) {
            Dao<Triangle> dao = factory.CreateTriangleDAO();
            dao.update((Triangle) forme);
        } else {
            Dao<FormeGroupe> dao = factory.CreateGroupeDao();
            dao.update((GroupeForme) forme);
        }
        factory.close();
		
	}

}
