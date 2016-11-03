package pages.backend;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.CommonElements;

public class AwardEntries extends CommonElements {

	@FindBy(name="filterByCategory")
	public WebElement PF_filterByCategory;
	
	@FindBy(name="filterBySubmitterID")
	public WebElement PF_filterBySubmitterID;
	
	@FindBy(xpath=".//div[contains(text(),'Search')]") 
	public  WebElement PF_SearchButtonAwards;
	
	@FindBy(xpath=".//input[contains(@name,'chk_')]") 
	public  WebElement PF_selectEntry;
	
	@FindBy(xpath=".//td[@title='view entry']") 
	public  WebElement PF_projectName;
	
	@FindBy(xpath=".//td[@title='view entry']//following::td[2]") 
	public  WebElement PF_category;
	
	@FindBy(xpath=".//td[@title='view entry']//following::td[10]") 
	public  WebElement PF_submissionStatus;
	
}
