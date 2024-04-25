package ch01;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class FlowLayoutEx2 extends JFrame {

	// 배열 활용
	private JButton[] buttons;

	// 생성자
	public FlowLayoutEx2() {
		super.setTitle("FlowLayout 연습");
		super.setSize(500, 500);
		super.setVisible(true);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 생성자에서 메서드 호출 가능
		initData();
		setInitLayout();

	}

	// 멤버 변수를 초기화 하는 기능(값 넣다)
	public void initData() {
		buttons = new JButton[6];
		// 반복문 활용
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton("button" + (i + 1));
		}
	}

	// 컴포넌트들을 배치하는 기능
	public void setInitLayout() {

		// 배치관리자 생성 및 JFrame 셋팅
		super.setLayout(new FlowLayout(FlowLayout.LEADING, 50, 50)); // 배치관리자 --> FlowLayout

		// 컴포넌트들을 붙이다.
		// 반복문 활용
		for (int i = 0; i < buttons.length; i++) {
			super.add(buttons[i]);
		}
	}

	// 코드 테스트
	public static void main(String[] args) {
		new FlowLayoutEx2(); // <----- 익명 클래스

	} // end of main

}
