package codeforces;


public enum ExecutionResult {
	TLE("Time Limit Exceed"),
	AC("Accepted"),
	WA("Wrong Answer"),
	NOT_RUN("Not Run"),
	RUNNING("Running"),
	RE("Runtime Error");
	
    private String detail;
	
    ExecutionResult(String detail){
    	this.detail = detail;
    }
    
    public String getDetail() {
        return detail; 
    }
}