package shapes;

import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Vector;

public class PenShape extends BasedShape{
	private static final long serialVersionUID = 1L;
	private Vector<Point2D.Float> points;
	public PenShape() {
		this.points = new Vector<Point2D.Float>();
	}
	
	@Override
	public void setShape(Vector<Point2D.Float> points) {
		Point2D.Float point = new Point2D.Float(points.get(0).x,points.get(0).y);
		this.points.add(point);
		GeneralPath generalPath = new GeneralPath();
		generalPath.moveTo(this.points.get(0).x, this.points.get(0).y);
		for(Point2D.Float p : this.points) {generalPath.lineTo(p.x,p.y);}
		this.shape = generalPath;
	}
}
