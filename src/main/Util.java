package main;

import java.awt.Color;
import java.util.Random;

/**
 * All Static Utility "Functions" 
 * @author Connor Lehmacher
 */
public class Util {
	/** Finds the square (a * a) of some double */
	public static double sq(double a) {
		return a * a;
	}
	
    /** Generates a new random color using random source r */
    public static Color randomColor(Random r) {
    	return new Color(r.nextFloat(), r.nextFloat(), r.nextFloat());
    }
    
    /** Detects if some number is very close to 0 and runs some code */
    public static void ifNearZeroThen(double x, Runnable r) {
    	final double EPSILON = Double.MIN_NORMAL * 1000000;
    	if(x > -EPSILON && x < EPSILON) {
    		r.run();
    	}
    }
    
    /** Detects if some number is very close to 0
     * runs some code for the yes case and some other code for the no case*/
    public static void ifNearZeroThen(double x, Runnable r1, Runnable r2) {
    	final double EPSILON = Double.MIN_NORMAL * 1000000;
    	if(x > -EPSILON && x < EPSILON) {
    		r1.run();
    	}
    	else {
    		r2.run();
    	}
    }
}
