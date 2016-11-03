package businessClasses;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pages.backend.AwardCategories;
import pages.backend.AwardEntries;
import pages.backend.AwardSections;
import pages.backend.AwardSettings;
import pages.backend.AwardTypes;

public class AwardsEventCreator extends EventCreator {

protected int numberOfcategories;
protected int numberOfAwardTypes;
protected String[] awardCategory;
protected String[] awardSubmission;
protected String[] awardType;



public AwardsEventCreator() {
		super();		
	}


public void createAwardsEvent(String fileName) throws Exception  {
			
			Header = "AWARDS EVENT";
			createEvents(fileName);
	}
	
	
@Override
protected void setupEvent() {
		
		log.logFunctionTrace("entry");
	
		goToEventCreationMenu();
		setEventPropertiesPage();
		setEventRegistrationPage();
		createEventPrice();
		selectVATcode();
		setPaymentMethod();
		setupAwardsSettings();
		setupAwardsSections();
		setupAwardCategories();
		setupAwardTypes();
		makeEventLive();
		
		log.logFunctionTrace("exit");
	}


@Override
protected void testEvent() {
		
		log.logFunctionTrace("entry");
	
		AwardSubmission awardSubmission = new AwardSubmission(driver, systemData, eventData, registrationData);
		awardSubmission.performAwardSubmission();
			
		viewAwardEntries();
		
		log.logFunctionTrace("exit");
	}


protected void setupAwardsSettings() {
	
		AwardSettings awardSettings = PageFactory.initElements(driver, AwardSettings.class);
	
		pageNavigator.navigateTo("Event", "Awards Setup", "Award Settings");
		pageLoader.waitForPageToLoad(driver, awardSettings.PF_lastAwardSubmission);
	
		awardSettings.PF_lastAwardSubmission.sendKeys(eventData.get("EventEndDate"));
		log.logDataToFile("info", "Last Award Submission Date", eventData.get("EventEndDate"));
	
		Select selectFileUpload = new Select(awardSettings.PF_optionalFileUpload);
		selectFileUpload.selectByVisibleText("Optional");
		log.logDataToFile("info", "File Upload is", "Optional");
	
		awardSettings.PF_useSections.click();
		log.logDataToFile("info", "Use Sections", "Checked");
	
		awardSettings.PF_chkManyEntriesPerCategory.click();
		log.logDataToFile("info", "Allow submissions for more categories", "Checked");
	
		Select deletionMode = new Select(awardSettings.PF_selDeletionMode);
		deletionMode.selectByValue("lastSubmissionDate");
		log.logDataToFile("info", "Allow Deletion of Submitted Entries", "Until Last Submission date");
	
		awardSettings.PF_saveByName.click();
	
		log.logMessage("Award Settings are done");
	}


protected void setupAwardsSections() {
	
		AwardSections awardSections = PageFactory.initElements(driver, AwardSections.class);
		
		String minEntries = "2";
		String maxEntries = "6";
		
		pageNavigator.navigateTo("Event", "Awards Setup", "Sections");
		pageLoader.waitForPageToLoad(driver, awardSections.PF_addNew);
		
		awardSections.PF_addNew.click();
		pageLoader.waitForPageToLoad(driver, awardSections.PF_description);
		
		awardSections.PF_description.sendKeys(eventData.get("AwardSection"));
		awardSections.PF_minEntries.sendKeys(minEntries);
		awardSections.PF_maxEntries.sendKeys(maxEntries);
		
		awardSections.PF_saveByName.click();
		
		log.logMessage("Award Section is created");
	}


protected void setupAwardCategories() {
	
		AwardCategories awardCategories = PageFactory.initElements(driver, AwardCategories.class);
	
		awardCategory = eventData.get("AwardCategories").split(",");
		numberOfcategories = awardCategory.length;
		
		pageNavigator.navigateTo("Event", "Awards Setup", "Categories");
		
		for(int i = 0 ; i < numberOfcategories ; i++)
		{
			awardCategory[i] = awardCategory[i].trim();
			
			pageLoader.waitForPageToLoad(driver, awardCategories.PF_addNew);
			awardCategories.PF_addNew.click();
			pageLoader.waitForPageToLoad(driver, awardCategories.PF_descriptionByName);
		
			awardCategories.PF_descriptionByName.sendKeys(awardCategory[i]);
			log.logDataToFile("info", "Award category added", awardCategory[i]);
			
			Select selectSection = new Select(awardCategories.PF_selectSection);
			selectSection.selectByVisibleText(eventData.get("AwardSection"));
	
			awardCategories.PF_saveByName.click();
			
		}
			log.logMessage("Award Categories are created");
   	}


protected void setupAwardTypes() {
	
		AwardTypes awardTypes = PageFactory.initElements(driver, AwardTypes.class);
	
		awardType = eventData.get("AwardTypes").split(",");
		numberOfAwardTypes = awardType.length;
		
		pageNavigator.navigateTo("Event", "Awards Setup", "Award Types");
		
		for(int i = 0 ; i < numberOfAwardTypes ; i++)
		{
			awardType[i] = awardType[i].trim();
			
			pageLoader.waitForPageToLoad(driver, awardTypes.PF_addNew);
			awardTypes.PF_addNew.click();
			pageLoader.waitForPageToLoad(driver, awardTypes.PF_description);
	
			awardTypes.PF_description.sendKeys(awardType[i]);
			log.logDataToFile("info", "New Award Type added", awardType[i]);
			
			Select section = new Select(awardTypes.PF_selectSection);
			section.selectByVisibleText(eventData.get("AwardSection"));
	
			awardTypes.PF_saveByName.click();
		}
		
			log.logMessage("Award Types are created");
	}


protected void viewAwardEntries() {
	
		AwardEntries awardEntries = PageFactory.initElements(driver, AwardEntries.class);
	
		awardSubmission = registrationData.get("AwardSubmissions").split(",");
		
		pageNavigator.navigateTo("Event", "Awards Management", "Entries");
		pageLoader.waitForPageToLoad(driver, awardEntries.PF_selectEntry);
	
		for(int i = 0 ; i < numberOfcategories ; i++)
		{
			awardSubmission[i] = awardSubmission[i].trim();
			
			Select filterCategory = new Select(awardEntries.PF_filterByCategory);
			filterCategory.selectByVisibleText(awardCategory[i]);
			
			Select filterSubmitter = new Select(awardEntries.PF_filterBySubmitterID);
			filterSubmitter.selectByVisibleText("All");
	
			awardEntries.PF_SearchButtonAwards.click();
			pageLoader.waitForPageToLoad(driver, awardEntries.PF_selectEntry);
	
			Assert.assertEquals(awardEntries.PF_projectName.getText().trim(), awardSubmission[i], "Match not found for Project Name");
			Assert.assertEquals(awardEntries.PF_category.getText(), awardCategory[i] , "Match not found for Category");
			Assert.assertEquals(awardEntries.PF_submissionStatus.getText(), "Submitted" , "Match not found for Submission Status");
		}
		
		log.logMessage("Award entries are verified successfully");
	}
}
