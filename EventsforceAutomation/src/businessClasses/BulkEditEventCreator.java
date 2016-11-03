package businessClasses;

import org.openqa.selenium.support.PageFactory;
import pages.backend.BulkEdit;
import org.testng.Assert;

public class BulkEditEventCreator extends EventCreator {

protected BulkEdit bulkEdit;

public BulkEditEventCreator() {
	super();
	}


public void createBulkEditEvent(String fileName) throws Exception {
			
		Header = "BULK EDIT EVENT";
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
	
		/* Perform 3 registrations */
		for (int i = 0 ; i < 3 ; i++)
		{
			SimpleRegistration simpleRegistration = new SimpleRegistration(driver, systemData, eventData, registrationData);
			simpleRegistration.performSimpleRegistration();
			log.logMessage((i+1) + " Registration successful");
		}
	
		viewRegistrationsInBackend("All", "Registered", 3);
		performBulkEdit();
	
		log.logFunctionTrace("exit");
	}


protected void performBulkEdit() {
	
	bulkEdit = PageFactory.initElements(driver, BulkEdit.class);
	
	pageNavigator.navigateTo("RegistrationMain", "Bulk Edit");
	pageLoader.waitForPageToLoad(driver, bulkEdit.PF_numPerPage);
	
	bulkEdit.PF_searchButton.click();
	pageLoader.waitForPageToLoad(driver, bulkEdit.PF_searchResultString);
	
	for(int i = 0 ; i < bulkEdit.PF_selectPerson.size(); i++)
	{
		bulkEdit.PF_selectPerson.get(i).click();
	}
	log.logMessage("All people are selected by clicking on the individual checkboxes");
	
	bulkEdit.PF_editButton.click();
	pageLoader.waitForPageToLoad(driver, bulkEdit.PF_updateButton);
	
	for(int i = 0 ; i < bulkEdit.PF_updateSelected.size(); i++)
	{
		bulkEdit.PF_updateSelected.get(i).click();
	}
	
	bulkEdit.PF_updateParams.get(2).sendKeys("XYZ");
	log.logMessage("Company name changed to XYZ");
	
	bulkEdit.PF_updateButton.click();
	pageLoader.waitForPageToLoad(driver, bulkEdit.PF_duplicateSaveButton);
	
	bulkEdit.PF_duplicateSaveButton.click();
	
	pageLoader.waitForPageToLoad(driver, bulkEdit.PF_saveByName);
	bulkEdit.PF_saveByName.click();
	
	pageLoader.waitForPageToLoad(driver, bulkEdit.PF_recordUpdatedMessage);
	Assert.assertEquals(bulkEdit.PF_recordUpdatedMessage.getText(), "3 records were updated", "Bulk editing failure. Some records are not updated");
	
	log.logMessage("Bulk edit is successful");
	}
}
