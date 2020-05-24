package fr.uvsq.pglp_9_9.Command;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.uvsq.pglp_9_9.Dao.Dao;
import fr.uvsq.pglp_9_9.Dao.JdbcDaoFactory;
import fr.uvsq.pglp_9_9.Dao.JdbcInitializer;
import fr.uvsq.pglp_9_9.Dessin.Carre;
import fr.uvsq.pglp_9_9.Dessin.Cercle;
import fr.uvsq.pglp_9_9.Dessin.Forme;
import fr.uvsq.pglp_9_9.Dessin.FormeGroupe;
import fr.uvsq.pglp_9_9.Dessin.Point;
import fr.uvsq.pglp_9_9.Dessin.Rectangle;
import fr.uvsq.pglp_9_9.Dessin.Triangle;

public class DrawingTUI {

	public Forme createCercle(String variableName,String[] split2) {

        String[] split = split2[1].split("Cercle");
        if (!split[0].equals("") || !(split[1].startsWith("(") && split[1].endsWith(")"))) {
            System.err.println("Commande invalide, parenthèses manquantes");
        } else {
            split[1] = split[1].substring(1, split[1].length() - 1);
            split = split[1].split(",");
            if (split.length != 3) {
                System.err.println("Commande invalide, "
                        + split.length + "/" + 3 + " arguments");
            } else {
                Point centre;
                int rayon;
                try {
                    centre = new Point(split[0] + "," + split[1]);
                    rayon = Integer.parseInt(split[2]);
                    return new Cercle(variableName, centre, rayon);
                } catch (Exception e) {
                    System.err.println("Commande invalide, impossible de créer la forme");
                }
            }
        }
        return null;
    }
	
	
	public Forme createCarre(String variableName, String[] split2) {
        
        String[] split = split2[1].split("Carre");
        if (!split[0].equals("") || !(split[1].startsWith("(") && split[1].endsWith(")"))) {
            System.err.println("Commande invalide, parenthèses manquantes");
        } else {
            split[1] = split[1].substring(1, split[1].length() - 1);
            split = split[1].split(",");
            if (split.length != 3) {
                System.err.println("Commande invalide, " + split.length + "/" + 3 + " arguments");
            } else {
                Point topLeft;
                int longueur;
                try {
                    topLeft = new Point(split[0] + "," + split[1]);
                    longueur = Integer.parseInt(split[2]);
                    return new Carre(variableName, topLeft, longueur);
                } catch (Exception e) {
                    System.err.println("Commande invalide, impossible de créer la forme");
                }
            }
        }
        return null;
    }
	
	private Forme createRectangle(String variableName, String[] split2) {

        String[] split = split2[1].split("Rectangle");
        if (!split[0].equals("") || !(split[1].startsWith("(") && split[1].endsWith(")"))) {
            System.err.println("Commande invalide, parenthèses manquantes");
        } else {
            split[1] = split[1].substring(1, split[1].length() - 1);
            split = split[1].split(",");
            if (split.length != 4) {
                System.err.println("Commande invalide, " + split.length + "/" + 4 + " arguments");
            } else {
                Point topLeft;
                int longueur;
                int largeur;
                try {
                    
                    topLeft = new Point(split[0] + "," + split[1]);
                    longueur = Integer.parseInt(split[2]);
                    largeur = Integer.parseInt(split[3]);
                    return new Rectangle(
                            variableName, topLeft, longueur, largeur);
                } catch (Exception e) {
                    System.err.println("Commande invalide, impossible de créer la forme");
                }
            }
        }
        return null;
    }
    
    private Forme createTriangle(String variableName, String[] split2) {
        String[]  split = split2[1].split("Triangle");
        if (!split[0].equals("") || !(split[1].startsWith("(") && split[1].endsWith(")"))) {
            System.err.println("Commande invalide, parenthèses manquantes");
        } else {
            split[1] = split[1].substring(1, split[1].length() - 1);
            split = split[1].split(",");
            if (split.length != 6) {
                System.err.println("Commande invalide, " + split.length + "/" + 6 + " arguments");
            }
            Point[] point = {null, null, null};
            try {
               
                point[0] = new Point(split[0] + "," + split[1]);
                point[1] = new Point(split[2] + "," + split[3]);
                point[2] = new Point(split[4] + "," + split[5]);
                return new Triangle(variableName, point[0], point[1], point[2]);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Commande invalide, impossible de créer la forme");
            }
        }
        return null;
    }
    
    
    
