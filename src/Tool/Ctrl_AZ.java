package Tool;

import component.Components;
import view.AttributePanel;
import view.MainPanel;

public class Ctrl_AZ {
	public static void control_A() {
		if(isSelectedAt())
			for (Components component : MainPanel.getComponentVc()) {component.setSelected(true);}
	}
	public static void control_X() {
		RedoUndo.saveHistory();
		if(isSelectedAt()) {
			int size = MainPanel.getComponentVc().size();
			for (int i = size-1; i >= 0; i--) {
				if(MainPanel.getComponentVc().get(i).isSelected()) {MainPanel.getComponentVc().remove(i);}
			}
		}
	}
	private static boolean isSelectedAt() {
		if(AttributePanel.getAttributeState()==AttributePanel.AttributeState.selectShape) {return true;}
		else {return false;}
	}
}
