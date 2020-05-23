package fr.uvsq.pglp_9_9.Forme;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.uvsq.pglp_9_9.Dessin.Point;
import fr.uvsq.pglp_9_9.Dessin.Rectangle;

public class RectangleTest {

	   @Test
	    public void GetterTest(){
	        Rectangle r = new Rectangle("rectangle1", new Point(1,1), 15, 20);
	        
	        assertEquals(r.getNomVar(), "rectangle1");
	        assertTrue(r.getTopLeft().toString().equals("(1,1)"));
	        assertTrue(r.getLongueur() == 15);
	        assertTrue(r.getLargeur() == 20);
	    }
	   
	   
	    @Test
	    public void ShowTest(){
	        Rectangle r = new Rectangle("rectangle1", new Point(1,1), 15, 20);
	        r.affiche();
	    }
	    
	    @Test
	    public void MoveTest(){
	        Rectangle r = new Rectangle("r1", new Point(10,20), 10, 5);
	        r.affiche();
	        r.deplace(15, 10);
	        r.affiche();
	        assertTrue(r.getTopLeft().toString().equals("(25,30)"));
	    }
	    

}
