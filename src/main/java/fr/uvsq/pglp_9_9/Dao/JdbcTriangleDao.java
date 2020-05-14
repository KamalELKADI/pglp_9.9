package fr.uvsq.pglp_9_9.Dao;

import java.sql.Connection;

import fr.uvsq.pglp_9_9.Dessin.Triangle;

public class JdbcTriangleDao implements Dao<Triangle>{

	
	private  Connection connect;
	
	public JdbcTriangleDao(Connection c) {
        connect = c;
    }
	
	@Override
	public Triangle create(Triangle object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Triangle find(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Triangle update(Triangle object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Triangle object) {
		// TODO Auto-generated method stub
		
	}

}
