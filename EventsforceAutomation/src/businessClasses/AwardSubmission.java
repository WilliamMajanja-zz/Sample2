package businessClasses;

import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import pages.DynamicXPath;
import pages.frontend.AwardSummary;

public class AwardSubmission extends Registration {

protected String[] awardSubmission;
protected String[] awardCategory;
	
protected AwardSubmission(WebDriver driver, Map<String, String> systemDataTemp, Map<String, String> eventDataTemp, Map<String, String> registrationDataTemp) {
		super(driver, systemDataTemp, eventDataTemp, registrationDataTemp );
	}
	
	
protected void performAwardSubmission(){
		
		testRegistrationDefault();
	}


@Override
protected void createRegistration() {
	
		enterEmail();
		enterPersonalDetails();
		personalDetails.PF_saveAndProceed.click();
		makeSubmission();
	}


protected void makeSubmission() {
	
		awardSubmission = registrationData.get("AwardSubmissions").split(",");
		int numberOfsubmissions = awardSubmission.length;
		
		awardCategory = eventData.get("AwardCategories").split(",");
		
		AwardSummary awardSummary = PageFactory.initElements(driver, AwardSummary.class);
		pageLoader.waitForPageToLoad(driver, awardSummary.PF_enterCategory);
	
		DynamicXPath xpath = new DynamicXPath();
		
		for(int i = 0 ; i < numberOfsubmissions; i++)
		{
			awardSubmission[i] = awardSubmission[i].trim();
			awardCategory[i] = awardCategory[i].trim();
			
			awardSummary.PF_enterCategory.click();
			awardSummary.PF_expandSection.click();
			
			driver.findElement(By.xpath(xpath.categoryLink1 + awardCategory[i] + xpath.categoryLink2)).click();;
			log.logMessage(awardCategory[i] + " Category entered");
			
			pageLoader.waitForPageToLoad(driver, awardSummary.PF_projectName);
			awardSummary.PF_projectName.sendKeys(awardSubmission[i]);
			log.logDataToFile("info", "Submission started for ", awardSubmission[i]);
			
			awardSummary.PF_saveByName.click();
	
			pageLoader.waitForPageToLoad(driver, awardSummary.PF_checkEntry);
			awardSummary.PF_checkEntry.click();
			awardSummary.PF_submitButton.click();
	
			Alert alert = driver.switchTo().alert();
			alert.accept();
			
			log.logMessage("Submission completed successfully");
			
			pageLoader.waitForPageToLoad(driver, awardSummary.PF_enterCategory);
		}
	}
}
