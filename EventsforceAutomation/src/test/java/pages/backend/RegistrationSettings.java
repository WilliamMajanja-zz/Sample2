package pages.backend;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.CommonElements;

public class RegistrationSettings extends CommonElements {

	@FindBy(id="identify-attendees_1-label") 
	public  WebElement PF_regmodeEmail;
	
	@FindBy(id="identify-attendees_2-label") 
	public  WebElement PF_regmodeUsername;
	
	@FindBy(id="identify-attendees_3-label") 
	public  WebElement PF_regmodeRegref;
	
	@FindBy(id="rad-access-public") 
	public  WebElement PF_accessPublic;
	
	@FindBy(id="rad-access-private-sharedcodes") 
	public  WebElement PF_accessPrivate;
	
	@FindBy(id="manage-entry-codes-button") 
	public  WebElement PF_manageCode;
	
	@FindBy(name="txtEntryCode") 
	public  WebElement PF_eventCodeName;
	
	@FindBy(id="rad-access-invitation-only") 
	public  WebElement PF_accessInvitation;
	
	@FindBy(id="allow-registration") 
	public  WebElement PF_regAllow;
	
	@FindBy(id="allow-registration-amendment") 
	public  WebElement PF_regAmendAllow;
	
	@FindBy(id="allow-cancellation") 
	public  WebElement PF_regCancelAllow;
	
	@FindBy(id="allow-session-amendment") 
	public  WebElement PF_regSessionAllow;
	
	@FindBy(id="allow-group-registrations") 
	public  WebElement PF_regGroupAllow;
	
}
