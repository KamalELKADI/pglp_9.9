package fr.uvsq.pglp_9_9.Command;

import fr.uvsq.pglp_9_9.Dessin.Carre;
import fr.uvsq.pglp_9_9.Dessin.Cercle;
import fr.uvsq.pglp_9_9.Dessin.Forme;
import fr.uvsq.pglp_9_9.Dessin.Point;

public class DrawingTUI {

	public Forme createCercle(String variableName,String[] split2) {
        final int trois = 3;
        String[] split = split2[1].split("Cercle");
        if (!split[0].equals("")
                || !(split[1].startsWith("(") && split[1].endsWith(")"))) {
            System.err.println("Commande invalide, parenthèses manquantes");
        } else {
            split[1] = split[1].substring(1, split[1].length() - 1);
            split = split[1].split(",");
            if (split.length != trois) {
                System.err.println("Commande invalide, "
                        + split.length + "/" + trois + " arguments");
            } else {
                Point centre;
                int rayon;
                try {
                    centre = new Point(split[0] + "," + split[1]);
                    rayon = Integer.parseInt(split[2]);
                    return new Cercle(variableName, centre, rayon);
                } catch (Exception e) {
                    System.err.println("Commande invalide, "
                            + "impossible de créer la forme");
                }
            }
        }
        return null;
    }
	
	
	public Forme createCarre(String variableName, String[] split2) {
        int trois = 3;
        String[] split = split2[1].split("Carre");
        if (!split[0].equals("")
                || !(split[1].startsWith("(") && split[1].endsWith(")"))) {
            System.err.println("Commande invalide, parenthèses manquantes");
        } else {
            split[1] = split[1].substring(1, split[1].length() - 1);
            split = split[1].split(",");
            if (split.length != trois) {
                System.err.println("Commande invalide, "
                        + split.length + "/" + trois + " arguments");
            } else {
                Point topLeft;
                int longueur;
                try {
                    topLeft = new Point(split[0] + "," + split[1]);
                    longueur = Integer.parseInt(split[2]);
                    return new Carre(variableName, topLeft, longueur);
                } catch (Exception e) {
                    System.err.println("Commande invalide, "
                            + "impossible de créer la forme");
                }
            }
        }
        return null;
    }
}
