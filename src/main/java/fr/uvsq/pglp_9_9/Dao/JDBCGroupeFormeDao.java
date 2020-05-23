package fr.uvsq.pglp_9_9.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.uvsq.pglp_9_9.Dessin.Carre;
import fr.uvsq.pglp_9_9.Dessin.Cercle;
import fr.uvsq.pglp_9_9.Dessin.Forme;
import fr.uvsq.pglp_9_9.Dessin.FormeGroupe;
import fr.uvsq.pglp_9_9.Dessin.Rectangle;
import fr.uvsq.pglp_9_9.Dessin.Triangle;


public class JdbcGroupeFormeDao implements Dao<FormeGroupe>{

	private  Connection connect;
	
	public JdbcGroupeFormeDao(Connection c) {
        connect = c;
    }
	
    
    
    public ArrayList<Forme> findComposition(String id) {

        ArrayList<Forme> find = new ArrayList<Forme>();
        JdbcDaoFactory factory = new JdbcDaoFactory();
        try {
            PreparedStatement prepare = connect.prepareStatement(
                    "SELECT idComposant "
                    + "FROM Composition WHERE idGroupe = ?");
            prepare.setString(1, id);
            ResultSet result = prepare.executeQuery();
            Dao<Cercle> CercleDao = factory.CreateCercleDao();
            Dao<Carre> CarreDao = factory.CreateCarreDAO();
            Dao<Rectangle> RectangleDao = factory.CreateRectangleDao();
            Dao<Triangle> TriangleDao = factory.CreateTriangleDAO();
            while (result.next()) {
                Forme f = CercleDao.find(result.getString("idComposant"));
                if (f == null) {
                    f = CarreDao.find(result.getString("idComposant"));
                }
                if (f == null) {
                    f = RectangleDao.find(result.getString("idComposant"));
                }
                if (f == null) {
                    f = TriangleDao.find(result.getString("idComposant"));
                }
                if (f == null) {
                    f = this.find(result.getString("idComposant"));
                }
                find.add(f);
            }
            factory.close();
        } catch (SQLException e) {
            factory.close();
            return new ArrayList<Forme>();
        }
        return find;
    }
    

 
   
    
    
    @Override
    public FormeGroupe create(FormeGroupe object) {
        JdbcDaoFactory factory = new JdbcDaoFactory();
        try {
            PreparedStatement prepare = connect.prepareStatement(
                    "INSERT INTO Forme"
                    + " (variableName)"
                    + " VALUES(?)");
            prepare.setString(1, object.getNomVar());
            prepare.executeUpdate();
            prepare = connect.prepareStatement(
                    "INSERT INTO GroupeForme"
                    + " (variableName)"
                    + " VALUES(?)");
            prepare.setString(1, object.getNomVar());
            prepare.executeUpdate();
            for (Forme f : object.getList()) {
                if (f.getClass() == Cercle.class) {
                    Dao<Cercle> dao = factory.CreateCercleDao();
                    dao.create((Cercle) f);
                } else if (f.getClass() == Carre.class) {
                    Dao<Carre> dao = factory.CreateCarreDAO();
                    dao.create((Carre) f);
                } else if (f.getClass() == Rectangle.class) {
                    Dao<Rectangle> dao = factory.CreateRectangleDao();
                    dao.create((Rectangle) f);
                } else if (f.getClass() == Triangle.class) {
                    Dao<Triangle> dao = factory.CreateTriangleDAO();
                    dao.create((Triangle) f);
                } else {
                    this.create((FormeGroupe) f);
                }
                
                PreparedStatement preparestat = connect.prepareStatement(
                        "INSERT INTO Composition"
                        + " (idGroupe, idComposant)"
                        + " VALUES(?, ?)");
                preparestat.setString(1, object.getNomVar());
                preparestat.setString(2, f.getNomVar());
                preparestat.executeUpdate();

            }
            factory.close();
        } catch (SQLException e) {
            factory.close();
            return null;
        }
        return object;
    }
    
    
    
    @Override
    public FormeGroupe find(String id) {
        FormeGroupe find = null;
        try {
            PreparedStatement prepare = connect.prepareStatement(
                    "SELECT * FROM GroupeForme WHERE variableName = ?");
            prepare.setString(1, id);
            ResultSet result = prepare.executeQuery();
            if (result.next()) {
                find = new FormeGroupe(id);
                ArrayList<Forme> list = findComposition(id);
                for (Forme f : list) {
                    find.add(f);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return find;
    }
    
    
    
    @Override
    public ArrayList<FormeGroupe> findAll() {
        ArrayList<FormeGroupe> find = new ArrayList<FormeGroupe>();
        try {
            PreparedStatement prepare = connect.prepareStatement(
                    "SELECT variableName FROM GroupeForme");
            ResultSet result = prepare.executeQuery();
            while (result.next()) {
                find.add(this.find(result.getString("variableName")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<FormeGroupe>();
        }
        return find;
    }
   
    
    
    @Override
    public FormeGroupe update(FormeGroupe object) {
        ArrayList<Forme> contenu = this.findComposition(
                object.getNomVar());
        if (!contenu.isEmpty()) {
        	try {
        		PreparedStatement prepare = connect.prepareStatement(
                        "DELETE FROM Composition WHERE idGroupe = ?");
                prepare.setString(1, object.getNomVar());
                prepare.executeUpdate();
			} catch (SQLException e) {
				// TODO: handle exception
			}
        	 

            JdbcDaoFactory factory = new JdbcDaoFactory();
            for (Forme f : object.getList()) {
                if (f.getClass() == Cercle.class) {
                    Dao<Cercle> dao = factory.CreateCercleDao();
                    dao.create((Cercle) f);
                } else if (f.getClass() == Carre.class) {
                    Dao<Carre> dao = factory.CreateCarreDAO();
                    dao.create((Carre) f);
                } else if (f.getClass() == Rectangle.class) {
                    Dao<Rectangle> dao = factory.CreateRectangleDao();
                    dao.create((Rectangle) f);
                } else if (f.getClass() == Triangle.class) {
                    Dao<Triangle> dao = factory.CreateTriangleDAO();
                    dao.create((Triangle) f);
                } else {
                    this.create((FormeGroupe) f);
                }
                try {
                	PreparedStatement preparestat = connect.prepareStatement(
                            "INSERT INTO Composition"
                            + " (idGroupe, idComposant)"
                            + " VALUES(?, ?)");
                    preparestat.setString(1, object.getNomVar());
                    preparestat.setString(2, f.getNomVar());
                    preparestat.executeUpdate();
				} catch (SQLException e) {
					
				}

            }
            factory.close();
        } else {
            return null;
        }
        return object;
    }
    
    
    
    @Override
    public void delete(FormeGroupe object) {
        try {
        	PreparedStatement preparestat = connect.prepareStatement(
                    "DELETE FROM Composition WHERE idGroupe = ?");
            preparestat.setString(1, object.getNomVar());
            preparestat.executeUpdate();
            PreparedStatement preparestat1 = connect.prepareStatement(
                    "DELETE FROM Composition WHERE idComposant = ?");
            preparestat1.setString(1, object.getNomVar());
            preparestat1.executeUpdate();
            PreparedStatement prepare = connect.prepareStatement(
                    "DELETE FROM GroupeForme WHERE variableName = ?");
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
