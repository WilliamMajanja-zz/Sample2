package pages.backend;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.CommonElements;

public class Registrations extends CommonElements{

	@FindBy(id="registrationsMainFilter") 
	public  WebElement PF_regMainFilter;
	
	@FindBy(id="statusFilter") 
	public  WebElement PF_regStatusFilter;
	
	@FindBy(xpath=".//*[@id='main-grid']/div[4]/span") 
	public  WebElement PF_showingResult;
}
