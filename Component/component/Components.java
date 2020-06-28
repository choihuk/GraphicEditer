package component;

import java.awt.Color;
import java.awt.Composite;
import java.awt.geom.Point2D.Float;
import java.io.Serializable;
import java.util.Vector;

import serializable.SBasicStroke;
import shapes.BasedShape;

public class Components implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private BasedShape shape;
	private Color lineColor;
	private Color fillColor;
	private SBasicStroke stroke;
	private Composite composite;
	private boolean isSelected = false;
	
	public Components(Color lineColor, SBasicStroke stroke, Composite composite) {
		this.shape = null;
		this.lineColor=lineColor;
		this.fillColor = null;
		this.stroke = stroke;
		this.composite = composite;
	}
	public BasedShape getBasedShape() {
		return shape;
	}
	public void setBasedShape(BasedShape shape, Vector<Float> points) {
		this.shape = shape;
		this.shape.setShape(points);
	}
	public void setBasedShapeNoPoint(BasedShape shape) {
		this.shape = shape;
	}
	public Color getLineColor() {
		return lineColor;
	}
	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}
	public Color getFillColor() {
		return fillColor;
	}
	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}
	public SBasicStroke getStroke() {
		return stroke;
	}
	public void setStroke(SBasicStroke stroke) {
		this.stroke = stroke;
	}
	public Composite getComposite() {
		return composite;
	}
	public void setComposite(Composite composite) {
		this.composite = composite;
	}
	public boolean isSelected() {
		return isSelected;
	}
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
}
