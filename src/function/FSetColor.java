package function;

import java.awt.Color;

import component.Components;
import view.MainPanel;

public class FSetColor {
	public static void setLineColor(Color color,int index){
		Components component = MainPanel.getComponentVc().get(index);
		component.setLineColor(color);
		MainPanel.repaintMainPanel();
	}
	public static void setFillColor(Color color,int index){
		Components component = MainPanel.getComponentVc().get(index);
		component.setFillColor(color);
		MainPanel.repaintMainPanel();
	}
	public static void setLineColor(Color color, Components component) {
		component.setLineColor(color);
		MainPanel.repaintMainPanel();
	}
	public static void setFillColor(Color color, Components component) {
		component.setFillColor(color);
		MainPanel.repaintMainPanel();
	}
}
