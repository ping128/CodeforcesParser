package util;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.KeyboardFocusManager;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

import codeforces.ExecutionResult;
import codeforces.TestResult;

public class ResultFrame {

	public static final float DEFAULT_FONTSIZE = 14f;

	public static void showMsg(String topic, List<TestResult> results) {
		final JFrame frame = new JFrame(topic);
		frame.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		int i = 0;
		List<Pair<String, String>> list = makeTable(results);
		for (Pair<String, String> a : list) {
			c.gridx = 0;
			c.gridy = i;
			addText(frame, a.first, c);
			c.gridx = 1;
			c.gridy = i;
			addText(frame, a.second, c);
			i++;
		}
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
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
	}

	private static void addText(JFrame frame, String s, GridBagConstraints c) {
		JLabel textLabel = new JLabel(s);
		textLabel.setFont(textLabel.getFont().deriveFont(DEFAULT_FONTSIZE));
		Border paddingBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		textLabel.setBorder(paddingBorder);
		frame.add(textLabel, c);
	}

	private static List<Pair<String, String>> makeTable(List<TestResult> results) {
		List<Pair<String, String>> ret = new ArrayList<Pair<String, String>>();
		for (TestResult test : results) {
			if (test.result == ExecutionResult.WA) {
				String a = "<html><font color='red'><em>" + test.sampleID + "</em></font></html>";
				String b = "<html><font color='red'><em>" + test.result.getDetail()
						+ "</em></font></html>";
				ret.add(new Pair<String, String>(a, b));
				int ind = test.detail.indexOf("received:");
				a = parse(test.detail.substring(0, ind));
				b = parse(test.detail.substring(ind));
				ret.add(new Pair<String, String>(a, b));
			} else if (test.result == ExecutionResult.AC) {
				String a = "<html><font color='#00AA00'><em>" + test.sampleID
						+ "</em></font></html>";
				String b = "<html><font color='#00AA00'><em>" + test.result.getDetail()
						+ "</em></font></html>";
				ret.add(new Pair<String, String>(a, b));
			} else {
				String a = "<html><font color='red'><em>" + test.sampleID + "</em></font></html>";
				String b = "<html><font color='red'><em>" + test.result.getDetail()
						+ "</em></font></html>";
				ret.add(new Pair<String, String>(a, b));
			}
		}
		return ret;
	}

	public static String parse(String s) {
		String[] temp = s.split("\n");
		StringBuilder ret = new StringBuilder();
		ret.append("<font color='blue'>" + temp[0] + "</font>");
		for (int i = 1; i < temp.length; i++) {
			ret.append("<br>");
			ret.append(temp[i]);
		}
		return "<html><font color='black'>" + ret.toString() + "</font></html>";
	}
}
