package codeforces;

import util.MsgFrame;

public class CommandLine {

	private WebCrawler crawler;
	private Contest contest;

	public CommandLine(WebCrawler crawler, Contest contest) {
		this.crawler = crawler;
		this.contest = contest;
	}

	public void run(String[] commands) {
		if (commands == null || commands.length == 0)
			throw new IllegalArgumentException();
		if (commands[0].equals("help")) {
			help();
		} else if (commands[0].equals("showtest")){
			if(commands.length >= 4 || commands.length == 1){
				throw new IllegalArgumentException();
			} else {
				int problem = commands[1].charAt(0) - 'a';
				int testnumber = -1;
				if(commands.length == 3) testnumber = Integer.parseInt(commands[2]);
				System.out.println(contest.showTests(problem, testnumber));
			}
		} else if (commands[0].equals("exit")) {
			MsgFrame.showMsg("Login", "<html><font color='black'>Bye!</font></html>");
			System.exit(0);
		} else {
			throw new IllegalArgumentException();
		}
	}

	private void help() {
		System.out.println("Command List");
		System.out.println("exit: to exit");
		System.out.println("showtest PROBLEM [test_number]: show sample tests (ex. showtest a 1");
	}
}
