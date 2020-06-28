package state;

import constant.Constant;
import constant.Constant.EAPenButton;

public class State {
	private static Constant.EAShapeButton shape = Constant.EAShapeButton.line;
	private static Constant.EAPenButton pen = Constant.EAPenButton.pen;
	public static boolean ctrlIsOn = false;
	
	public static Constant.EAShapeButton getShape() {
		return shape;
	}
	public static void setShape(Constant.EAShapeButton shape) {
		State.shape = shape;
	}
	public static Constant.EAPenButton getPen() {
		return pen;
	}
	public static void setPen(EAPenButton pen) {
		State.pen = pen;
	}
	
}
