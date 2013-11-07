import java.util.ArrayList;
import java.util.Collections;
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
	}
	
	public List<String> getInputs(){
		return Collections.unmodifiableList(inputs);
	}

	public List<String> getOutputs(){
		return Collections.unmodifiableList(outputs);
	}
	
	public String parse(String text) {
		StringBuilder ret = new StringBuilder();
		Scanner cin = new Scanner(text);
		while(cin.hasNextLine()){
			String s = cin.nextLine().trim();
			if (s.length() > 0) {
				if(ret.length() > 0) ret.append("\n");
				ret.append(s);
			}
		}
		cin.close();
		return ret.toString();
	}

	public int numSampleTest() {
		return this.inputs.size();
	}
}
