package auto1.searchfunctionalitytest.searchfunctionalitytest.pages;

import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import auto1.searchfunctionalitytest.searchfunctionalitytest.utils.WaitUtils;

public class AutoheroSearchPage extends BasePage{
	
	WebDriver driver;
	WebDriverWait wait;

	By yearFilter = By.cssSelector("div[data-qa-selector='filter-year']");
	By selectYear = By.name("yearRange.min");
	By priceFilter = By.name("sort");
	By specList = By.cssSelector("ul[data-qa-selector='spec-list']");
	By spec = By.cssSelector("li[data-qa-selector='spec']");
	By priceElement = By.cssSelector("div[data-qa-selector='price']");
	
	
	List<WebElement> speclistElements;
	List<WebElement> priceElements;
	//String regex = "^(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$";
	
	
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
		clickElement(yearFilter);
		WaitUtils.waitLocatedElement(wait, selectYear);
		selectOption(selectYear, regfilter);
		WaitUtils.waitLocatedElement(wait, priceFilter);
		selectOption(priceFilter, sortfilter);
	}
	
	public int[] getRegistrationYears() {

		speclistElements = driver.findElements(specList);
		int[] registratioYears = new int[speclistElements.size()];
		int i = 0;
		for(WebElement specList : speclistElements) {
			List <WebElement> specItems = specList.findElements(spec);
			registratioYears[i] =  Integer.parseInt(specItems.get(0).getText().substring(5));
			
			System.out.println(registratioYears[i]);
			i++;
		}
		return registratioYears;
		
	}
	
	public int[] getCarsPrices() {
		priceElements =  driver.findElements(priceElement);
		int[] prices = new int[priceElements.size()] ;
		int i = 0;
		for (WebElement priceElement: priceElements) {
			String text = priceElement.getText().substring(0, priceElement.getText().length() -2);
			System.out.println(text);
			prices[i]= Integer.parseInt(text.replace(".", ""));
			i++;
		}
		return prices;
	}
	
	/*public boolean verifyRegistrationDate() {
		boolean success = true;
		Pattern pattern = Pattern.compile(regex);
		for (WebElement el: els) {
			String eltext = el.getText().substring(el.getText().length() -7, el.getText().length());
			Matcher matcher = pattern.matcher(eltext);
			if (matcher.matches()) {
			System.out.println(eltext);
			int year = Integer.parseInt(eltext.substring(eltext.length() - 4, eltext.length()));
			
			int year = Integer.parseInt(registrationDate);
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
*/	

}


