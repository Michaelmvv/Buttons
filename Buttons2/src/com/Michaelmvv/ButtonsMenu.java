package com.Michaelmvv;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
public class ButtonsMenu {

	Color c;
	Random rand = new Random();
	public boolean isRandom;
	JFrame buttonMenu = new JFrame("Button Menu");
	JPanel buttonPanel = new JPanel();
	JTextField textField = new JTextField("Random");
	private JButton setColorButton = new JButton();
	String text = textField.getText();

	public Color getC() {
		return c;
	}

	public void setC(Color c) {
		this.c = c;
	}

	public boolean getIsRandom() {
		return isRandom;
	}

	public void CreateMenu() throws ClassNotFoundException {
		setColorButton.setText("Set Color");
		buttonMenu.setAlwaysOnTop(true);
		buttonMenu.setName("Color Menu");
		buttonMenu.setResizable(false);
		buttonMenu.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		buttonMenu.setVisible(true);

		buttonMenu.add(buttonPanel);

		setColorButton.addActionListener(changeColorButton);
		buttonPanel.add(textField);
		buttonPanel.add(setColorButton);
		buttonMenu.pack();

	}

	public Color getRandColor() {
		return new Color(rand.nextInt(0xFFFFFF));
	}

	ActionListener changeColorButton = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton buttonPressed = (JButton) e.getSource();
			if (buttonPressed.equals(setColorButton)) {
				text = textField.getText();
				if (!"random".equalsIgnoreCase(text)) {
					isRandom = false;
					Field field;

					try {
						field = Class.forName("java.awt.Color").getField(
								text.toLowerCase()); // toLowerCase because the
														// color fields are RED
														// or red, not Red
						c = (Color) field.get(null);
					} catch (ClassNotFoundException | NoSuchFieldException
							| SecurityException | IllegalArgumentException
							| IllegalAccessException ex) {
						Logger.getLogger(ButtonsMenu.class.getName()).log(
								Level.SEVERE, null, ex);
						JOptionPane.showMessageDialog(null,
								"Could not find that color");
						c = Color.BLACK;
					}
				} else {
					isRandom = true;
					c = getRandColor();
				}

			} else {
				System.err.println("We dont know what action that was");
			}

		}
	};

}
