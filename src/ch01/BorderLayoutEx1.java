package ch01;

// ctrl shift O 는 안쓰는 import 삭제
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class BorderLayoutEx1 extends JFrame{
	
	// 생성자
	public BorderLayoutEx1() {
		
		
		initData();
		setInitLayout();
	}
	
	public void initData() {
		setTitle("borderLayout 연습");
		setSize(600, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void setInitLayout() {
		// 배치 관리자 선정 (컨테이너)
		// BorderLayout -- 컴포넌트들을 동서남북가운데로 배치 시켜주는 레이아웃이다.
		setLayout(new BorderLayout());
		
		add(new JButton("동"), BorderLayout.EAST);
		add(new JButton("서"), BorderLayout.WEST);
		add(new JButton("남"), BorderLayout.SOUTH);
		add(new JButton("북"), BorderLayout.NORTH);
		add(new JButton("센터"), BorderLayout.CENTER);
	}
	
	public static void main(String[] args) {
		new BorderLayoutEx1();
	}
}
