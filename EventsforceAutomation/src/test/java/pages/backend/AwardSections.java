package pages.backend;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.CommonElements;

public class AwardSections extends CommonElements {

	@FindBy(id="minEntries") 
	public  WebElement PF_minEntries;
	
	@FindBy(id="maxEntries") 
	public  WebElement PF_maxEntries;
}
