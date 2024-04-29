package ch05;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ColorChangeFrame3 extends JFrame implements ActionListener {

	private JPanel panel1;
	private JPanel panel2;
	private JButton button1;
	private JButton button2;
	private JButton button3;

	public ColorChangeFrame3() {
		initData();
		setInitLayout();
		addEventListener();
	}

	private void initData() {
		setSize (400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		panel1 = new JPanel();
		panel2 = new JPanel();
		button1 = new JButton("RED");
		button2 = new JButton("GREEN");
		button3 = new JButton("BULE");
	}

	private void setInitLayout() {
		add(panel1, BorderLayout.CENTER);
		add(panel2, BorderLayout.SOUTH);
		panel1.setBackground(Color.WHITE);
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));
		panel2.add(button1);
		button1.setBackground(Color.RED);
		panel2.add(button2);
		button2.setBackground(Color.GREEN);
		panel2.add(button3);
		button3.setBackground(Color.BLUE);
		panel2.setBackground(Color.BLACK);
		setVisible(true);
	}

	private void addEventListener() {
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clickedButton = (JButton)e.getSource();
		if (clickedButton == button1) {
			panel1.setBackground(Color.RED);
			System.out.println("RED버튼이 눌러짐");
		} else if (clickedButton == button2) {
			panel1.setBackground(Color.GREEN);
			System.out.println("GREEN버튼이 눌러짐");
		} else if (clickedButton == button3) {
			panel1.setBackground(Color.BLUE);
			System.out.println("BLUE버튼이 눌러짐");
		}
	}
	
	public static void main(String[] args) {
		new ColorChangeFrame3();
	}
}
