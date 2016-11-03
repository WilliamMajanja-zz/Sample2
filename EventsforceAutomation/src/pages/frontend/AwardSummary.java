package pages.frontend;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonElements;

public class AwardSummary extends CommonElements {

	@FindBy(linkText="Enter Category") 
	public  WebElement PF_enterCategory;
	
	@FindBy(xpath=".//span[contains(@id,'expand_contract_icon_section')]")
	public WebElement PF_expandSection;

	@FindBy(xpath=".//input[contains(@id,'txtQuestion_')]")
	public WebElement PF_projectName;
	
	@FindBy(xpath=".//input[contains(@name,'chkEntry')]")
	public WebElement PF_checkEntry;
}
