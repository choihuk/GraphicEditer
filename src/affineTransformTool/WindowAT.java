package affineTransformTool;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import eventHandler.GiveMouseHandler;
import view.MainPanel;

public class WindowAT extends GiveMouseHandler {
	private boolean isBT3;
	public WindowAT() {this.isBT3 = false;}
	public void mouseWheelMoved(MouseWheelEvent e) {
		MainPanel.getMainAt().zoomCamera(e);
	}
	public void mousePressed(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON3) {
			this.isBT3 = true;
			MainPanel.getMainAt().setDragStartPoint(e.getPoint());
		}
	}
	public void mouseDragged(MouseEvent e) {
		if(isBT3) {
			MainPanel.getMainAt().moveCamera(e);
		}
	}
	public void mouseReleased(MouseEvent e) {
		this.isBT3 = false;
	}
}
