package affineTransformTool;

import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import component.Components;
import constant.Constant.ECursor;
import eventHandler.GiveMouseHandler;
import view.AttributePanel;
import view.MainPanel;

public class ShapeMove extends GiveMouseHandler{
	private boolean isMoveOK = false;
	private Point2D.Float startPoint;
	private MainPanel mainPanel;
	
	public ShapeMove(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
	}
	
	public void mousePressed(MouseEvent e) {
		if(isSelectedAt()) {
			Point2D.Float point = MainPanel.getMainAt().transformPoint(e.getPoint());
			for(Components components : MainPanel.getComponentVc()) {
				if(components.isSelected()&&components.getBasedShape().contains(point)) {
					startPoint = new Point2D.Float();
					startPoint.setLocation(point);
					isMoveOK=true;
				}
			}
		}
	}
	public void mouseDragged(MouseEvent e) {
		if(isMoveOK) {
			Point2D.Float nowPoint = MainPanel.getMainAt().transformPoint(e.getPoint());
			AffineTransform at = new AffineTransform();
			at.translate(nowPoint.getX()-startPoint.getX(), nowPoint.getY()-startPoint.getY());
			for(Components components : MainPanel.getComponentVc()) {
				if(components.isSelected()) {
					components.getBasedShape().createCenterAt(at);
				}
			}
			this.startPoint = nowPoint;
		}
	}
	public void mouseReleased(MouseEvent e) {
		if(isMoveOK) {isMoveOK = false;}
	}
	private boolean isSelectedAt() {
		if(AttributePanel.getAttributeState()==AttributePanel.AttributeState.selectShape&&
				this.mainPanel.getCursor()==ECursor.eMove.getCursor()) {return true;}
		else {return false;}
	}
}
