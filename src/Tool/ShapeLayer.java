package Tool;

import java.util.Vector;

import component.Components;
import view.MainPanel;

public class ShapeLayer {
	
	public static void moveBottom() {
		Vector<Components> unSortedVc = MainPanel.getComponentVc();
		Vector<Integer> intVc = new Vector<Integer>();
		for(int i=0;i<unSortedVc.size();i++){ 
			if(unSortedVc.get(i).isSelected()) {intVc.add(i);}
		}
		Vector<Components> sortedVc = new Vector<Components>();
		for(int i=0;i<intVc.size();i++){ 
			sortedVc.add(unSortedVc.get(intVc.get(i)));
		}
		for(int i=0;i<unSortedVc.size();i++){ 
			if(!intVc.contains(i)) {
				sortedVc.add(unSortedVc.get(i));
			}
		}
		MainPanel.getComponentVc().clear();
		for(Components component: sortedVc) {
			MainPanel.getComponentVc().add(component);
		}
		MainPanel.repaintMainPanel();
	}
	public static void moveFront() {
		Vector<Components> unSortedVc = MainPanel.getComponentVc();
		Vector<Integer> intVc = new Vector<Integer>();
		for(int i=0;i<unSortedVc.size();i++){ 
			if(unSortedVc.get(i).isSelected()) {intVc.add(i);}
		}
		Vector<Components> sortedVc = new Vector<Components>();
		for(int i=0;i<unSortedVc.size();i++){ 
			if(!intVc.contains(i)) {
				sortedVc.add(unSortedVc.get(i));
			}
		}
		for(int i=0;i<intVc.size();i++){ 
			sortedVc.add(unSortedVc.get(intVc.get(i)));
		}
		MainPanel.getComponentVc().clear();
		for(Components component: sortedVc) {
			MainPanel.getComponentVc().add(component);
		}
		MainPanel.repaintMainPanel();
	}
}
