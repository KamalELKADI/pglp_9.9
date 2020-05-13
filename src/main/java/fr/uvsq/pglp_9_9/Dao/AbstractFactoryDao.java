package fr.uvsq.pglp_9_9.Dao;

import fr.uvsq.pglp_9_9.Dessin.Carre;
import fr.uvsq.pglp_9_9.Dessin.Cercle;
import fr.uvsq.pglp_9_9.Dessin.FormeGroupe;
import fr.uvsq.pglp_9_9.Dessin.Rectangle;
import fr.uvsq.pglp_9_9.Dessin.Triangle;

public interface  AbstractFactoryDao<T> {

	  DAO<Carre> createCarreDao();
	  DAO<Triangle> createTriangleDao();
	  DAO<Rectangle> createRectangleDao();
	  DAO<Cercle> createCercleDao();
	  DAO<FormeGroupe> createGroupeDao();
 
  
}
