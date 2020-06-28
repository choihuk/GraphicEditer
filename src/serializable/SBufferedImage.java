package serializable;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.imageio.ImageIO;

public class SBufferedImage implements Serializable{
	private static final long serialVersionUID = 1L;
	private transient BufferedImage image;
	public SBufferedImage(BufferedImage image) {
		resetBufferedImage(image);
	}
	public BufferedImage getBImage() {
		return image;
	}
	private void resetBufferedImage(BufferedImage image) {
		this.image = image;
	}
	private void writeObject(ObjectOutputStream s) throws IOException{
		ImageIO.write(image, "png", s);
	}

	private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException{
		resetBufferedImage(ImageIO.read(s));
	}
}
