package main;

public class Vector {
	/**
	 * Basic Information
	 */
	double x;
	double y;
	
	//------------Constructors------------//
	private Vector(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	//------------Methods----------------//
    /**
     * Convert some magnitude and angle(Rad) into components
     */
    public static Vector createFromPolar(double mag, double theta){
    	final double x = mag * Math.cos(theta);
    	final double y = mag * Math.sin(theta);
    	return new Vector(x, y);
    }
    
    /**
     * 
     */
    public static Vector createFromRect(double x, double y) {
    	return new Vector(x, y);
    }
    
    /**
     * find c in pythagorean theorem
     */
    public double norm(double[] legs){
    	return Math.sqrt(legs[0] * legs[0] + legs[1] * legs[1]);
    }
    
    /**
     * Distance formula between two rectangular points
     */
    public double distance(double x1, double y1, double x2, double y2) {
    	return Math.sqrt(x1 - x2);
    }
    
    public double theta() {
    	//Compute theta
    	return 0.;
    }
}
