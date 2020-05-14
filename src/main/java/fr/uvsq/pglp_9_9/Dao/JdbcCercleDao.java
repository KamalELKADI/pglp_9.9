package fr.uvsq.pglp_9_9.Dao;

import java.sql.Connection;

import fr.uvsq.pglp_9_9.Dessin.Cercle;

public class JdbcCercleDao implements Dao<Cercle>{

    private  Connection connect;
	
	public JdbcCercleDao(Connection c) {
        connect = c;
    }
	
	@Override
	public Cercle create(Cercle object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cercle find(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cercle update(Cercle object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Cercle object) {
		// TODO Auto-generated method stub
		
	}

}
