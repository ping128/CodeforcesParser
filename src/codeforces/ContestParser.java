package codeforces;

import java.io.Console;
import java.util.Scanner;

import util.HandleColorMap;
import util.MsgFrame;

public class ContestParser {

	private static String LOGIN_URL = "http://codeforces.com/enter";
	private static Scanner cin;

	// will update later
	private static String pathToExecFile = "/home/ping128/Desktop/a.out";

	public static void main(String[] args) {
		cin = new Scanner(System.in);
		System.out.println("=============================");
		System.out.println("  Codeforces Contest Parser");
		System.out.println("=============================");

		WebCrawler crawler;

		while (true) {
			try {

				System.out.print("Username: ");
				String name = cin.nextLine();

				System.out.print("Password: ");
				Console cons = System.console();
				String password;
				if ((cons = System.console()) != null) {
					char[] passwd = cons.readPassword();
					password = new String(passwd);
				} else {
					password = cin.nextLine();
				}

				System.out.print("Path to Execute: ");
				String path = cin.nextLine();
				if(path.length() > 0) pathToExecFile = path;
				
				WebCrawler crawler2 = new WebCrawler(name, password, pathToExecFile);
				crawler2.login(LOGIN_URL);
				crawler = crawler2;
				MsgFrame.showMsg("Login", "<html>" + "<font color='black'>Hello, </font>"
						+ "<font color='" + crawler.getHandleColor() + "'>" + crawler.getUsername()
						+ "</font></html>");
				break;
			} catch (Exception e) {
				MsgFrame.showMsg("Login", "<html><font color='red'>Login Failed!</font></html>");
			}
		}

		Contest contest;

		while (true) {
			System.out.print("Contest: ");
			String contestURL = cin.nextLine();
			// String contestURL = "358";
			try {
				contest = crawler.parseContest(contestURL);
				MsgFrame.showMsg("Contest Parsing",
						"<html><font color='#00AA00'>Parsing Contest OK!</font></html>");

				break;
			} catch (Exception e) {
				MsgFrame.showMsg("Contest Parsing",
						"<html><font color='red'>Parsing Contest Failed!</font></html>");
			}
		}
		System.out.println();
		runCommands(crawler, contest);
	}

	public static void runCommands(WebCrawler crawler, Contest contest) {
		CommandLine driver = new CommandLine(crawler, contest);
		while (true) {
			System.out.print(">> ");
			String[] commands = cin.nextLine().split(" ");
			try {
				driver.run(commands);
			} catch (Exception e) {
				MsgFrame.showMsg("Commands",
						"<html><font color='red'>Invalid Commands!</font></html>");
			}
			System.out.println();
		}
	}
}
