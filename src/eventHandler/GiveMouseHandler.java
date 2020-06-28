package eventHandler;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class GiveMouseHandler {
	public void giveEvent(MouseEvent e) {
		int eID = e.getID();
		if(eID == MouseEvent.MOUSE_PRESSED) {this.mousePressed(e);}
		else if(eID == MouseEvent.MOUSE_RELEASED) {this.mouseReleased(e);}
		else if(eID == MouseEvent.MOUSE_CLICKED) {this.mouseClicked(e);}
		else if(eID == MouseEvent.MOUSE_DRAGGED) {this.mouseDragged(e);}
		else if(eID == MouseEvent.MOUSE_MOVED) {this.mouseMoved(e);}
		else if(eID == MouseEvent.MOUSE_ENTERED) {this.mouseEntered(e);}
		else if(eID == MouseEvent.MOUSE_EXITED) {this.mouseExited(e);}
	}
	public void giveWheelEvent(MouseWheelEvent e) {
		this.mouseWheelMoved(e);
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseWheelMoved(MouseWheelEvent e) {}
}
