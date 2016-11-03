package businessClasses;

import java.util.Map;
import org.testng.Assert;

import commonLibrary.Login;
import commonLibrary.Logout;
import commonLibrary.PageLoader;
import commonLibrary.PageNavigator;
import pages.DynamicXPath;
import pages.backend.MenuHeader;
import pages.frontend.AbstractSubmission;
import pages.frontend.BasketPage;
import pages.frontend.CategorySelection;
import pages.frontend.DaySelectionPage;
import pages.frontend.Email;
import pages.frontend.PersonalDetails;
import pages.frontend.TableGuestDetails;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CapacityRegistration extends Registration {

protected String url;
protected WebDriver capacityDriver;
protected Login login;
protected Logout logout;

protected CapacityRegistration(WebDriver driver, Map<String, String> systemDataTemp, Map<String, String> eventDataTemp, Map<String, String> registrationDataTemp ) {
		super(driver, systemDataTemp, eventDataTemp,  registrationDataTemp);
	}


protected void createRegistrationAgainstCapacity(String tempUrl) {
		
		url = tempUrl;
		
		if(registrationData.get("RegistrationType").equalsIgnoreCase("SingleDayCapacity"))
		{
				checkCapacityForSingleDayEvent();
				log.logMessage("Checking Capacity for a single day event...");
		}
		
		if(registrationData.get("RegistrationType").equalsIgnoreCase("MultiDayCapacity"))
		{
				checkCapacityForMultiDayEvent();
				log.logMessage("Checking Capacity for a multi day event...");
		}
	}


protected void checkCapacityForSingleDayEvent() {
	
		if(registrationData.get("regCounter").equals("1"))
		{
			initNewDriver();
			enterEmail();
			selectAttendee();
			enterPersonalDetailsAndProceed();
			verifyRegContactDetailsOnBasketPage();
			selectPaymentMethod();
		}
	
		/* Check when capacity is reached, registration is not possible */
		if(registrationData.get("regCounter").equals("2"))
		{
			initNewDriver();
			Assert.assertEquals(daySelectionPage.PF_regNotAvailable.getText().trim(), "Registration is not available", "Please check capacity settings. Capacity should be full.");
			log.logMessage("Capacity for day 2 is full as expected");
			
		}
	
		capacityDriver.close();
		log.logMessage("New driver is closed");
	}


protected void checkCapacityForMultiDayEvent() {
	
		/* Check when capacity is reached, registration is not possible */
		if(registrationData.get("regCounter").equals("4"))
		{
			initNewDriver();
			Assert.assertEquals(daySelectionPage.PF_regNotAvailable.getText().trim(), "Registration is not available", "Please check capacity settings. Capacity should be full.");
			log.logMessage("Capacity for all days  is full as expected");
		}
		else
		{
			initNewDriver();
			enterEmail();
			selectAttendee();
			enterPersonalDetailsAndProceed();
			selectDaysToAttend(capacityDriver);
			verifyRegContactDetailsOnBasketPage();
			selectPaymentMethod();
		}
		
		capacityDriver.close();
		log.logMessage("New driver is closed");
	}


protected void selectDaysToAttend(WebDriver drvTemp) {
	
		WebDriver newDriver = drvTemp;
	
		//systemData = tempSysData;
		//registrationData = tempRegData;
	
		DynamicXPath dynamicXPath = new DynamicXPath();
		PageLoader pageLoader = new PageLoader(systemData);
		pageLoader.waitForPageToLoad(newDriver, daySelectionPage.PF_saveDays);
	
		int size = daySelectionPage.PF_whichDayToAttendTable.size();
		String actualEventDays = (size-1) + "";
	
		/* Verify that the table size matches with the total number of event days set in Event settings */
		Assert.assertEquals(actualEventDays, registrationData.get("TotalEventDays"), "No. of days do not match");
	
		for(int i = 1 ; i < size ; i++)
		{
			switch(registrationData.get("regCounter")) 
			{
				case "1" : /* For 1st registration, Select all days */
				
					if(!newDriver.findElement(By.xpath(dynamicXPath.whichDayToAttend + i + "']")).isSelected())
					{
						newDriver.findElement(By.xpath(dynamicXPath.whichDayToAttend + i + "']")).click();
						log.logMessage("Selecting all days for 1st registration");
					}
					break;
		
				case "2" : /* For 2nd registration, Day 1 is not available. Select remaining days */
				
					Assert.assertEquals(daySelectionPage.PF_dayNotAvailable.size(), 1, "Day 1 should not be available for selection. Please check Capacity Settings");
				
					if(i != (size-1))
						{
							int j = 1;
							if(!newDriver.findElement(By.xpath(dynamicXPath.whichDayToAttend + (i+j) + "']")).isSelected())
							{
								newDriver.findElement(By.xpath(dynamicXPath.whichDayToAttend + (i+j) + "']")).click();
								log.logMessage("For 2nd registration, Day 1 is not available. Selected remaining days");
							}
						}
					break;
				
				case "3" : /* For 3rd registration, Day 1 & Day 2 are not available. Select remaining days */
				
					Assert.assertEquals(daySelectionPage.PF_dayNotAvailable.size(), 2, "Day 2 should not be available for selection. Please check Capacity Settings");
				
					if((i != (size-2)) && (i != (size-1)))
					{
						int j = 2;
						if(!newDriver.findElement(By.xpath(dynamicXPath.whichDayToAttend + (i+j) + "']")).isSelected())
						{
							newDriver.findElement(By.xpath(dynamicXPath.whichDayToAttend + (i+j) + "']")).click();
							log.logMessage("For 3rd registration, Day 1 & Day 2 are not available. Selected remaining days");
						}
					}
					break;
			
			default:
					log.logDataToFile("error", "This option is not implemented yet", "Please check the class file");
				break;
		}
	 }
		daySelectionPage.PF_saveDays.click();
	}


protected void initNewDriver(){
	
		capacityDriver = createDriver(url);
		log.logMessage("New driver is created");
			
		pageNavigator = new PageNavigator(capacityDriver,systemData);
		pageLoader = new PageLoader(systemData);
	
		menuHeader = PageFactory.initElements(capacityDriver, MenuHeader.class);
		email = PageFactory.initElements(capacityDriver, Email.class);
		personalDetails = PageFactory.initElements(capacityDriver, PersonalDetails.class);
		basketPage = PageFactory.initElements(capacityDriver, BasketPage.class);
		categorySelection = PageFactory.initElements(capacityDriver, CategorySelection.class);
		tableGuestDetails = PageFactory.initElements(capacityDriver, TableGuestDetails.class);
		abstractSubmission = PageFactory.initElements(capacityDriver, AbstractSubmission.class);
		daySelectionPage = PageFactory.initElements(capacityDriver, DaySelectionPage.class);
		wait = new WebDriverWait(capacityDriver, 30);	
	}


@Override
protected void createRegistration() {
	}
}
