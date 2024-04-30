package ch06;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MiniGame2 extends JFrame{
	
	JLabel backgroundMap;
	JLabel player;
	private int jPlayerX = 500;
	private int jPlayerY = 535;
	private final int FRAME_WIDTH = 1000;
	private final int FRAME_HEIGHT = 600;
	private final int PLAYER_WIDTH = 50;
	private final int PLAYER_HEIGHT = 50;
	private final int MOVE_DISTANCE = 30;
	private final int UP_END = MOVE_DISTANCE;
	private final int LEFT_END = 70;
	private final int RIGHT_END = FRAME_WIDTH - PLAYER_WIDTH - 60;
	private final int DOWN_END = FRAME_HEIGHT - PLAYER_HEIGHT - MOVE_DISTANCE;
	
	public MiniGame2() {
		initData();
		setInitLayout();
		addEventListener();
	}
	
	private void initData() {
		setSize(FRAME_WIDTH, FRAME_HEIGHT + 38);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		backgroundMap = new JLabel(new ImageIcon("images/backgroundMap.png"));
		backgroundMap.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		backgroundMap.setLocation(0, 0);
		player = new JLabel(new ImageIcon("images/playerR.png"));
		player.setSize(PLAYER_WIDTH, PLAYER_HEIGHT);
		player.setLocation(jPlayerX, jPlayerY);
	}
	private void setInitLayout() {
		setLayout(null);
		add(backgroundMap);
		backgroundMap.add(player);
		setVisible(true);
	}
	private void addEventListener() {
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					System.out.println("위");
					if (jPlayerY <= UP_END) {
						jPlayerY = UP_END;
					} else {
						jPlayerY -= MOVE_DISTANCE;
					}
					break;
				case KeyEvent.VK_LEFT:
					System.out.println("왼쪽");
					if (jPlayerX <= LEFT_END + MOVE_DISTANCE) {
						jPlayerX = LEFT_END;
					} else {
						jPlayerX -= MOVE_DISTANCE;
					}
					player.setIcon(new ImageIcon("images/playerL.png"));
					break;
				case KeyEvent.VK_RIGHT:
					System.out.println("오른쪽");
					if (jPlayerX >= RIGHT_END - MOVE_DISTANCE) {
						jPlayerX = RIGHT_END;
					} else {
						jPlayerX += MOVE_DISTANCE;
					}
					player.setIcon(new ImageIcon("images/playerR.png"));
					break;
				case KeyEvent.VK_DOWN:
					System.out.println("아래");
					if (jPlayerY >= DOWN_END) {
						jPlayerY = DOWN_END;
					} else {
						jPlayerY += MOVE_DISTANCE;
					}
					break;
				}
				player.setLocation(jPlayerX, jPlayerY);
			}
		});
	}
	
	// 코드 테스트
	public static void main(String[] args) {
		new MiniGame2();
	}
}
