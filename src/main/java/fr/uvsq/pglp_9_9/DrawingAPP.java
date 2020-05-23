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
                + " |Pour sortir de l'application tapez << exit >>                                                                    |\n"
    	        + " |-----------------------------------------------------------------------------------------------------------------|\n");
        String cmd = saisie.nextLine();
        Command c;
        while (!cmd.equalsIgnoreCase("exit")) {
            c = dtui.nextCommand(cmd);
            if (c != null) {
                c.execute();
            }
            dtui.afficheDessin();
            cmd = saisie.nextLine();
        }
    }
    
    
    @SuppressWarnings("resource")
    public static String selectNameDessin() throws Exception {
        System.out.println("Entrer 'M' pour modifier l'ancien dessin ou bien sur 'N' pour un nouveau dessin => M/N ");
        Scanner s = new Scanner(System.in);
        String name = "";
        while (!name.equalsIgnoreCase("m") && !name.equalsIgnoreCase("n")
                && !name.equalsIgnoreCase("exit")) {
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
        return "test";
    }
    
    
    public static void enregistre(final String name) {
        Scanner s = new Scanner(System.in);
        if (!name.equals("test")) {
            System.out.println("1. Enregistrer vers \"" + name + "\"");
            System.out.println("2. Enregistrer sous ...");
            String reponse  = "";
            while (!reponse.equals("1") && !reponse.equals("2")
                    && !reponse.equalsIgnoreCase("exit")) {
                s = new Scanner(System.in);
            }
            if (reponse.equals("1") || reponse.equalsIgnoreCase("exit")) {
                s.close();
                return;
            }
        }
        System.out.println("entrez un nom pour sauvegarder votre dessin :");
        String reponse  = "test";
        while (reponse.equals("test") && new File(reponse).exists()) {
            reponse = s.nextLine();
        }
        if (reponse.equalsIgnoreCase("exit")) {
            s.close();
            return;
        } else {
            File f = new File(name);
            if (!f.exists()) {
                f.renameTo(new File(reponse));
            }
        }
        s.close();
    }
    
    
    public static void main(final String[] args) throws Exception {
        try {
            String name = selectNameDessin();
            if (name.equals("test")) {
                JdbcInitializer.createDataBase();
                JdbcInitializer.init();
            } else {
            	JdbcInitializer.setNomDessin(name);
            }
            DrawingAPP app = new DrawingAPP();
            app.run();
            enregistre(name);
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    
    
}
