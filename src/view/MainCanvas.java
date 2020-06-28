package view;

import java.awt.Color;
import java.awt.Graphics2D;

import constant.ViewConstant;

public class MainCanvas {
	private static int width = 1000, height = 600;
	private static int screenWidth = ViewConstant.screenWidth;
	private static int screenHeight = ViewConstant.screenHeight;
	
	
	public static void drawCanvas(Graphics2D g2d) {
		Color currentColor = g2d.getColor();
		g2d.setColor(Color.WHITE);
		g2d.fillRect((screenWidth-width)/2,(screenHeight-height)/2,width,height);
		g2d.setClip((screenWidth-width)/2,(screenHeight-height)/2,width,height);
		g2d.setColor(currentColor);
	}
	public static int getWidth() {
		return width;
	}
	public static int getHeight() {
		return height;
	}
	public static void setCanvas(int width, int height) {
		MainCanvas.width = width;
		MainCanvas.height = height;
		MainPanel.repaintMainPanel();
	}
}
