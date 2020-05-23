package fr.uvsq.pglp_9_9.Dao;

import java.sql.Connection;
import java.sql.SQLException;

import fr.uvsq.pglp_9_9.Dessin.Carre;
import fr.uvsq.pglp_9_9.Dessin.Cercle;
import fr.uvsq.pglp_9_9.Dessin.FormeGroupe;
import fr.uvsq.pglp_9_9.Dessin.Rectangle;
import fr.uvsq.pglp_9_9.Dessin.Triangle;

public class JdbcDaoFactory extends AbstractFactoryDao{

	private Connection connect;
    
    public JdbcDaoFactory() {
        connect = JdbcInitializer.Connection();
    }
	
	public void close() {
        try {
            connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	@Override
	public Dao<Carre> CreateCarreDAO() {
		// TODO Auto-generated method stub
		return new JdbcCarreDao(connect);
	}

	@Override
	public Dao<Triangle> CreateTriangleDAO() {
		// TODO Auto-generated method stub
		return new JdbcTriangleDao(connect);
	}

	@Override
	public Dao<Rectangle> CreateRectangleDao() {
		// TODO Auto-generated method stub
		return new JdbcRectangleDao(connect);
	}

	@Override
	public Dao<Cercle> CreateCercleDao() {
		// TODO Auto-generated method stub
		return new JdbcCercleDao(connect);
	}

	@Override
	public Dao<FormeGroupe> CreateGroupeDao() {
		// TODO Auto-generated method stub
		return new JdbcGroupeFormeDao(connect);
	}

}
