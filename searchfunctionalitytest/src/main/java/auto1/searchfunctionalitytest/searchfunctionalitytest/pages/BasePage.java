package auto1.searchfunctionalitytest.searchfunctionalitytest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class BasePage {
	WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getElement(By locator){
		return driver.findElement(locator);
	}
	
	public void setElement(By locator, String value){
		getElement(locator).sendKeys(value);
	}
	
	public void selectOption(By locator, String value){
		new Select(getElement(locator)).selectByVisibleText(value);
	}
	
	public void clickElement(By locator){
		getElement(locator).click();
	}
	
	public void clearElement(By locator){
		getElement(locator).clear();
	}

}
