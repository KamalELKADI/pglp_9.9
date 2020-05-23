package fr.uvsq.pglp_9_9.Command;

import java.util.ArrayList;

import fr.uvsq.pglp_9_9.Dao.Dao;
import fr.uvsq.pglp_9_9.Dao.JdbcDaoFactory;
import fr.uvsq.pglp_9_9.Dessin.Carre;
import fr.uvsq.pglp_9_9.Dessin.Cercle;
import fr.uvsq.pglp_9_9.Dessin.Forme;
import fr.uvsq.pglp_9_9.Dessin.FormeGroupe;
import fr.uvsq.pglp_9_9.Dessin.Rectangle;
import fr.uvsq.pglp_9_9.Dessin.Triangle;

public class RemoveCommand implements Command{

	 private ArrayList<Forme> list;
	  
	    public RemoveCommand(ArrayList<Forme> f) {
	        list = f;
	    }
	    
	@Override
	public void execute() {
		    JdbcDaoFactory factory = new JdbcDaoFactory();
	        for (Forme forme : list) {
	            if (forme.getClass() == Cercle.class) {
	                Dao<Cercle> dao = factory.CreateCercleDao();
	                dao.delete((Cercle) forme);
	                System.out.println("La forme " + forme.getNomVar() + " a été supprimer avec succès.");
	            } else if (forme.getClass() == Carre.class) {
	                Dao<Carre> dao = factory.CreateCarreDAO();
	                dao.delete((Carre) forme);
	                System.out.println("La forme " + forme.getNomVar() + " a été supprimer avec succès.");
	            } else if (forme.getClass() == Rectangle.class) {
	                Dao<Rectangle> dao = factory.CreateRectangleDao();
	                dao.delete((Rectangle) forme);
	                System.out.println("La forme " + forme.getNomVar() + " a été supprimer avec succès.");
	            } else if (forme.getClass() == Triangle.class) {
	                Dao<Triangle> dao = factory.CreateTriangleDAO();
	                dao.delete((Triangle) forme);
	                System.out.println("La forme " + forme.getNomVar() + " a été supprimer avec succès.");
	            } else {
	                Dao<FormeGroupe> dao = factory.CreateGroupeDao();
	                dao.delete((FormeGroupe) forme);
	                System.out.println("La forme " + forme.getNomVar() + " a été supprimer avec succès.");
	            }
	        }
	        factory.close();
		
	}

}
