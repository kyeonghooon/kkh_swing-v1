package bubble.test.ex05;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BubbleFrame extends JFrame {

	private JLabel backgroundMap;
	private Player player;
	private final int BG_WIDTH = 1000;
	private final int BG_HEIGHT = 640;

	public BubbleFrame() {
		initData();
		setInitLayout();
		addEventListener();

		// Player 백그라운드 서비스 시작

		new Thread(new BackgroundPlayerService(player)).start();
	}

	private void initData() {
		// todo 이미지 변경
		backgroundMap = new JLabel(
				new ImageIcon("img/backgroundMapService.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Frame -> root Panel
		setContentPane(backgroundMap); // add 처리
		setSize(BG_WIDTH, BG_HEIGHT);

		player = new Player();
	}

	private void setInitLayout() {
		setLayout(null); // 좌표값으로 배치
		setResizable(false); // 프레임 조절 불가
		setLocationRelativeTo(null); // JFrame을 모니터 가운데 자동 배치
		setVisible(true);

		add(player);
	}

	private void addEventListener() {
		// KeyAdapter는 KeyListener 를 구현한 추상 클래스
		// 불필요한 메서드를 쓰지 않고 원하는 메서드만 오버라이드해서 쓰면됨
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// 개발단계에서는 확인 용도로 써놓음
				System.out.println("key code : " + e.getKeyCode());

				switch (e.getKeyCode()) {
					case KeyEvent.VK_LEFT :

						// 왼쪽 상태가 아니라면
						// 왼쪽 벽에 충돌한게 아니라면
						if (!player.isLeft() && !player.isLeftWallCrash()) {
							player.left();
						}
						break;
					case KeyEvent.VK_RIGHT :
						if (!player.isRight() && !player.isRightWallCrash()) {
							player.right();
						}
						break;
					case KeyEvent.VK_UP :
						player.up();
						break;
					case KeyEvent.VK_SPACE :
						add(new Bubble(player));
					default :
						break;
				}
			} // end of KeyPressed
			@Override
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
					case KeyEvent.VK_LEFT :
						player.setLeft(false);
						break;
					case KeyEvent.VK_RIGHT :
						player.setRight(false);
						break;
					default :
						break;
				}

			} // end of KeyReleased
		});
	}

	// 코드 테스트
	public static void main(String[] args) {
		new BubbleFrame();
	} // end of main

}
