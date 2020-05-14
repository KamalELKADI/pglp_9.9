package fr.uvsq.pglp_9_9.Dao;

import java.sql.Connection;

import fr.uvsq.pglp_9_9.Dessin.Rectangle;

public class JdbcRectangleDao implements Dao<Rectangle>{

	private  Connection connect;
	
	public JdbcRectangleDao(Connection c) {
        connect = c;
    }
	
	@Override
	public Rectangle create(Rectangle object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle find(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle update(Rectangle object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Rectangle object) {
		// TODO Auto-generated method stub
		
	}

}
