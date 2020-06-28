package attributePanel;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;

import Tool.GroupShapeTool;
import Tool.RedoUndo;
import Tool.ShapeLayer;
import attributeComponent.AButton;
import attributeComponent.ANullPanel;
import component.Components;
import constant.ColorConstant;
import function.FSetColor;
import serializable.SBasicStroke;
import serializable.SerializableAlphaComposite;
import shapes.GroupShape;
import view.MainPanel;

public class ASelectShapePanel extends APanel{
	
	public ASelectShapePanel(JPanel panel) {
		super(panel, "선택");

		strokeSelect();
		compositeSelect();
		this.panel.add(new ANullPanel());
		colorSelect("채우기",false);
		this.colorAtrribute(false);
		colorSelect("테두리",true);
		this.colorAtrribute(true);
		
		JPanel jPanel = new JPanel();
		AButton frontB = new AButton("맨앞 이동", 20); frontB.addMouseListener(this.eventHandler);
		AButton bottomB = new AButton("맨뒤 이동", 20); bottomB.addMouseListener(this.eventHandler);
		jPanel.add(frontB); jPanel.add(bottomB);
		this.panel.add(jPanel);
		
		JPanel groupPanel = new JPanel();
		AButton groupB = new AButton("Group", 20); groupB.addMouseListener(this.eventHandler);
		AButton ungroupB = new AButton("UnGroup", 20); ungroupB.addMouseListener(this.eventHandler);
		groupPanel.add(groupB); groupPanel.add(ungroupB);
		this.panel.add(groupPanel);
		
		for (int i = 0; i < 5; i++) {
			this.panel.add(new ANullPanel());
		}
	}
	
	protected void setStrock(int value) {
		for(Components component: MainPanel.getComponentVc()) {
			if(component.isSelected()) {
				if(component.getBasedShape() instanceof GroupShape) { 
					GroupShape groupShape = (GroupShape)component.getBasedShape();
					for(Components groupC: groupShape.getGroupShape()) {
						groupC.setStroke(new SBasicStroke(value,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
					}
				}else {component.setStroke(new SBasicStroke(value,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));}
			}
		}
		MainPanel.repaintMainPanel();
	}
	protected void setComposite(int value) {
		for(Components component: MainPanel.getComponentVc()) {
			if(component.isSelected()) {
				SerializableAlphaComposite a = new SerializableAlphaComposite(AlphaComposite.SRC_OVER, (float)value/255);
				if(component.getBasedShape() instanceof GroupShape) { 
					GroupShape groupShape = (GroupShape)component.getBasedShape();
					for(Components groupC: groupShape.getGroupShape()) {
						groupC.setComposite(a);
					}
				}else {component.setComposite(a);}
			}
		}
		MainPanel.repaintMainPanel();
	}
	public void mouseClickedEvent(MouseEvent e) {
		// 오우 더럽다 나중에 고쳐야지
		if(e.getSource() instanceof AButton) {
			AButton button = (AButton)e.getSource();
			for (int i = 0; i < ColorConstant.selectColor.length; i++) {
				if(button.getBackground()==ColorConstant.selectColor[i]) {
					if(button.getAttribute()) {
						for(int j=0;j<MainPanel.getComponentVc().size();j++) {
							if(MainPanel.getComponentVc().get(j).isSelected()) {
								if(MainPanel.getComponentVc().get(j).getBasedShape() instanceof GroupShape) { 
									GroupShape groupShape = (GroupShape)MainPanel.getComponentVc().get(j).getBasedShape();
									for(Components component: groupShape.getGroupShape()) {
										FSetColor.setLineColor(ColorConstant.selectColor[i],component);
									}
								}else {FSetColor.setLineColor(ColorConstant.selectColor[i],j);}
							}
						}
					}else {
						for(int j=0;j<MainPanel.getComponentVc().size();j++) {
							if(MainPanel.getComponentVc().get(j).isSelected()) {
								if(MainPanel.getComponentVc().get(j).getBasedShape() instanceof GroupShape) {
									GroupShape groupShape = (GroupShape)MainPanel.getComponentVc().get(j).getBasedShape();
									for(Components component: groupShape.getGroupShape()) {
										FSetColor.setFillColor(ColorConstant.selectColor[i],component);
									}
								}else {FSetColor.setFillColor(ColorConstant.selectColor[i],j);}
							}
						}
					}
				}
			}
			if(button.getText().equals("채우기 없음")) {
				for(int j=0;j<MainPanel.getComponentVc().size();j++) {
					if(MainPanel.getComponentVc().get(j).isSelected()) {FSetColor.setFillColor(null,j);}
				}
			}else if(button.getText().equals("맨뒤 이동")) {
				ShapeLayer.moveBottom();
			}else if(button.getText().equals("맨앞 이동")) {
				ShapeLayer.moveFront();
			}else if(button.getText().equals("Group")) {
				GroupShapeTool.group();
			}else if(button.getText().equals("UnGroup")) {
				GroupShapeTool.unGroup();
			}else if(button.getText().equals("다른색")) {
				if(button.getAttribute()) {
					Color lineColor = JColorChooser.showDialog(MainPanel.getMainPanel(), "색 선택", Color.BLACK);
					for(int j=0;j<MainPanel.getComponentVc().size();j++) {
						if(MainPanel.getComponentVc().get(j).isSelected()) {
							if(MainPanel.getComponentVc().get(j).getBasedShape() instanceof GroupShape) { 
								GroupShape groupShape = (GroupShape)MainPanel.getComponentVc().get(j).getBasedShape();
								for(Components component: groupShape.getGroupShape()) {
									FSetColor.setLineColor(lineColor,component);
								}
							}else {FSetColor.setLineColor(lineColor,j);}
						}
					}
				}else {
					Color fillColor = JColorChooser.showDialog(MainPanel.getMainPanel(), "색 선택", Color.BLACK);
					for(int j=0;j<MainPanel.getComponentVc().size();j++) {
						if(MainPanel.getComponentVc().get(j).isSelected()) {
							if(MainPanel.getComponentVc().get(j).getBasedShape() instanceof GroupShape) {
								GroupShape groupShape = (GroupShape)MainPanel.getComponentVc().get(j).getBasedShape();
								for(Components component: groupShape.getGroupShape()) {
									FSetColor.setFillColor(fillColor,component);
								}
							}else {FSetColor.setFillColor(fillColor,j);}
						}
					}
				}
			}
		}
		RedoUndo.saveHistory();
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
	
}
