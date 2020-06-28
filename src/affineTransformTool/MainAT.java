package affineTransformTool;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import state.State;

public class MainAT {
	private static AffineTransform mainAt = new AffineTransform();
	private static int maxZoomLevel = 10, minZoomLevel = -50;
	private static float zoomFactor = 1.15f;

	private static int zoomLevel = 0;
	private static Point dragStartPoint;

	public void setMainAt(AffineTransform at) {mainAt.setTransform(at);}
	public AffineTransform getMainAt() {return mainAt;}
	public void zoomCamera(MouseWheelEvent e) {
		if(State.ctrlIsOn) {
			Point2D p1 = transformPoint(e.getPoint());
			if (e.getWheelRotation() > 0&&zoomLevel<maxZoomLevel) {
				zoomLevel++; 
				mainAt.scale(1 / zoomFactor, 1 / zoomFactor);
			}
			else if(e.getWheelRotation() < 0&&zoomLevel>minZoomLevel){
				zoomLevel--;
				mainAt.scale(zoomFactor, zoomFactor);
			}
			Point2D p2 = transformPoint(e.getPoint());
			mainAt.translate(p2.getX() - p1.getX(), p2.getY() - p1.getY());
		}
	}
	public void setDragStartPoint(Point p) {dragStartPoint = p;}
	public void moveCamera(MouseEvent e) {
		Point dragEndPoint = e.getPoint();
		Point2D.Float dragStart = transformPoint(dragStartPoint);
		Point2D.Float dragEnd = transformPoint(dragEndPoint);
		mainAt.translate(dragEnd.getX() - dragStart.getX(), dragEnd.getY() - dragStart.getY());
		dragStartPoint = dragEndPoint;
	}
	public void moveCameraKey(int keyCode) {
		if(keyCode==KeyEvent.VK_RIGHT) {mainAt.translate(3, 0);}
		else if(keyCode==KeyEvent.VK_LEFT){mainAt.translate(-3, 0);}
		else if(keyCode==KeyEvent.VK_UP){mainAt.translate(0, -3);}
		else if(keyCode==KeyEvent.VK_DOWN){mainAt.translate(0, 3);}
	}
	public Point2D.Float transformPoint(Point p1)  {
		Point2D.Float p2 = new Point2D.Float();
		try {mainAt.createInverse().transform(p1, p2);}catch (Exception e) {e.printStackTrace();}
		return p2;
	}
	public Point2D.Float transformPoint(Point2D.Double p1)  {
		Point2D.Float p2 = new Point2D.Float();
		try {mainAt.createInverse().transform(p1, p2);}catch (Exception e) {e.printStackTrace();}
		return p2;
	}
	public Point2D.Float transformPoint(Point2D.Float p1)  {
		Point2D.Float p2 = new Point2D.Float();
		try {mainAt.createInverse().transform(p1, p2);}catch (Exception e) {e.printStackTrace();}
		return p2;
	}
}
