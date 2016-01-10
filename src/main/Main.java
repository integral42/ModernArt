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
    	w.t.start();
    }
}



