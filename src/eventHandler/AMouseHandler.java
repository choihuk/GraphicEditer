package eventHandler;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import affineTransformTool.ShapeRotate;
import constant.Constant;
import constant.Constant.EASelectButton;
import constant.ViewConstant;
import state.State;
import view.MainPanel;

public class AMouseHandler implements MouseListener{
	private boolean isMouseEntered = false;
	public AMouseHandler() {
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		this.isMouseEntered = true;
		JButton button = (JButton) e.getSource();
		if(button.isSelected()) {
			button.setBackground(new Color(69,119,255));
		}else {
			button.setBackground(new Color(230,230,230));
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		JButton button = (JButton) e.getSource();
		button.setBackground(new Color(69,119,255));
	}
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.isMouseEntered = false;
		JButton button = (JButton) e.getSource();
		if(button.isSelected()) {
			button.setBackground(new Color(69,119,255));
		}else {
			button.setBackground(ViewConstant.APanelColor);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		JButton button = (JButton) e.getSource();
		if(this.isMouseEntered) {
			button.setSelected(true);
			for(Constant.EAShapeButton shapeButton:Constant.EAShapeButton.values()) {
				if(shapeButton.getButton()!=button) {
					shapeButton.getButton().setSelected(false);
					shapeButton.getButton().setBackground(ViewConstant.APanelColor);
				}else {
					State.setShape(shapeButton);
				}
			}
			for(Constant.EAPenButton penButton:Constant.EAPenButton.values()) {
				if(penButton.getButton()!=button) {
					penButton.getButton().setSelected(false);
					penButton.getButton().setBackground(ViewConstant.APanelColor);
				}else {
					State.setPen(penButton);
				}
			}
			ifSelectButton(button);
		}
		for(Constant.EAShapeButton shapeButton:Constant.EAShapeButton.values()) {
			if(shapeButton.getButton()!=button) {
				shapeButton.getButton().setBackground(ViewConstant.APanelColor);
			}
		}
		for(Constant.EAPenButton penButton:Constant.EAPenButton.values()) {
			if(penButton.getButton()!=button) {
				penButton.getButton().setBackground(ViewConstant.APanelColor);
			}
		}
		for(Constant.EASelectButton sButton:Constant.EASelectButton.values()) {
			if(sButton.getButton()!=button) {
				sButton.getButton().setBackground(ViewConstant.APanelColor);
			}
		}
	}
	private void ifSelectButton(JButton button) {
		if(button==EASelectButton.leftR.getButton()) {
			ShapeRotate.buttomRotate(90);
		}else if(button==EASelectButton.R.getButton()) {
			ShapeRotate.buttomRotate(180);
		}else if(button==EASelectButton.rightR.getButton()) {
			ShapeRotate.buttomRotate(-90);
		}
		for(Constant.EASelectButton sButton:Constant.EASelectButton.values()) {
			if(sButton.getButton()!=button) {
				sButton.getButton().setSelected(false);
				sButton.getButton().setBackground(ViewConstant.APanelColor);
			}
		}
		MainPanel.repaintMainPanel();
	}
}
