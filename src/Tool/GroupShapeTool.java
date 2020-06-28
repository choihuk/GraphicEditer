package Tool;

import java.util.Vector;

import component.Components;
import shapes.GroupShape;
import view.MainPanel;

public class GroupShapeTool {

	public static void group() {
		Vector<Components> groupMember = new Vector<Components>();
		Components groupComponent = new Components(null,null,null);
		GroupShape groupShape = new GroupShape();
		
		for(Components component: MainPanel.getComponentVc()) {
			if(component.isSelected()) {
				groupMember.add(component);
			}
		}
		if(!groupMember.isEmpty()) {
			Ctrl_AZ.control_X();
			groupShape.addGroupVc(groupMember);
			groupComponent.setBasedShapeNoPoint(groupShape);
			groupComponent.setSelected(true);
			MainPanel.getComponentVc().add(groupComponent);
			MainPanel.repaintMainPanel();
		}
	}
	public static void unGroup() {
		Vector<GroupShape> groupShapeVc = new Vector<GroupShape>();
		for(int i = MainPanel.getComponentVc().size()-1;i>=0;i--) {
			if(MainPanel.getComponentVc().get(i).isSelected()) {
				if(MainPanel.getComponentVc().get(i).getBasedShape() instanceof GroupShape) {
					groupShapeVc.add((GroupShape)MainPanel.getComponentVc().get(i).getBasedShape());
					MainPanel.getComponentVc().remove(i);
				}
			}
		}
		if(!groupShapeVc.isEmpty()) {
			for(GroupShape groupShape: groupShapeVc) {
				for(Components component: groupShape.getGroupShape()) {
					MainPanel.getComponentVc().add(component);
				}
			}
			MainPanel.repaintMainPanel();
		}
	}
}
