package main;

import java.awt.Color;
/**
 * Movable Colorful Rectangle
 * @author Connor Lehmacher
 */
public class MyRectangle{
    double x, y;
    double length, width;
/**
* x and y directions movement after 1 time unit
*/
double[] directions = new double[2];
    Color color;
    /**
     * Used for adding of object to the array-list in main
     */
    Main location;
    /**
     * Used for convertToComponets
     */
double[] xandy = new double[2];
    
    /**
     * Forms Basis of all other constructors
     * @param x
     * @param y
     * @param length
     * @param width
     */
    public MyRectangle(double x, double y, double length, double width){
        this.x = x;
        this.y = y;
        this.width = width;
        this.length = length;
    }
/**
     * "Default"
     * @param p_1
     */
    public MyRectangle(Main p_1){
        this(50, 50, 10, 10);

        directions[0] = 0;
        directions[1] = 0;
        
        color = Color.BLACK;
        
        location = p_1;
        p_1.addToRectangles(this);
    }
    
    /**
     * No Motion
     * @param x
     * @param y
     * @param size
     * @param color
     * @param p_1
     */
    public MyRectangle(double x, double y, double size, Color color, Main p_1){
        this(x, y, size, size);
        
        directions[0] = 0;
        directions[1] = 0;
        
        this.color = color;
        
        location = p_1;
        p_1.addToRectangles(this);
    }
    /**
     * Fully Operational
     * @param x
     * @param y
     * @param size
     * @param color
     * @param dirX
     * @param dirY
     * @param p_1
     */
    public MyRectangle(double x, double y, double size, Color color, double dirX, double dirY, Main p_1){
        this(x, y, size, color, p_1);
                
        directions[0] = dirX;
        directions[1] = dirY;
    }
    /**
     * Moves in x and y they become some amount more or less based on directions
     */
    public void move(){
        x += directions[0];
        y += directions[1];
    }
    /**
     * Convert some magnitude and angle(Rad) into components
     * @param mag
     * @param theta
     * @return 
     */
    public double[] convertToComponents(double mag, double theta){
    //0 = x, 1 = y
    xandy[0] = mag * Math.cos(theta);
    xandy[1] = mag * Math.sin(theta);
    return xandy;
    }
    /**
     * collision between rectangles
     * @param myRect
     */
    public void collide(MyRectangle myRect){
        if(myRect != this){
        //Bottom Left
            if(x + width >= myRect.x && x + width <= myRect.x + (myRect.width / 2)
               && y + length >= myRect.y && y + length <= myRect.y + (myRect.length / 2)){
               massCollision(myRect);
               System.out.println("1");
            }
            //Top Right
            if(x <= myRect.x + myRect.width && x + (width / 2) >= myRect.x + myRect.width
               && y <= myRect.y + myRect.length && y + (length / 2) >= myRect.y + myRect.length){
                massCollision(myRect);
                System.out.println("2");
            }
            //Bottom Right
            if(x <= myRect.x + myRect.width && x + (width / 2) >= myRect.x + myRect.width
               && y + length >= myRect.y && y + length <= myRect.y + (myRect.length / 2)){
                massCollision(myRect);
                System.out.println("3");
            }
            //Top Left
            if(x + width >= myRect.x && x + width <= myRect.x + (myRect.width / 2)
               && y <= myRect.y + myRect.length && y + (length / 2) >= myRect.y + myRect.length){
                massCollision(myRect);
                System.out.println("4");
            }
        }
    }
    /**
     * edge collision
     */
    public void bounce(){    
        if(x<location.CONTENT[0]){
            directions[0] = -directions[0];
            x = location.CONTENT[0];   
        } 
        if(y<location.CONTENT[1]){
            directions[1] = -directions[1];
            y = location.CONTENT[1];   
        }        
        if(x + length>location.CONTENT[2]){
            directions[0] = -directions[0];
            x = location.CONTENT[2] - length;
        }
        
        if(y + width>location.CONTENT[3]){
            directions[1] = -directions[1];
            y = location.CONTENT[3] - width;
        }
    }
    /**
     * Physics Approved Collision
     */
    private void massCollision(MyRectangle myRect){
    //directions[0] = -((length * width * directions[0] + myRect.length * myRect.width * myRect.directions[0])/(length * width + myRect.length * myRect.width));
    //directions[1] = -((length * width * directions[1] + myRect.length * myRect.width * myRect.directions[1])/(length * width + myRect.length * myRect.width));
    //directions[0] = -(directions[0] + myRect.directions[0])/2;
    //directions[1] = -(directions[1] + myRect.directions[1])/2;
    directions[0] = -directions[0];
    directions[1] = -directions[1];
    }
}

