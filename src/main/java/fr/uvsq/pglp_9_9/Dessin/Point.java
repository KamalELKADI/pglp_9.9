package fr.uvsq.pglp_9_9.Dessin;

import java.io.CharConversionException;

public class Point {

	
    private int x;    
    private int y;
   
    
    public Point() {
        x = 0;
        y = 0;
    }
    
    
    public Point(final int xValue, final int yValue) {
        x = xValue;
        y = yValue;
    }
    
        
    public int getX() {
        return x;
    }
   
    
    public int getY() {
        return y;
    }
    
    public void deplace(final int xValue, final int yValue) {
        x += xValue;
        y += yValue;
    }
    
    public String toString() {
        return "(" + x + "," + y + ")";
    }
    
    
    public Point(final String position) throws CharConversionException {
        position.replace(" ", "");
        if (position.charAt(0) != '('
        || position.charAt(position.length() - 1) != ')') {
            System.err.println(position);
            throw new CharConversionException();
        }
        String position2 = position.substring(1, position.length() - 1);
        String[] positionSplit = position2.split(",");
        if (positionSplit.length != 2) {
            System.err.println(position);
            throw new CharConversionException();
        }
        try {
            x = Integer.parseInt(positionSplit[0]);
            y = Integer.parseInt(positionSplit[1]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    
}
