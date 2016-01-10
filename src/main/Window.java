package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

import enums.Teams;
import listening.MyKeyListener;

@SuppressWarnings("serial")
public class Window extends JFrame {
	//-------------Fields--------------//
    /** Initial Size of the frame: X */
    final static int FRAME_X = 700;
    /** Initial Size of the frame: Y */
    final static int FRAME_Y = 700;
    /** Padding for window size: X
     * ElCapitan: 0, Windows7: 8  */   
    final static int PADDING_X = 0;
    /** Padding for window size: Y
     * ElCapitan: 23, Windows7: 30  */ 
    final static int PADDING_Y = 23;
    
    //Double Buffering
    private Image photo;
    private Graphics dbg;
   
    Timer t;
    MyKeyListener m;
    ArrayList<Boolean> keysPressed;
    ArrayList<MyRectangle> rectangles;
    ArrayList<Person> people;

    /**
     * Makes Frame Makes objects
     */
    public Window() {
        super();
        
        //Set up how the frame works
        setSize(FRAME_X , FRAME_Y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.BLACK);
        
        t = new Timer(1, (ActionEvent e) ->  {
        	keysPressed = m.getKeysPressed();
            //Moving
            rectangles.forEach(r -> {
            	r.edgeBounce();
            	r.physics();
            	r.readjust(this.getContentPane().getWidth(), this.getContentPane().getHeight());
            	rectangles.forEach(r1 -> r.collideWith(r1));
            	if(keysPressed.get(MyKeyListener.UP)){
            		r.grow();
            	}
            	if(keysPressed.get(MyKeyListener.DOWN)){
            		r.shrink();
            	}
            });
            //people.forEach(p -> p.wander());
        });
        
        m = new MyKeyListener();
        addKeyListener(m);
        
        keysPressed = new ArrayList<Boolean>();
        rectangles = new ArrayList<MyRectangle>();
        people = new ArrayList<Person>();
        
        for(double i = 0 ; i <= 1 ; i += 0.1237) {
            for(double j = 0 ; j <= 1 ; j += 0.1) {
            	new Person(Vector.createFromRect(i, j), 0.01, Util.randomColor(new Random()), Teams.NONE, 10, this);
            }
        }
    }
    
    /**
     * Double buffering
     */
    @Override
    public void paint(Graphics g) {
    	final int frameX =  (int)(this.getContentPane().getWidth() + PADDING_X + 1);
        final int frameY =  (int)(this.getContentPane().getHeight() + PADDING_Y + 1);
        photo = createImage(frameX, frameY);
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
            g.setColor(mR.getColor());
            final int realX =  (int)(this.getContentPane().getWidth() * mR.getPosition().x + PADDING_X);
            final int realY =  (int)(this.getContentPane().getHeight() * mR.getPosition().y + PADDING_Y);
            final int realWidth =  (int)(this.getContentPane().getWidth() * mR.getWidth());
            final int realHeight =  (int)(this.getContentPane().getHeight() * mR.getHeight());
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
