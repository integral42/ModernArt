package main;

import java.awt.Color;
/**
 * Movable Colorful Rectangle
 * @author Connor Lehmacher
 */
public class MyRectangle{
	//-----------Fields-----------//
    Vector location;
    double length, width;
    double mass;
    /**
 	 * Some Vector in component form for Velocity
	 */
    Vector velocity;
    /**
     * Some Vector in component form for Acceleration
     */
    Vector acceleration;
    Color color;
    /**
     * Used for adding of object to the array-list in main
     */
    
    //----------Constructors-----------//
    /**
     * Forms Basis of all other constructors
     */
    public MyRectangle(Vector location, double length, double width){
        this.location = location;
        this.width = width;
        this.length = length;
        
        mass = width * length;
        velocity = new Vector();
        acceleration = new Vector();
    }
    
    /**
     * No Motion with color and place
     */
    public MyRectangle(Vector location, double size, Color color, Window place){
        this(location, size, size);
        
        this.color = color;
        
        place.addToRectangles(this);
    }
    
    /**
     * Fully Operational
     */
    public MyRectangle(Vector location, double size, Color color, Vector velocity, Window p_1){
        this(location, size, color, p_1);
                
        this.velocity = velocity;
    }
    
    //-----------Methods----------//
    /**
     * Acts upon distance based on velocity and velocity based on acceleration
     */
    public void move() {
        location.x += velocity.x;
        location.y += velocity.y;
        velocity.x += acceleration.x;
        velocity.y += acceleration.y;
    }
    /**
     * collision between rectangles
     */
    public void collide(MyRectangle mR) {
        if(mR != this){
            if(           		/*Bottom Left*/
            		location.x + width >= mR.location.x &&
            		location.x + width <= mR.location.x + (mR.width / 2) &&
                	location.y + length >= mR.location.y &&
               		location.y + length <= mR.location.y + (mR.length / 2)
               	||				/*Top Right*/
               		location.x <= mR.location.x + mR.width &&
               		location.x + (width / 2) >= mR.location.x + mR.width &&
        			location.y <= mR.location.y + mR.length &&
        			location.y + (length / 2) >= mR.location.y + mR.length	
            	||				/*Bottom Right*/
        		(location.x <= mR.location.x + mR.width &&
                	location.x + (width / 2) >= mR.location.x + mR.width &&
                	location.y + length >= mR.location.y &&
                   	location.y + length <= mR.location.y + (mR.length / 2))
                ||				/*Top Left*/
                (location.x + width >= mR.location.x &&
                	location.x + width <= mR.location.x + (mR.width / 2) &&
                	location.y <= mR.location.y + mR.length &&
                	location.y + (length / 2) >= mR.location.y + mR.length)
            		) {
            	velocity.x = -velocity.x;
            	velocity.y = -velocity.y;
            }
        }
    }

    /**
     * edge collision
     */
    public void edgeBounce(){    
        if(location.x < Window.CONTENT[0]){
        	velocity.x = -velocity.x;
            location.x = Window.CONTENT[0];   
        } 
        if(location.y < Window.CONTENT[1]){
        	velocity.y = -velocity.y;
        	location.y = Window.CONTENT[1];   
        }        
        if(location.x + length > Window.CONTENT[2]){
        	velocity.x = -velocity.x;
        	location.x = Window.CONTENT[2] - length;
        }
        
        if(location.y + width > Window.CONTENT[3]){
        	velocity.y = -velocity.y;
        	location.y = Window.CONTENT[3] - width;
        }
    }
    /**
     * Increases Width and Length by 1
     */
    public void grow(){
    	width++;
    	length++;
    	mass = width * length;
    	
    }
    /**
     * Decreases Width and Length by 1
     */
    public void shrink(){
    	width--;
    	length--;
    	mass = width * length;
    }
    /**
     * Physics-Gravity for any 2 bodies with mass and distance
     */
    public void gravity(MyRectangle myRect){
    	if(this != myRect){
    		//TODO
    	}
    }
}

