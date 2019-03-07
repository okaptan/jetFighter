package MyFighter;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class loadImage {

	public static BufferedImage background;
	public static BufferedImage myj;
	public static BufferedImage enemyj;
	public static BufferedImage mybull;
	public static BufferedImage enemybull;
	
	public static void init() {
		background = imageLoader("/back8.jpg");
		myj = imageLoader("/jet4.png");
		enemyj = imageLoader("/enem4.png");
		mybull = imageLoader("/bul5.png");
		enemybull = imageLoader("/bul1.png");
	}
	
	
	public static BufferedImage imageLoader(String path) {
		try {
			return ImageIO.read(loadImage.class.getResource(path));
		}
		catch(IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}


}