    private Forme createGroupe(String variableName, String[] split2) {
        String[] split = split2[1].split("Groupe");
        if (!split[0].equals("")
                || !(split[1].startsWith("(") && split[1].endsWith(")"))) {
            System.err.println("Commande invalide, parenthèses manquantes");
        } else {
            split[1] = split[1].substring(1, split[1].length() - 1);
            split = split[1].split(",");
            return createGroupeComposants(variableName, split);
        }
        return null;
    }
    
    
    private Forme createGroupeComposants(String variableName, String[] split) {
        FormeGroupe gf = new FormeGroupe(variableName);
        for (String s : split) {
            Forme f = this.getForme(s);
            if (f != null) {
                gf.add(f);
            } else {
                return null;
            }
        }
        return gf;
    }
    
    
    private Forme getForme(String variableName) {
        JdbcDaoFactory factory = new JdbcDaoFactory();
        Dao<Cercle> daoCe = factory.CreateCercleDao();
        Dao<Carre> daoCa = factory.CreateCarreDAO();
        Dao<Rectangle> daoR = factory.CreateRectangleDao();
        Dao<Triangle> daoT = factory.CreateTriangleDAO();
        Dao<FormeGroupe> daoG = factory.CreateGroupeDao();
        Forme f = daoCe.find(variableName);
        if (f == null) {
            f = daoCa.find(variableName);
        }
        if (f == null) {
            f = daoR.find(variableName);
        }
        if (f == null) {
            f = daoT.find(variableName);
        }
        if (f == null) {
            f = daoG.find(variableName);
        }
        if (f == null) {
            System.err.println("Aucune forme n'existe à ce nom : "+ variableName);
        }
        factory.close();
        return f;
    }
  
    //Create Forme
    private Forme create(String cmd2) {
        String[] split;
        split = cmd2.split("=");
        split[0] = split[0].trim();
        String variableName = split[0];
        if (split[0].contains(" ")) {
            System.out.println("Le nom de la variable contient des espaces");
        } else {
            split[1] = split[1].replace(" ", "");
            Forme f = null;
            if (split[1].contains("Cercle")) {
                f = createCercle(variableName, split);
            } else if (split[1].contains("Carre")) {
                f = createCarre(variableName, split);
            } else if (split[1].contains("Rectangle")) {
                f = createRectangle(variableName, split);
            } else if (split[1].contains("Triangle")) {
                f = createTriangle(variableName, split);
            } else if (split[1].contains("Groupe")) {
                f = createGroupe(variableName, split);
            }
            return f;
        }
        return null;
    }
    
    
    
