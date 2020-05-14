package fr.uvsq.pglp_9_9.Dao;

import fr.uvsq.pglp_9_9.Dessin.Carre;
import fr.uvsq.pglp_9_9.Dessin.Cercle;
import fr.uvsq.pglp_9_9.Dessin.FormeGroupe;
import fr.uvsq.pglp_9_9.Dessin.Rectangle;
import fr.uvsq.pglp_9_9.Dessin.Triangle;

public abstract class  AbstractFactoryDao<T> {
	
	
	public abstract Dao<Carre> CreateCarreDAO();
	public abstract Dao<Triangle> CreateTriangleDAO();
	public abstract Dao<Rectangle> CreateRectangleDao();
	public abstract Dao<Cercle> CreateCercleDao();
	public abstract Dao<FormeGroupe> CreateGroupeDao();
 
  
}
