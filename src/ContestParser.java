import java.io.Console;
import java.io.IOException;
import java.util.Scanner;

public class ContestParser {

	private static String LOGIN_URL = "http://codeforces.com/enter";
	private static Scanner cin;
	private static String contestURL;

	public static void main(String[] args) {
		cin = new Scanner(System.in);
		System.out.println("=============================");
		System.out.println("  Codeforces Contest Parser");
		System.out.println("=============================");
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
		WebCrawler crawler = new WebCrawler(name, password);
		crawler.login(LOGIN_URL);
		System.out.print("Contest: ");
		contestURL = cin.nextLine();
		try {
			crawler.parseContest(contestURL);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
