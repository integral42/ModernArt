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
    /** Initial Size of the frame */
    final static int FRAME_X = 770;
    final static int FRAME_Y = 600;
    /**	
     * The size of the usable frame
     * 0:x1 1:y1 2:y1 3:y2
     */
    final static int[] CONTENT = new int[]{4, 25, 766, 597};
    //Double Buffering
    private Image photo;
    private Graphics dbg;
    
    static Window window;
   
    MyKeyListener mKL;
    ArrayList<Boolean> keysPressed;
    ArrayList<MyRectangle> rectangles;
    ArrayList<Person> people;
    
    /**
     * Main Method
     */
    public static void main(String[] args) {
    	MyKeyListener.fillKeys();
    	window = new Window();
    	SwingUtilities.invokeLater(() -> window.setVisible(true));
    	window.run();
    }
    
    /**
     * Constructs all other objects
     */
    public Window() {
        super();
        
        //Set up how the frame works
        setSize(FRAME_X , FRAME_Y); //Golden Ratio -ish Makes the rectangle look nice
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(new Color(10, 10, 10));
        getContentPane();
        
        mKL = new MyKeyListener();
        addKeyListener(mKL);
        
        keysPressed = new ArrayList<Boolean>();
        rectangles = new ArrayList<MyRectangle>();
        people = new ArrayList<Person>();
            
        for(int i = CONTENT[0] ; i <= CONTENT[2] ; i += 50){
            for(int j = CONTENT[1] ; j <= CONTENT[3] ; j += 50){
                new Person(Vector.createFromRect(i, j), Math.random() * 12, randomColor(new Random()),Teams.NONE, 10, this);
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
        	keysPressed = mKL.getKeysPressed();
            //Moving
            rectangles.forEach(r -> {
            	r.edgeBounce();
            	r.move();
            	rectangles.forEach(r1 -> r.collide(r1));
            	if(keysPressed.get(MyKeyListener.UP)){
            		r.grow();
            	}
            	if(keysPressed.get(MyKeyListener.DOWN)){
            		r.shrink();
            	}
            });
            people.forEach(p -> p.wander());
            try {
                Thread.sleep(10);    
            } 
            catch(InterruptedException iE) {}
        }
    }
    
    /**
     * Double buffering
     */
    @Override
    public void paint(Graphics g) {
        photo = createImage(window.getContentPane().getSize().width, window.getContentPane().getSize().height);
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
            final int realX, realY, realWidth, realHeight;
            //TODO add this stuff to draw rect
            g.drawRect((int)mR.position.x, (int)mR.position.y, (int)mR.length, (int)mR.width);
        }
        repaint();
    }  
    /**
     * adds MyRectangle objects to rectangles
     */
    public void addToRectangles(MyRectangle myRect){
        rectangles.add(myRect);
    }
    /**
     * adds Person objects to People
     */
    public void addToPeople(Person person){
        people.add(person);
    }
}



