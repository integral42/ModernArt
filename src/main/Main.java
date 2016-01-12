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
    	SwingUtilities.invokeLater(() -> w.setVisible(true));
    	try { Thread.sleep(100); }
    	catch(InterruptedException ex) {}
    	w.run();
    }
}



