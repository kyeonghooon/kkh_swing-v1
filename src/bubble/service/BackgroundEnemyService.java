package bubble.service;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import bubble.components.Enemy;

/**
 * 현재 메인 쓰레드는 너~무 바쁨
 * 백그라운드에서 계속 Player의 움직임을 관찰할 예정
 */
public class BackgroundEnemyService implements Runnable {

	private BufferedImage image;
	private Enemy enemy;
	
	// 생성자 의존 주입 DI
	public BackgroundEnemyService(Enemy enemy) {
		this.enemy = enemy;
		
		try {
			image = ImageIO.read(new File("img/backgroundMapService.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(true) {
			 
			int bottomColorLeft = image.getRGB(enemy.getX() + 20, enemy.getY() + 50 + 5);
			int bottomColorRight = image.getRGB(enemy.getX() + 50 + 10, enemy.getY() + 50 + 5);
			
			// 흰색이면 -----> int 값이 - 2
			if(bottomColorLeft == -1 || bottomColorRight == -1) {
				enemy.setWhiteBottom(true);
			} 			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
