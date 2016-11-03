package pages.backend;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.CommonElements;

public class AttendeeCategories extends CommonElements {

	@FindBy(xpath=".//td[contains(text(),'Exhibitor')]//following::td[3]/select") 
	public  WebElement PF_attendeeCatExhibitor;
}
