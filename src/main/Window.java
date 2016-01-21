package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

import listening.KeyBoard;
import listening.Mouse;

@SuppressWarnings("serial")
public class Window extends JFrame {
	//-------------Fields--------------//
    /** Initial Size of the frame: X */
    final static int FRAME_X = 700;
    /** Initial Size of the frame: Y */
    final static int FRAME_Y = 700;
    /** Padding for window size: X
     * ElCapitan: 0, Windows7: 8  */   
    final static int PADDING_X;
    /** Padding for window size: Y
     * ElCapitan: 23, Windows7: 30  */ 
    final static int PADDING_Y;
    //Gives Values for padding based on OS
    static {
    	if (System.getProperty("os.name").equals("Mac OS X")) {
    		PADDING_X = 0;
    		PADDING_Y = 23;
    	}
    	else if (System.getProperty("os.name").equals("Windows 7")) {
    		PADDING_X = 8;
    		PADDING_Y = 30;
    	}
    	else {
    		System.err.println("OS Not Supported Yet");
    		PADDING_X = 0;
    		PADDING_Y = 0;
    	}
    }
    /** Lag Constant */
    final static double LAG = 0.0000001;
    /** Gravitational Constant of the Universe increased by 10 */
    final static double G = 6.67408e-10;
    
    //Double Buffering
    private Image photo;
    private Graphics dbg;
   
    Mouse m;
    boolean mousePressed;
    KeyBoard k;
    ArrayList<Boolean> keysPressed;
    ArrayList<Mass> masses;
    Timer t;
    Controlled controlled;

    //----------Constructor--------//
    /** Makes Frame Makes objects */
    public Window() {
        super();
        
        //Set up how the frame works
        setSize(FRAME_X , FRAME_Y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.BLACK);
        
        m = new Mouse();
        addMouseListener(m);
        
        k = new KeyBoard();
        addKeyListener(k);       
        keysPressed = new ArrayList<Boolean>();
        
        masses = new ArrayList<Mass>();
        
        new Mass(Vector.createFromRect(0.4, 0.6), 0.01, Util.randomColor(new Random()), Vector.createFromRect(0.0000001, 0), this);
        new Mass(Vector.createFromRect(0.6, 0.4), 0.01, Util.randomColor(new Random()), 2, this);
        new Controlled(Vector.createFromRect(0.2, 0.2), 0.01, Util.randomColor(new Random()), this);
         //Make lots of masses
//        for(double i = 0 ; i <= 1 ; i += 0.2) {
//            new Mass(Vector.createFromRect(i, (Math.random()/ 5) + 0.4),
//            		(Math.random() / 30) + 0.01, Util.randomColor(new Random()),
//            		1, this);
//        }
    }
    
    
    //------Methods--------//
    /** Logic loop */
    public void run() {
    	while(true) {
    		keysPressed = k.getKeysPressed();
    		mousePressed = m.getMousePressed();
            //Moving
            masses.forEach(r -> {
            	r.readjust(this.getContentPane().getWidth(), this.getContentPane().getHeight());
            	r.edgeBounce();
            	masses.forEach(r1 -> {
            		r.collideWith(r1);
            		r.gravity(r1);
            	});
            	if(keysPressed.get(KeyBoard.UP)) {
            		r.grow();
            	}
            	if(keysPressed.get(KeyBoard.DOWN)) {
            		r.shrink();
            	}
            	if(keysPressed.get(KeyBoard.SPACE)) {
            		r.resetPosition();
            	}
            	if(mousePressed) {
            		r.freeze();
            	}
            	
            	r.physics();
            });
            if(keysPressed.get(KeyBoard.W)) {
            	controlled.move(KeyBoard.W);
            }
            if(keysPressed.get(KeyBoard.A)) {
            	controlled.move(KeyBoard.A);
            }
            if(keysPressed.get(KeyBoard.S)) {
            	controlled.move(KeyBoard.S);
            }
            if(keysPressed.get(KeyBoard.D)) {
            	controlled.move(KeyBoard.D);
            }
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
    
    /** Defines Controlled Mass */
    public void defineControlled(Controlled controlled) {
    	this.controlled = controlled;
    }
    
}
