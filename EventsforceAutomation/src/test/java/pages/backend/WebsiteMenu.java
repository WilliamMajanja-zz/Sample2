package pages.backend;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WebsiteMenu {

	/*
	 * This section contains all the elements present on the
	 * Website > Settings > Web Addresses
	 */

	@FindBy(name="txtEventURL") 
	public  WebElement PF_homepageUrl;
	
	@FindBy(name="txtRegURL") 
	public  WebElement PF_registrationUrl;
	
	@FindBy(name="txtAmendURL") 
	public  WebElement PF_amendmentUrl;
	
	@FindBy(name="txtDeclineURL") 
	public  WebElement PF_declineUrl;
	
	@FindBy(name="txtTCURL") 
	public  WebElement PF_termConditionsUrl;
	
	@FindBy(name="txtVenueURL") 
	public  WebElement PF_VenueUrl;
	
	@FindBy(name="txtDailyAgendaURL") 
	public  WebElement PF_dailyAgendaUrl;
	
	@FindBy(name="txtAbstractSubmitterLoginURL") 
	public  WebElement PF_abstractSubmitterLoginUrl;
	
	@FindBy(name="txtAbstractReviewerLoginURL") 
	public  WebElement PF_reviewerLoginUrl;
}
