package fr.uvsq.pglp_9_9.Forme;

import static org.junit.Assert.*;

import org.junit.Test;


import fr.uvsq.pglp_9_9.Dessin.Cercle;
import fr.uvsq.pglp_9_9.Dessin.Point;

public class CercleTest {

	@Test
    public void GetterTest() {
        Cercle cercle = new Cercle("cercle1", new Point(0,0), 30);
        assertEquals(cercle.getNomVar(), "cercle1");
        assertTrue(cercle.getCentre().toString().equals("(0,0)"));
        assertTrue(cercle.getRayon() == 30);
    }
	
    @Test
    public void ShowTest(){
        Cercle cercle = new Cercle("cercle1", new Point(0,0), 30);
        cercle.affiche();
    }
    
    @Test
    public void MoveTest(){
        Cercle cercle = new Cercle("cer", new Point(0,0), 30);
        cercle.deplace(10, 20);
        cercle.affiche();
        assertTrue(cercle.getCentre().toString().equals("(10,20)"));
    }
    

}
