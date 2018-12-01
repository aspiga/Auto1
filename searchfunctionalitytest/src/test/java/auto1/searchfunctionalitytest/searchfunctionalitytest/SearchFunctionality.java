package auto1.searchfunctionalitytest.searchfunctionalitytest;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import auto1.searchfunctionalitytest.searchfunctionalitytest.pages.AutoheroSearchPage;
import auto1.searchfunctionalitytest.searchfunctionalitytest.utils.WebDriverSupport;


public class SearchFunctionality {
	WebDriver driver;
	WebDriverSupport support;
	AutoheroSearchPage searchPage;
	
	@Parameters ({"browser"})
	 @BeforeTest
	  public void beforeMethod(String browserType) {	
		    support = new WebDriverSupport(driver);
		    driver = support.getBrowser(browserType);
			driver.manage().window().maximize();
			searchPage = AutoheroSearchPage.navigateTo(driver);
	 }
	 
	 @Test
	 public void registrationYearVerify() throws InterruptedException {
		 
		 searchPage.getSortedCarsList("2015", "Höchster Preis");
		 Thread.sleep(2000);
		 int [] registrationYears = searchPage.getRegistrationYears();
		 assertTrue(registrationDateVerify(registrationYears));
	 }
	 
	 @Test(dependsOnMethods = {"registrationYearVerify"}, alwaysRun=true)
	 public void descendingPriceVerify() {		 
		 int[] carPrices = searchPage.getCarsPrices();
		assertTrue(priceSortVerify(carPrices));
	 }
	 
	 @AfterTest
	 public void closeBrowser(){
		 driver.quit();
	 }
	 
	 private boolean registrationDateVerify(int[] registrationYears) {
			boolean success = true;
			for (int i = 0; i < registrationYears.length ; i++) {

				if (registrationYears[i]<2015) {
					success = false;
					return success;
				}
				}
				
			return success;
		}
	 
	 
	 public boolean priceSortVerify(int[] carPrices) {
			boolean success = true;
			
			for (int i=1; i<carPrices.length; i++) {
				if (carPrices[i]>carPrices[i-1]){
					success = false;
					return success;
				}	
			}
			return success;
		}

}
