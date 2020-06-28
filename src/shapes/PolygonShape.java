package shapes;

import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D.Float;
import java.util.Vector;

public class PolygonShape extends BasedShape {
	private static final long serialVersionUID = 1L;

	@Override
	public void setShape(Vector<Float> points) {
		GeneralPath path = new GeneralPath();
		path.moveTo(points.get(0).x,points.get(0).y);
		for (int i = 1; i < points.size(); i++) {
			path.lineTo(points.get(i).x, points.get(i).y);
		}
		path.closePath();
		this.shape = path;
	}

}
