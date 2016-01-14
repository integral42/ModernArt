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
    
    public static boolean isNearZero(double x) {
    	final double EPSILON = Double.MIN_NORMAL * 1000000;
    	return x > -EPSILON && x < EPSILON;
    }
}
