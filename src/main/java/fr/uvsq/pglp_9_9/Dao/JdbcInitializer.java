package fr.uvsq.pglp_9_9.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcInitializer {
	
	private static final String userName = "Kamal";
	private static final String password = "root";
	
	public static String dburl = "jdbc:derby:dessin;create=false";
	
	public JdbcInitializer() {
		Properties connectionProps = new Properties(); // Inutilisé
		connectionProps.put("user", userName);
		connectionProps.put("user", password);
	}

	///////////////////////////////////////////////////////////////////////
	public static Connection getConnection() {
        try {
            return DriverManager.getConnection(dburl);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	
	public static void init() throws Exception {
        Connection connect = JdbcInitializer.getConnection();
        JdbcInitializer.suppTables(connect);
        JdbcInitializer.CreateForme(connect);
        JdbcInitializer.CreateTriangle(connect);
        JdbcInitializer.CreateCarre(connect);
        CreateRectangle(connect);
        CreateCercle(connect);
        CreateGroupeForme(connect);
        connect.close();
    }
	
	private static void suppTables(Connection connect) {
        Statement stat = null;
        try {
            stat = connect.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            
            stat.execute("drop table GroupeForme");
            stat.execute("drop table Cercle");
            stat.execute("drop table Rectangle");
            stat.execute("drop table Carre");
            stat.execute("drop table Triangle");
            stat.execute("drop table Forme");
        } catch (SQLException e) {
        }
        
    }
	
	//créer la table Forme
	private static void CreateForme(Connection connect){
		String table = "create table Forme ("
                + "variableName varchar(30) primary key"
                + ")";
		try {
			Statement stat = connect.createStatement();
            stat.execute(table);
		} catch (SQLException e) {
			
		}
		
        
    }
    
	//créer la table Triangle
    private static void CreateTriangle(Connection connect){
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
		try {
			Statement stat = connect.createStatement();
            stat.execute(table);
		} catch (SQLException e) {
			
		}
    	
    }
    
    //créer la table Carré.
    private static void CreateCarre(Connection connect){
    	 String table = "create table Carre ("
                 + "variableName varchar(30) primary key,"
                 + "topLeft_x int,"
                 + "topLeft_y int,"
                 + "longueur int,"
                 + "foreign key (variableName) references Forme (variableName)"
                 + ")";
    	
    	try {
    		 Statement stat = connect.createStatement();
    	        stat.execute(table);
		} catch (SQLException e) {
			
		}
       
       
    }
    
    //créer la table Rectangle.
    private static void CreateRectangle(Connection connect)
            throws SQLException {
        String table = "create table Rectangle ("
                + "variableName varchar(30) primary key,"
                + "topLeft_x int,"
                + "topLeft_y int,"
                + "longueur int,"
                + "largeur int,"
                + "foreign key (variableName) references Forme (variableName)"
                + ")";
        try {
        	 Statement stat = connect.createStatement();
             stat.execute(table);
		} catch (SQLException e) {
			
		}
       
    }
    
    
    //créer la table Cercle.
    private static void CreateCercle(Connection connect){
        String table = "create table Cercle ("
                + "variableName varchar(30) primary key,"
                + "centre_x int,"
                + "centre_y int,"
                + "rayon int,"
                + "foreign key (variableName) references Forme (variableName)"
                + ")";
        try {
        	 Statement stat = connect.createStatement();
             stat.execute(table);
		} catch (SQLException e) {
			
		}
        
    }
    
    
    //créer la table GroupeForme.
    private static void CreateGroupeForme(Connection connect){
        String table = "create table GroupeForme ("
                + "variableName varchar(30) primary key,"
                + "foreign key (variableName) references Forme (variableName)"
                + ")";
        try {
        	Statement stat = connect.createStatement();
            stat.execute(table);
		} catch (SQLException e) {
			
		}
        
    }
   
    /*private static void CreateComposition(final Connection connect){
        String table = "create table Composition ("
                + "idGroupe varchar(30),"
                + "idComposant varchar(30),"
                + "primary key (idGroupe, idComposant),"
                + "foreign key (idGroupe) references "
                + "GroupeForme (variableName),"
                + "foreign key (idComposant) "
                + "references Forme (variableName)"
                + ")";
        try {
        	Statement stat = connect.createStatement();
            stat.execute(table);
		} catch (SQLException e) {
			
		}
        
    }
	
	*/
	
}
