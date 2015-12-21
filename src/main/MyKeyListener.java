package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Key Detection
 * @author Connor Lehmacher
 */
public class MyKeyListener implements KeyListener{
    /**
     * 4 arrow-keys
     * Up = 0, Down = 1, Left = 2, Right=3
     */
	boolean[] directions = new boolean[4];
    
    @Override
    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() ==  KeyEvent.VK_UP){
            directions[0] = true;
        }
        if (e.getKeyCode() ==  KeyEvent.VK_DOWN){                
            directions[1] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            directions[2] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            directions[3] = true;
        }
    }
    @Override
    public void keyReleased(KeyEvent e){
        if (e.getKeyCode() ==  KeyEvent.VK_UP){
            directions[0] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN){
            directions[1] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            directions[2] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            directions[3] = false;
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e){
        
    }
    /**
     * passes a boolean array of the 4 arrow keys
     * @return
     */
    public boolean[] passArrows(){
        return directions;
    }
}

