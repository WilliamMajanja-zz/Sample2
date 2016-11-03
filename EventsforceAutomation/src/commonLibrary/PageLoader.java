package commonLibrary;

import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageLoader {

	private int globalWait;
	private int pageLoadWait;
	private int menuWait;
	
public PageLoader(Map<String, String> systemData){
		
		globalWait = Integer.valueOf(systemData.get("globalWait"));
		pageLoadWait = Integer.valueOf(systemData.get("pageloadWait"));
		menuWait = Integer.valueOf(systemData.get("menuWait"));
	}
	

public void waitForPageToLoad() {
	
		try 
		{
			Thread.sleep(globalWait*pageLoadWait);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

public void waitForMenuToLoad() {
	
		try 
		{
			Thread.sleep(globalWait*menuWait);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
public void waitForPageToLoad(WebDriver driver, WebElement element) {
	
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		try {
			Thread.sleep(globalWait*menuWait);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

