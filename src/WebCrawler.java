import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;


public class WebCrawler {
	
	private String username;
	private String password;
	private HtmlUnitDriver crawler;
	
	public WebCrawler(String username, String password){
		crawler = null;
		this.username = username;
		this.password = password;
	}
	
	public String getUsername(){
		return username;
	}
	
	public String getPassword(){
		return password;
	}
	
	public HtmlUnitDriver getCrawler(){
		return crawler;
	}
	
	public String getLocation(){
		if(crawler == null) return "Offline";
		else return crawler.getCurrentUrl();
	}
	
	public void parseContest(String contestURL){
		System.out.println("Parsing");
		crawler.get(contestURL);
	}
	
	public void login(String url){
		System.out.println("Crawler " + username + " is logging in to " + url);
		crawler = new HtmlUnitDriver(false);
		crawler.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		crawler.get(url);
		crawler.findElement(By.name("handle")).clear();
		crawler.findElement(By.name("handle")).sendKeys("ping128");
		crawler.findElement(By.name("password")).clear();
		crawler.findElement(By.name("password")).sendKeys("4631847");
		crawler.findElement(By.className("submit")).click();
		System.out.println("Login Finished");
	}
	
	public String toString(){
		String s = "Crawler: " + username + "\n";
		if(crawler == null){
			s += "Offline";
		} else {
			s += "Online: " + crawler.getCurrentUrl() + "\n";
		}
		return s;
	}
}
