package main;

import java.awt.Color;
/**
 * Movable Colorful Rectangle
 * @author Connor Lehmacher
 */
public class MyRectangle{
    double x, y;
    double length, width;
    double mass;
    /**
 	 * Some Vector in component form for Velocity
	 */
    double[] velocity = new double[2];
    /**
     * Some Vector in component form for Acceleration
     */
    double[] acceleration = new double[2];
    Color color;
    /**
     * Used for adding of object to the array-list in main
     */
    Window location;
    
    /**
     * Forms Basis of all other constructors
     */
    public MyRectangle(double x, double y, double length, double width){
        this.x = x;
        this.y = y;
        this.width = width;
        this.length = length;
        mass = width*length;
        velocity[0] = 0;
        velocity[1] = 0;
        acceleration[0] = 0;
        acceleration[1] = 0;
    }
    /**
     * No Motion
     */
    public MyRectangle(double x, double y, double size, Color color, Window p_1){
        this(x, y, size, size);
        
        this.color = color;
        
        location = p_1;
        p_1.addToRectangles(this);
    }
    /**
     * Fully Operational
     */
    public MyRectangle(double x, double y, double size, Color color, double dirX, double dirY, Window p_1){
        this(x, y, size, color, p_1);
                
        velocity[0] = dirX;
        velocity[1] = dirY;
    }
    
    /**
     * Acts upon distance based on velocity and velocity based on acceleration
     */
    public void move(){
        x += velocity[0];
        y += velocity[1];
        velocity[0] += acceleration[0];
        velocity[1] += acceleration[1];
    }
    /**
     * collision between rectangles
     */
    public void collide(MyRectangle myRect){
        if(myRect != this){
        	//Bottom Left
            if(x + width >= myRect.x && x + width <= myRect.x + (myRect.width / 2)
               && y + length >= myRect.y && y + length <= myRect.y + (myRect.length / 2)){
            }
            //Top Right
            if(x <= myRect.x + myRect.width && x + (width / 2) >= myRect.x + myRect.width
               && y <= myRect.y + myRect.length && y + (length / 2) >= myRect.y + myRect.length){
                massCollision(myRect);
            }
            //Bottom Right
            if(x <= myRect.x + myRect.width && x + (width / 2) >= myRect.x + myRect.width
               && y + length >= myRect.y && y + length <= myRect.y + (myRect.length / 2)){
                massCollision(myRect);
            }
            //Top Left
            if(x + width >= myRect.x && x + width <= myRect.x + (myRect.width / 2)
               && y <= myRect.y + myRect.length && y + (length / 2) >= myRect.y + myRect.length){
                massCollision(myRect);
            }
        }
    }
    /**
     * Physics Approved Collision
     * not yet, sorry
     */
    private void massCollision(MyRectangle myRect){
    	velocity[0] = -velocity[0];
    	velocity[1] = -velocity[1];
    }
    /**
     * edge collision
     */
    public void edgeBounce(){    
        if(x<location.CONTENT[0]){
            velocity[0] = -velocity[0];
            x = location.CONTENT[0];   
        } 
        if(y<location.CONTENT[1]){
            velocity[1] = -velocity[1];
            y = location.CONTENT[1];   
        }        
        if(x + length>location.CONTENT[2]){
            velocity[0] = -velocity[0];
            x = location.CONTENT[2] - length;
        }
        
        if(y + width>location.CONTENT[3]){
            velocity[1] = -velocity[1];
            y = location.CONTENT[3] - width;
        }
    }
    /**
     * Increases Width and Length by 1
     */
    public void grow(){
    	width++;
    	length++;
    }
    /**
     * Decreases Width and Length by 1
     */
    public void shrink(){
    	width--;
    	length--;
    }
    /**
     * Physics-Gravity for any 2 bodies with mass and distance
     */
    public void gravity(MyRectangle myRect){
    	if(this != myRect){
    		
    	}
    }
    
    //---------------------Vector/Math Utility--------------------
    /**
     * Convert some magnitude and angle(Rad) into components
     */
    public double[] convertToComponents(double mag, double theta){
    //0 = x, 1 = y
    double[] xandy = new double[2];
    xandy[0] = mag * Math.cos(theta);
    xandy[1] = mag * Math.sin(theta);
    return xandy;
    }
    /**
     * find c in pythagorean theorem
     */
    public double pythagorate(double[] legs){
    	return Math.sqrt(legs[0] * legs[0] + legs[1] * legs[1]);
    }
    /**
     * Distance formula between two rectangular points
     */
    public double distance(double x1, double y1, double x2, double y2) {
    	return Math.sqrt(x1 - x2);
    }
}

