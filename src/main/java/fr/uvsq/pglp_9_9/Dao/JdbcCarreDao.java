package fr.uvsq.pglp_9_9.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.uvsq.pglp_9_9.Dessin.Carre;
import fr.uvsq.pglp_9_9.Dessin.Point;

public class JdbcCarreDao implements Dao<Carre>{

    private  Connection connect;
	
	public JdbcCarreDao(Connection c) {
        connect = c;
    }
	
	@Override
	public Carre create(Carre object) {

        try {
            PreparedStatement prepare = connect.prepareStatement(
                    "INSERT INTO Forme"
                    + " (variableName)"
                    + " VALUES(?)");
                    prepare.setString(1, object.getNomVar());
                    prepare.executeUpdate();
            prepare = connect.prepareStatement(
                    "INSERT INTO Carre"
                    + " (variableName,topLeft_x,topLeft_y,longueur)"
                    + " VALUES(?, ?, ?, ?)");
            prepare.setString(1, object.getNomVar());
            prepare.setInt(2, object.getTopLeft().getX());
            prepare.setInt(3, object.getTopLeft().getY());
            prepare.setInt(4, object.getLongueur());
            prepare.executeUpdate();
        } catch (SQLException e) {
            return null;
        }
        return object;
    }
	
	
	@Override
	public Carre find(String id) {

        Carre find = null;
        try {
            PreparedStatement prepare = connect.prepareStatement(
                    "SELECT * FROM Carre WHERE variableName = ?");
            prepare.setString(1, id);
            ResultSet result = prepare.executeQuery();
            if (result.next()) {
                Point p = new Point(result.getInt("topLeft_x"),
                        result.getInt("topLeft_y"));
                try {
                    find = new Carre(id, p, result.getInt("longueur"));
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
    public ArrayList<Carre> findAll() {
        ArrayList<Carre> find = new ArrayList<Carre>();
        try {
            PreparedStatement prepare = connect.prepareStatement(
                    "SELECT variableName FROM Carre");
            ResultSet result = prepare.executeQuery();
            while (result.next()) {
                find.add(this.find(result.getString("variableName")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<Carre>();
        }
        return find;
    }

	@Override
	public Carre update(Carre object) {

        Carre before = this.find(object.getNomVar());
        if (before != null) {
            try {
                PreparedStatement prepare = connect.prepareStatement(
                "UPDATE Carre SET topLeft_x = ?, topLeft_y = ?, "
                + "longueur = ? WHERE variableName = ?");
                prepare.setInt(1, object.getTopLeft().getX());
                prepare.setInt(2, object.getTopLeft().getY());
                prepare.setInt(3, object.getLongueur());
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
	public void delete(Carre object) {

        try {
        	 PreparedStatement prepare = connect.prepareStatement(
                     "DELETE FROM Composition WHERE idComposant = ?");
             prepare.setString(1, object.getNomVar());
             prepare.executeUpdate();
            PreparedStatement prepare1 = connect.prepareStatement(
                    "DELETE FROM Carre WHERE variableName = ?");
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
