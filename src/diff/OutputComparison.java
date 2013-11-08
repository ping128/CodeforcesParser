package diff;

import java.util.StringTokenizer;

public class OutputComparison {

	public static String diff(String expected, String received) {
		StringTokenizer expected_ = new StringTokenizer(expected, "\n");
		StringTokenizer received_ = new StringTokenizer(received, "\n");
		StringBuilder expOut = new StringBuilder();
		StringBuilder recOut = new StringBuilder();

		while (expected_.hasMoreTokens() && received_.hasMoreTokens()) {
			String expectedToken = expected_.nextToken();
			String receivedToken = received_.nextToken();
			expOut.append(expectedToken + "\n");
			recOut.append(receivedToken + "\n");
			if (!expectedToken.equals(receivedToken)) {
				return "expected:\n" + expOut.toString() + "\n" + "received:\n" + recOut.toString()
						+ "\n";
			}
		}

		if (expected_.hasMoreElements()) {
			expOut.append(expected_.nextToken() + "\n");
			return "expected:\n" + expOut.toString() + "\n" + "received:\n" + recOut.toString()
					+ "\n";
		}

		if (received_.hasMoreElements()) {
			recOut.append(received_.nextToken() + "\n");
			return "expected:\n" + expOut.toString() + "\n" + "received:\n" + recOut.toString()
					+ "\n";
		}

		return "OK";
	}
}
