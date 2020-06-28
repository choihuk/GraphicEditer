package function;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Vector;

import component.Components;
import constant.Constant;
import constant.ViewConstant;
import eventHandler.GiveMouseHandler;
import serializable.SBasicStroke;
import serializable.SerializableAlphaComposite;
import state.State;
import view.AttributePanel;
import view.AttributePanel.AttributeState;
import view.MainPanel;

public class SelectShape extends GiveMouseHandler{
	private static Vector<Components> componentVc;
	private static SelectShape selectShape = new SelectShape();
	private static Vector<FAnchors> anchorVc;
	private boolean isDrawRec = false;
	private Vector<Point2D.Float> points;
	private Components rectangle;

	public SelectShape() {
		SelectShape.selectShape = this;
		anchorVc = new Vector<FAnchors>();
		componentVc = MainPanel.getComponentVc();
	}

	@Override public void mousePressed(MouseEvent e) {
		Point2D.Float point = MainPanel.getMainAt().transformPoint(e.getPoint());
		if(componentVc!=null&&e.getButton()==1&&AttributePanel.getAttributeState()==AttributeState.selectShape) {
			if(State.ctrlIsOn) {
				for(Components components : componentVc) {
					if(components.getBasedShape().getShape().contains(point)) {	components.setSelected(true);}
				}
			}else if(cursor(point)!=-1){}
			else {
				for(Components components : componentVc) {components.setSelected(false);}
				boolean isIn = false;
				for (int i = 0; i < componentVc.size(); i++) {
					if(componentVc.get(i).getBasedShape().contains(point)&&!isIn) {
						componentVc.get(i).setSelected(true);
						isIn = true;
					}
				}
				if(!isIn) {
					SelectShape.setAnchorVc(new Vector<FAnchors>());
					this.isDrawRec = true;
					this.rectangle = newComponent(point,Color.WHITE,ViewConstant.BasedStrokeWidth,100);
					this.points = new Vector<Point2D.Float>();
					this.points.add(new Point2D.Float()); this.points.get(0).setLocation(point);
					this.points.add(new Point2D.Float()); this.points.get(1).setLocation(point);
					this.rectangle.setBasedShape(Constant.EAShapeButton.square.getShape(), this.points);
					this.rectangle.setFillColor(ViewConstant.SelectRecColor);
					MainPanel.getComponentVc().add(this.rectangle);
				}
			}
		}
	}
	public void mouseDragged(MouseEvent e) {
		if(isDrawRec) {
			Point2D.Float point = MainPanel.getMainAt().transformPoint(e.getPoint());
			this.points.get(1).setLocation(point);
			this.rectangle.setBasedShape(Constant.EAShapeButton.square.getShape(),this.points);
			MainPanel.getComponentVc().set(MainPanel.getComponentVc().size()-1, this.rectangle);
		}
	}
	public void mouseReleased(MouseEvent e) {
		if(isDrawRec) {
			MainPanel.getComponentVc().remove(MainPanel.getComponentVc().size()-1);
			Rectangle2D rect = (Rectangle2D)this.rectangle.getBasedShape().getShape().getBounds2D();
			for(Components component: MainPanel.getComponentVc()) {
				if(rect.contains(component.getBasedShape().getShape().getBounds2D())) {
					component.setSelected(true);
				}
			}
			isDrawRec=false;
		}
	}

	private Components newComponent(Point2D.Float point, Color lineColor, int strokeWidth, int compositeDegree){
		SerializableAlphaComposite a = new SerializableAlphaComposite(AlphaComposite.SRC_OVER, (float)compositeDegree/255);
		SBasicStroke stroke = new SBasicStroke(strokeWidth,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
		return new Components(lineColor,stroke,a);
	}

	public static int cursor(Point2D.Float point) {
		if(anchorVc!=null) {
			for (int i = 0; i < anchorVc.size(); i++) {
				for (int j = 0; j < anchorVc.get(i).getAnchors().size(); j++) {
					if(anchorVc.get(i).getAnchors().get(j).contains(point)) {return i;}
				}
			}
		}
		return -1;
	}

	public static SelectShape getSelectShape() {
		return SelectShape.selectShape;
	}
	public static void setAnchorVc(Vector<FAnchors> anchorVc) {
		SelectShape.anchorVc = anchorVc;
	}
	public static Vector<FAnchors> getAnchorVc() {
		return SelectShape.anchorVc;
	}

	public static void drawSelectShape(Graphics2D g2d) {
		if(AttributePanel.getAttributeState()==AttributeState.selectShape) {
			anchorVc.clear();
			for(Components components : componentVc) {
				if(components.isSelected()) {
					FAnchors anchors = new FAnchors(components.getBasedShape().getShape().getBounds(),components);
					anchorVc.add(anchors);
					for(Ellipse2D ellipse2d : anchors.getAnchors()) {
						g2d.draw(ellipse2d);
					}
				}
			}
		}
	}
}
