package menuComponent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import constant.ViewConstant;

public class MenuButton extends JButton {
	private static final long serialVersionUID = 1L;

	public MenuButton(ImageIcon imageIcon) {
		super(imageIcon);
		
		this.setBorderPainted(false);
		this.setBackground(ViewConstant.MenuPanelColor);
		this.setFocusPainted(false);
		this.setName(imageIcon.getDescription());
	}

}
