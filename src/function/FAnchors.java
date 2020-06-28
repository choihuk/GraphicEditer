package function;

import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.Vector;

import component.Components;

public class FAnchors {
	public enum EAnchors{
		NW, NN, NE, EE, SE, SS, SW, WW, RR;
	}
	private Vector<Ellipse2D> anchors;
	private Components component;
	private int anchorSize = 10;
	public FAnchors(Rectangle boundingRectangle, Components component) {
		this.component=component;
		this.anchors = new Vector<Ellipse2D>();
		for(EAnchors eAnchor : EAnchors.values()) {
			Ellipse2D anchor = new Ellipse2D.Double();
			this.setCoordinae(anchor, eAnchor,boundingRectangle);
			this.anchors.add(anchor);
		}
	}
	public Components getComponent() {
		return this.component;
	}
	public Vector<Ellipse2D> getAnchors() {
		return this.anchors;
	}
	private void setCoordinae(Ellipse2D anchor, EAnchors eAnchor, Rectangle boundingRectangle) {
		double x1 = boundingRectangle.getX()-5;
		double y1 = boundingRectangle.getY()-5;
		double x2 = x1 + boundingRectangle.getWidth();
		double y2 = y1 + boundingRectangle.getHeight();
		switch(eAnchor) {
		case NW:
			anchor.setFrame(x1, y1, anchorSize, anchorSize);
			break;
		case NN:
			anchor.setFrame((x1+x2)/2, y1, anchorSize, anchorSize);
			break;
		case NE:
			anchor.setFrame(x2, y1, anchorSize, anchorSize);
			break;
		case EE:
			anchor.setFrame(x2, (y1+y2)/2, anchorSize, anchorSize);
			break;
		case SE:
			anchor.setFrame(x2, y2, anchorSize, anchorSize);
			break;
		case SS:
			anchor.setFrame((x1+x2)/2, y2, anchorSize, anchorSize);
			break;
		case SW:
			anchor.setFrame(x1, y2, anchorSize, anchorSize);
			break;
		case WW:
			anchor.setFrame(x1, (y1+y2)/2, anchorSize, anchorSize);
			break;
		case RR:
			anchor.setFrame((x1+x2)/2,y1-30,anchorSize,anchorSize);
			break;
		}
	}
}
