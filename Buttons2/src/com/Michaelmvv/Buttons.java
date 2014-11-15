package com.Michaelmvv;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Buttons implements ActionListener {

	ButtonsMenu bm = new ButtonsMenu();

	int buttonsPressed = 0;
	Color backgroundColor;

	JButton leftButton = new JButton();
	JButton rightButton = new JButton();

	Dimension BIG = new Dimension(400, 400);
	Dimension SMALL = new Dimension(200, 200);
	Dimension NORMAL = new Dimension(100, 50);

	JFrame frame = new JFrame();
	JPanel panel = new JPanel();

	public static void main(String[] args) throws ClassNotFoundException {
		new Buttons().createUI();
	}

	private void createUI() throws ClassNotFoundException {
		rightButton.setPreferredSize(NORMAL);
		leftButton.setPreferredSize(NORMAL);
		bm.CreateMenu();
		frame.setBounds(0, 100, 100, 100);

		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				JFrame frame = (JFrame) e.getSource();

				int result = JOptionPane.showConfirmDialog(frame,
						"Are you sure you want to exit Button Clicker?",
						"Exit Application", JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.YES_OPTION) {
					frame.setVisible(false);
					frame.dispose();
					System.exit(0);

				}
			}
		});

		frame.add(panel);
		frame.setVisible(true);
		leftButton.setText("Click Me!");
		rightButton.setText("Click Me!");
		leftButton.addActionListener(this);
		rightButton.addActionListener(this);
		panel.add(leftButton);
		panel.add(rightButton);

		frame.pack();
		frame.setTitle("Demanding Buttons");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		JButton buttonPressed = (JButton) arg0.getSource();

		buttonsPressed++;
		frame.setTitle("Buttons pressed: " + buttonsPressed);
		if (buttonPressed.equals(leftButton)) {
			rightButton.setText("NO, Click Me!");
			rightButton.setPreferredSize(BIG);
			leftButton.setText("Click Me!");
			leftButton.setPreferredSize(SMALL);
		} else if (buttonPressed.equals(rightButton)) {
			leftButton.setText("No, Click ME!");
			leftButton.setPreferredSize(BIG);
			rightButton.setPreferredSize(SMALL);
			rightButton.setText("Click Me!");
		}
		if (bm.getIsRandom()) {
			backgroundColor = bm.getRandColor();

		} else {
			backgroundColor = bm.getC();

		}
		frame.setBackground(backgroundColor);
		panel.setBackground(backgroundColor);
		rightButton.setBackground(backgroundColor);
		rightButton.setForeground(Color.BLACK);
		leftButton.setBackground(backgroundColor);
		leftButton.setForeground(Color.BLACK);
		frame.pack();
	}
}
