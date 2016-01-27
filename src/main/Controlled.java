package main;

import java.awt.Color;

public class Controlled extends Mass{
	//----------------Constructors------------------//
	/** Acts the same as parallel Mass constructor */
	public Controlled(Vector position, double size, Color color, Window location) {
		super(position, size, color, location);
		
		location.defineControlled(this);
	}
	
	//-----------------Methods-----------------//
	/** Accelerates in a direction based on an argument */
	public void move(int mark) {
		final double aAmount = Window.LAG / 10000000;
		if(mark == UP) {
			netForce = netForce.addWith(Vector.createFromRect(0, -aAmount));
		}
		if(mark == LEFT) {
			netForce = netForce.addWith(Vector.createFromRect(-aAmount, 0));
		}
		if(mark == DOWN) {
			netForce = netForce.addWith(Vector.createFromRect(0, aAmount));
		}
		if(mark == RIGHT) {
			netForce = netForce.addWith(Vector.createFromRect(aAmount, 0));
		}
		
	}
	
	//-------------Marks-Static--------//
	final static int UP = 0;
	final static int LEFT = 1;
	final static int DOWN = 2;
	final static int RIGHT = 3;
}
