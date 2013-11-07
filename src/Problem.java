import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem {

	public String name;
	public List<String> inputs;
	public List<String> outputs;

	public Problem(String name) {
		this.name = name;
		this.inputs = new ArrayList<String>();
		this.outputs = new ArrayList<String>();
	}

	public String toString() {
		String ret = "";
		ret += name + "\n";
		return ret;
	}

	public void addSampleTest(String input, String output) {
		this.inputs.add(parse(input));
		this.outputs.add(parse(output));
		System.out.println(input);
	}

	public String parse(String text) {
		StringBuilder ret = new StringBuilder();
		Scanner cin = new Scanner(text);
		System.out.println(text.length());
		String[] s2 = text.split("\n|\r\n");
		for (String s : s2) {
			s = s.trim();
			if (s.length() > 0) {
				ret.append(s + "\n");
				break;
			}
		}
		return ret.toString();
	}

	public int numSampleTest() {
		return this.inputs.size();
	}
}
