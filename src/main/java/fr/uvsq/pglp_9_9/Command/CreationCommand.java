package fr.uvsq.pglp_9_9.Command;

import fr.uvsq.pglp_9_9.Dao.Dao;
import fr.uvsq.pglp_9_9.Dao.JdbcDaoFactory;
import fr.uvsq.pglp_9_9.Dessin.Carre;
import fr.uvsq.pglp_9_9.Dessin.Cercle;
import fr.uvsq.pglp_9_9.Dessin.Forme;
import fr.uvsq.pglp_9_9.Dessin.FormeGroupe;
import fr.uvsq.pglp_9_9.Dessin.Rectangle;
import fr.uvsq.pglp_9_9.Dessin.Triangle;

public class CreationCommand implements Command{
	private Forme forme;

	
	public CreationCommand(Forme forme) {
		super();
		this.forme = forme;
	}


	@Override
	public void execute() {
		Forme f;
        JdbcDaoFactory factory = new JdbcDaoFactory();
        if (forme.getClass() == Cercle.class) {
            Dao<Cercle> dao = factory.CreateCercleDao();
            f = dao.create((Cercle) forme);
        } else if (forme.getClass() == Carre.class) {
            Dao<Carre> dao = factory.CreateCarreDAO();
            f = dao.create((Carre) forme);
        } else if (forme.getClass() == Rectangle.class) {
            Dao<Rectangle> dao = factory.CreateRectangleDao();
            f = dao.create((Rectangle) forme);
        } else if (forme.getClass() == Triangle.class) {
            Dao<Triangle> dao = factory.CreateTriangleDAO();
            f = dao.create((Triangle) forme);
        } else {
            Dao<FormeGroupe> dao = factory.CreateGroupeDao();
            f = dao.create((FormeGroupe) forme);
        }
        factory.close();
        if (f != null) {
            System.out.println("La forme " + forme.getNomVar() + " a été ajouter avec succès.");
        } else {
            System.out.println("Une forme existe déjà à ce nom : "+ forme.getNomVar());
        }
		
	}

}
