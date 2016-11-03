package pages.backend;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.CommonElements;

public class AwardSettings extends CommonElements {

	@FindBy(name="lastSubmissionDate") 
	public  WebElement PF_lastAwardSubmission;
	
	@FindBy(name="optionalFileUpload") 
	public  WebElement PF_optionalFileUpload;
	
	@FindBy(name="useSections") 
	public  WebElement PF_useSections;
	
	@FindBy(name="chkManyEntriesPerCategory") 
	public  WebElement PF_chkManyEntriesPerCategory;
	
	@FindBy(name="selDeletionMode") 
	public  WebElement PF_selDeletionMode;
}
