package view;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.Vector;

import Tool.RedoUndo;
import affineTransformTool.MainAT;
import affineTransformTool.ShapeMove;
import affineTransformTool.ShapeResize;
import affineTransformTool.ShapeRotate;
import affineTransformTool.WindowAT;
import component.Components;
import constant.ViewConstant;
import eventHandler.GiveMouseHandler;
import eventHandler.KeyEventHandler;
import eventHandler.MainMouseHandler;
import function.FCursor;
import function.SelectShape;
import serializable.SerializableAlphaComposite;
import shapes.GroupShape;
import shapes.HPenShape;
import shapes.ImageShape;

public class MainPanel extends AutoRepaintPanel {
	private static final long serialVersionUID = 1L;
	// Component
	private static Vector<Components> componentVc;
	private MainMouseHandler mainMouseHandler;
	private static MainAT mainAt = new MainAT();
	private static MainPanel mainPanel;
	private Vector<GiveMouseHandler> giveMouseVc;

	// Constructor
	public MainPanel() {
		super();
		// Set Attribute
		MainPanel.mainPanel=this;
		this.setBackground(ViewConstant.PanelBGColor);
		this.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		// Create Component
		MainPanel.componentVc = new Vector<Components>();
		RedoUndo.saveHistory();// ∫Û »≠∏È ¿˙¿Â
		this.giveMouseVc = new Vector<GiveMouseHandler>();

		// Create Handler
		this.mainMouseHandler = new MainMouseHandler(MainPanel.componentVc,this.giveMouseVc);
		this.addMouseListener(this.mainMouseHandler);
		this.addMouseMotionListener(this.mainMouseHandler);
		this.addMouseWheelListener(this.mainMouseHandler);
		this.giveMouseVc();
		KeyboardFocusManager keyboardFocusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		keyboardFocusManager.addKeyEventDispatcher(new KeyEventHandler());
	}

	private void giveMouseVc() {
		this.giveMouseVc.add(new WindowAT());
		this.giveMouseVc.add(SelectShape.getSelectShape());
		this.giveMouseVc.add(new FCursor(this));
		this.giveMouseVc.add(new ShapeMove(this));
		this.giveMouseVc.add(new ShapeResize(this));
		this.giveMouseVc.add(new ShapeRotate(this));
	}
	public static MainPanel getMainPanel() {return MainPanel.mainPanel;}
	public static void repaintMainPanel() {
		mainPanel.repaint();
	}
	public static Vector<Components> getComponentVc() {
		return componentVc;
	}
	public static MainAT getMainAt() {
		return mainAt;
	}
	public static void setMainAt(MainAT mainAt) {
		MainPanel.mainAt = mainAt;
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		super.paint(g2d);
		AffineTransform saveAt = g2d.getTransform();
		g2d.transform(mainAt.getMainAt());
		
		setCanvas(g2d);
		drawComponents(g2d);
		setG2d(g2d);
		SelectShape.drawSelectShape(g2d);
		drawPipette(g2d);
		
		g2d.setTransform(saveAt);
	}


	private void setG2d(Graphics2D g2d) {
		g2d.setStroke(new BasicStroke());
		g2d.setColor(Color.BLACK);
		g2d.setComposite(new SerializableAlphaComposite(AlphaComposite.SRC_OVER,1f));
	}

	private void drawPipette(Graphics2D g2d) {
		FCursor.drawPipette(g2d);
	}

	private void setCanvas(Graphics2D g2d) {
		MainCanvas.drawCanvas(g2d);
	}

	private void drawComponents(Graphics2D g2d) {
		for(Components components : componentVc) {
			if(components.getBasedShape() instanceof HPenShape) {
				g2d.setColor(components.getLineColor());
				g2d.setStroke(components.getStroke());
				g2d.setComposite(components.getComposite());
				g2d.draw(components.getBasedShape().getShape());
				if(components.getFillColor()!=null) {
					g2d.setColor(components.getFillColor());
					g2d.fill(components.getBasedShape().getShape());
				}
			}
		}
		for(Components components : componentVc) {
			if(!(components.getBasedShape() instanceof HPenShape)) {
				if(components.getBasedShape() instanceof ImageShape) {
					ImageShape iS = (ImageShape) components.getBasedShape();
					Rectangle b = iS.getShape().getBounds();
					g2d.setComposite(components.getComposite());
					g2d.drawImage(iS.getImage().getBImage(),b.x,b.y,b.width,b.height,null);
				}else if(components.getBasedShape() instanceof GroupShape) {
					GroupShape gS = (GroupShape) components.getBasedShape();
					for(Components groupCom : gS.getGroupShape()) {
						g2d.setColor(groupCom.getLineColor());
						g2d.setStroke(groupCom.getStroke());
						g2d.setComposite(groupCom.getComposite());
						g2d.draw(groupCom.getBasedShape().getShape());
						if(groupCom.getFillColor()!=null) {
							g2d.setColor(groupCom.getFillColor());
							g2d.fill(groupCom.getBasedShape().getShape());
						}
					}
				}else {
				g2d.setColor(components.getLineColor());
				g2d.setStroke(components.getStroke());
				g2d.setComposite(components.getComposite());
				g2d.draw(components.getBasedShape().getShape());
				if(components.getFillColor()!=null) {
					g2d.setColor(components.getFillColor());
					g2d.fill(components.getBasedShape().getShape());
				}
				}
			}
		}
	}
}
