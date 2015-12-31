package listening;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.ArrayList;

/**
 * Key Detection
 * @author Connor Lehmacher
 */
public class MyKeyListener implements KeyListener{
	/**
	 * all the keys to be tested
	 */
	static ArrayList<Integer> keys = new ArrayList<Integer>();
	/**
	 * which keys are currently pressed down
	 */
	ArrayList<Boolean> keysPressed = new ArrayList<Boolean>();
	
	/**
	 * Default Constructor makes keysPressed get as many falses as in the length keys	
	 */
	public MyKeyListener(){
		super();
		for(int i = 0; i < keys.size(); i++){
			keysPressed.add(false);
		}
	}
    /**
     * Adds keys to be tested to "keys"
     */
	public static void fillKeys(){
		keys.add(KeyEvent.VK_SPACE);
		keys.add(KeyEvent.VK_LEFT);
		keys.add(KeyEvent.VK_RIGHT);
	}
	
    @Override
    public void keyPressed(KeyEvent e){
        for(int i = 0; i < keys.size(); i++){
        	if(e.getKeyCode() == keys.get(i)){
        		keysPressed.set(i, true);
        	}
        }
    }
    @Override
    public void keyReleased(KeyEvent e){
    	for(int i = 0; i < keys.size(); i++){
        	if(e.getKeyCode() == keys.get(i)){
        		keysPressed.set(i, false);
        	}
        }
    }
    @Override
    public void keyTyped(KeyEvent e){}
    /**
     * passes the keyPressed information
     * @return
     */
    public ArrayList<Boolean> getKeysPressed(){
        return keysPressed;
    }
    
    // MARK: Static
    public static final int VK_SPACE = 0;
    public static final int VK_LEFT = 1;
    public static final int VK_RIGHT = 2;
    
    
    
}