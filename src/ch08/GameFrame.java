package ch08;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame {

	BufferedImage backgroundImage;
	BufferedImage player1;
	BufferedImage diedPlayer1;
	BufferedImage enemy1;
	ImagePanel imagePanel;
	private final int MOVE_DISTANCE = 10;
	private int playerX = 150;
	private int playerY = 300;
	private int enemyX = 250;
	private int enemyY = 420;
	private boolean isEnemyMove = true;
	private boolean isMeet = false;

	public GameFrame() {
		initData();
		setInitLayout();
		addEventListener();
	}

	// 클래스 안에 클래스 -> 중첩 클래스 -> 외부 클래스, 내부 클래스
	private class ImagePanel extends JPanel implements Runnable {

		// paintComponents X
		// paintComponent O
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(backgroundImage, 0, 0, 600, 600, null);
			if (isMeet == false) {
				g.drawImage(player1, playerX, playerY, 50, 50, null);
			} else {
				g.drawImage(diedPlayer1, playerX, playerY, 50, 50, null);
			}
			g.drawImage(enemy1, enemyX, enemyY, 50, 50, null);
			// @Todo 플레이어, 적군 그림 그려야함
			// 쓰레드를 활용할 예정
		}

		@Override
		public void run() {
			// true : 왼쪽으로 가는 상황
			// false : 오른쪽으로 가는 상황
			boolean direction = true;
			while (true) {
				if (isEnemyMove) {

					if (direction) {
						enemyX -= MOVE_DISTANCE;
					} else {
						enemyX += MOVE_DISTANCE;
					}
					// 방향 바꾸는 개념은 적군 x 좌표값이
					if (enemyX <= 130) {
						direction = false;
					} else if (enemyX >= 420) {
						direction = true;
					}

					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (Math.abs(playerX - enemyX) <= 50 && Math.abs(playerY - enemyY) <= 50) {
					isMeet = true;
					System.out.println("작동함");
				}
				repaint();
			}
		}
	}

	private void initData() {
		setTitle("Thread를 활용한 미니 예제");
		setSize(600, 640);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			// 예외가 발생할 수 있는 코드를 작성하는 영역
			backgroundImage = ImageIO.read(new File("img/backgroundMap.png"));
			player1 = ImageIO.read(new File("img/playerL.png"));
			enemy1 = ImageIO.read(new File("img/enemyL.png"));
			diedPlayer1 = ImageIO.read(new File("img/playerLDie.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		imagePanel = new ImagePanel();
		// 다른 작업자에게 일을 위임 시킨다.
		Thread thread = new Thread(imagePanel);
		thread.start();
	}

	private void setInitLayout() {
		// 배치 관리자를 좌표 기반
		// setLayout(null);
		// setResizable(false); // 프레임 크기 조절 불가 설정
		setVisible(true);

		add(imagePanel);
	}

	private void addEventListener() {
		// 익명 클래스 활용
		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("여기가 동작 합니다.");
				int code = e.getKeyCode();
				if (isMeet == false) {
					if (code == KeyEvent.VK_UP) {
						playerY -= MOVE_DISTANCE;
					} else if (code == KeyEvent.VK_DOWN) {
						playerY += MOVE_DISTANCE;
					} else if (code == KeyEvent.VK_LEFT) {
						playerX -= MOVE_DISTANCE;
					} else if (code == KeyEvent.VK_RIGHT) {
						playerX += MOVE_DISTANCE;
					}
				}
				if (code == KeyEvent.VK_SPACE) {
					// 1. 스페이스바를 눌렀을 때 적군을 멈출 수 있도록 코드 수정
					if (isEnemyMove) {
						isEnemyMove = false;
					} else {
						isEnemyMove = true;
					}
				}

				// 2. player 가 적군과 만나면 play 그림을 없애 주세요.
				if (Math.abs(playerX - enemyX) <= 50 && Math.abs(playerY - enemyY) <= 50) {
					isMeet = true;
					System.out.println("작동함");
				}
				repaint();
			}
		});
	}

}
