package affineTransformTool;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;

import component.Components;
import constant.Constant;
import constant.Constant.ECursor;
import eventHandler.GiveMouseHandler;
import view.AttributePanel;
import view.MainPanel;

public class ShapeResize extends GiveMouseHandler {
	private boolean isResizeOK = false;
	private Point2D.Float startPoint;
	private MainPanel mainPanel;
	private Cursor currentCursor;
	
	public ShapeResize(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
		this.startPoint = null;
		this.currentCursor = null;
	}
	public void mousePressed(MouseEvent e) {
		if(isSelectedAt()) {
			for(Components components : MainPanel.getComponentVc()) {
				if(components.isSelected()) {
					startPoint = MainPanel.getMainAt().transformPoint(e.getPoint());
					currentCursor = mainPanel.getCursor();
					isResizeOK=true;
				}
			}
		}
	}
	public void mouseDragged(MouseEvent e) {
		if(isResizeOK) {
			Point2D.Float nowPoint = MainPanel.getMainAt().transformPoint(e.getPoint());
			AffineTransform at = new AffineTransform();
			for(Components components : MainPanel.getComponentVc()) {
				if(components.isSelected()) {
					Point2D.Float tPoint = this.setTranslate(components.getBasedShape().getShape().getBounds().getX(),
							components.getBasedShape().getShape().getBounds().getY(),
							components.getBasedShape().getShape().getBounds().getWidth(),
							components.getBasedShape().getShape().getBounds().getHeight());
					Point2D.Float sPoint = this.getScale(nowPoint, components);
					at.setToTranslation(tPoint.getX(),tPoint.getY());
					at.scale(sPoint.getX(), sPoint.getY());
					at.translate(-tPoint.getX(),-tPoint.getY());
					components.getBasedShape().createCenterAt(at);
				}
			}
			this.startPoint = nowPoint;
		}
	}
	private Float setTranslate(double x, double y, double w, double h) {
		Point2D.Float tPoint = new Point2D.Float();
		double tX = 0;
		double tY = 0;
		currentCursor = mainPanel.getCursor();
		if(currentCursor==ECursor.eNN.getCursor()) {
			tX = x+w/2; tY = y+h;
		}else if(currentCursor==ECursor.eNE.getCursor()) {
			tX = x; tY = y+h;
		}else if(currentCursor==ECursor.eEE.getCursor()) {
			tX = x; tY = y+h/2;
		}else if(currentCursor==ECursor.eSE.getCursor()) {
			tX = x; tY = y;
		}else if(currentCursor==ECursor.eSS.getCursor()) {
			tX = x+w/2; tY = y;
		}else if(currentCursor==ECursor.eSW.getCursor()) {
			tX = x+w; tY = y;
		}else if(currentCursor==ECursor.eWW.getCursor()) {
			tX = x+w; tY = y+h/2;
		}else if(currentCursor==ECursor.eNW.getCursor()) {
			tX = x+w; tY = y+h;
		}
		tPoint.setLocation(tX,tY);
		return tPoint;
	}
	private Point2D.Float getScale(Point2D.Float nowPoint, Components components) {
		double sX = this.startPoint.getX();
		double sY = this.startPoint.getY();
		double nX = nowPoint.getX();
		double nY = nowPoint.getY();
		double deltaW = 0;
		double deltaH = 0;
		double w = components.getBasedShape().getShape().getBounds().getWidth();
		double h = components.getBasedShape().getShape().getBounds().getHeight();
		if(currentCursor==ECursor.eNN.getCursor()) {
			deltaW = 0; deltaH = -(nY-sY);if(deltaH<0&&h<=30)mainPanel.setCursor(Constant.ECursor.eSS.getCursor());
		}else if(currentCursor==ECursor.eNE.getCursor()) {
			deltaW = nX-sX; deltaH=-(nY-sY);if((deltaW<0&&w<=30)||(deltaH<0&&h<=30))mainPanel.setCursor(Constant.ECursor.eSW.getCursor());
		}else if(currentCursor==ECursor.eEE.getCursor()) {
			deltaW =nX-sX; deltaH= 0;if(deltaW<0&&w<=30) {mainPanel.setCursor(Constant.ECursor.eWW.getCursor());}
		}else if(currentCursor==ECursor.eSE.getCursor()) {
			deltaW = nX-sX; deltaH= nY-sY;if((deltaW<0&&w<=30)||(deltaH<0&&h<=30))mainPanel.setCursor(Constant.ECursor.eNW.getCursor());
		}else if(currentCursor==ECursor.eSS.getCursor()) {
			deltaW = 0; deltaH= nY-sY;if(deltaH<0&&h<=30)mainPanel.setCursor(Constant.ECursor.eNN.getCursor());
		}else if(currentCursor==ECursor.eSW.getCursor()) {
			deltaW = -(nX-sX); deltaH= nY-sY;if((deltaW<0&&w<=30)||(deltaH<0&&h<=30))mainPanel.setCursor(Constant.ECursor.eNE.getCursor());
		}else if(currentCursor==ECursor.eWW.getCursor()) {
			deltaW = -(nX-sX); deltaH = 0;if(deltaW<0&&w<=20) {mainPanel.setCursor(Constant.ECursor.eEE.getCursor());}
		}else if(currentCursor==ECursor.eNW.getCursor()) {
			deltaW =-(nX-sX); deltaH=-(nY-sY);if((deltaW<0&&w<=30)||(deltaH<0&&h<=30))mainPanel.setCursor(Constant.ECursor.eSE.getCursor());
		}
		Point2D.Float point = new Point2D.Float();
		point.setLocation(deltaW / w + 1.0,deltaH / h + 1.0);
		return point;
	}
	public void mouseReleased(MouseEvent e) {
		if(isResizeOK) {
			this.currentCursor = null;
			isResizeOK = false;
		}
	}
	private boolean isSelectedAt() {
		if(AttributePanel.getAttributeState()==AttributePanel.AttributeState.selectShape
				&&this.mainPanel.getCursor()!=ECursor.eMove.getCursor()
				&&this.mainPanel.getCursor()!=ECursor.eDefault.getCursor()
				&&this.mainPanel.getCursor()!=ECursor.eRotate.getCursor()) {return true;}
		else {return false;}
	}
	
}
