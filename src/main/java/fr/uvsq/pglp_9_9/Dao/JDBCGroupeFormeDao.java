package fr.uvsq.pglp_9_9.Dao;

import java.sql.Connection;

import fr.uvsq.pglp_9_9.Dessin.FormeGroupe;

public class JDBCGroupeFormeDao implements Dao<FormeGroupe>{

	private final Connection connect;
	
	public JDBCGroupeFormeDao(final Connection c) {
        connect = c;
    }
	@Override
	public FormeGroupe create(FormeGroupe object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FormeGroupe find(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FormeGroupe update(FormeGroupe object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(FormeGroupe object) {
		// TODO Auto-generated method stub
		
	}

}
