package pages.frontend;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.CommonElements;

public class Email extends CommonElements{

	/* Register menu button */
	
	@FindBy(xpath=".//span[contains(text(),'Register')]") 
	public  List<WebElement> PF_menuRegister;
	
	/* Email id for registration */
	
	@FindBy(id="txtEmail") 
	public  WebElement PF_email;
		
	@FindBy(name="txtEntryCode") 
	public  WebElement PF_entryCode;
	
	@FindBy(xpath=".//td[contains(text(),'Invalid Code')]") 
	public  WebElement PF_invalidCode;
}
