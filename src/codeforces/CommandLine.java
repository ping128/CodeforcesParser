package codeforces;

public class CommandLine {

	private WebCrawler crawler;
	private Contest contest;
	
	public CommandLine(WebCrawler crawler, Contest contest) {
		this.crawler = crawler;
		this.contest = contest;
	}
	
	public void help(){
		System.out.println("Command List");
		System.out.println("exit: to exit");
	}
}
