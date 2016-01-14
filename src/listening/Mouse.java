package listening;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse implements MouseListener {
	//--------Fields--------//
	private boolean MousePressed = false;
	
	//--------Methods-------//
	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {
		MousePressed = true;
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		MousePressed = false;
	}
	
	public boolean getMousePressed() {
		return MousePressed;
	}
}
