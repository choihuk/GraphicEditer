package attributePanel;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;

import Tool.RedoUndo;
import attributeComponent.AButton;
import attributeComponent.ANullPanel;
import constant.ColorConstant;
import constant.Constant;
import eventHandler.AMouseHandler;
import eventHandler.MainMouseHandler;
import state.State;
import view.MainPanel;

public class APenPanel extends APanel{
	public APenPanel(JPanel panel) {
		super(panel,"브러시");
		
		ANullPanel jPanel = new ANullPanel();
		jPanel.setLayout(new GridLayout(1,3));
		AMouseHandler handler = new AMouseHandler();
		for(Constant.EAPenButton penButton:Constant.EAPenButton.values()) {
			if(!penButton.getButton().getIsAddHandler()) {
				penButton.getButton().addMouseListener(handler);
				penButton.getButton().setIsAddHandler(true);
			}
			jPanel.add(penButton.getButton());
		}
		this.panel.add(jPanel);
		this.panel.add(new ANullPanel());
		
		this.colorSelect("색 선택", true);
		this.colorAtrribute(true);
		this.strokeSelect();
		this.compositeSelect();
		
		for (int i = 0; i < 7; i++) {
			this.panel.add(new ANullPanel());
		}
	}
	protected void colorAtrribute(boolean isLineColor) {
		JPanel colorPanel = new JPanel();
		if(!isLineColor) {
			AButton button = new AButton("채우기 없음"); button.addMouseListener(this.eventHandler);
			colorPanel.add(button);
		}
		AButton button = new AButton("다른색"); button.setAttribute(isLineColor);
		button.addMouseListener(this.eventHandler); colorPanel.add(button);
		button = new AButton("색추출"); button.setAttribute(isLineColor);
		button.addMouseListener(this.eventHandler); colorPanel.add(button);
		this.panel.add(colorPanel);
	}
	public void mouseClickedEvent(MouseEvent e) {
		if(e.getSource() instanceof AButton) {
			AButton button = (AButton)e.getSource();
			for (int i = 0; i < ColorConstant.selectColor.length; i++) {
				if(button.getBackground()==ColorConstant.selectColor[i]) {
					if(State.getPen()==Constant.EAPenButton.pen) {
						MainMouseHandler.setPenColor(ColorConstant.selectColor[i]);
					}else if(State.getPen()==Constant.EAPenButton.hPen) {
						MainMouseHandler.setHPenColor(ColorConstant.selectColor[i]);
					}
				}
			}
			if(button.getText().equals("다른색")) {
				if(button.getAttribute()) {
					Color lineColor = JColorChooser.showDialog(MainPanel.getMainPanel(), "색 선택", Color.BLACK);
					MainMouseHandler.setPenColor(lineColor);
				}else {
					Color fillColor = JColorChooser.showDialog(MainPanel.getMainPanel(), "색 선택", Color.BLACK);
					MainMouseHandler.setHPenColor(fillColor);
				}
			}else if(button.getText().equals("색추출")) {
				MainPanel.getMainPanel().setCursor(Constant.ECursor.eColor.getCursor());
			}
			RedoUndo.saveHistory();
		}
	}
	public void mouseDraggedEvent(MouseEvent e) {
		if(e.getSource() instanceof JScrollBar) {
			JScrollBar bar = (JScrollBar) e.getSource();
			if(bar.getMaximum()==60) {
				sValue.setText(Integer.toString(bar.getValue()));
				MainMouseHandler.setStrokeWidth(bar.getValue());
			}else {
				cValue.setText(Integer.toString(bar.getValue()));
				MainMouseHandler.setCompositeDegree(bar.getValue());
			}
		}
	}
	public void mouseReleasedEvent(MouseEvent e) {
		if(e.getSource() instanceof JScrollBar) {
			RedoUndo.saveHistory();
		}
	}
	public void keyPressedEvent(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			JTextField textField = (JTextField) e.getSource();
			if(textField.getInsets().left==32) {
				int value = Integer.parseInt(sValue.getText());
				if(value>50) {value=50;sValue.setText("50");}else if(value<1) {value = 1;sValue.setText("1");}
				sScrollBar.setValue(value);
				MainMouseHandler.setStrokeWidth(value);
			}else {
				int value = Integer.parseInt(cValue.getText());
				if(value>255) {value=255;cValue.setText("255");}else if(value<1) {value = 1;cValue.setText("1");}
				cScrollBar.setValue(value);
				MainMouseHandler.setCompositeDegree(value);
			}
			RedoUndo.saveHistory();
		}
	}
}
