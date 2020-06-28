package shapes;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.Vector;

public class EllipseShape extends BasedShape{
	private static final long serialVersionUID = 1L;

	@Override
	public void setShape(Vector<Point2D.Float> points) {
		x = Math.min(points.get(0).x, points.get(1).x);
		y = Math.min(points.get(0).y, points.get(1).y);
		w = Math.abs(points.get(0).x-points.get(1).x);
		h= Math.abs(points.get(0).y-points.get(1).y);
		this.shape = new Ellipse2D.Float(x,y,w,h);
	}
}
