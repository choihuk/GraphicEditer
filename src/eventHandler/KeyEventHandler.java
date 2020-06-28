package eventHandler;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;
import java.util.Vector;

import Tool.CopyPaste;
import Tool.Ctrl_AZ;
import Tool.RedoUndo;
import Tool.Save;
import affineTransformTool.WindowAT;
import component.Components;
import state.State;
import view.MainPanel;

public class KeyEventHandler implements KeyEventDispatcher{

	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		if(e.getID()==KeyEvent.KEY_PRESSED) {
			keyPressedEvent(e);
		}else if(e.getID()==KeyEvent.KEY_RELEASED) {
			keyReleasedEvent(e);
		}
		return false;
	}

	private void keyPressedEvent(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_CONTROL) {State.ctrlIsOn = true;}
		if(State.ctrlIsOn) {
			if(e.getKeyCode()==KeyEvent.VK_A ) {Ctrl_AZ.control_A();}
			if(e.getKeyCode()==KeyEvent.VK_X ) {Ctrl_AZ.control_X();}
			if(e.getKeyCode()==KeyEvent.VK_C ) {copy();}
			if(e.getKeyCode()==KeyEvent.VK_V ) {paste();}
			if(e.getKeyCode()==KeyEvent.VK_S ) {Save.save();}
			if(e.getKeyCode()==KeyEvent.VK_Y ) {RedoUndo.undo();}
			if(e.getKeyCode()==KeyEvent.VK_Z ) {RedoUndo.redo();}
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {MainPanel.getMainAt().moveCameraKey(e.getKeyCode());}
		else if(e.getKeyCode()==KeyEvent.VK_LEFT) {MainPanel.getMainAt().moveCameraKey(e.getKeyCode());}
		else if(e.getKeyCode()==KeyEvent.VK_UP) {MainPanel.getMainAt().moveCameraKey(e.getKeyCode());}
		else if(e.getKeyCode()==KeyEvent.VK_DOWN) {MainPanel.getMainAt().moveCameraKey(e.getKeyCode());}
		
		MainPanel.repaintMainPanel();
	}

	private void copy() {
		 Vector<Components> storagy = new Vector<Components>();
		for(Components component: MainPanel.getComponentVc()) {
			if(component.isSelected()) {
				storagy.add(component);
			}
		}
		if(!storagy.isEmpty()) {CopyPaste.copy(storagy);}
	}

	private void paste() {
		for(Components component: CopyPaste.paste()) {
			MainPanel.getComponentVc().add(component);
		}
		MainPanel.repaintMainPanel();
	}

	private void keyReleasedEvent(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_CONTROL) {State.ctrlIsOn = false;}
	}

}
