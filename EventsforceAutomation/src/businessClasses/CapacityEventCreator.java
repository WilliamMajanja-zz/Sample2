package businessClasses;

import org.openqa.selenium.WebDriver;
import commonLibrary.Login;
import commonLibrary.Logout;

public class CapacityEventCreator extends EventCreator {


public CapacityEventCreator() {
		super();
	}
	
	
public void createCapacityEvent(String fileName) throws Exception {
		
		Header = "CAPACITY CHECKING EVENT";
		createEvents(fileName);
	}


@Override
protected void setupEvent() {
		
		log.logFunctionTrace("entry");
		
		goToEventCreationMenu();
		setEventPropertiesPage();
		setEventRegistrationPage();
		selectAttendeeCategory();
		setCapacityPage();
		createEventPrice();
		selectVATcode();
		setPaymentMethod();
		makeEventLive();
		
		log.logFunctionTrace("exit");
	}

	
@Override
protected void testEvent() {
		
	log.logFunctionTrace("entry");
	
	CapacityRegistration capacityRegistration = new CapacityRegistration(driver, systemData, eventData, registrationData);
	String url = capacityRegistration.getWebAddress("Registration");
	
	Logout logout = new Logout();
	logout.logoutFromEventsForce(driver);
	log.logMessage("Logged out from Eventsforce");
	
	switch(registrationData.get("RegistrationType"))
		{
			case "SingleDayCapacity":
					
					int registrationCounterSingle = 0;
					for( int i = 1 ; i < 3 ; i++) /* Perform 2 Registrations for Single Day Event */
					{
						registrationCounterSingle = registrationCounterSingle + 1;
						registrationData.put("regCounter", (registrationCounterSingle + ""));
						capacityRegistration.createRegistrationAgainstCapacity(url);
						log.logDataToFile("info", registrationCounterSingle + " single day registration", "performed");
					}
						break;
		
			case "MultiDayCapacity":
		
					int registrationCounterMulti = 0;
					for( int i = 1 ; i < 5 ; i++) /* Perform 4 registrations for a multi day event */
					{
						registrationCounterMulti = registrationCounterMulti + 1;
						registrationData.put("regCounter", (registrationCounterMulti + ""));
						capacityRegistration.createRegistrationAgainstCapacity(url);
						log.logDataToFile("info", registrationCounterMulti + " multi day registration", "performed");
						
					}
						break;
	
			default:
					log.logDataToFile("error", "Please check your excel file for RegistrationType. You provided", registrationData.get("RegistrationType"));
					break;
		}
	
		Login login = new Login();
		WebDriver tempDrv = login.loginToEventsForce(systemData);
		driver = tempDrv;
		
		initEventDriver(driver);
		
		log.logFunctionTrace("exit");
	}
}
