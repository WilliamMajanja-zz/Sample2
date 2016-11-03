package businessClasses;

import java.util.Map;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.DynamicXPath;
import pages.backend.AttendanceRecording;

public class AttendanceRegistration extends Registration {

protected WebDriver capacityDriver;
	
protected AttendanceRecording attendanceRecording;


protected AttendanceRegistration(WebDriver driver, Map<String, String> systemDataTemp, Map<String, String> eventDataTemp, Map<String, String> registrationDataTemp) {
		
	super(driver, systemDataTemp, eventDataTemp, registrationDataTemp);
	attendanceRecording =  PageFactory.initElements(driver, AttendanceRecording.class); 
 	}


protected void performRegistrationAndRecordAttendance(String tempUrl){
	
	WebDriver tempDriver = createDriver(tempUrl);
	initRegistrationDriver(tempDriver);
	enterEmail();
	selectAttendee();
	enterPersonalDetailsAndProceed();
	CapacityRegistration registration = new CapacityRegistration(tempDriver, systemData, eventData, registrationData);
	registration.selectDaysToAttend(tempDriver);
	verifyRegContactDetailsOnBasketPage();
	selectPaymentMethod();
	tempDriver.close();
	}


protected void recordAttendance() {
	
	DynamicXPath dynamicXPath = new DynamicXPath();
	
	pageNavigator.navigateTo("RegistrationMain", "Attendance Recording");
	
	int recordAttendanceTimes = attendanceRecording.PF_recordAttendanceButton.size();
		
	for(int i = 0 ; i < recordAttendanceTimes ; i++)
	{
		attendanceRecording.PF_recordAttendanceButton.get(i).click();
		log.logMessage("Clicked attendance recording button");
		pageLoader.waitForPageToLoad(driver, attendanceRecording.PF_checkAttended.get(i));
		
		attendanceRecording.PF_ALLPeople.click();
		log.logMessage("Clicked ALL to show all attendees");
		pageLoader.waitForPageToLoad(driver, attendanceRecording.PF_checkAttended.get(i));
				
		for(int j = 0 ; j < attendanceRecording.PF_checkAttended.size(); j++ )
		{
			attendanceRecording.PF_checkAttended.get(j).click();
			pageLoader.waitForMenuToLoad();
			log.logMessage("Updating the attendance recording");
		}
		
		attendanceRecording.PF_backImage.click();
		pageLoader.waitForPageToLoad();
		
		String actualAttendanceCount = driver.findElement(By.xpath(dynamicXPath.attendedCountPartOne + (i+1) + dynamicXPath.attendedCountPartTwo)).getText().trim();
		String expectedAttendanceCount = (i+1) + " / " + (i+1);
		Assert.assertEquals(actualAttendanceCount, expectedAttendanceCount, "Attendance not recorded correctly" );
	}
	
		log.logMessage("Updated attendance successfully");
	}


@Override
protected void createRegistration() {}

}
