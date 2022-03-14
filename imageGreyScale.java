package ProiectGreyScale;

import java.awt.image.BufferedImage;

public abstract class imageGreyScale extends Image{
	protected abstract void grayImage();
	protected abstract void setWidth(int w);
	protected abstract void setHeight(int h);
	static BufferedImage img;
}
