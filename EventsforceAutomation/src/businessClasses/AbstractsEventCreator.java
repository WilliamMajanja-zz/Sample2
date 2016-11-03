package businessClasses;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import pages.backend.AbstractSettings;
import pages.backend.PresentationMethods;
import pages.backend.ReviewingCriteria;
import pages.backend.Topics;

public class AbstractsEventCreator extends EventCreator {

	protected Topics topics;
	protected ReviewingCriteria reviewingCriteria;
	protected PresentationMethods presentationMethods;
	protected AbstractSettings abstractSettings;
			
public AbstractsEventCreator() {
		super();
	}

	
public void createAbstractsEvent(String fileName) throws Exception {
		
		Header = "ABSTRACTS EVENT";
		createEvents(fileName);
	}
	
	
@Override
protected void setupEvent() {
		
		log.logFunctionTrace("entry");
		
		goToEventCreationMenu();	
		setEventPropertiesPage();
		setEventRegistrationPage();
		createAbstractTopics();
		createReviewingCriteria();
		setPresentationMethods();
		setAbstractSettings();
		makeEventLive();
		
		log.logFunctionTrace("exit");
	}


@Override
protected void testEvent() {
		
		log.logFunctionTrace("entry");
		
		/* Creates 2 registrations */
		AbstractSubmissionsAndRegistrations abstracts = new AbstractSubmissionsAndRegistrations(driver, systemData, eventData, registrationData);
		abstracts.performAbstractRegistration();
		
		viewRegistrationsInBackend("All", "Registered", 2);
		
		/* performs 2 abstract submissions */
		abstracts.createAbstractSubmissions(registrationData);
		
		log.logFunctionTrace("exit");
	}
	
	
protected void createAbstractTopics() {
		
		String abstractTopic = "Planets";
		
		topics = PageFactory.initElements(driver, Topics.class);
		
		pageNavigator.navigateTo("Event", "Abstracts Setup", "Topics");
		pageLoader.waitForPageToLoad(driver, topics.PF_addNew);
		topics.PF_addNew.click();
		
		pageLoader.waitForPageToLoad(driver, topics.PF_description);
		topics.PF_description.sendKeys(abstractTopic);
		topics.PF_saveByName.click();
		pageLoader.waitForPageToLoad(driver, topics.PF_addNew);
		
		log.logMessage("Abstract topic " + abstractTopic +" created successfully");
	}
	
	
protected void createReviewingCriteria() {
		
		String abstractCriteria = "Criteria 1";
		
		reviewingCriteria = PageFactory.initElements(driver, ReviewingCriteria.class);
		
		pageNavigator.navigateTo("Event", "Abstracts Setup", "Reviewing Criteria");
		pageLoader.waitForPageToLoad(driver, reviewingCriteria.PF_addNew);
		reviewingCriteria.PF_addNew.click();
		
		pageLoader.waitForPageToLoad(driver, reviewingCriteria.PF_description);
		reviewingCriteria.PF_description.sendKeys(abstractCriteria);
		
		Select selectTopic = new Select(reviewingCriteria.PF_selectTopic);
		selectTopic.selectByVisibleText("Planets");
		
		reviewingCriteria.PF_saveByName.click();
		pageLoader.waitForPageToLoad(driver, reviewingCriteria.PF_addNew);
		
		log.logMessage("Abstract criteria " + abstractCriteria +" created successfully");
	}
	
	
protected void setPresentationMethods() {
		
		presentationMethods = PageFactory.initElements(driver, PresentationMethods.class);
		
		pageNavigator.navigateTo("Event", "Abstracts Setup", "Presentation Methods");
		
		pageLoader.waitForPageToLoad(driver, presentationMethods.PF_presentMethodOral);
		if (!presentationMethods.PF_presentMethodOral.isSelected()) 
		{	
			presentationMethods.PF_presentMethodOral.click();
			log.logDataToFile("info", "Presentation Method selected :", "oral");
		}
		
		pageLoader.waitForPageToLoad(driver, presentationMethods.PF_presentMethodPoster);
		if (!presentationMethods.PF_presentMethodPoster.isSelected()) 
		{	
			presentationMethods.PF_presentMethodPoster.click();
			log.logDataToFile("info", "Presentation Method selected :", "poster");
		}
		
		pageLoader.waitForPageToLoad(driver, presentationMethods.PF_presentMethodEither);
		if (!presentationMethods.PF_presentMethodEither.isSelected()) 
		{	
			presentationMethods.PF_presentMethodEither.click();
			log.logDataToFile("info", "Presentation Method selected :", "either");
		}
		
	}
	
	
protected void setAbstractSettings() {
		
		abstractSettings = PageFactory.initElements(driver, AbstractSettings.class);
		
		String maxCoAuthor = "1";
		String maxAbstractPerPerson = "2";
		
		pageNavigator.navigateTo("Event", "Abstracts Setup", "AbstractSettings");
		pageLoader.waitForPageToLoad(driver, abstractSettings.PF_allowTextSubmission);
		
		abstractSettings.PF_maxCoAuthors.clear();
		abstractSettings.PF_maxCoAuthors.sendKeys(maxCoAuthor);
		abstractSettings.PF_maxAbstractPerPerson.clear();
		abstractSettings.PF_maxAbstractPerPerson.sendKeys(maxAbstractPerPerson);
		
		if (!abstractSettings.PF_allowTextSubmission.isSelected()) 
		{	
			abstractSettings.PF_allowTextSubmission.click();
			log.logDataToFile("info", "Option Selected :", "Allow text submission");
		}
		
		if (!abstractSettings.PF_allowTables.isSelected()) 
		{	
			abstractSettings.PF_allowTables.click();
			log.logDataToFile("info", "Option Selected :", "Allow tables");
		}
		
		if (!abstractSettings.PF_allowFileUploads.isSelected()) 
		{	
			abstractSettings.PF_allowFileUploads.click();
			log.logDataToFile("info", "Option Selected :", "Allow file uploads");
		}
		
		abstractSettings.PF_saveEvent.click();
		pageLoader.waitForPageToLoad(driver, abstractSettings.PF_allowTextSubmission);
		
		log.logMessage("Abstract settings updated");
	}
}
