import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class ContestParser {

	private static String LOGIN_URL = "http://codeforces.com/enter";
	private static Scanner cin;
	private static String contestURL;
	
	public static void main(String[] args) {
		cin = new Scanner(System.in);
		String s;
		System.out.println("=============================");
		System.out.println("  Codeforces Contest Parser");
		System.out.println("=============================");
		System.out.print("Username: ");
		String name = cin.nextLine();
		System.out.print("Password: ");
		String password = cin.nextLine();
		WebCrawler crawler = new WebCrawler(name, password);
		crawler.login(LOGIN_URL);
		System.out.print("Contest: ");
		contestURL = cin.nextLine();
		crawler.parseContest(contestURL);
		System.out.println(crawler.getLocation());
		/*
		String htmlContent = driver.getTitle();
		System.out.println(htmlContent);
		System.out.println("DONE");
		
/*
		try {
			Document doc = Jsoup.connect(contestURL).get();
		//	Element input = doc.cla
			System.out.println(input.data());
		} catch (IOException e) {
			System.out.println("fail to connect");
			e.printStackTrace();
		}*/
	}
}
