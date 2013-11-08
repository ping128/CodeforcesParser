package diff;

import java.util.StringTokenizer;

public class OutputComparison {

	public static String diff(String expected, String received){
		StringTokenizer expected_ = new StringTokenizer(expected);
		StringTokenizer received_ = new StringTokenizer(received);
		
		while(expected_.hasMoreTokens() && received_.hasMoreTokens()){
			String expectedToken = expected_.nextToken();
			String receivedToken = received_.nextToken();
			if(!expectedToken.equals(receivedToken)){
				return "expected:\n" + expected + "\n" + "received:\n" + received + "\n";
			}
		}
		
		while(expected_.hasMoreTokens() || received_.hasMoreTokens()){
			return "expected:\n" + expected + "\n" + "received:\n" + received + "\n";
		}
		
		return "OK";
	}
}
