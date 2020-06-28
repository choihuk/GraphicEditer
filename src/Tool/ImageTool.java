package Tool;

import java.awt.AWTException;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import component.Components;
import constant.ViewConstant;
import serializable.SBufferedImage;
import serializable.SerializableAlphaComposite;
import shapes.ImageShape;
import view.AttributePanel;
import view.AttributePanel.AttributeState;
import view.MainCanvas;
import view.MainPanel;

public class ImageTool {
	private static int screenWidth = ViewConstant.screenWidth;
	private static int screenHeight = ViewConstant.screenHeight;
	
	public static void getImage() {
		JFileChooser fileChooser = new JFileChooser(new File("./TestImage"));
		int returnValue = fileChooser.showOpenDialog(MainPanel.getMainPanel());
		if(returnValue == JFileChooser.APPROVE_OPTION) {
			File imageFile = fileChooser.getSelectedFile();
			ImageShape imageShape = new ImageShape();
			try {
				BufferedImage image = ImageIO.read(imageFile);
				SBufferedImage sImage = new SBufferedImage(image);
				imageShape.setImage(sImage);
			} catch (IOException e1) {e1.printStackTrace();}
			
			int width = MainCanvas.getWidth();
			int height = MainCanvas.getHeight();
			SerializableAlphaComposite a = new SerializableAlphaComposite(AlphaComposite.SRC_OVER, (float)255/255);
			Vector<Point2D.Float> points = new Vector<Point2D.Float>();
			points.add(MainPanel.getMainAt().transformPoint(new Point((screenWidth-width)/2,(screenHeight-height)/2)));
			points.add(MainPanel.getMainAt().transformPoint(new Point((int)points.get(0).getX()+imageShape.getImage().getBImage().getWidth(),(int)points.get(0).getY()+imageShape.getImage().getBImage().getHeight())));
			Components component = new Components(null,null,a);
			component.setBasedShape(imageShape, points);
			MainPanel.getComponentVc().add(component);
			AttributePanel.setAttributePanel(AttributeState.image);
			RedoUndo.saveHistory();
		}
	}

	public static void insertImage() {
		int width = MainCanvas.getWidth();
		int height = MainCanvas.getHeight();
		Rectangle screenRec = new Rectangle((screenWidth-width)/2,70+(screenHeight-height)/2,width,height);
		Robot robot = null;
		try {robot = new Robot();} catch (AWTException e) {e.printStackTrace();}
		BufferedImage bufferedImage = robot.createScreenCapture(screenRec);
		try {ImageIO.write(bufferedImage, "png", new File("./ScreenImage/screenImage.png"));} catch (IOException e) {e.printStackTrace();}
	}
	
	public static Color pipette(Point2D.Float point) {
		Rectangle screenRect = new Rectangle((int)point.getX(),(int)point.getY()+70,1,1);
		Robot robot = null;
		try {robot = new Robot();} catch (AWTException e1) {e1.printStackTrace();}
		BufferedImage image = robot.createScreenCapture(screenRect);
		Color color = new Color(image.getRGB(0, 0));
		return color;
	}

	public static void BWImageT(ImageShape image) {
		ImageShape tragetImage = image;
		int width = tragetImage.getImage().getBImage().getWidth();
		int height = tragetImage.getImage().getBImage().getHeight();
		for(int i=0; i<height; i++) {
			for(int j=0; j<width; j++) {
				Color c = new Color(tragetImage.getImage().getBImage().getRGB(j, i));
				int red = (int)(c.getRed() * 0.299);
				int green = (int)(c.getGreen() * 0.587);
				int blue = (int)(c.getBlue() * 0.114);
				Color newColor = new Color(red+green+blue, red+green+blue, red+green+blue);
				tragetImage.getImage().getBImage().setRGB(j, i, newColor.getRGB());
			}
		}
		MainPanel.getComponentVc().get(MainPanel.getComponentVc().size()-1).setBasedShapeNoPoint(tragetImage);
	}
}
