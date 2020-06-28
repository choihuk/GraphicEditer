package eventHandler;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import constant.Constant;
import constant.ViewConstant;
import view.AttributePanel;

public class MenuMouseHandler implements MouseListener{
	private boolean isMouseEntered = false;
	@Override
	public void mouseEntered(MouseEvent e) {
		this.isMouseEntered = true;
		JButton button = (JButton) e.getSource();
		if(Constant.EMenuButton.x.getButton().getName().equals(button.getName())) {
			button.setBackground(Color.RED);
		}else if(button.isSelected()){
			button.setBackground(new Color(69,119,255));
		}else {
			button.setBackground(new Color(195,195,195));
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		JButton button = (JButton) e.getSource();
		if(Constant.EMenuButton.x.getButton().getName().equals(button.getName())) {
			button.setBackground(Color.RED);
		}else {
			button.setBackground(new Color(69,119,255));
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		JButton button = (JButton) e.getSource();
		if(this.isMouseEntered) {
			button.setSelected(true);
			for(Constant.EMenuButton menuPanel:Constant.EMenuButton.values()) {
				if(menuPanel.getButton()!=button) {
					menuPanel.getButton().setSelected(false);
				}
			}
			if(Constant.EMenuButton.x.getButton().getName().equals(button.getName())) {
				System.exit(0);
			}else if(Constant.EMenuButton.shape.getButton().getName().equals(button.getName())){
				AttributePanel.setAttributePanel(AttributePanel.AttributeState.shape);
			}else if(Constant.EMenuButton.pen.getButton().getName().equals(button.getName())) {
				AttributePanel.setAttributePanel(AttributePanel.AttributeState.pen);
			}else if(Constant.EMenuButton.selectShape.getButton().getName().equals(button.getName())) {
				AttributePanel.setAttributePanel(AttributePanel.AttributeState.selectShape);
			}else if(Constant.EMenuButton.menu.getButton().getName().equals(button.getName())) {
				AttributePanel.setAttributePanel(AttributePanel.AttributeState.menu);
			}
		}
		for(Constant.EMenuButton menuPanel:Constant.EMenuButton.values()) {
			if(menuPanel.getButton()!=button) {
				menuPanel.getButton().setBackground(ViewConstant.MenuPanelColor);
			}
		}
	}
	@Override
	public void mouseExited(MouseEvent e) {
		this.isMouseEntered = false;
		JButton button = (JButton) e.getSource();
		if(button.isSelected()) {
			button.setBackground(new Color(69,119,255));
		}else {
			button.setBackground(ViewConstant.MenuPanelColor);
		}
	}
}
