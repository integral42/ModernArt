package main;

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
    	w.run();
    }
}



