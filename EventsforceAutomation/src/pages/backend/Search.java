package pages.backend;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.CommonElements;

public class Search extends CommonElements{

	@FindBy(xpath=".//td[contains(text(),'With data matching')]//following::img[1]") 
	public  WebElement PF_dataMatchingEditButton;
	
	@FindBy(name="txtItemName1") 
	public  WebElement PF_searchFirstName;
	
	@FindBy(name="txtItemName2") 
	public  WebElement PF_searchLastName;
	
	@FindBy(name="selOperator1") 
	public  WebElement PF_FirstnameCombo;
	
	@FindBy(name="selOperator2") 
	public  WebElement PF_LastnameCombo;
	
	@FindBy(xpath=".//td[contains(text(),'3 People Found')]") 
	public  WebElement PF_peopleFound;
	
}
