package shapes;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.Vector;

import Tool.DeepClone;
import view.MainPanel;

public abstract class BasedShape implements Cloneable,Serializable{
	private static final long serialVersionUID = 1L;
	protected Shape shape;
	protected float x,y,w,h;
	public abstract void setShape(Vector<Point2D.Float> points);
	public Shape getShape() {
		return this.shape;
	}
	public void setShape(Shape shape) {
		this.shape = shape;
	}
	public boolean contains(Point2D p) {
		return this.shape.contains(p);
	}
	public BasedShape clone() {
		return (BasedShape) DeepClone.clone(this);
	}
	public Point2D.Float getCenter(){
		Point point = new Point();
		point.setLocation(this.shape.getBounds2D().getCenterX(),this.shape.getBounds2D().getCenterY());
		return MainPanel.getMainAt().transformPoint(point);
	}
	public void createCenterAt(AffineTransform at) {
		Point2D.Float point = new Point2D.Float();
		at.transform(this.getCenter(), point);
		this.shape = at.createTransformedShape(this.shape);
	}
	public void rotateShape(double radian) {
		AffineTransform at = new AffineTransform();
		at.setToRotation(Math.toRadians(radian), this.getCenter().getX(), this.getCenter().getY());
		this.createCenterAt(at);
	}
}
