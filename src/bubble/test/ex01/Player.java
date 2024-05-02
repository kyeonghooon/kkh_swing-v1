package bubble.test.ex01;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel implements Moveable {

	private int x;
	private int y;
	private ImageIcon playerR, playerL;
	private final int P_WIDTH = 50;
	private final int P_HEIGTH = 50;
	private final int MOVE_DISTANCE = 10;

	public Player() {
		initData();
		setInitLayout();
	}

	private void initData() {
		playerR = new ImageIcon("img/playerR.png");
		playerL = new ImageIcon("img/playerL.png");

		// 처음 실행 시 초기 값 세팅
		x = 55;
		y = 535;
		setIcon(playerR);
		setSize(P_WIDTH, P_HEIGTH);
		setLocation(x, y);
	}

	private void setInitLayout() {

	}

	@Override
	public void left() {
		// 왼쪽 방향키 이벤트 발생 시
		// 이미지를 왼쪽으로 보는 이미지로 세팅
		setIcon(playerL);
		x -= MOVE_DISTANCE;
		setLocation(x, y);
	}

	@Override
	public void right() {
		setIcon(playerR);
		x += MOVE_DISTANCE;
		setLocation(x, y);

	}

	@Override
	public void up() {
		System.out.println("점프");
	}

	@Override
	public void down() {
		System.out.println();
	}

}
