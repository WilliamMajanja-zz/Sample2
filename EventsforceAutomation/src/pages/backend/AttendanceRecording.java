package pages.backend;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.CommonElements;

public class AttendanceRecording extends CommonElements {

	@FindBy(name="butAttendanceDay") 
	public  List<WebElement> PF_recordAttendanceButton;
	
	@FindBy(xpath=".//input[contains(@id,'chkAttended')]") 
	public  List<WebElement> PF_checkAttended;
	
	@FindBy(xpath=".//a[contains(text(),'ALL')]") 
	public  WebElement PF_ALLPeople;
	
	@FindBy(xpath=".//td[contains(text(),'Attendance Recording')]//preceding::img[2]") 
	public  WebElement PF_backImage;
}
