package shapes;

import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Path2D.Double;
import java.awt.geom.Point2D;
import java.util.Vector;

public class LineShape extends BasedShape{
	private static final long serialVersionUID = 1L;
	@Override
	public void setShape(Vector<Point2D.Float> points) {
		this.shape = new Line2D.Float(points.get(0),points.get(1));
	}
	public boolean contains(Point2D p) {
		if(this.shape instanceof Line2D.Float) {
			Line2D.Float line = (Line2D.Float) this.shape;
			if(line.ptSegDist(p.getX(),p.getY())<5) {
				return true;
			}else {
				return false;
			}
		}else {
			Path2D.Double path = (Double) this.shape;
			return path.getBounds().contains(p);
		}
	}
}
