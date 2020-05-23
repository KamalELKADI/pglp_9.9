package fr.uvsq.pglp_9_9.Forme;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.uvsq.pglp_9_9.Dessin.Carre;
import fr.uvsq.pglp_9_9.Dessin.Point;

public class CarreTest {

	@Test
    public void GetterTest() {
        Carre r = new Carre("carre1", new Point(0,0), 50);
        assertEquals(r.getNomVar(), "carre1");
        assertTrue(r.getTopLeft().toString().equals("(0,0)"));
        assertTrue(r.getLongueur() == 10);
    }
	
    @Test
    public void ShowTest() {
        Carre r = new Carre("carre1", new Point(0,0), 50);
        r.affiche();
    }
    
    @Test
    public void MoveTest() {
        Carre r = new Carre("carre1", new Point(0,0), 50);
        r.deplace(10, 20);
        assertTrue(r.getTopLeft().toString().equals("(10,20)"));
    }

}
