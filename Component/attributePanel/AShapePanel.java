package attributePanel;

import java.awt.GridLayout;

import javax.swing.JPanel;

import attributeComponent.ANullPanel;
import constant.Constant;
import eventHandler.AMouseHandler;

public class AShapePanel extends APanel{
	
	public AShapePanel(JPanel panel) {
		super(panel,"2D ºŒ¿Ã«¡");
		ANullPanel jPanel = new ANullPanel();
		jPanel.setLayout(new GridLayout(3,3));
		
		AMouseHandler handler = new AMouseHandler();
		for(Constant.EAShapeButton shapeButton:Constant.EAShapeButton.values()) {
			if(!shapeButton.getButton().getIsAddHandler()) {
				shapeButton.getButton().addMouseListener(handler);
				shapeButton.getButton().setIsAddHandler(true);
			}
			jPanel.add(shapeButton.getButton());
		}
		this.panel.add(jPanel);
		for (int i = 0; i < 5; i++) {
			this.panel.add(new ANullPanel());
		}
	}

}
