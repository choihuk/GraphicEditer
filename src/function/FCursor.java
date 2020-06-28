package function;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import Tool.ImageTool;
import component.Components;
import constant.Constant;
import constant.Constant.ECursor;
import eventHandler.GiveMouseHandler;
import eventHandler.MainMouseHandler;
import view.AttributePanel;
import view.AttributePanel.AttributeState;
import view.MainPanel;

public class FCursor extends GiveMouseHandler{
	private MainPanel mainPanel;
	private static Point2D.Float point;
	public FCursor(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
	}
	
	public void mouseClicked(MouseEvent e) {
		if(AttributePanel.getAttributeState()==AttributeState.pen&&this.mainPanel.getCursor()==Constant.ECursor.eColor.getCursor()) {
			Point2D.Float point = MainPanel.getMainAt().transformPoint(e.getPoint());
			Color color = ImageTool.pipette(point);
			MainMouseHandler.setHPenColor(color);
			MainMouseHandler.setPenColor(color);
			this.mainPanel.setCursor(Constant.ECursor.eDefault.getCursor());
		}
	}

	public void mouseMoved(MouseEvent e) {
		if(AttributePanel.getAttributeState()==AttributeState.selectShape) {
			checkCursor(MainPanel.getMainAt().transformPoint(e.getPoint()));
		}else if(AttributePanel.getAttributeState()==AttributeState.pen&&this.mainPanel.getCursor()==Constant.ECursor.eColor.getCursor()) {
			point = MainPanel.getMainAt().transformPoint(e.getPoint());
		}
	}
	private void checkCursor(Point2D.Float p) {
		if(this.onShape(p)) {
			this.mainPanel.setCursor(Constant.ECursor.eMove.getCursor());
		}else {
			this.mainPanel.setCursor(Constant.ECursor.eDefault.getCursor());
		}
		if(SelectShape.getAnchorVc().size()!=0){setAnchorCursor(p);}
	}
	private void setAnchorCursor(Point2D.Float point) {
		for (int i = 0; i < SelectShape.getAnchorVc().size(); i++) {
			for (int j = 0; j <  SelectShape.getAnchorVc().get(i).getAnchors().size(); j++) {
				Cursor cursor = null;
				for(ECursor eCursor:ECursor.values()) {
					if(eCursor.ordinal()-2==j) cursor=eCursor.getCursor();
				}
				if(SelectShape.getAnchorVc().get(i).getAnchors().get(j).contains(point)) {
					this.mainPanel.setCursor(cursor);
				}
			}
		}
	}
	private boolean onShape(Point2D.Float p) {
		for(Components component : MainPanel.getComponentVc()) {
			if(component.getBasedShape().contains(p)) {return true;}
		}
		return false;
	}


	public static void drawPipette(Graphics2D g2d) {
		if(AttributePanel.getAttributeState()==AttributeState.pen&&MainPanel.getMainPanel().getCursor()==Constant.ECursor.eColor.getCursor()) {
			Ellipse2D.Double ellipse = new Ellipse2D.Double();
			ellipse.setFrame(point.getX()+50, point.getY()-50, 50, 50);
			g2d.setColor(ImageTool.pipette(point));
			g2d.fill(ellipse);
			g2d.setStroke(new BasicStroke());g2d.setColor(Color.BLACK);
			g2d.draw(ellipse);
		}
	}
}
