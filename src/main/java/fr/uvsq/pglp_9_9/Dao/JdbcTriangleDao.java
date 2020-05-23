package fr.uvsq.pglp_9_9.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.uvsq.pglp_9_9.Dessin.Point;
import fr.uvsq.pglp_9_9.Dessin.Triangle;

public class JdbcTriangleDao implements Dao<Triangle>{

	
	private  Connection connect;
	
	public JdbcTriangleDao(Connection c) {
        connect = c;
    }
	
	
	@Override
    public Triangle create(Triangle object) {
        try {
            PreparedStatement prepare = connect.prepareStatement(
                    "INSERT INTO Forme"
                    + " (variableName)"
                    + " VALUES(?)");
                    prepare.setString(1, object.getNomVar());
                    prepare.executeUpdate();
            prepare = connect.prepareStatement(
                    "INSERT INTO Triangle"
                    + " (variableName,"
                    + "point1_x,point1_y,"
                    + "point2_x,point2_y,"
                    + "point3_x,point3_y)"
                    + " VALUES(?, ?, ?, ?, ?, ?, ?)");
            prepare.setString(1, object.getNomVar());
            prepare.setInt(2, object.getPosition(0).getX());
            prepare.setInt(3, object.getPosition(0).getY());
            prepare.setInt(4, object.getPosition(1).getX());
            prepare.setInt(5, object.getPosition(1).getY());
            prepare.setInt(6, object.getPosition(2).getX());
            prepare.setInt(7, object.getPosition(2).getY());
            prepare.executeUpdate();
        } catch (SQLException e) {
            return null;
        }
        return object;
    }
   
	
    @Override
    public Triangle find(String id) {

        Triangle find = null;
        try {
            PreparedStatement prepare = connect.prepareStatement(
                    "SELECT * FROM Triangle WHERE variableName = ?");
            prepare.setString(1, id);
            ResultSet result = prepare.executeQuery();
            if (result.next()) {
                Point[] p = {
                    new Point(
                            result.getInt("point1_x"),
                            result.getInt("point1_y")),
                    new Point(
                            result.getInt("point2_x"),
                            result.getInt("point2_y")),
                    new Point(
                            result.getInt("point3_x"),
                            result.getInt("point3_y")),
                };
                find = new Triangle(id, p[0], p[1], p[2]);
            }
        } catch (SQLException e) {
            return null;
        }
        return find;
    }
   
    
    @Override
    public ArrayList<Triangle> findAll() {
        ArrayList<Triangle> find = new ArrayList<Triangle>();
        try {
            PreparedStatement prepare = connect.prepareStatement(
                    "SELECT variableName FROM Triangle");
            ResultSet result = prepare.executeQuery();
            while (result.next()) {
                find.add(this.find(result.getString("variableName")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<Triangle>();
        }
        return find;
    }
   
    @Override
    public Triangle update(Triangle object) {
    	
        Triangle before = this.find(object.getNomVar());
        if (before != null) {
            try {
                PreparedStatement prepare = connect.prepareStatement(
                "UPDATE Triangle SET point1_x = ?, point1_y = ?, "
                + "point2_x = ?, point2_y = ?, point3_x = ?, point3_y = ?"
                + " WHERE variableName = ?");
                prepare.setInt(1, object.getPosition(0).getX());
                prepare.setInt(2, object.getPosition(0).getY());
                prepare.setInt(3, object.getPosition(1).getX());
                prepare.setInt(4, object.getPosition(1).getY());
                prepare.setInt(5, object.getPosition(2).getX());
                prepare.setInt(6, object.getPosition(2).getY());
                prepare.setString(7, object.getNomVar());
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
    public void delete(Triangle object) {
        try {
        	PreparedStatement preparestat = connect.prepareStatement(
                    "DELETE FROM Composition WHERE idComposant = ?");
            preparestat.setString(1, object.getNomVar());
            preparestat.executeUpdate();
            PreparedStatement prepare = connect.prepareStatement(
                    "DELETE FROM Triangle WHERE variableName = ?");
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
