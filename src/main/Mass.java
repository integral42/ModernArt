package main;


import java.awt.Color;
import java.awt.Graphics;
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
    //Adjusted for screen size (full size)
    protected int realX, realY;
    protected int realWidth, realHeight;
    private double mass;
    private double density;
    /** Some Vector in component form for Position (Displacement) */
    private Vector position;
    /** Some Vector in component form for Velocity */
    private Vector velocity;
    /** Some Vector in component form for Acceleration */
    private Vector acceleration;
    /** Some Vector in component form for Net Force */
    Vector netForce;
    private Color color;
    Window location;
    
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
        location = null;
        
        velocity = new Vector();
        acceleration = new Vector();
        netForce = new Vector();
    }
    
    /** Color and place */
    public Mass(Vector position, double size, Color color, Window location){
        this(position, size, size);
        
        this.color = color;
        location.addToMasses(this);
        this.location = location;
    }
    
    /** Velocity, color and place */
    public Mass(Vector position, double size, Color color, Vector velocity, Window location){
        this(position, size, color, location);
        if(velocity.isZero()) {
        	this.velocity = Vector.createFromPolar(0 /* 250 * Window.LAG */, Math.random() * Math.PI * 2);
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
    
    /** Only Refers to the object it self and how it moves
     * to be run at the end of a cycle */
    public void physics() {
        //F = ma implementation
        if(mass > Util.sq(MIN_LENGTH)) {
        	acceleration = netForce.scaleBy(1 / mass);
        }
        else { acceleration = new Vector(); }
        velocity = velocity.addWith(acceleration);
    	position = position.addWith(velocity);

        mass = width * height * density;
    	netForce = new Vector();
    }
    
    /** collision between rectangles */
    public void collideWith(Mass m) {
        if(m != this) {
            if(           		/*Bottom Left*/
            	(position.x + width >= m.position.x &&
            		position.x + width <= m.position.x + (m.width / 2) &&
                	position.y + height >= m.position.y &&
               		position.y + height <= m.position.y + (m.height / 2))
               	||				/*Top Right*/
               	(position.x <= m.position.x + m.width &&
               		position.x + (width / 2) >= m.position.x + m.width &&
        			position.y <= m.position.y + m.height &&
        			position.y + (height / 2) >= m.position.y + m.height)	
            	||				/*Bottom Right*/
        		(position.x <= m.position.x + m.width &&
                	position.x + (width / 2) >= m.position.x + m.width &&
                	position.y + height >= m.position.y &&
                   	position.y + height <= m.position.y + (m.height / 2))
                ||				/*Top Left*/
                (position.x + width >= m.position.x &&
                	position.x + width <= m.position.x + (m.width / 2) &&
                	position.y <= m.position.y + m.height &&
                	position.y + (height / 2) >= m.position.y + m.height)
            																) {
            	final Vector positionDifference = position.subtractWith(m.position);
            	final Vector velocityDifference = velocity.subtractWith(m.velocity);
            	final double massConstant = 2 * m.mass / (mass + m.mass);
            	final double speedConstant = velocityDifference.dotProduct(positionDifference) / Util.sq(positionDifference.norm());
            	velocity = velocity.subtractWith(positionDifference.scaleBy(massConstant * speedConstant));
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
    	virtualWidth += Window.LAG;
    	virtualHeight += Window.LAG;
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
    
    /** Decreases Width and Length by some small amount */
    public void shrink() {
        virtualWidth -= Window.LAG;
    	virtualHeight -= Window.LAG;
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
    
    /** Resets location if it glitches
     * also resets velocity */
    public void resetPosition() {
    	position = Vector.createFromRect((Math.random()/ 2) + 0.25, (Math.random()/ 2) + 0.25);
    	freeze();
    }
    
    /** Set Velocity and netForce to 0 */
    public void freeze() {
    	velocity = new Vector();
    	netForce = new Vector();
    }
    
    /**
     * Physics-Gravity for any 2 bodies with mass and distance
     * adds to netForce
     */
    public void gravity(Mass m) {
    	if(this != m) {
    		final double weight = Window.G * mass * m.mass / Util.sq(position.distanceWith(m.position));
    		final double theta = position.angleWith(m.position);
    		netForce = netForce.addWith(Vector.createFromPolar(weight, theta));
    	}
    }
    
    /**Draws Rectangle to simulate the mass */
    public void draw(Graphics g) {
        g.setColor(color);
        realX =  (int)(location.getContentPane().getWidth() * position.x + Window.PADDING_X);
        realY =  (int)(location.getContentPane().getHeight() * position.y + Window.PADDING_Y);
        realWidth =  (int)(location.getContentPane().getWidth() * width);
        realHeight =  (int)(location.getContentPane().getHeight() * height);
        g.drawRect(realX, realY, realWidth, realHeight);
    }
    
    //---------Getter Methods----------//
    /** Color Passer */
    public Color getColor() {
    	return color;
    }
    
    /** Position Passer */
    public Vector getPosition() {
    	return position;
    }
    
    /** Width Passer */
    public double getWidth() {
    	return width;
    }
    	
    /** Height Passer */
    public double getHeight() {
    	return height;
    }
    
    //-------------Static-------------//
    /** Very Small amount to retain good physics */
    private final static double MIN_LENGTH = 0.000000000000000001;
}

