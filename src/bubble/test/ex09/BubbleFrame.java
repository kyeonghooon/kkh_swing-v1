package bubble.test.ex09;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BubbleFrame extends JFrame {

	// 컨텍스트를 생성하는 방법 (셀프 참조)
	BubbleFrame mContext = this;
	
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
				new ImageIcon("img/backgroundMap.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Frame -> root Panel
		setContentPane(backgroundMap); // add 처리
		setSize(BG_WIDTH, BG_HEIGHT);

		// mContext --> 참조 타입( ) --> 주소값의 크기는 기본 4byte 이다.
		player = new Player(mContext);
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
						player.attack();
						// 프레임에 컴포넌트를 add하는 동작은 JFrame --> add() 메서드이다.
						// 버블 실행시에 끊김 현상이 발생하는 이유는 왜 일까?
						break;
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
	
	
	// getter
	public Player getPlayer() {
		return player;
	}
	
	// 코드 테스트
	public static void main(String[] args) {
		// main 함수를 가지고 있는 클래스를 하위에 생성된 모든 객체들의
		// 주소값을 알고 있다. ( 중요! 중요! 중요!)
		
		new BubbleFrame();
	} // end of main

}
