package util;

import java.awt.BorderLayout;
import java.awt.KeyboardFocusManager;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class MsgFrame {

	public static void showMsg(String topic, String s) {
		final JFrame frame = new JFrame(topic);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel textLabel = new JLabel(s);
		Border paddingBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		textLabel.setBorder(paddingBorder);
		textLabel.setFont(textLabel.getFont().deriveFont(20.0f));
		frame.getContentPane().add(textLabel, BorderLayout.CENTER);

		// frame will be disposed once we click something else.
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addVetoableChangeListener(
				"focusedWindow", new VetoableChangeListener() {
					private boolean gained = false;

					@Override
					public void vetoableChange(PropertyChangeEvent evt)
							throws PropertyVetoException {
						if (evt.getNewValue() == frame) {
							gained = true;
						}
						if (gained && evt.getNewValue() != frame) {
							frame.dispose();
						}
					}
				});

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
