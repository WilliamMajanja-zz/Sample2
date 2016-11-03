package businessClasses;

public class SimpleEventCreator extends EventCreator {
	

public SimpleEventCreator() {
	super();		
	}


public void createSimpleEvent(String fileName) throws Exception {
		
		Header = "SIMPLE EVENT";
		createEvents(fileName);
	}

	
@Override
protected void setupEvent() {
		
		log.logFunctionTrace("entry");
		
		if(eventData.get("ChangeDayName").equalsIgnoreCase("Yes"))
		{
			goToEventCreationMenu();
			setEventPropertiesPage();
			setCapacityPage();
		}
		else
		{
			goToEventCreationMenu();
			setEventPropertiesPage();
			setEventRegistrationPage();
			selectAttendeeCategory();
			createEventPrice();
			selectVATcode();
			setPaymentMethod();
			makeEventLive();
		}
		
		log.logFunctionTrace("exit");
	}

	
@Override
protected void testEvent() {
		
		log.logFunctionTrace("entry");
		
		if(eventData.get("ChangeDayName").equalsIgnoreCase("Yes"))
		{
			log.logMessage("Test for changing Day Names is successful");
		}
		else
		{
			SimpleRegistration simpleRegistration = new SimpleRegistration(driver, systemData, eventData, registrationData);
			simpleRegistration.performSimpleRegistration();
			log.logMessage("1 Registration successful");
		
			viewRegistrationsInBackend("All", "Registered", 1);
		}
		
		log.logFunctionTrace("exit");
	}
}
