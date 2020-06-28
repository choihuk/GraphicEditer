package eventHandler;


import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Point2D;
import java.util.Vector;

import Tool.RedoUndo;
import component.Components;
import constant.Constant;
import constant.Constant.EAShapeButton;
import constant.ViewConstant;
import serializable.SBasicStroke;
import serializable.SerializableAlphaComposite;
import shapes.HPenShape;
import shapes.PenShape;
import state.State;
import view.AttributePanel;
import view.AttributePanel.AttributeState;
import view.MainPanel;

public class MainMouseHandler implements MouseListener, MouseMotionListener, MouseWheelListener{

	// Associate
	private Vector<Components> componentVc;

	// Working Variable
	private Vector<Point2D.Float> points;
	private PenShape pen;
	private HPenShape hPen;
	private boolean isRightBt = false;

	// components Attributes
	private static Color penColor;
	private static Color hPenColor;
	private static int strokeWidth;
	private static int compositeDegree;
	//new Components
	private Components components;
	private Vector<GiveMouseHandler> giveMouseVc;
	// Constructor
	public MainMouseHandler(Vector<Components> componentVc, Vector<GiveMouseHandler> giveMouseVc) {
		this.componentVc=componentVc;
		this.giveMouseVc=giveMouseVc;
		penColor = ViewConstant.BasedLineColor;
		strokeWidth = ViewConstant.BasedStrokeWidth;
		compositeDegree = ViewConstant.BasedCompositeDegree;
	}
	private void giveEvent(MouseEvent e) {
		for(GiveMouseHandler g:this.giveMouseVc) {
			g.giveEvent(e);
		}
	}
	private void giveWheelEvent(MouseWheelEvent e) {
		for(GiveMouseHandler g:this.giveMouseVc) {
			g.giveWheelEvent(e);
		}
	}
	public static void setPenColor(Color penColor) {
		MainMouseHandler.penColor=penColor;
	}
	public static void setHPenColor(Color hPenColor) {
		MainMouseHandler.hPenColor=hPenColor;
	}
	public static void setStrokeWidth(int strokeWidth) {
		MainMouseHandler.strokeWidth=strokeWidth;
	}
	public static void setCompositeDegree(int degree) {
		MainMouseHandler.compositeDegree = degree;
	}

	private void newComponent(Point2D.Float point, Color lineColor, int strokeWidth, int compositeDegree){
		SerializableAlphaComposite a = new SerializableAlphaComposite(AlphaComposite.SRC_OVER, (float)compositeDegree/255);
		SBasicStroke stroke = new SBasicStroke(strokeWidth,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
		this.components = new Components(lineColor,stroke,a);
		this.points = new Vector<Point2D.Float>();
		this.points.add(new Point2D.Float());
		this.points.add(new Point2D.Float());
		this.points.get(0).setLocation(point);
		this.points.get(1).setLocation(point);
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON1&&isShapeDraw()) {
			isRightBt = true;
			Point2D.Float point =MainPanel.getMainAt().transformPoint(e.getPoint());
			if(AttributePanel.getAttributeState()==AttributePanel.AttributeState.pen) {
				if(State.getPen()==Constant.EAPenButton.pen) {
					newComponent(point,penColor,strokeWidth,compositeDegree);
					this.pen = new PenShape();
					this.components.setBasedShape(this.pen,points);
					this.componentVc.add(this.components);
				}else if(State.getPen()==Constant.EAPenButton.hPen) {
					newComponent(point,hPenColor,strokeWidth,compositeDegree);
					this.hPen = new HPenShape();
					this.components.setBasedShape(this.hPen,points);
					this.componentVc.add(this.components);
				}else if(State.getPen()==Constant.EAPenButton.eraser) {
					for (int i = 0; i < componentVc.size(); i++) {
						if(componentVc.get(i).getBasedShape().contains(e.getPoint())) {componentVc.remove(i);}
					}
				}
			}else if(State.getShape()!=EAShapeButton.polygon){
				newComponent(point,ViewConstant.BasedLineColor,ViewConstant.BasedStrokeWidth,ViewConstant.BasedCompositeDegree);
				this.components.setBasedShape(State.getShape().getShape(),points);
				this.componentVc.add(this.components);
			}else {

			}
		}
		giveEvent(e);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(isRightBt&&isShapeDraw()) {
			Point2D.Float point =MainPanel.getMainAt().transformPoint(e.getPoint());
			if(AttributePanel.getAttributeState()==AttributePanel.AttributeState.pen) {
				if(State.getPen()==Constant.EAPenButton.pen) {
					this.points.get(0).setLocation(point);
					this.components.setBasedShape(this.pen,points);
					this.componentVc.set(this.componentVc.size()-1, this.components);
				}else if(State.getPen()==Constant.EAPenButton.hPen) {
					this.points.get(0).setLocation(point);
					this.components.setBasedShape(this.hPen,points);
					this.componentVc.set(this.componentVc.size()-1, this.components);
				}else if(State.getPen()==Constant.EAPenButton.eraser) {
					for (int i = 0; i < componentVc.size(); i++) {
						if(componentVc.get(i).getBasedShape().contains(point)) {componentVc.remove(i);}
					}
				}
			}else if(State.getShape()!=EAShapeButton.polygon){
				this.points.get(1).setLocation(point);
				this.components.setBasedShape(State.getShape().getShape(),points);
				this.componentVc.set(this.componentVc.size()-1, this.components);
			}
		}
		giveEvent(e);
	}

