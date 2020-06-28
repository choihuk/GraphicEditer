package attributeComponent;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class ALabel extends JLabel{
	private static final long serialVersionUID = 1L;

	public ALabel(String name) {
		super(name);
		this.setFont(new Font("°íµñ",Font.PLAIN,25));
		this.setForeground(new Color(0,100,182));
	}
	public ALabel(String name, int size) {
		super(name);
		this.setFont(new Font("°íµñ",Font.BOLD,size));
//		this.setForeground(new Color(0,100,182));
	}
}
