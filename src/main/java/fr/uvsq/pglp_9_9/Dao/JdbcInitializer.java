package fr.uvsq.pglp_9_9.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;



public class JdbcInitializer {
	
	
    private static String nameBdd = "dessin";
    
    
    public static void setNomDessin(final String name) {
        nameBdd = name + "";
    }
    
    
    public static Connection Connection() {
    	 Properties props = new Properties(); // connection properties
    	 props.put("user", "kamal");
    	 props.put("password", "test");
        try {
            return DriverManager.getConnection(
                    "jdbc:derby:" + nameBdd + ";create=false");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    public static void init() throws Exception {
        Connection connect = JdbcInitializer.Connection();
        JdbcInitializer.delTables(connect);
        JdbcInitializer.CreateTableForme(connect);
        JdbcInitializer.CreateTableTriangle(connect);
        JdbcInitializer.CreateTableCarre(connect);
        JdbcInitializer.CreateTableRectangle(connect);
        JdbcInitializer.CreateTableCercle(connect);
        JdbcInitializer.CreateTableGroupeForme(connect);
        JdbcInitializer.CreateTableComposition(connect);
        connect.close();
    }
    
    
    public static void createDataBase()  {
        Connection c;
        try {
            c = DriverManager.getConnection(
                "jdbc:derby:" + nameBdd + ";create=true");
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void delTables(Connection connect) {
        Statement stat = null;
        try {
            stat = connect.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stat.execute("drop table Composition");
            stat.execute("drop table GroupeForme");
            stat.execute("drop table Cercle");
            stat.execute("drop table Rectangle");
            stat.execute("drop table Carre");
            stat.execute("drop table Triangle");
            stat.execute("drop table Forme");
        } catch (SQLException e) {
        }
      
    }
    
    
    private static void CreateTableForme(Connection connect){
    	try {
			String table = "create table Forme ("
                + "variableName varchar(30) primary key"
                + ")";
        Statement stat = connect.createStatement();
        stat.execute(table);
		} catch (SQLException e) {
			// TODO: handle exception
		}
        
    }
    
    
    private static void CreateTableTriangle(Connection connect){
    	try {
    		String table = "create table Triangle ("
                    + "variableName varchar(30) primary key,"
                    + "point1_x int,"
                    + "point1_y int,"
                    + "point2_x int,"
                    + "point2_y int,"
                    + "point3_x int,"
                    + "point3_y int,"
                    + "foreign key (variableName) references Forme (variableName)"
                    + ")";
            Statement stat = connect.createStatement();
            stat.execute(table);
		} catch (SQLException e) {
			
		}
        
    }
    
    
    private static void CreateTableCarre(Connection connect){
    	try {
    		String table = "create table Carre ("
                    + "variableName varchar(30) primary key,"
                    + "topLeft_x int,"
                    + "topLeft_y int,"
                    + "longueur int,"
                    + "foreign key (variableName) references Forme (variableName)"
                    + ")";
            Statement stat = connect.createStatement();
            stat.execute(table);
		} catch (SQLException e) {
			// TODO: handle exception
		}
        
    }
   
    
    private static void CreateTableRectangle(Connection connect){
    	try {
    		String table = "create table Rectangle ("
                    + "variableName varchar(30) primary key,"
                    + "topLeft_x int,"
                    + "topLeft_y int,"
                    + "longueur int,"
                    + "largeur int,"
                    + "foreign key (variableName) references Forme (variableName)"
                    + ")";
            Statement stat = connect.createStatement();
            stat.execute(table);
		} catch (SQLException e) {
			// TODO: handle exception
		}
        
    }
    
    
    private static void CreateTableCercle(Connection connect){
    	try {
    		 String table = "create table Cercle ("
    	                + "variableName varchar(30) primary key,"
    	                + "centre_x int,"
    	                + "centre_y int,"
    	                + "rayon int,"
    	                + "foreign key (variableName) references Forme (variableName)"
    	                + ")";
    	        Statement stat = connect.createStatement();
    	        stat.execute(table);
		} catch (SQLException e) {
			// TODO: handle exception
		}
       
    }
    
    
    private static void CreateTableGroupeForme(Connection connect){
    	try {
    		String table = "create table GroupeForme ("
                    + "variableName varchar(30) primary key,"
                    + "foreign key (variableName) references Forme (variableName)"
                    + ")";
            Statement stat = connect.createStatement();
            stat.execute(table);
		} catch (SQLException e) {
			// TODO: handle exception
		}
        
    }
    
    
    private static void CreateTableComposition(Connection connect){
    	try {
    		String table = "create table Composition ("
                    + "idGroupe varchar(30),"
                    + "idComposant varchar(30),"
                    + "primary key (idGroupe, idComposant),"
                    + "foreign key (idGroupe) references "
                    + "GroupeForme (variableName),"
                    + "foreign key (idComposant) "
                    + "references Forme (variableName)"
                    + ")";
            Statement stat = connect.createStatement();
            stat.execute(table);
		} catch (SQLException e) {
			// TODO: handle exception
		}
        
    }
	
	
}
