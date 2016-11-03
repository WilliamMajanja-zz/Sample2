package pages.frontend;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.CommonElements;

public class CategorySelection extends CommonElements {

	@FindBy(xpath=".//*[contains(text(),'Exhibitor')]//preceding::input[1]") 
	public  WebElement PF_selectExhibitor;
	
	@FindBy(id="submit") 
	public  WebElement PF_selectCatProceed;
}
