package view;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import attributeComponent.ANullPanel;
import constant.Constant;
import constant.ViewConstant;

public class AttributePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public enum AttributeState{
		shape,pen,attributeShape,selectShape,menu,image;
	}
	private static AttributeState state = AttributePanel.AttributeState.shape;
	
	private JPanel panel;
	
	public AttributePanel() {
		BorderLayout borderLayout = new BorderLayout();
		this.setLayout(borderLayout);
		
		this.panel = new JPanel();
		this.panel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		this.panel.setLayout(new BoxLayout(this.panel,BoxLayout.Y_AXIS));
		this.panel.setBackground(ViewConstant.APanelColor);
		
		for(Constant.EAPanel eAPanel:Constant.EAPanel.values()) {
			if(AttributePanel.state==eAPanel.getAttributeState()) {
				eAPanel.setPanel(AttributePanel.state,this.panel);
			}
		}
		
		this.add(this.panel,BorderLayout.CENTER);
		this.add(new ANullPanel(),BorderLayout.NORTH);
		this.add(new ANullPanel(),BorderLayout.EAST);
		this.add(new ANullPanel(),BorderLayout.WEST);
	}

	public static AttributePanel.AttributeState getAttributeState(){
		return state;
	}
	public static void setAttributePanel(AttributePanel.AttributeState state) {
		AttributePanel.state=state;
		MainFrame.mainFrame.repaintAttributePanel();
	}
}
