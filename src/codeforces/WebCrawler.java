package codeforces;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class WebCrawler {

	private String username;
	private String password;
	private WebDriver crawler;

	public WebCrawler(String username, String password) {
		crawler = null;
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public WebDriver getCrawler() {
		return crawler;
	}

	public String getLocation() {
		if (crawler == null)
			return "Offline";
		else
			return crawler.getCurrentUrl();
	}

	public void parseContest(String contestURL) throws IOException {
		System.out.println("Parsing...\n");
		crawler.get(contestURL);
		Document doc = Jsoup.parse(crawler.getPageSource());
		Elements problems = doc.select(".problems tr");
		System.out.println(crawler.getTitle());
		int nProblem = problems.size() - 1;
		System.out.println("There are " + nProblem + " problems.");
		for (int i = 1; i <= nProblem; i++) {
			String problemURL = "http://codeforces.com/"
					+ problems.get(i).select("a").first().attr("href");
			crawler.get(problemURL);
			System.out.println(crawler.getTitle());
			doc = Jsoup.parse(crawler.getPageSource());
			Elements temp = doc.getElementsByClass("title");
			Problem problem = new Problem(temp.first().text());
			Elements inputs = doc.select(".input pre");
			Elements outputs = doc.select(".output pre");
			for (int j = 0; j < inputs.size(); j++) {
				problem.addSampleTest(inputs.get(j).text(), outputs.get(j).text());
			}

/*
			List<String> input = problem.getInputs();
			for (int j = 0; j < input.size(); j++) {
				FileMaker.buildFile((char) ('a' + (i - 1)) + ".in." + (j + 1), input.get(j));
			}
			List<String> output = problem.getInputs();
			for (int j = 0; j < output.size(); j++) {
				FileMaker.buildFile((char) ('a' + (i - 1)) + ".out." + (j + 1), output.get(j));
			}
*/
		}
		System.out.println("Done Parsing");
	}

	
	/**
	 * @param url
	 * @throws IllegalAccessException : login failed
	 */
	public void login(String url) throws IllegalAccessException {
		System.out.println("logging in to " + url + "\n");
		crawler = new HtmlUnitDriver(false);
		crawler.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		crawler.get(url);
		crawler.findElement(By.name("handle")).clear();
		crawler.findElement(By.name("handle")).sendKeys(username);
		crawler.findElement(By.name("password")).clear();
		crawler.findElement(By.name("password")).sendKeys(password);
		crawler.findElement(By.className("submit")).click();
		if (crawler.getPageSource().indexOf("Invalid handle or password") == -1) {
			System.out.println("Login Finished\n");
		} else {
			throw new IllegalAccessException();
		}
	}

	@Override
	public String toString() {
		String s = "Crawler: " + username + "\n";
		if (crawler == null) {
			s += "Offline";
		} else {
			s += "Online: " + crawler.getCurrentUrl() + "\n";
		}
		return s;
	}
}
