package shapes;

import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Vector;

public class HexagonShape extends BasedShape{
	private static final long serialVersionUID = 1L;

	@Override
	public void setShape(Vector<Point2D.Float> points) {
		GeneralPath generalPath = new GeneralPath();
		x = Math.min(points.get(0).x, points.get(1).x);
		y = Math.min(points.get(0).y, points.get(1).y);
		w = Math.abs(points.get(0).x-points.get(1).x);
		h = Math.abs(points.get(0).y-points.get(1).y);
		generalPath.moveTo(x, y + h*(0.5));
		generalPath.lineTo(x + w*0.25, y + h);
		generalPath.lineTo(x + w*0.75, y + h);
		generalPath.lineTo(x + w, y+h*0.5);
		generalPath.lineTo(x + w*0.75, y);
		generalPath.lineTo(x + w*0.25, y);
		generalPath.lineTo(x, y + h*(0.5));
		this.shape = generalPath;
	}
}
