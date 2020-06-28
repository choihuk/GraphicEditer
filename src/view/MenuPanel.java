package view;

import java.awt.GridLayout;

import javax.swing.JPanel;

import constant.Constant;
import constant.ViewConstant;
import eventHandler.MenuMouseHandler;

public class MenuPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	public MenuPanel() {
		// set attribute
		this.setBackground(ViewConstant.MenuPanelColor);
		this.setLayout(new GridLayout(2,30));

		// Component
		MenuMouseHandler handler = new MenuMouseHandler();
		for(Constant.EMenuButton menuPanel : Constant.EMenuButton.values()) {
			menuPanel.getButton().addMouseListener(handler);
		}

		for (int i = 0; i < 25; i++) {
			JPanel jPanel = new JPanel();
			jPanel.setBackground(ViewConstant.MenuPanelColor);
			this.add(jPanel);
		}
		this.add(Constant.EMenuButton.x.getButton());
		this.add(Constant.EMenuButton.menu.getButton());
		for (int i = 0; i < 22; i++) {
			JPanel jPanel = new JPanel();
			jPanel.setBackground(ViewConstant.MenuPanelColor);
			this.add(jPanel);
		}
		this.add(Constant.EMenuButton.shape.getButton());
		this.add(Constant.EMenuButton.pen.getButton());
		this.add(Constant.EMenuButton.selectShape.getButton());
	}




}
