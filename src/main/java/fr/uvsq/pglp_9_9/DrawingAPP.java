package fr.uvsq.pglp_9_9;

import java.io.File;
import java.util.Scanner;

import fr.uvsq.pglp_9_9.Command.Command;
import fr.uvsq.pglp_9_9.Command.DrawingTUI;
import fr.uvsq.pglp_9_9.Dao.JdbcInitializer;



public class DrawingAPP {
   
	
    private Scanner saisie;
    
    private DrawingTUI dtui;
    
    
    public DrawingAPP() {
        dtui = new DrawingTUI();
        saisie = new Scanner(System.in);
    }
    
    public void run() {
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
        String cmd = saisie.nextLine();
        Command c;
        while (!cmd.equalsIgnoreCase("save")) {
            c = dtui.nextCommand(cmd);
            if (c != null) {
                c.execute();
            }
            dtui.ShowDessin();
            cmd = saisie.nextLine();
        }
    }
    
    
    @SuppressWarnings("resource")
    public static String selectNameDessin() throws Exception {
        System.out.println("Pour Modifier l'ancien Dessin entrer => 'M ou m' \n"
        		+ "Pour créer un Nouveau Dessin entrer => 'N ou n'");
        Scanner s = new Scanner(System.in);
        String name = "";
        while (!name.equalsIgnoreCase("m") && !name.equalsIgnoreCase("n")
                && !name.equalsIgnoreCase("save")) {
            name = s.nextLine();
        }
        if (name.equalsIgnoreCase("m")) {
            System.out.println("Entrez le nom du dessin enregistrer dans la BD : ");
            name = s.nextLine();
            JdbcInitializer.setNomDessin(name);
            File f = new File(name);
            if (!f.exists()) {
                System.err.println("Aucun dessin n'existe à ce nom dans la BD");
                throw new Exception();
            }
            return name;
        }
        return "dessin";
    }
    
    
    public static void main(final String[] args){
        try {
            String name = selectNameDessin();
            if (name.equals("dessin")) {
                JdbcInitializer.createDataBase();
                JdbcInitializer.init();
            } else {
            	JdbcInitializer.setNomDessin(name);
            }
            DrawingAPP app = new DrawingAPP();
            app.run();
            Scanner s = new Scanner(System.in);
            if (!name.equals("dessin")) {
                String reponse  = "";
                while (!reponse.equalsIgnoreCase("save")) {
                    s = new Scanner(System.in);
                }
                if (reponse.equalsIgnoreCase("save")) {
                    s.close();
                    return;
                }
            }
            System.out.println("Entrez un nom pour sauvegarder votre dessin :");
            String reponse  = "dessin";
            while (reponse.equals("dessin") && new File(reponse).exists()) {
                reponse = s.nextLine();
            }
            if (reponse.equalsIgnoreCase("save")) {
                s.close();
                return;
            } else {
                File f = new File(name);
                if (!f.exists()) {
                    f.renameTo(new File(reponse));
                }
            }
            s.close();
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    
    
}
