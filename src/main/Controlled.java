package main;

import java.awt.Color;

import listening.KeyBoard;

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
		if(mark == KeyBoard.W) {
			netForce = netForce.addWith(Vector.createFromRect(0, -aAmount));
		}
		if(mark == KeyBoard.A) {
			netForce = netForce.addWith(Vector.createFromRect(-aAmount, 0));
		}
		if(mark == KeyBoard.S) {
			netForce = netForce.addWith(Vector.createFromRect(0, aAmount));
		}
		if(mark == KeyBoard.D) {
			netForce = netForce.addWith(Vector.createFromRect(aAmount, 0));
		}
		
	}
}
