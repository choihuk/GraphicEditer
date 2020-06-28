package shapes;

import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.util.Vector;

import component.Components;

public class GroupShape extends BasedShape {
	private static final long serialVersionUID = 1L;
	private Vector<Components> groupMember;
	@Override
	public void setShape(Vector<Float> points) {
		
	}
	public void addGroupVc(Vector<Components> groupMember) {
		this.groupMember=groupMember;
		Path2D path = new Path2D.Float();
		for(Components selectedShape : groupMember) {
			path.append(selectedShape.getBasedShape().getShape(), false);
		}
		Area compound = new Area(path);
		this.shape = compound.getBounds2D();
	}
	public Vector<Components> getGroupShape() {
		return groupMember;
	}
	public boolean contains(Point2D p) {
		for(Components component:groupMember) {
			if(component.getBasedShape().contains(p)) {return true;}
		}
		return false;
	}
	public void createCenterAt(AffineTransform at) {
		Point2D.Float point = new Point2D.Float();
		at.transform(this.getCenter(), point);
		for(Components component:groupMember) {
			component.getBasedShape().setShape(at.createTransformedShape(component.getBasedShape().getShape()));
		}
		this.shape = at.createTransformedShape(this.shape);
	}
}
