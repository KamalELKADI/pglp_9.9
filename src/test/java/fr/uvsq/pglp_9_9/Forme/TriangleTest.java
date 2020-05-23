package fr.uvsq.pglp_9_9.Forme;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.uvsq.pglp_9_9.Dessin.Point;
import fr.uvsq.pglp_9_9.Dessin.Triangle;

public class TriangleTest {

	 @Test
	    public void testConstructorAndGetters() {
	        Triangle t = new Triangle("triangle1", new Point(1,1), new Point(2,1), new Point(1,0));
	        
	        assertEquals(t.getNomVar(), "triangle1");
	        assertTrue(t.getPosition(0).toString().equals("(1,1)"));
	        assertTrue(t.getPosition(1).toString().equals("(2,1)"));
	        assertTrue(t.getPosition(2).toString().equals("(1,0)"));
	    }
	 
	    @Test
	    public void testAffiche() {
	        Triangle t = new Triangle("t1", new Point(1,1), new Point(2,1), new Point(1,0));
	        t.affiche();
	    }
	    
	    @Test
	    public void testDeplace() {
	        Triangle t = new Triangle("t2", new Point(1,1), new Point(2,1), new Point(1,0));
	        t.deplace(10, 20);
	        t.affiche();
	        assertTrue(t.getPosition(0).toString().equals("(11,21)"));
	        assertTrue(t.getPosition(1).toString().equals("(12,21)"));
	        assertTrue(t.getPosition(2).toString().equals("(11,20)"));
	    }

}
