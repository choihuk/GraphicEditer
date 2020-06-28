package attributePanel;

import javax.swing.JPanel;

import attributeComponent.ANullPanel;
import state.State;

public class AAttributeShapePanel extends APanel{


	public AAttributeShapePanel(JPanel panel) {
		super(panel, State.getShape().getShapeName());

		strokeSelect();
		compositeSelect();
		this.panel.add(new ANullPanel());
		colorSelect("ä���",false);
		colorAtrribute(false);
		colorSelect("�׵θ�",true);
		colorAtrribute(true);

		for (int i = 0; i < 5; i++) {
			this.panel.add(new ANullPanel());
		}
	}
}
