package serializable;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.CompositeContext;
import java.awt.RenderingHints;
import java.awt.image.ColorModel;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializableAlphaComposite implements Composite, Serializable{
	private static final long serialVersionUID = 1L;
	private transient AlphaComposite delegate;
	public SerializableAlphaComposite(int rule, float alpha){
		resetAlphaComposite(rule, alpha);
	}
	private void resetAlphaComposite(int rule, float alpha){
		this.delegate = AlphaComposite.getInstance(rule, alpha);
	}
	@Override public CompositeContext createContext(ColorModel srcColorModel, ColorModel dstColorModel, RenderingHints hints){
		return delegate.createContext(srcColorModel, dstColorModel, hints);
	}
	@Override public int hashCode(){
		return delegate.hashCode();
	}
	@Override public boolean equals(Object obj){
		if(obj == this) return true;
		if(!(obj instanceof SerializableAlphaComposite)) return false;

		SerializableAlphaComposite ac = (SerializableAlphaComposite)obj;
		if(delegate.getRule() != ac.delegate.getRule()) return false;
		if(delegate.getAlpha() != ac.delegate.getAlpha()) return false;
		return true;
	}
	private void writeObject(ObjectOutputStream s) throws IOException{
		s.defaultWriteObject();
		s.writeInt(delegate.getRule());
		s.writeFloat(delegate.getAlpha());
	}

	private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException{
		s.defaultReadObject();
		int rule = s.readInt();
		float alpha = s.readFloat();
		resetAlphaComposite(rule, alpha);
	}
}
