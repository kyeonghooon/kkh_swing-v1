package ch02;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

// 내부 클래스를 활용해서 코드를 완성해주세요
public class PaintFrame extends JFrame {
	MyPaintPanel myPaintPanel;

	public PaintFrame() {
		initData();
		setInitLayout();
	}

	private void initData() {
		setTitle("우리집");
		setSize(800, 810);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		myPaintPanel = new MyPaintPanel();
	}

	private void setInitLayout() {
		add(myPaintPanel);
		setVisible(true);

	}

	class MyPaintPanel extends JPanel {

		@Override
		public void paint(Graphics g) {
			super.paint(g);
			g.drawRect(50, 500, 200, 200);
			g.drawRect(250, 400, 300, 300);
			g.drawRect(550, 500, 200, 200);
			g.drawLine(50, 500, 150, 400);
			g.drawLine(150, 400, 250, 500);
			g.drawLine(250, 400, 400, 250);
			g.drawLine(400, 250, 550, 400);
			g.drawLine(550, 500, 650, 400);
			g.drawLine(750, 500, 650, 400);
			g.drawRect(116, 610, 70, 90);
			g.drawRect(616, 610, 70, 90);
			g.drawRect(325, 450, 150, 150);
			g.drawRect(325, 450, 75, 75);
			g.drawRect(400, 525, 75, 75);
			g.drawLine(300, 290, 300, 350);
			g.drawLine(300, 290, 350, 290);
			g.drawLine(350, 290, 350, 300);
			g.drawRect(300, 240, 50, 50);
			g.drawOval(300, 190, 30, 30);
			g.drawOval(230, 110, 60, 60);
			g.drawOval(150, 20, 90, 90);
		}
	}
}
