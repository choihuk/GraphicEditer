package attributePanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import Tool.ImageTool;
import Tool.RedoUndo;
import attributeComponent.ALabel;
import attributeComponent.ANullPanel;
import constant.Constant;
import constant.ViewConstant;
import shapes.ImageShape;
import view.MainPanel;
import constant.Constant.EAImageButton;

public class AImagePanel {
	public AImagePanel(JPanel panel) {
		ALabel aLabel = new ALabel("이미지 속성");
		ANullPanel aNullPanel = new ANullPanel();
		aNullPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		aNullPanel.add(aLabel);
		panel.add(aNullPanel);
		
		ANullPanel jPanel = new ANullPanel();
		jPanel.setLayout(new GridLayout(5,1));
		ImageMouseHandler handler = new ImageMouseHandler();
		for(Constant.EAImageButton imageButton:Constant.EAImageButton.values()) {
			if(!imageButton.getButton().getIsAddHandler()) {
				imageButton.getButton().addMouseListener(handler);
				imageButton.getButton().setIsAddHandler(true);
			}
			jPanel.add(imageButton.getButton());
		}
		panel.add(jPanel);
		for (int i = 0; i < 10; i++) {
			panel.add(new ANullPanel());
		}
	}
	private class ImageMouseHandler extends MouseAdapter{
		private boolean isMouseEntered = false;
		private ImageShape originImage;
		public void mouseEntered(MouseEvent e) {
			isMouseEntered = true;
			JButton button = (JButton) e.getSource();
			button.setBackground(new Color(229,230,231));
		}
		public void mouseExited(MouseEvent e) {
			isMouseEntered = false;
			JButton button = (JButton) e.getSource();
			button.setBackground(ViewConstant.APanelColor);
		}
		public void mouseReleased(MouseEvent e) {
			if(isMouseEntered) {
				JButton button = (JButton) e.getSource();
				ImageShape temp = (ImageShape) MainPanel.getComponentVc().get(MainPanel.getComponentVc().size()-1).getBasedShape();
				if(!temp.getIdentify()) {
					temp.setIdentify(true);
					originImage = temp;
				}
				if(button==EAImageButton.origin.getButton()) {
					MainPanel.getComponentVc().get(MainPanel.getComponentVc().size()-1).setBasedShapeNoPoint(originImage);
				}else if(button==EAImageButton.gray.getButton()) {
					ImageTool.BWImageT((ImageShape) originImage.clone());
				}
				RedoUndo.saveHistory();
				MainPanel.repaintMainPanel();
			}
		}
	}
}
