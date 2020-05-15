package fr.uvsq.pglp_9_9.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import fr.uvsq.pglp_9_9.Dao.Dao;
import fr.uvsq.pglp_9_9.Dao.JdbcDaoFactory;
import fr.uvsq.pglp_9_9.Dessin.Carre;
import fr.uvsq.pglp_9_9.Dessin.FormeGroupe;
import fr.uvsq.pglp_9_9.Dessin.Point;

public class CarreDaoTest {

	public Connection connect = null;
	Statement statement;
	
	public void setUp() {
	
		 
		 try {
		      String sql = "CREATE TABLE Groupe(groupeid varchar(30) primary key not null)";
		      statement = connect.createStatement();
		      statement.execute(sql);
		      sql = "CREATE TABLE Carre(nom varchar(20) PRIMARY KEY NOT NULL, " 
				         + " cote int, x int, y int, "   
				         + "groupeid varchar(30) references groupe(groupeid))";
			  statement.execute(sql);
			  statement.close();
			  
			
		      
		     
		    } catch (SQLException e) {

		      e.printStackTrace();
		    }
		 
	}
	
	@Test
    public void testConstructorAndGetters() throws Exception {
        Carre r = new Carre("r", new Point(1,1), 10);
        assertEquals(r.getNomVar(), "r");
        assertTrue(r.getTopLeft().toString().equals("(1,1)"));
        assertTrue(r.getLongueur() == 10);
    }
    @Test
    public void testAffiche() throws Exception {
        Carre r = new Carre("r", new Point(1,1), 10);
        r.affiche();
    }
    @Test
    public void testDeplace() throws Exception {
        Carre r = new Carre("r", new Point(1,1), 10);
        r.deplace(10, 20);
        assertTrue(r.getTopLeft().toString().equals("(11,21)"));
    }
 
	


}
