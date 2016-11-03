package businessClasses;


import java.util.Set;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import pages.backend.Search;

public class StandardSearch extends EventCreator{

public StandardSearch() {
		super();
	}


public void performStandardSearch(String fileName) throws Exception {
				
			Header = "STANDARD SEARCH";
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
			performStandardSearch();
		
			log.logFunctionTrace("exit");
	}


protected void performStandardSearch() {
	
			Search search = PageFactory.initElements(driver, Search.class);
			pageNavigator.navigateTo("Search and Reports","Search");
		
			pageLoader.waitForPageToLoad(driver, search.PF_dataMatchingEditButton);
		
			search.PF_dataMatchingEditButton.click();
		
			Set<String> handles = driver.getWindowHandles();
		
			int j = 0;
			String originalHandle = "";
		
			for(String i : handles)
			{
				/* Store the original handle */
				if(j==0)
				{
					originalHandle = i;
				}
			
				/* Switch to pop-up window */
				if(j==1)
				{
					driver.switchTo().window(i);
				
					Select firstNameSearchCriteria = new Select(search.PF_FirstnameCombo);
					firstNameSearchCriteria.selectByValue("1");
					search.PF_searchFirstName.sendKeys(registrationData.get("FirstName"));
					log.logDataToFile("info", "First name entered for search", registrationData.get("FirstName"));
				
					Select lastNameSearchCriteria = new Select(search.PF_LastnameCombo);
					lastNameSearchCriteria.selectByValue("1");
					search.PF_searchLastName.sendKeys(registrationData.get("LastName"));
					log.logDataToFile("info", "Last name entered for search", registrationData.get("LastName"));
				
					search.PF_continueButton.click();
					log.logMessage("Clicked Continue button...");
				
					driver.switchTo().window(originalHandle);
				}
			
				j = j+1;
			}
		
			pageLoader.waitForPageToLoad(driver, search.PF_searchButton);
			search.PF_searchButton.click();
			
			pageLoader.waitForPageToLoad(driver, search.PF_peopleFound);
			log.logMessage("People were searched successfully using standard search");
	}
}
