package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

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
    final static double LAG = 0.000001;
    /** Gravitational Constant of the Universe increased by 10 */
    final static double G = 6.67408e-10;
    /** Friction Coefficient */
    final static double MU = 0.5;
    /** Gravity for Earth */
    final static double g = 9.8e-20;
    
    //Double Buffering
    private Image photo;
    private Graphics dbg;
   
    /** Modified Mouse Listener */
    Mouse m;
    /** if the mouse is pressed */
    boolean mousePressed;
    /** Modified KeyBoard Listener */
    KeyBoard k;
    /** if the keys are pressed */
    ArrayList<Boolean> keysPressed = new ArrayList<Boolean>();
    /** all the masses */
    ArrayList<Mass> masses = new ArrayList<Mass>();
    /** controllable mass */
    Controlled c;
    /** the controllable masses directions */
    ArrayList<Direction> cDirs = new ArrayList<Direction>();

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
        
        //new Mass(Vector.createFromRect(0.4, 0.6), 0.02, Util.randomColor(new Random()), 10, this);
        //new Mass(Vector.createFromRect(0.6, 0.4), 0.02, Util.randomColor(new Random()), 2, this);
        new Controlled(Vector.createFromRect(0.2, 0.2), 0.02, Util.randomColor(new Random()), this);
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
            masses.forEach(m -> {
            	m.readjust(this.getContentPane().getWidth(), this.getContentPane().getHeight());
            	m.edgeBounce();
            	masses.forEach(m1 -> {
            		m1.testColliding(m);
            		m1.collideWith(m);
            		m1.gravity(m);
            	});
//            	m.airResitance();
            	if(keysPressed.get(KeyBoard.LEFT)) {
            		m.grow();
            	}
            	if(keysPressed.get(KeyBoard.RIGHT)) {
            		m.shrink();
            	}
            	if(keysPressed.get(KeyBoard.SPACE)) {
            		m.resetPosition();
            	}
            	if(keysPressed.get(KeyBoard.F)) {
            		m.reverseVelocity();
            	}
            	if(mousePressed) {
            		m.freeze();
            	}
            	m.physics();
            });
            if(keysPressed.get(KeyBoard.W)) {
            	Util.addDirectionOnce(Direction.UP, cDirs);
            } else cDirs.remove(Direction.UP);
            if(keysPressed.get(KeyBoard.A)) {
            	Util.addDirectionOnce(Direction.LEFT, cDirs);
            } else cDirs.remove(Direction.LEFT);
            if(keysPressed.get(KeyBoard.S)) {
            	Util.addDirectionOnce(Direction.DOWN, cDirs);	
            } else cDirs.remove(Direction.DOWN);
            if(keysPressed.get(KeyBoard.D)) {
            	Util.addDirectionOnce(Direction.RIGHT, cDirs);
            } else cDirs.remove(Direction.RIGHT);
            c.move(cDirs);
    	}
    }
    
    /** Double buffering */
    @Override
    public void paint(Graphics g) {
    	final int frameX = (int)(this.getContentPane().getWidth() + PADDING_X + 1);
        final int frameY = (int)(this.getContentPane().getHeight() + PADDING_Y + 1);
        photo = createImage(frameX, frameY);
        dbg = photo.getGraphics();
        paintComponent(dbg);
        g.drawImage(photo, 0, 0, this);
    }
    
    /** paint loop
     * draws rectangles/people */
    public void paintComponent(Graphics g) {   
        //Draws all the rectangles
    	masses.forEach(m -> {
        	m.draw(g);
        });
    	c.draw(g, cDirs);
        repaint();
    }  
    
    /** adds MyRectangle objects to rectangles */
    public void addToMasses(Mass myRect) {
        masses.add(myRect);
    }
    
    /** Defines Controlled Mass */
    public void defineControlled(Controlled controlled) {
    	this.c = controlled;
    }
}