    private Command move(String cmd2) {

        String cmd = cmd2.replace(" ", "");
        String[] split = cmd.split("move");
        if (!split[0].equals("")|| !(split[1].startsWith("(") && split[1].endsWith(")"))) {
            System.err.println("Commande invalide, parenthèses manquantes");
        } else {
            split[1] = split[1].substring(1, split[1].length() - 1);
            split = split[1].split(",");
            if (split.length != 3) {
                System.err.println("Commande invalide, " + split.length + "/" + 3 + " arguments");
            } else {
                String variableName;
                Point deplacement;
                try {
                    variableName = split[0];
                    deplacement = new Point(split[1] + "," + split[2]);
                    Forme f = getForme(variableName);
                    if (f != null) {
                        return new MovementCommand(f, deplacement);
                    }
                } catch (Exception e) {
                    System.err.println("Commande invalide");
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    
    
    private Command remove(String cmd2) {
        String cmd = cmd2.replace(" ", "");
        String[] split = cmd.split("delete");
        if (!split[0].equals("")
                || !(split[1].startsWith("(") && split[1].endsWith(")"))) {
            System.err.println("Commande invalide, parenthèses manquantes");
        } else {
            split[1] = split[1].substring(1, split[1].length() - 1);
            split = split[1].split(",");
            ArrayList<Forme> list = new ArrayList<Forme>();
            for (String var : split) {
                Forme f = getForme(var);
                if (f != null) {
                    list.add(f);
                } else {
                    System.err.println("Commande invalide, forme inconnu");
                    return null;
                }
            }
            return new RemoveCommand(list);
        }
        return null;
    }
    
    // cette fonction sert la gestion des commande de saisie et de l'affichage
    public Command nextCommand(String cmd) {
        if (cmd.contains("=")) {
            Forme f = create(cmd);
            if (f != null) {
                return new CreationCommand(f);
            }
        } else if (cmd.contains("move")) {
            return move(cmd);
        }else if (cmd.equals("help")) {
        	System.out.println("Commandes disponibles : \n"
        			+ " |-----------------------------------------------------------------------------------------------------------------|\n"    			
                    + " |Pour les commandes de créations des formes ==>                                                                   |\n"
        			+ " |Créer un cercle :                                    variableNameCercle = Cercle((x,y), rayon)                   |\n"
                    + " |Créer un carré :                                     variableNameCarre = Carre((x,y), longueur)                  |\n"
                    + " |Créer un rectangle :                                 variableNameRectangle = Rectangle((x,y), longueur, largeur) |\n"
                    + " |Créer un triangle :                                  variableNameTriangle = Triangle((x,y), (x,y), (x,y))        |\n"
                    + " |Créer un groupe de forme(s) :                        variableNameGroupe = Groupe(variableName, ...)              |\n"
                    + " |                                                                                                                 |\n"
                    + " |Déplacer une forme ou un groupe de forme :           move(variableName, (x,y))                                   |\n"
                    + " |Supprimer une forme ou un groupe de forme :          delete(variableName, ...)                                   |\n"
            		+ " |                                                                                                                 |\n"
                    + " |Pour Avoir de l'aide tapez << help >>                                                                            |\n"
                    + " |Pour sauvegarder le dessin << save >>                                                                            |\n"                                      
                    + " |Pour sortir de l'application sans sauvegarder dans la BD tapez << exit >>                                        |\n"
        	        + " |-----------------------------------------------------------------------------------------------------------------|\n");
        }else if (cmd.contains("delete")) {
            return remove(cmd);
        }else if (cmd.contains("exit")) {
        	System.exit(0);
        }
        return null;
    }
    
    
    private boolean IsInGroupe(Forme f) {
        Connection connect = JdbcInitializer.Connection();
        try {
            PreparedStatement prepare = connect.prepareStatement(
                    "SELECT * FROM Composition WHERE idComposant = ?");
            prepare.setString(1, f.getNomVar());
            ResultSet result = prepare.executeQuery();
            boolean b = result.next();
            connect.close();
            return b;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connect.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return false;
        }
    }
    
    //afficher les formes du dessin
    public void ShowDessin() {
        JdbcDaoFactory factory = new JdbcDaoFactory();
        Dao<Cercle> daoCercle = factory.CreateCercleDao();
        Dao<Carre> daoCarre = factory.CreateCarreDAO();
        Dao<Rectangle> daoRectangle = factory.CreateRectangleDao();
        Dao<Triangle> daoTriangle = factory.CreateTriangleDAO();
        Dao<FormeGroupe> daoGroupe = factory.CreateGroupeDao();
        ArrayList<Forme> formes = new ArrayList<Forme>();
        formes.addAll(daoCercle.findAll());
        formes.addAll(daoCarre.findAll());
        formes.addAll(daoRectangle.findAll());
        formes.addAll(daoTriangle.findAll());
        formes.addAll(daoGroupe.findAll());
        for (Forme f : formes) {
            if (!IsInGroupe(f)) {
                f.affiche();
            }
        }
        factory.close();
    }
}
