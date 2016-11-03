package commonLibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import pages.backend.MenuHeader;

public class Logout {
	
public void logoutFromEventsForce(WebDriver driver){
		
		MenuHeader menuHeader = PageFactory.initElements(driver, MenuHeader.class);
		menuHeader.PF_logout.click();
		driver.close();
		driver.quit();
	}
}
