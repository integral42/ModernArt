package main;
//TODO
public class Vector {
	//----------FIELDS----------//
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
	
	//--------------Methods----------------//
    /**
     * Creates a vector based on polar form
     */
    public static Vector createFromPolar(double mag, double theta){
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
    //TODO
    /**
     * find c in pythagorean theorem
     */
    public double norm(double[] legs){
    	return Math.sqrt(legs[0] * legs[0] + legs[1] * legs[1]);
    }

    
    public double theta() {
    	//Compute theta
    	return 0.;
    }
    //-----------------STATIC------------//
    /**
     * Distance formula between vectors
     */
    //TODO
    public double distance(double x1, double y1, double x2, double y2) {
    	return Math.sqrt(x1 - x2);
    }
}