	@Override 
	public void mouseClicked(MouseEvent e) {
		Point2D.Float point =MainPanel.getMainAt().transformPoint(e.getPoint());
		if(isShapeDraw()) {
			if(AttributePanel.getAttributeState()==AttributePanel.AttributeState.shape&&
					State.getShape()==EAShapeButton.polygon) {
				if(components==null) {
					newComponent(point,ViewConstant.BasedLineColor,ViewConstant.BasedStrokeWidth,ViewConstant.BasedCompositeDegree);
					this.components.setBasedShape(State.getShape().getShape(),points);
					this.componentVc.add(this.components);
				}else {
					if(e.getClickCount()==2) {
						this.components = null;
						AttributePanel.setAttributePanel(AttributeState.attributeShape);
						MainPanel.repaintMainPanel();
					}else {
						this.points.add(point);
						this.components.setBasedShape(State.getShape().getShape(),points);
						this.componentVc.set(this.componentVc.size()-1, this.components);
					}
				}
			}
			if(e.getButton()==1&&State.getPen()!=Constant.EAPenButton.eraser&&State.getShape()!=EAShapeButton.polygon) {
				this.componentVc.remove(this.componentVc.size()-1);
			}
		}
		giveEvent(e);
	}
	@Override public void mouseMoved(MouseEvent e) {
		if(isShapeDraw()) {
			Point2D.Float point =MainPanel.getMainAt().transformPoint(e.getPoint());
			if(AttributePanel.getAttributeState()==AttributePanel.AttributeState.shape&&
					State.getShape()==EAShapeButton.polygon&&components!=null) {
				this.points.set(this.points.size()-1,point);
				this.components.setBasedShape(State.getShape().getShape(),points);
				this.componentVc.set(this.componentVc.size()-1, this.components);
			}
		}
		giveEvent(e);
	}
	@Override public void mouseReleased(MouseEvent e) {
		if(isRightBt&&isShapeDraw()) {
			if((AttributePanel.getAttributeState()==AttributePanel.AttributeState.shape||
					AttributePanel.getAttributeState()==AttributePanel.AttributeState.attributeShape)&&
					State.getShape()!=EAShapeButton.polygon) {
				this.components = null;
				AttributePanel.setAttributePanel(AttributeState.attributeShape);
				MainPanel.repaintMainPanel();
				RedoUndo.saveHistory();
			}else if(AttributePanel.getAttributeState()==AttributePanel.AttributeState.pen) {
				RedoUndo.saveHistory();
			}
			isRightBt = false;
		}
		giveEvent(e);
	}
	private boolean isShapeDraw() {
		if((AttributePanel.getAttributeState()==AttributePanel.AttributeState.shape||
				AttributePanel.getAttributeState()==AttributePanel.AttributeState.pen||
				AttributePanel.getAttributeState()==AttributePanel.AttributeState.attributeShape)&&
				MainPanel.getMainPanel().getCursor()!=Constant.ECursor.eColor.getCursor()) {return true;}
		else {return false;}
	}

	@Override public void mouseEntered(MouseEvent e) {giveEvent(e);}
	@Override public void mouseExited(MouseEvent e) {giveEvent(e);}
	@Override public void mouseWheelMoved(MouseWheelEvent e) {giveWheelEvent(e);}
}
