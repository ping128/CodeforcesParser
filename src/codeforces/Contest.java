package codeforces;

import java.util.ArrayList;
import java.util.List;

public class Contest {

	private String name;
	private List<Problem> problems;
	private String contestURL;
	
	public Contest(String contestName, String contestURL) {
		this.name = contestName;
		this.contestURL = contestURL;
		this.problems = new ArrayList<Problem>();
	}

	public void addProblem(Problem p){
		problems.add(p);
	}
	
	@Override
	public String toString() {
		String ret = "";
		ret += name;
		return ret;
	}

	public String getContestURL(){
		return this.contestURL;
	}
	
	public String showTests(int problemID, int numTest){
		StringBuilder ret = new StringBuilder();
		if(numTest == -1){
			Problem p = problems.get(problemID);
			List<String> input = p.getInputs();
			List<String> output = p.getOutputs();
			for(int i = 0; i < p.numSampleTest(); i++ ){
				ret.append("Input " + (i + 1) + "\n");
				ret.append(input.get(i));
				ret.append("Output " + (i + 1) + "\n");
				ret.append(output.get(i));
			}
		} else {
			numTest--;
			Problem p = problems.get(problemID);
			List<String> input = p.getInputs();
			List<String> output = p.getOutputs();
			ret.append("Input " + (numTest + 1) + "\n");
			ret.append(input.get(numTest));
			ret.append("Output " + (numTest + 1) + "\n");
			ret.append(output.get(numTest));
		}
		return ret.toString();
	}
	
	public List<TestResult> runTests(int problemID, int numTest, String pathToExecFile){
		List<TestResult> results = new ArrayList<TestResult>();
		Problem p = problems.get(problemID - 1);
		if(numTest == -1){
			for(int i = 0; i < p.numSampleTest(); i++ ){
				results.add(p.runTest(i, pathToExecFile));
			}
		} else {
			results.add(p.runTest(numTest - 1, pathToExecFile));
		}
		return results;
	}
}
