package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import enums.Teams;
import listening.MyKeyListener;
/**
 * Main Class: all main code
 * @author Connor Lehmacher
 */
@SuppressWarnings("serial")
public class Window extends JFrame{     
	//-------------Fields--------------//
    /** Initial Size of the frame: X */
    final static int FRAME_X = 700;
    /** Initial Size of the frame: Y */
    final static int FRAME_Y = 700;
    /** Padding for window size: X
     * ElCapitan: 0, Windows7: 7  */   
    final static int PADDING_X = 7;
    /** Padding for window size: Y
     * ElCapitan: 23, Windows7: 30  */ 
    final static int PADDING_Y = 30;
    
    //Double Buffering
    private Image photo;
    private Graphics dbg;
   
    MyKeyListener m;
    ArrayList<Boolean> keysPressed;
    ArrayList<MyRectangle> rectangles;
    ArrayList<Person> people;
    
    /**
     * Main Method
     */
    public static void main(String[] args) {
    	Window window = new Window();
    	SwingUtilities.invokeLater(() -> window.setVisible(true));
    	window.run();
    }
    
    /**
     * Makes Frame Makes objects
     */
    public Window() {
        super();
        
        //Set up how the frame works
        setSize(FRAME_X , FRAME_Y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(new Color(10, 10, 10));
        
        m = new MyKeyListener();
        addKeyListener(m);
        
        keysPressed = new ArrayList<Boolean>();
        rectangles = new ArrayList<MyRectangle>();
        people = new ArrayList<Person>();
        
        for(double i = 0 ; i <= 1 ; i += 0.1){
            for(double j = 0 ; j <= 1 ; j += 0.1){
            	new Person(Vector.createFromRect(i, j), (Math.random() / 50), randomColor(new Random()),Teams.NONE, 10, this);
            }
        }
    }
    
    /**
     * Generates a new random color using random source r.
     */
    private Color randomColor(Random r) {
    	return new Color(r.nextFloat(), r.nextFloat(), r.nextFloat());
    }
    
    /**
     * Let the program run.  Never returns!
     */
    public void run() {
        while(true){
        	keysPressed = m.getKeysPressed();
            //Moving
            rectangles.forEach(r -> {
            	r.edgeBounce();
            	r.move();
            	rectangles.forEach(r1 -> r.collideWith(r1));
            	if(keysPressed.get(MyKeyListener.UP)){
            		r.grow();
            	}
            	if(keysPressed.get(MyKeyListener.DOWN)){
            		r.shrink();
            	}
            });
            people.forEach(p -> p.wander());
        }
    }
    
    /**
     * Double buffering
     */
    @Override
    public void paint(Graphics g) {
        photo = createImage(this.getContentPane().getWidth() + 8, this.getContentPane().getHeight() + 30);
        dbg = photo.getGraphics();
        paintComponent(dbg);
        g.drawImage(photo, 0, 0, this);
    }
    /**
     * paint loop
     * draws rectangles/people
     */
    public void paintComponent(Graphics g) {   
        //Draws all the rectangles
        for (MyRectangle mR : rectangles) {
            g.setColor(mR.color);
            final int realX =  (int)(this.getContentPane().getWidth() * mR.position.x + 8);
            final int realY =  (int)(this.getContentPane().getHeight() * mR. position.y + 30);
            final int realWidth =  (int)(this.getContentPane().getWidth() * mR.width);
            final int realHeight =  (int)(this.getContentPane().getHeight() * mR.height);
            g.drawRect(realX, realY, realWidth, realHeight);
        }
        repaint();
    }  
    
    /**
     * adds MyRectangle objects to rectangles
     */
    public void addToRectangles(MyRectangle myRect) {
        rectangles.add(myRect);
    }
    
    /**
     * adds Person objects to People
     */
    public void addToPeople(Person person) {
        people.add(person);
    }
}



