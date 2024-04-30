package ch06;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MiniGame3 extends JFrame {

	private JLabel jPlayer;
	private int jPlayerX = 100;
	private int jPlayerY = 100;
	private final int FRAME_WIDTH = 500;
	private final int FRAME_HEIGHT = 500;
	private final String PLAYER_NAME = "야스오";
	private final int PLAYER_WIDTH = 100;
	private final int PLAYER_HEIGHT = 100;
	private final int MOVE_DISTANCE = 10;
	private final int UP_END = MOVE_DISTANCE;
	private final int LEFT_END = MOVE_DISTANCE;
	private final int RIGHT_END = FRAME_WIDTH - PLAYER_WIDTH - MOVE_DISTANCE;
	private final int DOWN_END = FRAME_HEIGHT - PLAYER_HEIGHT - MOVE_DISTANCE;

	public MiniGame3() {
		initData();
		setInitLayout();
		addEventListener();
	}

	private void initData() {
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jPlayer = new JLabel(PLAYER_NAME);
		jPlayer.setSize(PLAYER_WIDTH, PLAYER_HEIGHT);
	}

	private void setInitLayout() {
		// 좌표 기반으로 배치관리자 변경
		setLayout(null);
		add(jPlayer);
		jPlayer.setLocation(jPlayerX, jPlayerY);
		setVisible(true);
	}

	private void addEventListener() {
		// jPlayer 객체에게서만 keyListener 동작을 시키고자 한다면
		// 익명 구현클래스로 KeyListener 인터페이스를 재정의할 수 있다.
		// jPlayer.addKeyListener(this);

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
					if (jPlayerX <= LEFT_END) {
						jPlayerX = LEFT_END;
					} else {
						jPlayerX -= MOVE_DISTANCE;
					}
					break;
				case KeyEvent.VK_RIGHT:
					System.out.println("오른쪽");
					if (jPlayerX >= RIGHT_END) {
						jPlayerX = RIGHT_END;
					} else {
						jPlayerX += MOVE_DISTANCE;
					}
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
				jPlayer.setLocation(jPlayerX, jPlayerY);
			}
		});
	}

	// 코드 테스트
	public static void main(String[] args) {
		new MiniGame3();
	} // end of main
} // end of class
