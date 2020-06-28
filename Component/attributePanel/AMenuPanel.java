package attributePanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Tool.ImageTool;
import Tool.Save;
import attributeComponent.ALabel;
import attributeComponent.ANullPanel;
import constant.Constant;
import constant.Constant.EAMenuButton;
import constant.ViewConstant;
import view.MainCanvas;
import view.MainPanel;

public class AMenuPanel {
	private ALabel label;
	private JTextField canvasW;
	private JTextField canvasH;
	public AMenuPanel(JPanel panel) {
		ALabel aLabel = new ALabel("메뉴");
		ANullPanel aNullPanel = new ANullPanel();
		aNullPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		aNullPanel.add(aLabel);
		panel.add(aNullPanel);
		
		ANullPanel jPanel = new ANullPanel();
		jPanel.setLayout(new GridLayout(6,1));
		MenuMouseHandler handler = new MenuMouseHandler();
		for(Constant.EAMenuButton menuButton:Constant.EAMenuButton.values()) {
			if(!menuButton.getButton().getIsAddHandler()) {
				menuButton.getButton().addMouseListener(handler);
				menuButton.getButton().setIsAddHandler(true);
			}
			jPanel.add(menuButton.getButton());
		}
		panel.add(jPanel);
		
		this.label = new ALabel("Canvas Size",20);
		JPanel textPanel = new JPanel();
		this.canvasW = new JTextField(8); this.canvasW.addActionListener(handler);
		this.canvasH = new JTextField(8); this.canvasH.addActionListener(handler);
		this.canvasW.setText(Integer.toString(MainCanvas.getWidth()));
		this.canvasH.setText(Integer.toString(MainCanvas.getHeight()));
		textPanel.add(this.canvasW);
		textPanel.add(this.canvasH);
		panel.add(label);
		panel.add(textPanel);
		
		for (int i = 0; i < 10; i++) {
			panel.add(new ANullPanel());
		}
	}
	private class MenuMouseHandler extends MouseAdapter implements ActionListener{
		private boolean isMouseEntered = false;
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
				if(button==EAMenuButton.newMenu.getButton()) {
					if(!MainPanel.getComponentVc().isEmpty()) {
						int a = JOptionPane.showConfirmDialog(MainPanel.getMainPanel(), "저장하지 않고 새로 만드시겠습니까?","메시지",JOptionPane.YES_NO_OPTION);
						if(a==JOptionPane.NO_OPTION) {
							Save.save();
						}else if(a==JOptionPane.YES_OPTION){
							MainPanel.getComponentVc().clear();
						}
					}
				}else if(button==EAMenuButton.openMenu.getButton()) {
					Save.openFile();
				}else if(button==EAMenuButton.insertMenu.getButton()) {
					ImageTool.getImage();
				}else if(button==EAMenuButton.saveMenu.getButton()) {
					Save.save();
				}else if(button==EAMenuButton.saveAsMenu.getButton()) {
					Save.saveAs();
				}else if(button==EAMenuButton.insertImage.getButton()) {
					ImageTool.insertImage();
				}
			}
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(canvasW.getText()!=null&&canvasH.getText()!=null) {
				int width = Integer.parseInt(canvasW.getText());
				int height = Integer.parseInt(canvasH.getText());
				MainCanvas.setCanvas(width,height);
			}
		}
	}

}
