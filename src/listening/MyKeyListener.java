package listening;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.ArrayList;

/**
 * Key Detection
 * @author Connor Lehmacher
 */
public class MyKeyListener implements KeyListener{
	//----------Fields----------//
	/**
	 * which keys are currently pressed down
	 */
	ArrayList<Boolean> keysPressed = new ArrayList<Boolean>();
	
	//---------Constructors---------//
	/**
	 * Default Constructor makes keysPressed get as many falses as in the length keys	
	 */
	public MyKeyListener() {
		super(); 
		for(int i = 0; i < keys.size(); i++){
			keysPressed.add(false);
		}
	}
	
	//-----------Methods-----------//
    @Override
    public void keyPressed(KeyEvent e) {
        for(int i = 0; i < keys.size(); i++){
        	if(e.getKeyCode() == keys.get(i)){
        		keysPressed.set(i, true);
        	}
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
    	for(int i = 0; i < keys.size(); i++){
        	if(e.getKeyCode() == keys.get(i)){
        		keysPressed.set(i, false);
        	}
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}
    
    /**
     * passes the keyPressed information
     */
    public ArrayList<Boolean> getKeysPressed() {
        return keysPressed;
    }
    
    
    
    //----------STATIC------------//
    
	/**
	 * all the keys to be tested
	 */
	static ArrayList<Integer> keys = new ArrayList<Integer>();
    // MARKS
    public static final int SPACE = 0;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int UP = 3;
    public static final int DOWN = 4;
    
    /**
     * Adds keys to be tested to "keys"
     */
	static {
		keys.add(KeyEvent.VK_SPACE);
		keys.add(KeyEvent.VK_LEFT);
		keys.add(KeyEvent.VK_RIGHT);
		keys.add(KeyEvent.VK_UP);
		keys.add(KeyEvent.VK_DOWN);
	}
}