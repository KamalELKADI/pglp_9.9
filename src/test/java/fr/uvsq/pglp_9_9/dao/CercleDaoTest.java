package fr.uvsq.pglp_9_9.dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;

import javax.management.openmbean.KeyAlreadyExistsException;

import org.junit.BeforeClass;
import org.junit.Test;

import fr.uvsq.pglp_9_9.Dao.Dao;
import fr.uvsq.pglp_9_9.Dao.JdbcCercleDao;
import fr.uvsq.pglp_9_9.Dao.JdbcInitializer;
import fr.uvsq.pglp_9_9.Dessin.Cercle;
import fr.uvsq.pglp_9_9.Dessin.Point;

public class CercleDaoTest {

	  private static Cercle c1;
	  static Dao<Cercle> dao;

	  @BeforeClass
	  public static void init() throws SQLException {
	   /* Properties props = new Properties(); // connection properties
	    props.put("user", "user");
	    props.put("password", "user");
	    Connection conn = DriverManager.getConnection("jdbc:derby:dessins", props);*/
	    JdbcCercleDao cercle = JdbcInitializer.Connection();
	    InitBdd.createTables();
 
	    c1 = new Cercle("c1",new Point(1,1),6);
	    dao = DaoFactory.getDaoCercle();

	  }



	  @Test
	  public void testCreate() throws SQLException, IOException, ClassNotFoundException, KeyAlreadyExistsException {
	    dao.create(c1);
	  }

	  @Test
	  public void testGet() throws IOException, SQLException, ClassNotFoundException {
	    Cercle c = (Cercle) dao.get("c1");
	    //System.out.println(c.getClass().getSimpleName());
	  }


	  @Test
	  public void testUpdate() throws IOException, SQLException, ClassNotFoundException {
	    c1 = new Cercle("c3",2,1,4);
	    dao.update(c1);
	    dao.get("c3");

	  }

	  @Test
	  public void testDelete() {

	  }
}
