package pages.backend;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.CommonElements;

public class PresentationMethods extends CommonElements {

	@FindBy(xpath=".//td[contains(text(),'oral')]//following::td[2]/input") 
	public  WebElement PF_presentMethodOral;
	
	@FindBy(xpath=".//td[contains(text(),'poster')]//following::td[2]/input") 
	public  WebElement PF_presentMethodPoster;
	
	@FindBy(xpath=".//td[contains(text(),'either')]//following::td[2]/input") 
	public  WebElement PF_presentMethodEither;
}
