package businessClasses;

import java.util.Map;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import commonLibrary.Utility;
import pages.backend.Submissions;
import pages.frontend.AbstractSubmission;
import pages.frontend.PersonalDetails;

public class AbstractSubmissionsAndRegistrations extends Registration {

	protected WebDriver abstractDriver;
	protected String abstractTitle;
	protected String coAuthorFname;
	protected String coAuthorLname;
	protected String coAuthorCompany;
	protected String coAuthorEmail;
	protected String abstractContent;
	protected String url;
	protected Submissions submissions;
		
protected AbstractSubmissionsAndRegistrations(WebDriver driver, Map<String, String> systemDataTemp, Map<String, String> eventDataTemp, Map<String, String> registrationDataTemp) {
		super(driver, systemDataTemp, eventDataTemp, registrationDataTemp );
		submissions = PageFactory.initElements(driver, Submissions.class);
	}

	
protected void performAbstractRegistration(){
		
		int totalRegistrations = 0;
		
		for(int i = 0 ; i < 2; i++)
		{
			testRegistrationDefault();
			totalRegistrations = totalRegistrations + 1;
			log.logDataToFile("info", totalRegistrations + " registration", "performed");
		}
	}


@Override
protected void createRegistration() {

		enterEmail();
		selectAttendee();
		enterPersonalDetailsAndProceed();
		verifyRegContactDetailsOnBasketPage();
		selectPaymentMethod();
		verifyPaymentMethod();
	}
	
	
protected void createAbstractSubmissions(Map<String, String> registrationDataTemp){
		
		int totalSubmissions = 0;
		url = getWebAddress("Submitter Login");
		
		for(int i = 1 ; i < 3; i++)
		{
			abstractTitle = "Earth" + i;
			coAuthorFname = "co firstname" + i;
			coAuthorLname = "co lastname" + i;
			coAuthorCompany = "ABC" + i;
			coAuthorEmail = "co" + i + "@dummy.dummy";
			abstractContent = "Abstract Content : add an abstarct that should be a maximim fifty characters in length since that is what the system has as its minmum requirements";
		
			createDriver();
			populateAbstractSubmitterDetails(i);
			populateAbstractSubmissionDetails();
			populateAbstractContent();
		    previewAbstractSubmission(i);
		    abstractDriver.close();
		    totalSubmissions = totalSubmissions + 1;
		    log.logDataToFile("info", totalSubmissions + " abstract", "Submitted");
		}
			
		viewAbstractSubmissionsInBackend("Planets", "All", "Submitted", totalSubmissions);
	}
	

protected void createDriver() {
		
		abstractDriver = new FirefoxDriver();
		abstractDriver.get(url);
		abstractDriver.manage().window().maximize();
		pageLoader.waitForPageToLoad();
		
		abstractSubmission = PageFactory.initElements(abstractDriver, AbstractSubmission.class);
		personalDetails = PageFactory.initElements(abstractDriver, PersonalDetails.class);
		
		abstractSubmission.PF_createAccount.click();
		log.logMessage("New Firefox driver is created");
	}
	
	
protected void populateAbstractSubmitterDetails(int i) {
	
		wait.until(ExpectedConditions.visibilityOf(personalDetails.PF_firstName));
		personalDetails.PF_firstName.sendKeys(registrationData.get("FirstName") + i);
		personalDetails.PF_lastName.sendKeys(registrationData.get("LastName") + i);
		personalDetails.PF_company.sendKeys(registrationData.get("Company") + i);
		personalDetails.PF_address1.sendKeys(registrationData.get("Adr1") + i);
		personalDetails.PF_address2.sendKeys(registrationData.get("Adr2") + i);
		personalDetails.PF_town.sendKeys(registrationData.get("Town") + i);
		personalDetails.PF_postcode.sendKeys(registrationData.get("Postcode"));
		personalDetails.PF_phone.sendKeys(registrationData.get("Phone"));
	
		Utility util = new Utility();
		String dummy_email = "abstract";
		dummy_email = dummy_email + util.generateTimeStamp() + "@abc.dummy";
		personalDetails.PF_abstractEmail.sendKeys(dummy_email);
		log.logDataToFile("info", "Email id used for abstract submission", dummy_email);
		personalDetails.PF_saveAndProceed.click();
		wait.until(ExpectedConditions.visibilityOf(abstractSubmission.PF_addAbstract));
		log.logMessage("Abstract submitter details are populated");
	}
	
	
protected void populateAbstractSubmissionDetails() {
		
		abstractSubmission.PF_addAbstract.click();
		
		pageLoader.waitForPageToLoad(driver, abstractSubmission.PF_abstractTitle);
		abstractSubmission.PF_abstractTitle.sendKeys(abstractTitle);
		abstractSubmission.PF_abstractPresentationMethod.click();
	
		if(!abstractSubmission.PF_abstractSubmitterAsAuthor.isSelected())
		{
			abstractSubmission.PF_abstractSubmitterAsAuthor.click();
			log.logMessage("Use the submitter as the main author option is checked");
		}
	
		abstractSubmission.PF_coAuthorFirstname.sendKeys(coAuthorFname);
		abstractSubmission.PF_coAuthorLastname.sendKeys(coAuthorLname);
		abstractSubmission.PF_coAuthorCompany.sendKeys(coAuthorCompany);
		abstractSubmission.PF_coAuthorEmail.sendKeys(coAuthorEmail);
	
		Select selectPresentingAuthor = new Select(abstractSubmission.PF_selPresentingAuthor);
		selectPresentingAuthor.selectByValue("mainAuthor");
		Select selectCorrespondingAuthor = new Select(abstractSubmission.PF_selCorrespondingAuthor);
		selectCorrespondingAuthor.selectByValue("coAuthor_1");
	
		abstractSubmission.PF_saveAndProceed.click();
		log.logMessage("Abstract submission details are populated");
	}
	
	
protected void populateAbstractContent() {
		
		abstractDriver.switchTo().frame(abstractSubmission.PF_iframe);
		JavascriptExecutor js=(JavascriptExecutor) abstractDriver;
		js.executeScript("arguments[0].innerHTML ='" + abstractContent + "'" , abstractSubmission.editor);
		abstractDriver.switchTo().defaultContent();
	    
		abstractSubmission.PF_saveAndProceed.click();
		log.logMessage("Abstract contents are populated");
	}
	
	
protected void previewAbstractSubmission(int i) {
		
		String expectedHeading = "Abstract Submission Preview";
	    String actualHeading = abstractSubmission.PF_abstractSubmissionHeading.getText();
	    String actualTitle = abstractSubmission.PF_abstractFinalTitle.getText();
	    String actualTopic = abstractSubmission.PF_abstractFinalTopic.getText();
	    String actualAuthor = abstractSubmission.PF_abstractFinalName.getText();
	    String expectedAuthor = registrationData.get("FirstName") + i + " " + registrationData.get("LastName") + i;
	    String actualCompany = abstractSubmission.PF_abstractFinalCompany.getText();
	   
	    Assert.assertEquals(actualHeading, expectedHeading, "Abstract Submission Preview page is not displayed");
	    Assert.assertEquals(actualTitle, abstractTitle , "Abstract Titles do not match");
	    Assert.assertEquals(actualTopic, "Planets", "Abstract Topics do not match");
	    Assert.assertEquals(actualAuthor, expectedAuthor, "Authors do not match" );
	    Assert.assertEquals(actualCompany, registrationData.get("Company") + i , "Company does not match");
	    abstractSubmission.PF_saveAndProceed.click();
	    log.logMessage("Abstract preview OK");
	 }
	
	
protected void viewAbstractSubmissionsInBackend( String topicFilter, String methodFilter, String mainFilter, int tempTotalSubmissions ) {
		
		pageNavigator.navigateTo("Event", "Abstracts Management", "Submissions");
		
		Select tempTopicFilter = new Select(submissions.PF_abstractTopicFilter);
		tempTopicFilter.selectByVisibleText(topicFilter);
		
		Select tempMethodFilter = new Select(submissions.PF_abstractMethodFilter);
		tempMethodFilter.selectByVisibleText(methodFilter);
		
		Select tempShowFilter = new Select(submissions.PF_abstractMainFilter);
		tempShowFilter.selectByVisibleText(mainFilter);
		
		String results = submissions.PF_abstractTotalSubmissions.getText();
		String showing = "Showing " + tempTotalSubmissions + " submissions";
		Assert.assertEquals(results, showing, "No.of submissions are not equal to " + tempTotalSubmissions);
		log.logMessage("Total abstracts submitted are " + tempTotalSubmissions);
	}
}
