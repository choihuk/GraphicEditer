package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class AutoRepaintPanel extends JPanel {
	// Constructor
	public AutoRepaintPanel() {
		super();
		AutoRepainter autoRepainter = new AutoRepainter();
		this.addMouseListener(autoRepainter);
		this.addMouseMotionListener(autoRepainter);
		this.addMouseWheelListener(autoRepainter);
	}
	
	// Inner Class
	private class AutoRepainter extends MouseAdapter {
		@Override public void mouseDragged(MouseEvent e) {repaint();}
		@Override public void mouseMoved(MouseEvent e) {repaint();}
		@Override public void mousePressed(MouseEvent e) {repaint();}
		@Override public void mouseReleased(MouseEvent e) {repaint();}
		@Override public void mouseWheelMoved(MouseWheelEvent e) {repaint();}
	}

}
