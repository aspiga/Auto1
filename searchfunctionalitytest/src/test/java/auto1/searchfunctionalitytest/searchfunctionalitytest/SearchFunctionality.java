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
	AutoheroSearchPage auSearch;
	
	@Parameters ({"browser"})
	 @BeforeTest
	  public void beforeMethod(String browserType) {	
		    support = new WebDriverSupport(driver);
		    driver = support.getBrowser(browserType);
			driver.manage().window().maximize();
			auSearch = AutoheroSearchPage.navigateTo(driver);
	 }
	 
	 @Test
	 public void verifyRegistrationYear() throws InterruptedException {
		 
		 auSearch.getSortedCarsList("2015", "Höchster Preis");
		 Thread.sleep(2000);
		 auSearch.retrievedElements();
		 assertTrue(auSearch.verifyRegistrationDate());
	 }
	 
	 @Test(dependsOnMethods = {"verifyRegistrationYear"}, alwaysRun=true)
	 public void verifyDescendingPrice() {
		 assertTrue(auSearch.verifySortedPrice());
	 }
	 
	 @AfterTest
	 public void closeBrowser(){
		 driver.quit();
	 }

}
