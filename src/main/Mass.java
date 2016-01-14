package main;

import java.awt.Color;
/**
 * Movable Colorful Rectangle
 * @author Connor Lehmacher
 */
public class Mass{
	//-----------Fields-----------//
    //"Original Size" (adjusted by arrows) != 0
    private double inputWidth, inputHeight;
    //Same as input but can be < 0
    private double virtualWidth, virtualHeight;
    //Adjusted for screen size but still between 0 and 1
    private double width, height;
    private double mass;
    private double density;
    /** Some Vector in component form for Position (Displacement) */
    private Vector position;
    /** Some Vector in component form for Velocity */
    private Vector velocity;
    /** Some Vector in component form for Acceleration */
    private Vector acceleration;
    /** Some Vector in component form for Net Force */
    private Vector netForce;
    private Color color;
    
    //----------Constructors-----------//
    /** Forms Basis of all other constructors */
    public Mass(Vector position, double inputWidth, double inputHeight){
        this.position = position;
        this.inputWidth = inputWidth;
        this.inputHeight = inputHeight;
        
        density = 1;
        mass = inputWidth * inputHeight * density;
        virtualWidth = inputWidth;
        virtualHeight = inputHeight;
        
        velocity = new Vector();
        acceleration = new Vector();
        netForce = new Vector();
    }
    
    /** Color and place */
    public Mass(Vector position, double size, Color color, Window location){
        this(position, size, size);
        
        this.color = color;
        location.addToMasses(this);
    }
    
    /** Velocity, color and place */
    public Mass(Vector position, double size, Color color, Vector velocity, Window location){
        this(position, size, color, location);
        if(velocity.isZero()) {
        	this.velocity = Vector.createFromPolar(Window.INITIAL_SPEED, Math.random() * Math.PI * 2);
        }
        else {
        	this.velocity = velocity;
        }
    }
    
    /** Density, color and place */
    public Mass(Vector position, double size, Color color, double density, Window location){
        this(position, size, color, location);

        this.density = density;
    }
    
    //------------------------Methods----------------------//
    /** Reset Size
     * @param w the width of the real screen
     * @param h the height of the real screen */
    public void readjust(double w, double h) {
    	width = inputWidth * Window.FRAME_X / w;
    	height = inputHeight  * Window.FRAME_Y / h;
    }
    
    /** OK physics for now */
    public void physics() {
        mass = width * height * density;
        //F = ma implementation
        if(mass > Util.sq(MIN_LENGTH)) {
        	acceleration = netForce.scaleBy(1 / mass);
        }
        else { acceleration = new Vector(); }
        velocity = velocity.addWith(acceleration);
    	position = position.addWith(velocity);

        //Controls Sizes below 0
    	if(virtualWidth > MIN_LENGTH) {
    		inputWidth = virtualWidth;
    	}
    	else {
    		inputWidth = MIN_LENGTH; 
    	}
    	if(virtualHeight > MIN_LENGTH) {
    		inputHeight = virtualHeight;
    	}
    	else {
    		inputHeight = MIN_LENGTH;
    	}
    }
    
    /** collision between rectangles */
    public void collideWith(Mass mR) {
        if(mR != this) {
            if(           		/*Bottom Left*/
            	(position.x + width >= mR.position.x &&
            		position.x + width <= mR.position.x + (mR.width / 2) &&
                	position.y + height >= mR.position.y &&
               		position.y + height <= mR.position.y + (mR.height / 2))
               	||				/*Top Right*/
               	(position.x <= mR.position.x + mR.width &&
               		position.x + (width / 2) >= mR.position.x + mR.width &&
        			position.y <= mR.position.y + mR.height &&
        			position.y + (height / 2) >= mR.position.y + mR.height)	
            	||				/*Bottom Right*/
        		(position.x <= mR.position.x + mR.width &&
                	position.x + (width / 2) >= mR.position.x + mR.width &&
                	position.y + height >= mR.position.y &&
                   	position.y + height <= mR.position.y + (mR.height / 2))
                ||				/*Top Left*/
                (position.x + width >= mR.position.x &&
                	position.x + width <= mR.position.x + (mR.width / 2) &&
                	position.y <= mR.position.y + mR.height &&
                	position.y + (height / 2) >= mR.position.y + mR.height)
            																) {
            	velocity.x = -mR.velocity.x;
            	velocity.y = -mR.velocity.y;
            }
        }
    }

    /** edge collision */
    public void edgeBounce() {
        if(position.x < 0) {
        	velocity.x = -velocity.x;
            position.x = 0;   
        } 
        if(position.y < 0) {
        	velocity.y = -velocity.y;
        	position.y = 0;   
        }        
        if(position.x + width > 1) {
        	velocity.x = -velocity.x;
        	position.x = 1 - width;
        }
        
        if(position.y + height > 1) {
        	velocity.y = -velocity.y;
        	position.y = 1 - height;
        }
    }
    
    /** Increases Width and Length by some small amount */
    public void grow() {
    	virtualWidth += Window.EPSILON;
    	virtualHeight += Window.EPSILON;
    }
    
    /** Decreases Width and Length by some small amount */
    public void shrink() {
        virtualWidth -= Window.EPSILON;
    	virtualHeight -= Window.EPSILON;
    }
    
    /** Resets location if it glitches */
    public void resetPosition() {
    	position = Vector.createFromRect((Math.random()/ 5) + 0.4, (Math.random()/ 5) + 0.4);
    }
    
    /**
     * Physics-Gravity for any 2 bodies with mass and distance
     * adds to netForce
     */
    public void gravity(Mass mR) {
    	if(this != mR) {
    		final double weight = Window.G * mass * mR.mass / Util.sq(position.distanceWith(mR.position));
    		final double theta = position.angleWith(mR.position);
    		netForce = netForce.addWith(Vector.createFromPolar(weight, theta));
    	}
    }
    
    //---------Getter Methods----------//
    /** Color Passer */
    public Color getColor(){
    	return color;
    }
    
    /** Position Passer */
    public Vector getPosition(){
    	return position;
    }
    
    /** Width Passer */
    public double getWidth(){
    	return width;
    }
    	
    /** Height Passer */
    public double getHeight(){
    	return height;
    }
    
    //-------------Static-------------//
    /** Very Small amount to retain good physics */
    private final static double MIN_LENGTH = 0.000000000000000001;
}

