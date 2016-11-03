package businessClasses;

import commonLibrary.Login;
import commonLibrary.Logout;

public class AttendanceEventCreator extends EventCreator {

public AttendanceEventCreator() {
	super();
	}


public void createAttendanceEvent(String fileName) throws Exception {
		
		Header = "ATTENDANCE RECORDING EVENT";
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
	
	Login login = new Login();
	Logout logout = new Logout();
	
	AttendanceRegistration attendanceRegistration = new AttendanceRegistration(driver, systemData, eventData, registrationData);
	
	String url = attendanceRegistration.getWebAddress("Registration");
	
	logout.logoutFromEventsForce(driver);	
	
	int registrationCounter = 0;
	
	for( int i = 1 ; i < 3 ; i++) /* Perform 2 Registrations */
		{
				registrationCounter = registrationCounter + 1;
				registrationData.put("regCounter", (registrationCounter + ""));
				attendanceRegistration.performRegistrationAndRecordAttendance(url);
				log.logDataToFile("info", registrationCounter + " registration", "performed");
		}
		
		driver = login.loginToEventsForce(systemData);
		initEventDriver(driver);
		
		attendanceRegistration = new AttendanceRegistration(driver, systemData, eventData, registrationData);

		attendanceRegistration.recordAttendance();
		
		log.logFunctionTrace("exit");
	}
}
