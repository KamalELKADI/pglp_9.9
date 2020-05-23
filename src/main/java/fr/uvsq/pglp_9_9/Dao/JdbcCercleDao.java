package fr.uvsq.pglp_9_9.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.uvsq.pglp_9_9.Dessin.Cercle;
import fr.uvsq.pglp_9_9.Dessin.Point;


public class JdbcCercleDao implements Dao<Cercle>{

    private  Connection connect;
	
	public JdbcCercleDao(Connection c) {
        connect = c;
    }
	
	@Override
	public Cercle create(Cercle object) {

         try {
             PreparedStatement prepare = connect.prepareStatement(
                     "INSERT INTO Forme"
                     + " (variableName)"
                     + " VALUES(?)");
                     prepare.setString(1, object.getNomVar());
                     prepare.executeUpdate();
             prepare = connect.prepareStatement(
                     "INSERT INTO Cercle"
                     + " (variableName,centre_x,centre_y,rayon)"
                     + " VALUES(?, ?, ?, ?)");
             prepare.setString(1, object.getNomVar());
             prepare.setInt(2, object.getCentre().getX());
             prepare.setInt(3, object.getCentre().getY());
             prepare.setInt(4, object.getRayon());
             prepare.executeUpdate();
         } catch (SQLException e) {
             return null;
         }
         return object;
    }
	
	
	@Override
	public Cercle find(String id) {

        Cercle find = null;
        try {
            PreparedStatement prepare = connect.prepareStatement(
                    "SELECT * FROM Cercle WHERE variableName = ?");
            prepare.setString(1, id);
            ResultSet result = prepare.executeQuery();
            if (result.next()) {
                Point centre = new Point(
                        result.getInt("centre_x"),
                        result.getInt("centre_y"));
                try {
                    find = new Cercle(id, centre, result.getInt("rayon"));
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
    public ArrayList<Cercle> findAll() {
        ArrayList<Cercle> find = new ArrayList<Cercle>();
        try {
            PreparedStatement prepare = connect.prepareStatement(
                    "SELECT variableName FROM Cercle");
            ResultSet result = prepare.executeQuery();
            while (result.next()) {
                find.add(this.find(result.getString("variableName")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<Cercle>();
        }
        return find;
    }

	 @Override
	    public Cercle update(Cercle object) {

	        Cercle before = this.find(object.getNomVar());
	        if (before != null) {
	            try {
	                PreparedStatement prepare = connect.prepareStatement(
	                "UPDATE Cercle SET centre_x = ?, centre_y = ?, "
	                + "rayon = ? WHERE variableName = ?");
	                prepare.setInt(1, object.getCentre().getX());
	                prepare.setInt(2, object.getCentre().getY());
	                prepare.setInt(3, object.getRayon());
	                prepare.setString(4, object.getNomVar());
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
	    public void delete(Cercle object) {
	        try {
	        	PreparedStatement prepare = connect.prepareStatement(
	                    "DELETE FROM Composition WHERE idComposant = ?");
	            prepare.setString(1, object.getNomVar());
	            prepare.executeUpdate();
	            PreparedStatement prepare1 = connect.prepareStatement(
	                    "DELETE FROM Cercle WHERE variableName = ?");
	            prepare1.setString(1, object.getNomVar());
	            prepare1.executeUpdate();
	            prepare1 = connect.prepareStatement(
	                    "DELETE FROM Forme WHERE variableName = ?");
	            prepare1.setString(1, object.getNomVar());
	            prepare1.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	
	

	

}
