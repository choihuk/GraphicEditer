package serializable;

import java.awt.BasicStroke;
import java.awt.Shape;
import java.awt.Stroke;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SBasicStroke implements Stroke,Serializable{
	private static final long serialVersionUID = 1L;
	private transient BasicStroke stroke;
	public SBasicStroke(float width, int cap, int join) {
		resetBasicStroke(width,cap,join);
	}
	private void resetBasicStroke(float width, int cap, int join) {
		this.stroke = new BasicStroke(width, cap, join);
	}
	@Override
	public Shape createStrokedShape(Shape s) {
		return this.stroke.createStrokedShape(s);
	}
	private void writeObject(ObjectOutputStream s) throws IOException{
		s.defaultWriteObject();
		s.writeFloat(stroke.getLineWidth());
		s.writeInt(stroke.getEndCap());
		s.writeInt(stroke.getLineJoin());
	}

	private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException{
		s.defaultReadObject();
		resetBasicStroke(s.readFloat(), s.readInt(),s.readInt());
	}
}
