package Tool;

import java.util.Vector;

import component.Components;

public class CopyPaste {

	private static Vector<Components> storagy = new Vector<Components>();

	public static void copy(Vector<Components> storagy) {
		if(!CopyPaste.storagy.isEmpty()) {CopyPaste.storagy.clear();}
		for(Components component:storagy) {
			CopyPaste.storagy.add((Components)DeepClone.clone(component));
		}
	}
	public static Vector<Components> paste() {
		return storagy;
	}
}
