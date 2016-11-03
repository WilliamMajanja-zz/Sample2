package pages.backend;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonElements;

public class BulkEdit extends CommonElements {

	@FindBy(name="numPerPage") 
	public  WebElement PF_numPerPage;
	
	@FindBy(xpath=".//td[contains(text(),'3 People Found')]") 
	public  WebElement PF_searchResultString;
	
	@FindBy(xpath=".//input[contains(@name,'chkPersonID')]") 
	public  List<WebElement> PF_selectPerson;
	
	@FindBy(id="uptAll") 
	public  WebElement PF_updateButton;
	
	@FindBy(xpath=".//input[contains(@name,'chk_toBeUpdated')]") 
	public  List<WebElement> PF_updateSelected;
	
	@FindBy(xpath=".//input[contains(@name,'updateAll')]") 
	public  List<WebElement> PF_updateParams;
	
	@FindBy(xpath=".//*[text()='3 records were updated']") 
	public  WebElement PF_recordUpdatedMessage;
	
	@FindBy(xpath=".//img[contains(@src,'save')]") 
	public  WebElement PF_duplicateSaveButton;
}
