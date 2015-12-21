package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import enums.Teams;
/**
 * Main Class: all main code
 * @author Connor Lehmacher
 */
@SuppressWarnings("serial")
public class Main extends JFrame{     
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
    boolean firstdelay = true;
   
    ArrayList<MyRectangle> rectangles;
    ArrayList<Person> people;
    
    /**
     * Main Method
     * @param args
     */
    public static void main(String[] args) {
    	Main window = new Main();
    	SwingUtilities.invokeLater(() -> window.setVisible(true));
    	window.run();
    }
    /**
     * Does all main code
     * Has game loop
     */
    public Main() {
        super();
        
        //timer = new Timer(10, this);
        //timer.setInitialDelay(40); 
        
        //Set up how the frame works
        setSize(FRAME_X , FRAME_Y); //Golden Ratio -ish Makes the rectangle look nice
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBackground(new Color(10, 10, 10));
        rectangles = new ArrayList<>();
        //people! - Non-permanent part of code
        people = new ArrayList<Person>();
            
        for(int a = CONTENT[0] ; a <= CONTENT[2] ; a += 50){
            for(int b = CONTENT[1] ; b <= CONTENT[3] ; b += 50){
                new Person(a, b, Math.random() * 12, new Color((float)Math.random(), (float)Math.random(), (float)Math.random()),Teams.NONE, 10, 10, this);
            }
        }
        /*
        new Person(100, 100, 3, Teams.NONE, this);
        new Person(100, 200, 25, Color.MAGENTA, Teams.NONE, 10, 10, this);
        new Person(100, 300, 20, Color.RED, Teams.NONE, 10, 10, this);S
        new Person(200, 100, 2, Color.BLUE, Teams.NONE, 10, 10, this);
        new Person(200, 200, 8, Color.GREEN, Teams.NONE, 10, 10, this);
        new Person(200, 300, 15, Color.ORANGE, Teams.NONE, 10, 10, this);
        */
   
    }
    
    /**
     * Let the program run.  Never returns!
     */
    public void run() {
        while(true){
            //Moving
            rectangles.forEach(r -> {
            r.bounce();
            r.move();
            rectangles.forEach(r1 -> r.collide(r1));
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
        //Delay
        if(firstdelay){
            try{
                Thread.sleep(100);
            } 
            catch (InterruptedException e){}
            firstdelay = false;
        }
        
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

