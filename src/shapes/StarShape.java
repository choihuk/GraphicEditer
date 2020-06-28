package shapes;

import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Vector;

public class StarShape extends BasedShape{
	private static final long serialVersionUID = 1L;

	@Override
	public void setShape(Vector<Point2D.Float> points) {
		GeneralPath generalPath = new GeneralPath();
		x = Math.min(points.get(0).x, points.get(1).x);
		y = Math.min(points.get(0).y, points.get(1).y);
		w = Math.abs(points.get(0).x-points.get(1).x);
		h = Math.abs(points.get(0).y-points.get(1).y);
		generalPath.moveTo(x+(w/2), y);
		generalPath.lineTo(x+w*0.4, y+h*(0.4));
		generalPath.lineTo(x, y+h*(0.4));
		generalPath.lineTo(x+w*0.33, y+h*(0.6));
		generalPath.lineTo(x+w*(0.2), y+h);
		generalPath.lineTo(x+(w/2), y+h*0.75);
		generalPath.lineTo(x+(w*(0.8)), y+h);
		generalPath.lineTo(x+w*0.66, y+h*(0.6));
		generalPath.lineTo(x+w, y+h*(0.4));
		generalPath.lineTo(x+w*0.6, y+h*(0.4));
		generalPath.lineTo(x+(w/2), y);
		this.shape = generalPath;
	}
}
