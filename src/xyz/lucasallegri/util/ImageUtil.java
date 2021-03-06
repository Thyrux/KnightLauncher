package xyz.lucasallegri.util;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

import xyz.lucasallegri.logging.KnightLog;

public class ImageUtil {
	
	public static Image getImageFromURL(String url, int width, int height) {
		
		Image image = null;
		
		try {
			URL _url = new URL(url);
			image = ImageIO.read(_url);
			image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			KnightLog.logException(e);
		}
		
		return image;
	}

}
