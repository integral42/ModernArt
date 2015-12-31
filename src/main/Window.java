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
    //Important for base drawing
    final int FRAME_X = 770;
    final int FRAME_Y = 600;
    /**	
     * The size of the usable frame
     * 0:x1 1:y1 2:y1 3:y2
     */
    final int[] CONTENT = new int[]{4, 25, 766, 597};
    private Image photo;
    private Graphics dbg;
    //Timer timer;
   
    MyKeyListener mKL;
    ArrayList<Boolean> keysPressed;
    ArrayList<MyRectangle> rectangles;
    ArrayList<Person> people;
    
    /**
     * Main Method
     * @param args
     */
    public static void main(String[] args) {
    	MyKeyListener.fillKeys();
    	Window window = new Window();
    	SwingUtilities.invokeLater(() -> window.setVisible(true));
    	window.run();
    }
    /**
     * Does all main code
     * Has game loop
     */
    public Window() {
        super();
        
        //timer = new Timer(10, this);
        //timer.setInitialDelay(40); 
        
        //Set up how the frame works
        setSize(FRAME_X , FRAME_Y); //Golden Ratio -ish Makes the rectangle look nice
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBackground(new Color(10, 10, 10));
        
        mKL = new MyKeyListener();
        keysPressed = new ArrayList<Boolean>();
        rectangles = new ArrayList<MyRectangle>();
        //people! - Non-permanent part of code
        people = new ArrayList<Person>();
            
        for(int i = CONTENT[0] ; i <= CONTENT[2] ; i += 50){
            for(int j = CONTENT[1] ; j <= CONTENT[3] ; j += 50){
                new Person(i, j, Math.random() * 12, randomColor(new Random()),Teams.NONE, 10, 10, this);
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
            	r.bounce();
            	r.move();
            	rectangles.forEach(r1 -> r.collide(r1));
            	if(keysPressed.get(MyKeyListener.VK_LEFT)){
            		System.out.println("Hello");
            		r.grow();
            	}
            	if(keysPressed.get(MyKeyListener.VK_RIGHT)){
            		r.shrink();
            	}
            });
            people.forEach(p -> p.wander());
            try {
                Thread.sleep(10);
            } 
            catch (InterruptedException e) {}
        }
    }
    
    /**
     * Double buffering
     * @param g
     */
    @Override
    public void paint(Graphics g){
        photo = createImage(FRAME_X, FRAME_Y);
        dbg = photo.getGraphics();
        paintComponent(dbg);
        g.drawImage(photo, 0, 0, this);
    }
    /**
     * paint loop
     * draws rectangles/people
     * @param g
     */
    public void paintComponent(Graphics g)
    {   
        //Draws all the rectangles
        for (MyRectangle myRect : rectangles) {
            g.setColor(myRect.color);
            g.drawRect((int)myRect.x, (int)myRect.y, (int)myRect.length, (int)myRect.width);
        }
        repaint();
    }  
    /**
     * adds MyRectangle objects to rectangles
     * @param myRect
     */
    public void addToRectangles(MyRectangle myRect){
        rectangles.add(myRect);
    }
    /**
     * adds Person objects to People
     * @param person
     */
    public void addToPeople(Person person){
        people.add(person);
    }
}

