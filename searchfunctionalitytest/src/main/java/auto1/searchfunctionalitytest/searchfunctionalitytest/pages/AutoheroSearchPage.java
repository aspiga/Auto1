package auto1.searchfunctionalitytest.searchfunctionalitytest.pages;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import auto1.searchfunctionalitytest.searchfunctionalitytest.utils.WaitUtils;

public class AutoheroSearchPage extends BasePage{
	
	WebDriver driver;
	WebDriverWait wait;

	By registration = By.cssSelector("#app > div > main > div.root___3C6lR.container > div > div.col-md-3 > div > div > div > div:nth-child(3) > div.label___3agdr > span");
	By selectyear = By.name("yearRange.min");
	By pricefilter = By.name("sort");
	By specClass = By.className("specItem___2gMHn");
	By priceClass = By.className("totalPrice___3yfNv");
	
	List<WebElement> els;
	List<WebElement> prices;
	String regex = "^(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$";
	
	
	public AutoheroSearchPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 20);
	}
	
	public static AutoheroSearchPage navigateTo(WebDriver driver) {
		driver.get("https://www.autohero.com/de/search/");
		return new AutoheroSearchPage(driver);
	}
	
	public void getSortedCarsList(String regfilter, String sortfilter) {
		clickElement(registration);
		WaitUtils.waitLocatedElement(wait, selectyear);
		selectOption(selectyear, regfilter);
		WaitUtils.waitLocatedElement(wait, pricefilter);
		selectOption(pricefilter, sortfilter);
	}
	
	public void retrievedElements() {
		els = driver.findElements(specClass);
		prices = driver.findElements(priceClass);
	}
	
	public boolean verifyRegistrationDate() {
		boolean success = true;
		Pattern pattern = Pattern.compile(regex);
		for (WebElement el: els) {
			String eltext = el.getText().substring(el.getText().length() -7, el.getText().length());
			Matcher matcher = pattern.matcher(eltext);
			if (matcher.matches()) {
			System.out.println(eltext);
			int year = Integer.parseInt(eltext.substring(eltext.length() - 4, eltext.length()));
			if (year<2015) {
				success = false;
				return success;
			}
			}
			
		}
		return success;
	}
		
	public boolean verifySortedPrice() {
		boolean success = true;
		int i = 0;
		int[] descPrices = new int[prices.size()];
		for (WebElement price: prices) {
			String text = price.getText().substring(0, price.getText().length() -2);
			System.out.println(text);
			int  descprice= Integer.parseInt(text.replace(".", ""));
			descPrices[i] = descprice;
			if ((i>0)&& (descPrices[i]>descPrices[i-1])){
				success = false;
				return success;
			}
			i++;	
		}
		return success;
	}
	

}


