package codeforces;

import java.io.Console;
import java.util.Scanner;

import util.MsgFrame;

public class ContestParser {

	private static String LOGIN_URL = "http://codeforces.com/enter";
	private static Scanner cin;
	private static String contestURL;

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
				WebCrawler crawler2 = new WebCrawler(name, password);
				crawler2.login(LOGIN_URL);
				crawler = crawler2;
				MsgFrame.showMsg("Login", "<html>"
						+ "<font color='black'>Hello, </font>" + "<font color='"
						+ crawler.getHandleColor() + "'>" + crawler.getUsername() + "</font></html>");
				break;
			} catch (Exception e) {
				MsgFrame.showMsg("Login", "<html><font color='red'>Login Failed!</font></html>");
			}
		}

		Contest contest;

		while (true) {
			System.out.print("Contest Link: ");
			contestURL = cin.nextLine();
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
			if (commands.length > 0) {
				if (commands[0].equals("help")) {
					driver.help();
				} else if (commands[0].equals("exit")) {
					MsgFrame.showMsg("Login", "<html><font color='black'>Bye!</font></html>");

					break;
				} else {
					System.out.println("Invalid Command!");
				}
			} else {
				System.out.println("Invalid Command!");
			}
			System.out.println();
		}
	}
}
