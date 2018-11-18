package auto1.searchfunctionalitytest.searchfunctionalitytest.utils;




import org.openqa.selenium.By;


import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {
	

static public void waitLocatedElement(WebDriverWait wait, By locator){
	
	wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		
	}
	

}
