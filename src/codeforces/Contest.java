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
}
