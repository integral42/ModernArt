package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

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
    /** Initial Speed */
    final static double INITIAL_SPEED = 0/*0.000025*/;
    /** Small amount for growth */
    final static double EPSILON = 0.0000001;
    /** Gravitational Constant of the Universe */
    final static double G = 6.67408e-11;
    
    //Double Buffering
    private Image photo;
    private Graphics dbg;
   
    MyKeyListener m;
    ArrayList<Boolean> keysPressed;
    ArrayList<Mass> masses;
    Timer t;

    //----------Constructor--------//
    /** Makes Frame Makes objects */
    public Window() {
        super();
        
        //Set up how the frame works
        setSize(FRAME_X , FRAME_Y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.BLACK);
        
        m = new MyKeyListener();
        addKeyListener(m);
        
        keysPressed = new ArrayList<Boolean>();
        masses = new ArrayList<Mass>();
        
        new Mass(Vector.createFromRect(0.2, 0.7), 0.01, Util.randomColor(new Random()), this);
        new Mass(Vector.createFromRect(0.7, 0.2), 0.01, Util.randomColor(new Random()), this);
        // Make lots of rectangles
        //for(double i = 0 ; i <= 1 ; i += 0.05) {
        //    new PointMass(Vector.createFromRect(i, (Math.random()/ 5) + 0.4), (Math.random() / 30), Util.randomColor(new Random()), this);
        //}
    }
    
    
    //------Methods--------//
    /** Logic loop */
    public void run() {
    	while(true) {
    		keysPressed = m.getKeysPressed();
            //Moving
            masses.forEach(r -> {
            	r.edgeBounce();
            	r.physics();
            	r.readjust(this.getContentPane().getWidth(), this.getContentPane().getHeight());
            	masses.forEach(r1 -> {
            		r.collideWith(r1);
            		r.gravity(r1);
            	});
            	if(keysPressed.get(MyKeyListener.UP)){
            		r.grow();
            	}
            	if(keysPressed.get(MyKeyListener.DOWN)){
            		r.shrink();
            	}
            });
    	}
    }
    
    /** Double buffering */
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
        for (Mass mR : masses) {
            g.setColor(mR.getColor());
            final int realX =  (int)(this.getContentPane().getWidth() * mR.getPosition().x + PADDING_X);
            final int realY =  (int)(this.getContentPane().getHeight() * mR.getPosition().y + PADDING_Y);
            final int realWidth =  (int)(this.getContentPane().getWidth() * mR.getWidth());
            final int realHeight =  (int)(this.getContentPane().getHeight() * mR.getHeight());
            g.drawRect(realX, realY, realWidth, realHeight);
        }
        repaint();
    }  
    
    /** adds MyRectangle objects to rectangles */
    public void addToMasses(Mass myRect) {
        masses.add(myRect);
    }
}
