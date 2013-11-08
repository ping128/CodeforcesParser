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
	private String handleColor;
	
	public WebCrawler(String username, String password) {
		this.crawler = null;
		this.username = username;
		this.password = password;
		this.handleColor = "black";
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
	
	public String getHandleColor(){
		return handleColor;
	}

	/**
	 * @param contestURL
	 * @throws IOException
	 */
	public Contest parseContest(String contestURL) throws IOException {
		
		System.out.println("Parsing...\n");
		crawler.get(contestURL);
		Document doc = Jsoup.parse(crawler.getPageSource());
		Elements problems = doc.select(".problems tr");

		int nProblem = problems.size() - 1;
		if(nProblem <= 0 || contestURL.indexOf("contest") == -1){
			throw new IOException();
		}
		Contest ret = new Contest(doc.select(".rtable .left a").first().text(), contestURL);
		System.out.println(ret);
		
		System.out.println("There are " + nProblem + " problems.");
		for (int i = 1; i <= nProblem; i++) {
			String problemURL = "http://codeforces.com/"
					+ problems.get(i).select("a").first().attr("href");
			crawler.get(problemURL);
			System.out.print(crawler.getTitle());
			doc = Jsoup.parse(crawler.getPageSource());
			Elements temp = doc.getElementsByClass("title");
			Problem problem = new Problem(temp.first().text());
			Elements inputs = doc.select(".input pre");
			Elements outputs = doc.select(".output pre");
			for (int j = 0; j < inputs.size(); j++) {
				problem.addSampleTest(inputs.get(j).text(), outputs.get(j).text());
			}
			// List<String> input = problem.getInputs();
			// for (int j = 0; j < input.size(); j++) {
			// FileMaker.buildFile((char) ('a' + (i - 1)) + ".in." + (j + 1),
			// input.get(j));
			// }
			// List<String> output = problem.getInputs();
			// for (int j = 0; j < output.size(); j++) {
			// FileMaker.buildFile((char) ('a' + (i - 1)) + ".out." + (j + 1),
			// output.get(j));
			// }
			System.out.println(": " + problem.numSampleTest() + " sample tests");
			ret.addProblem(problem);
		}
		return ret;
	}

	/**
	 * @param url
	 * @throws IllegalAccessException
	 *             : login failed
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
		if (crawler.getPageSource().indexOf("Invalid handle or password") != -1) {
			throw new IllegalAccessException();
		}
		
		handleColor = Jsoup.parse(crawler.getPageSource()).select(".avatar a").get(1).attr("class");
		String[] tokens = handleColor.split("-");
		handleColor = tokens[tokens.length - 1];
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
