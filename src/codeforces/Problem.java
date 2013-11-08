package codeforces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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

	public List<String> getInputs() {
		return Collections.unmodifiableList(inputs);
	}

	public List<String> getOutputs() {
		return Collections.unmodifiableList(outputs);
	}

	private String parse(String text) {
		StringBuilder ret = new StringBuilder();
		Scanner cin = new Scanner(text);
		while (cin.hasNextLine()) {
			String s = cin.nextLine().trim();
			if (s.length() > 0) {
				ret.append(s);
				ret.append("\n");
			}
		}
		cin.close();
		return ret.toString();
	}

	public int numSampleTest() {
		return this.inputs.size();
	}

	public TestResult runTest(int testID, String pathToExecFile) {
		TestRunner runner = new TestRunner(pathToExecFile, inputs.get(testID), outputs.get(testID));
		ExecutorService executor = Executors.newSingleThreadExecutor();
		try {
			executor.submit(runner);
			executor.shutdown();
			if (!executor.awaitTermination(TestRunner.DEFAULT_TIME_LIMIT, TimeUnit.MILLISECONDS)) {
				if (!executor.isTerminated()) {
					if (runner.getResult().result != ExecutionResult.RE)
						runner.setExecutionResult(ExecutionResult.TLE);
					runner.kill();
				}
			}

		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		runner.getResult().sampleID = "Sample " + (testID + 1) + ":";
		return runner.getResult();
	}
}