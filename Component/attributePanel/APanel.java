package attributePanel;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;

import Tool.RedoUndo;
import attributeComponent.AButton;
import attributeComponent.ALabel;
import attributeComponent.ANullPanel;
import component.Components;
import constant.ColorConstant;
import constant.ViewConstant;
import function.FSetColor;
import serializable.SBasicStroke;
import serializable.SerializableAlphaComposite;
import view.MainPanel;

public abstract class APanel {
	protected JPanel panel;
	protected JTextField sValue;
	protected JScrollBar sScrollBar;
	protected JTextField cValue;
	protected JScrollBar cScrollBar;
	protected MouseHandler eventHandler;
	
	public APanel(JPanel panel,String name) {
		this.panel=panel;
		this.eventHandler = new MouseHandler();
		ALabel aLabel = new ALabel(name);
		ANullPanel aNullPanel = new ANullPanel();
		aNullPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		aNullPanel.add(aLabel);
		this.panel.add(aNullPanel);
	}
	protected void colorAtrribute(boolean isLineColor) {
		JPanel colorPanel = new JPanel();
		if(!isLineColor) {
			AButton button = new AButton("채우기 없음"); button.addMouseListener(this.eventHandler);
			colorPanel.add(button);
		}
		AButton button = new AButton("다른색"); button.setAttribute(isLineColor);
		button.addMouseListener(this.eventHandler); colorPanel.add(button);
		this.panel.add(colorPanel);
	}
	protected void colorSelect(String name, boolean isLineColor) {
		this.panel.add(new ALabel(name,20));
		ANullPanel jPanel = new ANullPanel();
		jPanel.setLayout(new GridLayout(3,6));
		for (int i = 0; i < ColorConstant.selectColor.length; i++) {
			AButton aButton = new AButton(ColorConstant.selectColor[i]);
			aButton.addMouseListener(this.eventHandler);
			aButton.setAttribute(isLineColor);
			jPanel.add(aButton);
		}
		this.panel.add(jPanel);
	}
	
	protected void strokeSelect() {
		ANullPanel jPanel = new ANullPanel();
		int width = ViewConstant.BasedStrokeWidth;
		sScrollBar = new JScrollBar(JScrollBar.HORIZONTAL, width, 10, 1, 60);
		sScrollBar.addMouseListener(eventHandler);
		sScrollBar.addMouseMotionListener(eventHandler);
		ALabel label = new ALabel("두께      ",20);
		jPanel.add(label);
		sValue = new JTextField(2);
		sValue.setMargin(new Insets(10, 30, 10, 30));
		sValue.addKeyListener(this.eventHandler);
		sValue.setText(Integer.toString(sScrollBar.getValue()));
		jPanel.add(sValue);

		this.panel.add(jPanel);
		this.panel.add(sScrollBar);
	}
	protected void compositeSelect() {
		ANullPanel jPanel = new ANullPanel();
		cScrollBar = new JScrollBar(JScrollBar.HORIZONTAL, ViewConstant.BasedCompositeDegree, 10, 1, 265);
		cScrollBar.addMouseListener(eventHandler);
		cScrollBar.addMouseMotionListener(eventHandler);
		ALabel label = new ALabel("투명도      ",17);
		jPanel.add(label);
		cValue = new JTextField(3);
		cValue.setMargin(new Insets(10, 27, 10, 27));
		cValue.addKeyListener(this.eventHandler);
		cValue.setText(Integer.toString(cScrollBar.getValue()));
		jPanel.add(cValue);

		this.panel.add(jPanel);
		this.panel.add(cScrollBar);
	}
	protected void setStrock(int value) {
		Components component = MainPanel.getComponentVc().get(MainPanel.getComponentVc().size()-1);
		component.setStroke(new SBasicStroke(value,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
		MainPanel.repaintMainPanel();
	}
	protected void setComposite(int value) {
		Components component = MainPanel.getComponentVc().get(MainPanel.getComponentVc().size()-1);
		SerializableAlphaComposite a = new SerializableAlphaComposite(AlphaComposite.SRC_OVER, (float)value/255);
		component.setComposite(a);
		MainPanel.repaintMainPanel();
	}
	
	public void mouseClickedEvent(MouseEvent e) {
		if(e.getSource() instanceof AButton) {
			AButton button = (AButton)e.getSource();
			for (int i = 0; i < ColorConstant.selectColor.length; i++) {
				if(button.getBackground()==ColorConstant.selectColor[i]) {
					if(button.getAttribute()) {FSetColor.setLineColor(ColorConstant.selectColor[i], MainPanel.getComponentVc().size()-1);}
					else {FSetColor.setFillColor(ColorConstant.selectColor[i], MainPanel.getComponentVc().size()-1);}
				}
			}
			if(button.getText().equals("채우기 없음")) {
				FSetColor.setFillColor(null, MainPanel.getComponentVc().size()-1);
			}else if(button.getText().equals("다른색")) {
				if(button.getAttribute()) {
					Color lineColor = JColorChooser.showDialog(MainPanel.getMainPanel(), "색 선택", Color.BLACK);
					FSetColor.setLineColor(lineColor, MainPanel.getComponentVc().size()-1);
				}else {
					Color fillColor = JColorChooser.showDialog(MainPanel.getMainPanel(), "색 선택", Color.BLACK);
					FSetColor.setFillColor(fillColor, MainPanel.getComponentVc().size()-1);
				}
			}
			RedoUndo.saveHistory();
		}
	}
	public void mouseDraggedEvent(MouseEvent e) {
		if(e.getSource() instanceof JScrollBar) {
			JScrollBar bar = (JScrollBar) e.getSource();
			if(bar.getMaximum()==60) {
				sValue.setText(Integer.toString(bar.getValue()));
				setStrock(bar.getValue());
			}else {
				cValue.setText(Integer.toString(bar.getValue()));
				setComposite(bar.getValue());
			}
		}
	}
	public void keyPressedEvent(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			JTextField textField = (JTextField) e.getSource();
			if(textField.getInsets().left==32) {
				int value = Integer.parseInt(sValue.getText());
				if(value>50) {value=50;sValue.setText("50");}else if(value<1) {value = 1;sValue.setText("1");}
				sScrollBar.setValue(value);
				setStrock(value);
			}else {
				int value = Integer.parseInt(cValue.getText());
				if(value>255) {value=255;cValue.setText("255");}else if(value<1) {value = 1;cValue.setText("1");}
				cScrollBar.setValue(value);
				setComposite(value);
			}
			RedoUndo.saveHistory();
		}
	}
	public void mouseReleasedEvent(MouseEvent e) {
		if(e.getSource() instanceof JScrollBar) {
			RedoUndo.saveHistory();
		}
	}
	protected class MouseHandler extends MouseAdapter implements KeyListener{
		public void mouseClicked(MouseEvent e) {
			mouseClickedEvent(e);
		}
		public void mouseDragged(MouseEvent e) {
			mouseDraggedEvent(e);
		}
		public void mouseReleased(MouseEvent e) {
			mouseReleasedEvent(e);
		}
		@Override
		public void keyPressed(KeyEvent e) {
			keyPressedEvent(e);
		}
		@Override public void keyTyped(KeyEvent e) {}
		@Override public void keyReleased(KeyEvent e) {}
	}
}
