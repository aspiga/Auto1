package auto1.searchfunctionalitytest.searchfunctionalitytest.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverSupport {
	WebDriver driver;
	
	public WebDriverSupport(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebDriver getBrowser(String browserType) {
		
		switch(browserType) {
		case "Firefox":
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\Michyeza\\Documents\\Andrea\\AutomationProjects\\geckodriver.exe");
			driver = new FirefoxDriver();
			break;
			
		case "Chrome":
			System.setProperty("webdriver.chrome.driver","C:\\Users\\Michyeza\\Documents\\Andrea\\AutomationProjects\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("disable-infobars");
			driver = new ChromeDriver(options);
			break;
		}
		return driver;
	}
	

}
