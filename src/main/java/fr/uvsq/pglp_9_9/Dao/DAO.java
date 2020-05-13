package fr.uvsq.pglp_9_9.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public abstract class  DAO<T> {

	  public Connection connect = null;
	  public static String dburl = "jdbc:derby:bd;create=true";

	  public abstract T create(T obj);

	  public abstract T find(String id);

	  public abstract T update(T obj);

	  public abstract void delete(T obj);
	  public abstract ArrayList<T> getAllGroupeObject(String id);

	  

	  public void connect() {

	    try {
	      Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
	      connect = DriverManager.getConnection(dburl);
	    } catch (ClassNotFoundException | SQLException e) {
	      e.printStackTrace();
	      try {
	        connect.close();
	      } catch (SQLException ex) {
	        ex.printStackTrace();
	      }
	    }

	  }

	  /**
	  * Disonnects from database.
	  */
	  public void disconnect() {

	    try {
	      connect.close();
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	  }
	  
	  
	  
	
}
