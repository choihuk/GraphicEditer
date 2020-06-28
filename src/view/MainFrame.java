package view;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JFrame;

import constant.ViewConstant;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	// Component
	private MainPanel mainPanel;
	private AttributePanel attributePanel;
	private MenuPanel menuPanel;
	public static MainFrame mainFrame;
	
	// Constructor
	public MainFrame() {
		// Set Attribute
		this.setTitle(ViewConstant.FrameTitle);
		this.setSize(ViewConstant.FrameWidth, ViewConstant.FrameHeight);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		
		// Create Component
		MainFrame.mainFrame = this;
		this.mainPanel = new MainPanel();
		this.add(this.mainPanel,BorderLayout.CENTER);
		
		this.menuPanel = new MenuPanel();
		this.add(this.menuPanel,BorderLayout.NORTH);
		
		paintAttributePanel();
		
		this.setVisible(true);
	}
	public void paintAttributePanel() {
		this.attributePanel = new AttributePanel();
		this.add(this.attributePanel,BorderLayout.EAST);
	}
	public void repaintAttributePanel() {
		MainFrame.mainFrame.getContentPane().remove(this.attributePanel);
		paintAttributePanel();
		this.attributePanel.updateUI();
		this.mainPanel.updateUI();
	}
}
