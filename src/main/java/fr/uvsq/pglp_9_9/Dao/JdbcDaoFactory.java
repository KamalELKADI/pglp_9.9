package fr.uvsq.pglp_9_9.Dao;

import fr.uvsq.pglp_9_9.Dessin.Carre;
import fr.uvsq.pglp_9_9.Dessin.Cercle;
import fr.uvsq.pglp_9_9.Dessin.FormeGroupe;
import fr.uvsq.pglp_9_9.Dessin.Rectangle;
import fr.uvsq.pglp_9_9.Dessin.Triangle;

public class JdbcDaoFactory extends AbstractFactoryDao{

	@Override
	public Dao<Carre> CreateCarreDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dao<Triangle> CreateTriangleDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dao<Rectangle> CreateRectangleDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dao<Cercle> CreateCercleDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dao<FormeGroupe> CreateGroupeDao() {
		// TODO Auto-generated method stub
		return null;
	}

}
