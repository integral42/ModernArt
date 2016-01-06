package main;

import java.awt.Color;
/**
 * Movable Colorful Rectangle
 * @author Connor Lehmacher
 */
public class MyRectangle{
	//-----------Fields-----------//
    Vector position;
    double width, height;
    double mass;
    /**  Some Vector in component form for Velocity */
    Vector velocity;
    /**  Some Vector in component form for Acceleration */
    Vector acceleration;
    /**  Some Vector in component form for Acceleration */
    Vector netForce;
    Color color;
    /** Used for adding of object to the array-list in main */
    
    //----------Constructors-----------//
    /**
     * Forms Basis of all other constructors
     */
    public MyRectangle(Vector position, double width, double height){
        this.position = position;
        this.width = width;
        this.height = height;
        
        mass = width * height;
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
    /**
     * Acts upon distance based on velocity and velocity based on acceleration
     */
    public void physics() {
        position.x += velocity.x;
        position.y += velocity.y;
        velocity.x += acceleration.x;
        velocity.y += acceleration.y;
        //TODO implement scalar diving
        //acceleration = netForce / mass;
    }
    /**
     * collision between rectangles
     */
    public void collideWith(MyRectangle mR) {
        if(mR != this){
            if(           		/*Bottom Left*/
            		position.x + width >= mR.position.x &&
            		position.x + width <= mR.position.x + (mR.width / 2) &&
                	position.y + width >= mR.position.y &&
               		position.y + width <= mR.position.y + (mR.width / 2)
               	||				/*Top Right*/
               		position.x <= mR.position.x + mR.width &&
               		position.x + (width / 2) >= mR.position.x + mR.width &&
        			position.y <= mR.position.y + mR.width &&
        			position.y + (width / 2) >= mR.position.y + mR.width	
            	||				/*Bottom Right*/
        		(position.x <= mR.position.x + mR.width &&
                	position.x + (width / 2) >= mR.position.x + mR.width &&
                	position.y + width >= mR.position.y &&
                   	position.y + width <= mR.position.y + (mR.width / 2))
                ||				/*Top Left*/
                (position.x + width >= mR.position.x &&
                	position.x + width <= mR.position.x + (mR.width / 2) &&
                	position.y <= mR.position.y + mR.width &&
                	position.y + (width / 2) >= mR.position.y + mR.width)
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
        
        if(position.y + width > 1) {
        	velocity.y = -velocity.y;
        	position.y = 1 - width;
        }
    }
    
    /**
     * Increases Width and Length by some small amount
     */
    public void grow() {
    	width += epsilon;
    	height += epsilon;
    	mass = width * height;
    	
    }
    
    /**
     * Decreases Width and Length by some small amount
     */
    public void shrink() {
    	width -= epsilon;
    	height -= epsilon;
    	mass = width * height;
    }
    
    /**
     * Physics-Gravity for any 2 bodies with mass and distance
     * adds to <b> netForce </b>
     */
    public void gravity(MyRectangle myRect) {
    	if(this != myRect){
    		
    	}
    }
    
    //-------------Static-------------//
    /** Small amount for growth */
    private final static double epsilon = 0.00015;
}

