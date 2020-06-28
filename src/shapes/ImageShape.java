package shapes;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

import serializable.SBufferedImage;

public class ImageShape extends BasedShape {
	private static final long serialVersionUID = 1L;
	private SBufferedImage image;
	private boolean identify = false;
	@Override
	public void setShape(Vector<Point2D.Float> points) {
		x = Math.min(points.get(0).x, points.get(1).x);
		y = Math.min(points.get(0).y, points.get(1).y);
		w = Math.abs(points.get(0).x-points.get(1).x);
		h= Math.abs(points.get(0).y-points.get(1).y);
		this.shape =  new Rectangle2D.Float(x,y,w,h);
	}

	public void setImage(SBufferedImage sImage) {
		this.image = sImage;
	}
	public SBufferedImage getImage() {return this.image;}
	public boolean getIdentify() {return this.identify;}
	public void setIdentify(boolean identify) {this.identify=identify;}
}
