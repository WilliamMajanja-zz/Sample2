package pages.backend;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MenuHeader {
	
	/* Plus button */
	@FindBy(id="ef_menu_button_add") 
	public  WebElement PF_plusButton;
		
	@FindBy(linkText="Event") 
	public  WebElement PF_newEvent;
	
	/* Test / Preview button */
	@FindBy(id="ef_menu_button_preview") 
	public  WebElement PF_eventPreview;
	
	/* Event Dashboard button */
	@FindBy(xpath=".//a[@title='Event Dashboard']") 
	public  WebElement PF_eventDashboard;
		
	@FindBy(id="ef_menu_button_logout") 
	public  WebElement PF_logout;
	
	/*
	 * Events Menu
	 */
	
	@FindBy(linkText="Event") 
	public  WebElement PF_eventMenuMain;
	
	@FindBy(linkText="Settings") 
	public  WebElement PF_eventSettings;
	
	@FindBy(linkText="Properties") 
	public  WebElement PF_eventSettingsProperties;
	
	@FindBy(linkText="Registration") 
	public  WebElement PF_eventSettingsRegistration;
	
	@FindBy(linkText="Attendee Categories") 
	public  WebElement PF_eventSettingsAttendee;
	
	@FindBy(linkText="Financial Setup") 
	public  WebElement PF_eventFinSetup;
	
	@FindBy(linkText="Prices") 
	public  WebElement PF_eventFinsetupPrice;
	
	@FindBy(id="ef_menu_li_FINSETT") 
	public  WebElement PF_eventFinsetupSettings;
	
	@FindBy(id="ef_menu_li_PAYMETH") 
	public  WebElement PF_eventFinsetupPaymeth;
	
	@FindBy(id="ef_menu_li_TABL") 
	public  WebElement PF_tableBooking;
	
	@FindBy(id="ef_menu_li_TABLELIST") 
	public  WebElement PF_tableList;
	
	@FindBy(id="ef_menu_li_TABLSEAT") 
	public  WebElement PF_seatArrangement;
	
	@FindBy(id="ef_menu_li_TABLSETT") 
	public  WebElement PF_tableSettings;
	
	@FindBy(linkText="Abstracts Setup") 
	public  WebElement PF_abstractsSetup;
	
	@FindBy(linkText="Topics") 
	public  WebElement PF_abstractTopics;
	
	@FindBy(linkText="Reviewing Criteria") 
	public  WebElement PF_abstractCriteria;
	
	@FindBy(linkText="Reviewers") 
	public  WebElement PF_abstractReviewers;
	
	@FindBy(linkText="Presentation Methods") 
	public  WebElement PF_abstractPresentationMethods;
	
	@FindBy(xpath=".//*[@id='ef_menu_li_ABSSETT']/a") 
	public  WebElement PF_abstractSettings;
	
	@FindBy(linkText="Abstracts Management") 
	public  WebElement PF_abstractManagement;
	
	@FindBy(linkText="Submissions") 
	public  WebElement PF_abstractSubmissions;
	
	@FindBy(linkText="Reviews") 
	public  WebElement PF_abstractReviews;
	
	@FindBy(linkText="Capacity") 
	public  WebElement PF_capacity;
	
	@FindBy(linkText="Awards Setup") 
	public  WebElement PF_awardSetup;
	
	@FindBy(xpath=".//*[@id='ef_menu_li_AWCAT']/a") 
	public  WebElement PF_awardCategory;
	
	@FindBy(xpath=".//*[@id='ef_menu_li_AWSEC']/a") 
	public  WebElement PF_awardSection;
	
	@FindBy(linkText="Award Types") 
	public  WebElement PF_awardTypes;
	
	@FindBy(xpath=".//*[@id='ef_menu_li_AWSETT']/a") 
	public  WebElement PF_awardSettings;
	
	@FindBy(linkText="Awards Management") 
	public  WebElement PF_awardManagement;
	
	@FindBy(linkText="Entries") 
	public  WebElement PF_awardEntries;
	
	/*
	 * Registrations Menu
	 */
	
	@FindBy(xpath=".//*[@id='ef_menu_li_BOOKADM']/a") 
	public  WebElement PF_registrationsMain;
	
	@FindBy(xpath=".//*[@id='ef_menu_li_REGISTRATIONLIST']/a") 
	public  WebElement PF_registrationsSub;
	
	@FindBy(xpath=".//*[@id='ef_menu_li_ATTREC']/a") 
	public  WebElement PF_attendanceRecodring;
	
	@FindBy(xpath=".//*[@id='ef_menu_li_BULK']/a") 
	public  WebElement PF_bulkEdit;
	
	/*
	 * Website Menu
	 */
	
	@FindBy(id="ef_menu_li_FRONT") 
	public  WebElement PF_website;
	
	@FindBy(xpath=".//*[@id='ef_menu_li_FRONTSITESETNG']/a") 
	public  WebElement PF_websiteMainSettings;
	
	@FindBy(xpath=".//*[@id='ef_menu_li_WEBADDR']/a") 
	public  WebElement PF_webAddresses;
	
	/*
	 * Search and Reports Menu
	 */
	
	@FindBy(linkText="Search & Reports") 
	public  WebElement PF_searchReports;
	
	@FindBy(linkText="Search") 
	public  WebElement PF_search;
}
