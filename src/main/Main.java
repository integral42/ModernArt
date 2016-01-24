package main;

import java.awt.Color;

import javax.swing.SwingUtilities;

/**
 * Main Class
 * @author Connor Lehmacher
 */
public class Main {     
    /**
     * Main Method
     */
    public static void main(String[] args) {
    	Window w = new Window();
    	try {
    		SwingUtilities.invokeAndWait(() -> w.setVisible(true));
    	} catch (Exception e) {}
    	w.createBufferStrategy(2);
    	w.setBackground(Color.BLACK);
    	w.run();
    }
}



