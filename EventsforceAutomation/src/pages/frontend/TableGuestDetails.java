package pages.frontend;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.CommonElements;

public class TableGuestDetails extends CommonElements {

	@FindBy(id="butAdd") 
	public  WebElement PF_addSeats;
	
	@FindBy(id="butSubmit") 
	public  WebElement PF_buttonSubmit;
}
