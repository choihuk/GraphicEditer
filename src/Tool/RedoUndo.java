package Tool;

import java.util.Vector;

import component.Components;
import view.MainPanel;

public class RedoUndo {


	private static Vector<Vector<Components>> historyVc = new Vector<Vector<Components>>();
	private static int historyNum = 0;
	private static final int maxNumSize = 20;

	// ���� draw / �Ӽ� ���� / �̹��� ���� / �̹��� ��麯�� / �Ǿ�,�ǵ� �̵� / �׷�,��׷� / ctrl_X
	public static void redo() {//�ǵ�����
		if(historyNum>0&&historyVc.size()!=1) {
			if(historyNum==historyVc.size()) {historyNum--;}
			MainPanel.getComponentVc().clear();
			addComponents(historyVc.get(--historyNum));
			MainPanel.repaintMainPanel();
		}
	}
	public static void undo() {//������ ����
		if(historyVc.size()-1>historyNum) {
			MainPanel.getComponentVc().clear();
			addComponents(historyVc.get(++historyNum));
			MainPanel.repaintMainPanel();
		}
	}
	@SuppressWarnings("unchecked")
	public static void saveHistory() {
		if(historyVc.size()>maxNumSize) {historyVc.remove(0);}
		if(historyVc.size()!=historyNum) {
			for(int i = historyVc.size();i>historyNum;i--) {
				historyVc.remove(i-1);
			}
		}
		historyVc.add((Vector<Components>)DeepClone.clone(MainPanel.getComponentVc()));
		historyNum = historyVc.size();
	}
	private static void addComponents(Vector<Components> addVc) {
		for(Components component : addVc) {
			MainPanel.getComponentVc().add(component);
		}
	}
}
