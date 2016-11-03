package pages.frontend;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.CommonElements;

public class AbstractSubmission extends CommonElements{

	@FindBy(partialLinkText="Create your") 
	public  WebElement PF_createAccount;
	
	@FindBy(xpath=".//input[@type='button'][@value='Add']") 
	public  WebElement PF_addAbstract;
	
	@FindBy(id="abstractTitleText") 
	public  WebElement PF_abstractTitle;
	
	@FindBy(xpath=".//input[@name='preferredPresentationMethod'][@value='2']") 
	public  WebElement PF_abstractPresentationMethod;
	
	@FindBy(id="chkAuthorIsSubmitter") 
	public  WebElement PF_abstractSubmitterAsAuthor;
	
	@FindBy(id="selPresentingAuthor") 
	public  WebElement PF_selPresentingAuthor;
	
	@FindBy(id="selCorrespondingAuthor") 
	public  WebElement PF_selCorrespondingAuthor;
	
	@FindBy(xpath=".//input[contains(@id,'txtQuestion_1_coAuthor')]") 
	public  WebElement PF_coAuthorFirstname;
	
	@FindBy(xpath=".//input[contains(@id,'txtQuestion_2_coAuthor')]") 
	public  WebElement PF_coAuthorLastname;
	
	@FindBy(xpath=".//input[contains(@id,'txtQuestion_14_coAuthor')]") 
	public  WebElement PF_coAuthorCompany;
	
	@FindBy(xpath=".//input[contains(@id,'txtQuestion_12_coAuthor')]") 
	public  WebElement PF_coAuthorEmail;
	
	@FindBy(xpath=".//iframe[contains(@id, 'moxieElement')]") 
	public  WebElement PF_iframe;
	
	@FindBy(css="body") 
	public  WebElement editor;
	
	@FindBy(id="ef-id-page-heading") 
	public  WebElement PF_abstractSubmissionHeading;
	
	@FindBy(xpath=".//td[contains(text(),'Title')]//following::td[1]") 
	public  WebElement PF_abstractFinalTitle;
	
	@FindBy(xpath=".//td[contains(text(),'Topic')]//following::td[1]") 
	public  WebElement PF_abstractFinalTopic;
	
	@FindBy(xpath=".//td[contains(text(),'Author')]//following::td[1]") 
	public  WebElement PF_abstractFinalName;
	
	@FindBy(xpath=".//td[contains(text(),'Company')]//following::td[1]") 
	public  WebElement PF_abstractFinalCompany;
}
