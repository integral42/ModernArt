package main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Controlled extends Mass{
	//---------------Fields-------------//
	
	//----------------Constructors------------------//
	/** Acts the same as parallel Mass constructor */
	public Controlled(Vector position, double size, Color color, Window location) {
		super(position, size, color, location);
		
		location.defineControlled(this);
	}
	
	//-----------------Methods-----------------//
	/** Accelerates in a direction based on an argument */
	public void move(ArrayList<Direction> marks) {
		final double aAmount = Window.LAG / 2000000;
		if(marks.contains(Direction.UP)) {
			netForce = netForce.addWith(Vector.createFromRect(0, -aAmount));
		}
		if(marks.contains(Direction.LEFT)) {
			netForce = netForce.addWith(Vector.createFromRect(-aAmount, 0));
		}
		if(marks.contains(Direction.DOWN)) {
			netForce = netForce.addWith(Vector.createFromRect(0, aAmount));
		}
		if(marks.contains(Direction.RIGHT)) {
			netForce = netForce.addWith(Vector.createFromRect(aAmount, 0));
		}
	}
	
	/** Draws Lines for the Object */
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		g.drawOval(realX + 1, realY + 1, realWidth - 2, realHeight - 2);
		final int midPointX = realX + realWidth / 2;
		final int midPointY = realY + realHeight / 2;
		g.drawLine(midPointX, midPointY, 1, 1);
	}
}
