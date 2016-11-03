package pages.backend;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonElements;

public class HomePage extends CommonElements {

	@FindBy(id="search-button") 
	public  WebElement PF_homePageSearchButton;
	
}
