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
	
	/** Default; 0 */
	public Vector() {
		x = 0;
		y = 0;
	}
	
	//--------------Methods----------------//
    /** find the size of a vector */
    public double norm() {
    	return Math.sqrt(Util.sq(x) + Util.sq(y));
    }
    
    /** Find the angle of a vector */
    public double theta() {
    	return Math.atan(y / x);
    }
    
    /** Distance formula between two vectors */
    public double distanceWith(Vector v) {
    	return Math.sqrt(Util.sq(x - v.x) + Util.sq(y - v.y));
    }
    
    /** Angle between two vectors */
    public double angleWith(Vector v) {
    	final double relX = v.x - x;
    	final double relY = v.y - y;
    	if(!Util.isNearZero(relX)) {
    		final double BUTTERFLY_ANGLE = Math.acos(dotProduct(v) / (norm() * v.norm()));
    		if(relY >= 0) {
    			//Quadrant I
    			if(relX >= 0) {
    				return BUTTERFLY_ANGLE;
    			}
    			//Quadrant II
    			else {
    				return Math.PI - BUTTERFLY_ANGLE;
    			}
    		}
    		else {
    			//Quadrant III
    			if(relX <= 0) {
    				return Math.PI + BUTTERFLY_ANGLE;
    			}
    			//Quadrant IV
    			else {
    				return Math.PI * 2 - BUTTERFLY_ANGLE;
    			}
    		}
    	}
    	else {
    		//Vertical Up
    		if(relY >= 0) {
    			return Math.PI / 2;
    		}
    		//Vertical Down
    		else {
    			return Math.PI * 3 / 2;
    		}
    	}
    }
    
    /** Scalar Multiply */
    public Vector scaleBy(double a) {
    	return createFromRect(x * a, y * a);
    }
    
    /** Negative of the Vector */
    public Vector negative() {
    	return scaleBy(-1);
    }
    
    /** Dot Product */
    public double dotProduct(Vector v) {
    	return x * v.x + y* v.y;
    }
    
    /** Vector Add */
    public Vector addWith(Vector v) {
    	return createFromRect(x + v.x, y + v.y);
    }
    
    /** Vector Subtract */
    public Vector subtractWith(Vector v) {
    	return createFromRect(x - v.x, y - v.y);
    }
    
    /** true if vector is a zero Vector */
    public boolean isZero() {
    	return x == 0 && y == 0;
    }
    //-----------------STATIC------------//
    /**
     * Creates a vector based on polar form
     */
    public static Vector createFromPolar(double norm, double theta) {
    	final double x = norm * Math.cos(theta);
    	final double y = norm * Math.sin(theta);
    	return new Vector(x, y);
    }
    
    /**
     * Creates a vector based on rectangular form
     */
    public static Vector createFromRect(double x, double y) {
    	return new Vector(x, y);
    }
}
