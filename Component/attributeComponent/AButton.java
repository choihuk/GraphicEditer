package attributeComponent;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import constant.ViewConstant;

public class AButton extends JButton {
	private boolean isAddHandler;
	private boolean isLineColor;
	private static final long serialVersionUID = 1L;
	public AButton(ImageIcon imageIcon) {
		super(imageIcon);
		
		this.isAddHandler = false;
		this.setSize(50,50);
		this.setBorderPainted(false);
		this.setBackground(ViewConstant.APanelColor);
		this.setFocusPainted(false);
		this.setName(imageIcon.getDescription());
	}
	public AButton(Color color) {
		super(new ImageIcon("Image/nullImage.png"));
		this.setBackground(color);
		this.setFocusPainted(false);
	}
	public AButton(String name) {
		super(name);
		this.setFont(new Font("°íµñ",Font.PLAIN,12));
		this.setBackground(Color.WHITE);
		this.setFocusPainted(false);
	}
	public AButton(String name,int size) {
		super(name);
		Font font = new Font("°íµñ",Font.PLAIN,size);
		this.setFont(font);
		this.setBackground(ViewConstant.APanelColor);
		this.setFocusPainted(false);
	}
	
	public void setIsAddHandler(boolean is) {
		isAddHandler = is;
	}
	public boolean getIsAddHandler() {
		return isAddHandler;
	}
	public void setAttribute(boolean isLineColor) {
		this.isLineColor = isLineColor;
	}
	public boolean getAttribute() {
		return isLineColor;
	}
}
