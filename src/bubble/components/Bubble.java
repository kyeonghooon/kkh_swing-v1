package bubble.components;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import bubble.BubbleFrame;
import bubble.interfaces.Moveable;
import bubble.service.BackgroundBubbleService;

public class Bubble extends JLabel implements Moveable {

	private Player player;
	private BubbleFrame mContext;
	private BackgroundBubbleService backgroundBubbleService;

	private int x;
	private int y;
	
	// 움직임 상태
	private boolean left;
	private boolean right;
	private boolean up;

	// 적군을 맞춘 상태
	private int state; // 0 : 기본 물방울, 1 : 적을 가둔 상태 물방울

	private ImageIcon bubble; // 기본 물방울
	private ImageIcon bubbled; // 적을 가둔 물방울
	private ImageIcon bomb; // 물방울 팡!

	// 연관관계, 의존성 컴포지션 관계, 생성자 의존 주입 (DI)
	public Bubble(BubbleFrame mContext) {
		this.mContext = mContext;
		this.player = mContext.getPlayer();
		initData();
		setInitLayout();
	}

	// get, set
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public ImageIcon getBubble() {
		return bubble;
	}

	public void setBubble(ImageIcon bubble) {
		this.bubble = bubble;
	}

	public ImageIcon getBubbled() {
		return bubbled;
	}

	public void setBubbled(ImageIcon bubbled) {
		this.bubbled = bubbled;
	}

	public ImageIcon getBomb() {
		return bomb;
	}

	public void setBomb(ImageIcon bomb) {
		this.bomb = bomb;
	}

	private void initData() {
		bubble = new ImageIcon("img/bubble.png");
		bubbled = new ImageIcon("img/bubbled.png");
		bomb = new ImageIcon("img/bomb.png");
		
		backgroundBubbleService = new BackgroundBubbleService(this);

		left = false;
		right = false;
		up = false;
		state = 0;
	}
	private void setInitLayout() {
		x = player.getX();
		y = player.getY();
		setIcon(bubble);
		setSize(50, 50);
		setLocation(x, y);
	}

	@Override
	public void left() {
		left = true;
		for (int i = 0; i < 400; i++) {
			x--;
			setLocation(x, y);
			if (backgroundBubbleService.leftWall()) {
				break;
			}
			
			// 현재 버블의 x, y 좌표 값을 알고 있다
			
			System.out.println("적군 x 좌표 위치 : " + mContext.getEnemy().getX());
			System.out.println("적군 y 좌표 위치 : " + mContext.getEnemy().getY());
			// x 절대값만 확인해 보자.
			
			int absX = Math.abs(x - mContext.getEnemy().getX());
			System.out.println("absXResult : " + absX);
			int absY = Math.abs(y - mContext.getEnemy().getY());
			System.out.println("absYResult : " + absY);
			if (absX < 40 && absY < 50) {
				if (mContext.getEnemy().getState() == 0) {
					crash();
				}
			}
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		left = false;
		up();
	}

	@Override
	public void right() {
		right = true;
		for (int i = 0; i < 400; i++) {
			x++;
			setLocation(x, y);
			if (backgroundBubbleService.rightWall()) {
				break;
			}
			int absX = Math.abs(x - mContext.getEnemy().getX());
			int absY = Math.abs(y - mContext.getEnemy().getY());
			if (absX < 40 && absY < 50) {
				if (mContext.getEnemy().getState() == 0) {
					crash();
				}
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		right = false;
		up();
	}

	@Override
	public void up() {
		up = true;
		while (up) {
			y--;
			setLocation(x, y);
			if (backgroundBubbleService.topWall()) {
				break;
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		up = false;
		clearBubble();
	}
	
	private void clearBubble() {
		try {
			Thread.sleep(3000);
			setIcon(bomb);
			Thread.sleep(500);
			// todo 테스트 필요 !!
			setIcon(null);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void crash() {
		// 적군이 살아 있는 상태에서
		// 버블에 갇힌 상태로 변경
		mContext.getEnemy().setState(1);
		state = 1;
		setIcon(bubbled);
//		mContext.getEnemy().setIcon(null);
		mContext.remove(mContext.getEnemy());
		// mContext.getEnemy() 가바지 컬렉터의 제거 대상이 된다.
		mContext.repaint();
	}
}
