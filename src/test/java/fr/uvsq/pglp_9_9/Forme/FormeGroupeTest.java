package fr.uvsq.pglp_9_9.Forme;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.uvsq.pglp_9_9.Dessin.Carre;
import fr.uvsq.pglp_9_9.Dessin.Cercle;
import fr.uvsq.pglp_9_9.Dessin.FormeGroupe;
import fr.uvsq.pglp_9_9.Dessin.Point;

public class FormeGroupeTest {

	
	
    @Test
    public void AddTest(){
        FormeGroupe gr = new FormeGroupe("groupe1");
        Cercle c1 = new Cercle("cercle1", new Point(0,0),500);
        Carre ca1 = new Carre("ca1", new Point(10,20), 40);
        gr.add(c1);
        gr.add(ca1);
        gr.add(gr);
        gr.affiche();
        assertTrue(gr.getList().get(0) == c1 && gr.getList().get(1) == ca1);
    }
        
    
    @Test
    public void ShowTest() {
    	FormeGroupe g = new FormeGroupe("groupe1");
        Cercle c = new Cercle("c1", new Point(1,1),10);
        g.add(c);
        g.affiche();
    }
    
    @Test
    public void MoveTest() {
    	FormeGroupe g = new FormeGroupe("groupe1");
        Cercle c = new Cercle("c", new Point(1,2),10);
        g.add(c);
        g.deplace(10,10);
        g.affiche();
        assertTrue(c.getCentre().toString().equals("(11,12)"));
    }
    
    @Test
    public void RemoveTest() {
        FormeGroupe g = new FormeGroupe("gr1");
        Cercle c = new Cercle("c", new Point(1,1),20);
        Carre ca1 = new Carre("ca1", new Point(10,20), 40);
        g.add(c);
        g.add(ca1);
        g.remove(c);
        g.remove(ca1);
        assertTrue(g.getList().isEmpty());
    }

}
