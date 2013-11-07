package util;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class MsgFrame {

	public static void showMsg(String topic, String s){
		JFrame frame = new JFrame(topic);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel textLabel = new JLabel(s);
		Border paddingBorder = BorderFactory.createEmptyBorder(10,10,10,10);
		textLabel.setBorder(paddingBorder);
		textLabel.setFont(textLabel.getFont().deriveFont(20.0f));
		frame.getContentPane().add(textLabel, BorderLayout.CENTER);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
