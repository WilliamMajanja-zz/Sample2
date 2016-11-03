package pages.frontend;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.CommonElements;

public class DaySelectionPage extends CommonElements {

	@FindBy(xpath=".//input[@title='Go Back']//preceding::table[2]//tr") 
	public  List<WebElement> PF_whichDayToAttendTable;
	
	@FindBy(xpath=".//td[contains(text(),'Day')]//following::img[@title='This day is not available']") 
	public  List<WebElement> PF_dayNotAvailable;
	
	@FindBy(id="butSaveDays") 
	public  WebElement PF_saveDays;
	
	@FindBy(id="ef-id-page-heading") 
	public  WebElement PF_regNotAvailable;
}
