package fr.uvsq.pglp_9_9.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.uvsq.pglp_9_9.Dessin.Point;
import fr.uvsq.pglp_9_9.Dessin.Rectangle;

public class JdbcRectangleDao implements Dao<Rectangle>{

	private  Connection connect;
	
	public JdbcRectangleDao(Connection c) {
        connect = c;
    }
	
	 @Override
	    public Rectangle create(Rectangle object) {
	        try {
	            PreparedStatement prepare = connect.prepareStatement(
	                    "INSERT INTO Forme"
	                    + " (variableName)"
	                    + " VALUES(?)");
	                    prepare.setString(1, object.getNomVar());
	                    prepare.executeUpdate();
	            prepare = connect.prepareStatement(
	                    "INSERT INTO Rectangle"
	                    + " (variableName,topLeft_x,topLeft_y,longueur,largeur)"
	                    + " VALUES(?, ?, ?, ?, ?)");
	            prepare.setString(1, object.getNomVar());
	            prepare.setInt(2, object.getTopLeft().getX());
	            prepare.setInt(3, object.getTopLeft().getY());
	            prepare.setInt(4, object.getLongueur());
	            prepare.setInt(5, object.getLargeur());
	            prepare.executeUpdate();
	        } catch (SQLException e) {
	            return null;
	        }
	        return object;
	    }
	    
	 
	    @Override
	    public Rectangle find(String id) {

	        Rectangle find = null;
	        try {
	            PreparedStatement prepare = connect.prepareStatement(
	                    "SELECT * FROM Rectangle WHERE variableName = ?");
	            prepare.setString(1, id);
	            ResultSet result = prepare.executeQuery();
	            if (result.next()) {
	                Point p = new Point(
	                        result.getInt("topLeft_x"),
	                        result.getInt("topLeft_y")
	                );
	                try {
	                    find = new Rectangle(id, p,
	                            result.getInt("longueur"),
	                            result.getInt("largeur")
	                    );
	                } catch (Exception e) {
	                    e.printStackTrace();
	                    return null;
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return null;
	        }
	        return find;
	    }
	    
	    @Override
	    public ArrayList<Rectangle> findAll() {
	        ArrayList<Rectangle> find = new ArrayList<Rectangle>();
	        try {
	            PreparedStatement prepare = connect.prepareStatement(
	                    "SELECT variableName FROM Rectangle");
	            ResultSet result = prepare.executeQuery();
	            while (result.next()) {
	                find.add(this.find(result.getString("variableName")));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return new ArrayList<Rectangle>();
	        }
	        return find;
	    }
	    
	    
	    @Override
	    public Rectangle update(Rectangle object) {

	        Rectangle before = this.find(object.getNomVar());
	        if (before != null) {
	            try {
	                PreparedStatement prepare = connect.prepareStatement(
	                "UPDATE Rectangle SET topLeft_x = ?, topLeft_y = ?, "
	                + "longueur = ?, largeur = ? WHERE variableName = ?");
	                prepare.setInt(1, object.getTopLeft().getX());
	                prepare.setInt(2, object.getTopLeft().getY());
	                prepare.setInt(3, object.getLongueur());
	                prepare.setInt(4, object.getLargeur());
	                prepare.setString(5, object.getNomVar());
	                prepare.executeUpdate();
	            } catch (SQLException e) {
	                e.printStackTrace();
	                return before;
	            }
	        } else {
	            return null;
	        }
	        return object;
	    }
	    

	    
	    @Override
	    public void delete(Rectangle object) {
	        
	        try {
	        	PreparedStatement preparestat = connect.prepareStatement(
	                    "DELETE FROM Composition WHERE idComposant = ?");
	            preparestat.setString(1, object.getNomVar());
	            preparestat.executeUpdate();
	            
	            PreparedStatement prepare = connect.prepareStatement(
	                    "DELETE FROM Rectangle WHERE variableName = ?");
	            prepare.setString(1, object.getNomVar());
	            prepare.executeUpdate();
	            prepare = connect.prepareStatement(
	                    "DELETE FROM Forme WHERE variableName = ?");
	            prepare.setString(1, object.getNomVar());
	            prepare.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

}
