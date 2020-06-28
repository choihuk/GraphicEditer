package affineTransformTool;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import component.Components;
import constant.Constant.ECursor;
import eventHandler.GiveMouseHandler;
import function.SelectShape;
import state.State;
import view.AttributePanel;
import view.MainPanel;

public class ShapeRotate extends GiveMouseHandler {

	private boolean isRotateOK = false;
	private Point2D.Float startPoint;
	private Components clickComponent;
	private MainPanel mainPanel;

	public ShapeRotate(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

	public void mousePressed(MouseEvent e) {
		if(isSelectedAt()) {
			for(Components components : MainPanel.getComponentVc()) {
				if(components.isSelected()) {
					startPoint = MainPanel.getMainAt().transformPoint(e.getPoint());
					this.clickComponent = getClickComponent(e);
					isRotateOK=true;
				}
			}
		}
	}
	public void mouseDragged(MouseEvent e) {
		if(isRotateOK) {
			Point2D.Float nowPoint = MainPanel.getMainAt().transformPoint(e.getPoint());
			for(Components components : MainPanel.getComponentVc()) {
				if(components.isSelected()) {
					Point2D.Float center;
					if(clickComponent!=null&&!State.ctrlIsOn) {center = clickComponent.getBasedShape().getCenter();}
					else {center = components.getBasedShape().getCenter();}
					components.getBasedShape().rotateShape(getRadian(nowPoint,center));
				}
			}
			this.startPoint = nowPoint;
		}
	}

	public void mouseReleased(MouseEvent e) {
		if(isRotateOK) {isRotateOK = false;}
	}

	public static void buttomRotate(int radian) {
		for(Components components : MainPanel.getComponentVc()) {
			if(components.isSelected()) {
				components.getBasedShape().rotateShape(radian);
			}
		}
	}
	private Components getClickComponent(MouseEvent e) {
		if(SelectShape.getAnchorVc().size()!=0) {
			int index = SelectShape.cursor(MainPanel.getMainAt().transformPoint(e.getPoint()));
			if(index!=-1) {
				return SelectShape.getAnchorVc().get(index).getComponent();
			}
		}
		return null;
	}
	private double getRadian(Point2D.Float nowPoint, Point2D.Float center) {
		double startRadian = Math.toDegrees(Math.atan2(center.getX()-this.startPoint.getX(), center.getY()-this.startPoint.getY()));
		double endRadian = Math.toDegrees(Math.atan2(center.getX()-nowPoint.getX(), center.getY()-nowPoint.getY()));
		double radian = startRadian-endRadian;
		if (radian < 0) {radian += 360;}
		return radian;
	}


	private boolean isSelectedAt() {
		if(AttributePanel.getAttributeState()==AttributePanel.AttributeState.selectShape&&
				this.mainPanel.getCursor()==ECursor.eRotate.getCursor()) {return true;}
		else {return false;}
	}
}
