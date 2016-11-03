package businessClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import commonLibrary.Login;
import commonLibrary.Logout;
import pages.frontend.Email;

public class EventEntryCodeCreator extends EventCreator {

	
public EventEntryCodeCreator() {
		super();
	}


public void createEventEntryCode(String fileName) throws Exception {
				
		Header = "EVENT ENTRY CODE TEST";
		createEvents(fileName);
	}


@Override
protected void setupEvent() {
	
		log.logFunctionTrace("entry");
	
		goToEventCreationMenu();
		setEventPropertiesPage();
		setEventRegistrationPage();
		selectAttendeeCategory();
		createEventPrice();
		selectVATcode();
		setPaymentMethod();
		makeEventLive();

		log.logFunctionTrace("exit");
	}


@Override
protected void testEvent() {
	
		log.logFunctionTrace("entry");
	
		Logout logout = new Logout();
		Login login = new Login();
		
		SimpleRegistration registration = new SimpleRegistration(driver, systemData, eventData, registrationData);
	
		String url = registration.getWebAddress("Registration");
		
		logout.logoutFromEventsForce(driver);
		WebDriver tempDriver = registration.createDriver(url);
		
		Email email = PageFactory.initElements(tempDriver, Email.class);
		email.PF_entryCode.sendKeys("abc");
		email.PF_proceed2Button.click();
		log.logMessage("Event code abc used : " + email.PF_invalidCode.getText());
		log.logMessage("Person can not log in with an invalide code as expected");
		
		email.PF_entryCode.sendKeys(eventCode);
		email.PF_proceed2Button.click();
		
		email.PF_menuRegister.get(1).click();
		log.logMessage("Logged in using the correct event entry code as : " + eventCode);
		tempDriver.quit();
		
		driver = login.loginToEventsForce(systemData);
				
		log.logFunctionTrace("exit");
	}	
}
