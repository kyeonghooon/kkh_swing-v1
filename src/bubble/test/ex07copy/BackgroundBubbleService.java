package bubble.test.ex07copy;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BackgroundBubbleService implements Runnable {

	private BufferedImage image;
	private Bubble bubble;
	
	public BackgroundBubbleService(Bubble bubble) {
		this.bubble = bubble;
		
		try {
			image = ImageIO.read(new File("img/backgroundMapService.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while (true) {
			Color topColor = new Color(image.getRGB(bubble.getX() + 30,bubble.getY()));
			Color leftColor = new Color(image.getRGB(bubble.getX() + 10,bubble.getY() + 25));
			Color rightColor = new Color(image.getRGB(bubble.getX() + 50 + 10,bubble.getY() + 25));
			if (leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) {
				System.out.println("왼쪽벽에 충돌함");
				bubble.setLeft(false);
			} else if (rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) {
				System.out.println("오른쪽벽에 충돌함");
				bubble.setRight(false);
			} 
			if (topColor.getRed() == 255 && topColor.getGreen() == 0 && topColor.getBlue() == 0) {
				System.out.println("천장에 충돌함");
				bubble.setUp(false);
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
