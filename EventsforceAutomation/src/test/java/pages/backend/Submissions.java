package pages.backend;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.CommonElements;

public class Submissions extends CommonElements {

	@FindBy(id="abstractTopicFilter") 
	public  WebElement PF_abstractTopicFilter;
	
	@FindBy(id="abstractMethodFilter") 
	public  WebElement PF_abstractMethodFilter;
	
	@FindBy(id="abstractMainFilter") 
	public  WebElement PF_abstractMainFilter;
	
	@FindBy(xpath=".//span[contains(text(),'Showing')]") 
	public  WebElement PF_abstractTotalSubmissions;
}
