package main;

public class Vector {
	//----------Fields----------//
	// Ze Basic Information   
	public double x;
	public double y;
	
	//------------Constructors------------//
	private Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Default; 0s
	 */
	public Vector() {
		x = 0;
		y = 0;
	}
	
	//--------------Methods----------------//
    /**
     * find the size of a vector
     */
    public double norm() {
    	return Math.sqrt(Util.sq(x) + Util.sq(y));
    }
    
    /**
     * Find the angle of a vector
     */
    public double theta() {
    	return Math.atan2(y, x);
    }
    
    /**
     * Distance formula between two vectors
     */
    public double distanceWith(Vector v) {
    	return Math.sqrt(Util.sq(x - v.x) + Util.sq(y - v.y));
    }
    
    /** Angle between two vectors */
    public double angleWith(Vector v) {
    	return Math.acos((x * v.x + y * v.y)/(norm() * v.norm()));
    }
    //-----------------STATIC------------//
    /**
     * Creates a vector based on polar form
     */
    public static Vector createFromPolar(double mag, double theta) {
    	final double x = mag * Math.cos(theta);
    	final double y = mag * Math.sin(theta);
    	return new Vector(x, y);
    }
    
    /**
     * Creates a vector based on rectangular form
     */
    public static Vector createFromRect(double x, double y) {
    	return new Vector(x, y);
    }
}
