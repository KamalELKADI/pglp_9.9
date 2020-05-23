package fr.uvsq.pglp_9_9.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class JdbcTest {

	public Connection connect = null;
	Statement statement;
	
	
	public void setUp() {
		 try {
		      Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		      connect = DriverManager.getConnection("jdbc:derby:dessin;create=true");
		    } catch (ClassNotFoundException | SQLException e) {
		      e.printStackTrace();
		      try {
		        connect.close();
		      } catch (SQLException ex) {
		        ex.printStackTrace();
		      }
		    }
		 
		 
		 try {
			 
			 
			 String sql = "create table Forme ("
		                + "variableName varchar(30) primary key"
		                + ")";
			 statement = connect.createStatement();
		     statement.execute(sql);
			 
			 sql = "create table Triangle ("
		                + "variableName varchar(30) primary key,"
		                + "point1_x int,"
		                + "point1_y int,"
		                + "point2_x int,"
		                + "point2_y int,"
		                + "point3_x int,"
		                + "point3_y int,"
		                + "foreign key (variableName) references Forme (variableName)"
		                + ")";
			 statement.execute(sql);
			 
			 sql = "create table Carre ("
	                 + "variableName varchar(30) primary key,"
	                 + "topLeft_x int,"
	                 + "topLeft_y int,"
	                 + "longueur int,"
	                 + "foreign key (variableName) references Forme (variableName)"
	                 + ")";
			 statement.execute(sql);
			 
			 sql = "create table Rectangle ("
		                + "variableName varchar(30) primary key,"
		                + "topLeft_x int,"
		                + "topLeft_y int,"
		                + "longueur int,"
		                + "largeur int,"
		                + "foreign key (variableName) references Forme (variableName)"
		                + ")";
			 statement.execute(sql);
			 
			 sql = "create table Cercle ("
		                + "variableName varchar(30) primary key,"
		                + "centre_x int,"
		                + "centre_y int,"
		                + "rayon int,"
		                + "foreign key (variableName) references Forme (variableName)"
		                + ")";
			 statement.execute(sql);
			 
			 sql = "create table GroupeForme ("
		                + "variableName varchar(30) primary key,"
		                + "foreign key (variableName) references Forme (variableName)"
		                + ")";
			 statement.execute(sql);
			 statement.close();
		
		      
		     
		    } catch (SQLException e) {

		      e.printStackTrace();
		    }
		 
	}

	@Test
	public void works() {
		setUp();
		
		
	}

}
