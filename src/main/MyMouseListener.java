package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Mouse Detection
 * @author Connor Lehmacher
 */
public class MyMouseListener implements MouseListener {
    /**
     * Mouse Location
     * x = 0, y = 1
     */
    int[] m = new int[2];
    
    @Override
    public void mousePressed(MouseEvent e){
    
    }
    
    @Override
    public void mouseReleased(MouseEvent e){
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        m[0] = e.getX();
        m[1] = e.getY();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
    /*public void mouseMoved(MouseEvent e) {
        m[0] = e.getX();
        m[1] = e.getY();
    }
    
    public int[] passMouse(){
        return m;
    }*/
}



