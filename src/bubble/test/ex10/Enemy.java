package bubble.test.ex10;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Enemy extends JLabel implements Moveable {

	BubbleFrame mContext;
	// 살아 있는 상태 0, 물방울에 갇힌 상태 1
	private int state;
	
	private int x;
	private int y;
	private ImageIcon enemyR, enemyL;

	// 적군 움직임의 상태
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;

	// 벽에 충돌한 상태
	private boolean leftWallCrash;
	private boolean rightWallCrash;

	// 적군 속도 상태
	private final int SPEED = 3;
	private final int JUMPSPEED = 1;

	// 적군 방향 상태
	private EnemyWay enemyWay;

	// get, set

	// 적군 크기
	private final int E_WIDTH = 50;
	private final int E_HEIGTH = 50;

	// setter
	public void setLeft(boolean left) {
		this.left = left;
	}
	public void setRight(boolean right) {
		this.right = right;
	}

	public Enemy(BubbleFrame mContext) {
		this.mContext = mContext;
		initData();
		setInitLayout();
	}
	
	public int getState() {
		return state;
	}
	
	public void setState(int state) {
		this.state = state;
	}
	

	public BubbleFrame getmContext() {
		return mContext;
	}
	public void setmContext(BubbleFrame mContext) {
		this.mContext = mContext;
	}
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
	public ImageIcon getEnemyR() {
		return enemyR;
	}
	public void setEnemyR(ImageIcon enemyR) {
		this.enemyR = enemyR;
	}
	public ImageIcon getEnemyL() {
		return enemyL;
	}
	public void setEnemyL(ImageIcon enemyL) {
		this.enemyL = enemyL;
	}
	public boolean isUp() {
		return up;
	}
	public void setUp(boolean up) {
		this.up = up;
	}
	public boolean isDown() {
		return down;
	}
	public void setDown(boolean down) {
		this.down = down;
	}
	public boolean isLeftWallCrash() {
		return leftWallCrash;
	}
	public void setLeftWallCrash(boolean leftWallCrash) {
		this.leftWallCrash = leftWallCrash;
	}
	public boolean isRightWallCrash() {
		return rightWallCrash;
	}
	public void setRightWallCrash(boolean rightWallCrash) {
		this.rightWallCrash = rightWallCrash;
	}
	public EnemyWay getEnemyWay() {
		return enemyWay;
	}
	public void setEnemyWay(EnemyWay enemyWay) {
		this.enemyWay = enemyWay;
	}
	public boolean isLeft() {
		return left;
	}
	public boolean isRight() {
		return right;
	}
	public int getSPEED() {
		return SPEED;
	}
	public int getJUMPSPEED() {
		return JUMPSPEED;
	}
	public int getE_WIDTH() {
		return E_WIDTH;
	}
	public int getE_HEIGTH() {
		return E_HEIGTH;
	}
	private void initData() {
		enemyR = new ImageIcon("img/enemyL.png");
		enemyL = new ImageIcon("img/enemyR.png");
		state = 0;
		// 처음 실행 시 초기 값 세팅
		x = 720;
		y = 175;
		// 플레이어가 가만히 멈춘 상태
		left = false;
		right = false;
		up = false;
		down = false;

		leftWallCrash = false;
		rightWallCrash = false;

		enemyWay = EnemyWay.LEFT;
		
		left();
	}

	private void setInitLayout() {
		setIcon(enemyR);
		setSize(E_WIDTH, E_HEIGTH);
		setLocation(x, y);
	}

	@Override
	public void left() {
		enemyWay = EnemyWay.LEFT;
		left = true;
		setIcon(enemyL);

		// 메인 작업자는 계속 키 입력을 받아야함
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (left) {
					x -= SPEED;
					setLocation(x, y);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	@Override
	public void right() {
		enemyWay = EnemyWay.RIGHT;
		right = true;
		setIcon(enemyR);

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (right) {
					x += SPEED;
					setLocation(x, y);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

	}
	@Override
	public void up() {
		System.out.println("점프");
		up = true;
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 130 / JUMPSPEED; i++) {
					y -= JUMPSPEED;
					setLocation(x, y);
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				// 객체의 상태값을 잘 조절해야 한다.
				up = false;
				down();
			}
		}).start();
	}
	@Override
	public void down() {
		System.out.println("다운");
		down = true;

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (down) {
					y += SPEED;
					setLocation(x, y);
					try {
						Thread.sleep(3);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				down = false;
			}
		}).start();
	}
}
