package main;

import java.awt.Color;
/**
 * Movable Colorful Rectangle
 * @author Connor Lehmacher
 */
public class MyRectangle{
	//-----------Fields-----------//
    //"Original Size" (adjusted by arrows) != 0
    private double inputWidth, inputHeight;
    //Same as input* but can be < 0
    private double virtualWidth, virtualHeight;
    //Adjusted for screen size but still between 0 and 1
    private double width, height;
    private double mass;
    /** Some Vector in component form for Position (Displacement) */
    private Vector position;
    /** Some Vector in component form for Velocity */
    protected Vector velocity;
    /** Some Vector in component form for Acceleration */
    private Vector acceleration;
    /** Some Vector in component form for Net Force */
    private Vector netForce;
    private Color color;
    /** Used for adding of object to the array-list in main */
    
    //----------Constructors-----------//
    /**
     * Forms Basis of all other constructors
     */
    public MyRectangle(Vector position, double inputwidth, double inputHeight){
        this.position = position;
        this.inputWidth = inputwidth;
        this.inputHeight = inputHeight;
        
        mass = inputwidth * inputHeight;
        virtualWidth = inputWidth;
        virtualHeight = inputHeight;
        
        velocity = new Vector();
        acceleration = new Vector();
        netForce = new Vector();
    }
    
    /**
     * No Motion with color and place
     */
    public MyRectangle(Vector position, double size, Color color, Window location){
        this(position, size, size);
        
        this.color = color;
        
        location.addToRectangles(this);
    }
    
    /**
     * Fully Operational
     */
    public MyRectangle(Vector position, double size, Color color, Vector velocity, Window location){
        this(position, size, color, location);
                
        this.velocity = velocity;
    }
    
    //------------------------Methods----------------------//
    /** Reset Size
     * @param w the width of the real screen
     * @param h the height of the real screen */
    public void readjust(double w, double h) {
    	width = inputWidth * Window.FRAME_X / w;
    	height = inputHeight  * Window.FRAME_Y / h;
    }
    
    /**
     * Acts upon distance based on velocity and velocity based on acceleration
     */
    public void physics() {
    	position = position.addWith(velocity);
        velocity = velocity.addWith(acceleration);
        mass = width * height;
//        acceleration = netForce.multiplyWith(1 / mass);
        
        //Controls Sizes below 0
    	if(virtualWidth > 0) {
    		inputWidth = virtualWidth;
    	}
    	else{
    		inputWidth = 0; 
    	}
    	if(virtualHeight > 0) {
    		inputHeight = virtualHeight;
    	}
    	else{
    		inputHeight = 0;
    	}
    }
    
    /**
     * collision between rectangles
     */
    public void collideWith(MyRectangle mR) {
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
            	velocity.x = -velocity.x;
            	velocity.y = -velocity.y;
            }
        }
    }

    /**
     * edge collision
     */
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
    	virtualWidth += epsilon;
    	virtualHeight += epsilon;
    }
    
    /** Decreases Width and Length by some small amount */
    public void shrink() {
        virtualWidth -= epsilon;
    	virtualHeight -= epsilon;
    }
    
    /**
     * Physics-Gravity for any 2 bodies with mass and distance
     * adds to <b> netForce </b>
     */
    public void gravity(MyRectangle myRect) {
    	if(this != myRect){
    		
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
    /** Small amount for growth */
    private final static double epsilon = 0.001;
}

