package commonLibrary;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import pages.backend.MenuHeader;

public class PageNavigator {
			
	private PageLoader pageLoader;
	private WebDriver driver;
	
public PageNavigator(WebDriver driver, Map<String, String> systemData){
		
		this.driver = driver;
		pageLoader = new PageLoader(systemData);
	}
	

public void navigateTo(String... arg1){
		
		String menuSoFar = "";
		Actions build = new Actions(driver);
		MenuHeader menuHeader = PageFactory.initElements(driver, MenuHeader.class);
		Log log = new Log();
		
		for(String menuItem: arg1) 
		{
			switch(menuItem)
			{
			
			case "Plus":
				build.moveToElement(menuHeader.PF_plusButton).build().perform(); 
				menuHeader.PF_plusButton.click();
				break;
				
			case "New Event":
				build.moveToElement(menuHeader.PF_newEvent).build().perform(); 
				menuHeader.PF_newEvent.click();
				break;
				
			case "Preview":
				build.moveToElement(menuHeader.PF_eventPreview).build().perform();
				menuHeader.PF_eventPreview.click();
				break;
				
			case "Dashboard":
				build.moveToElement(menuHeader.PF_eventDashboard).build().perform();
				menuHeader.PF_eventDashboard.click();
				break;
				
			case "Event":
				build.moveToElement(menuHeader.PF_eventMenuMain).build().perform(); // Here you perform hover mouse over the needed elemnt to triger the visibility of the hidden element
				menuHeader.PF_eventMenuMain.click();
				break;
				
			case "Settings":
				build.moveToElement(menuHeader.PF_eventSettings).build().perform(); // Here you perform hover mouse over the needed elemnt to triger the visibility of the hidden element
				menuHeader.PF_eventSettings.click();
				break;
				
			case "Registration":
				build.moveToElement(menuHeader.PF_eventSettingsRegistration).build().perform();
				menuHeader.PF_eventSettingsRegistration.click();
				break;
			 
			case "Attendee Category":
				build.moveToElement(menuHeader.PF_eventSettingsAttendee).build().perform();
				menuHeader.PF_eventSettingsAttendee.click();
				break;
			
			case "Properties":
				build.moveToElement(menuHeader.PF_eventSettingsProperties).build().perform();
				menuHeader.PF_eventSettingsProperties.click();
				break;
							
			case "Financial Setup":
				build.moveToElement(menuHeader.PF_eventFinSetup).build().perform(); // Here you perform hover mouse over the needed elemnt to triger the visibility of the hidden element
				menuHeader.PF_eventFinSetup.click();
				break;
				
			case "Prices":
				build.moveToElement(menuHeader.PF_eventFinsetupPrice).build().perform();
				menuHeader.PF_eventFinsetupPrice.click();
				break;
			
			case "FinSettings":
				build.moveToElement(menuHeader.PF_eventFinsetupSettings).build().perform();
				menuHeader.PF_eventFinsetupSettings.click();
				break;
				
			case "Payment Methods":
				build.moveToElement(menuHeader.PF_eventFinsetupPaymeth).build().perform();
				menuHeader.PF_eventFinsetupPaymeth.click();
				break;
			
			case "Table Bookings":
				build.moveToElement(menuHeader.PF_tableBooking).build().perform();
				menuHeader.PF_tableBooking.click();
				break;
				
			case "Table List":
				build.moveToElement(menuHeader.PF_tableList).build().perform();
				menuHeader.PF_tableList.click();
				break;
				
			case "Table Settings":
				build.moveToElement(menuHeader.PF_tableSettings).build().perform();
				menuHeader.PF_tableSettings.click();
				break;
				
			case "Abstracts Setup":
				build.moveToElement(menuHeader.PF_abstractsSetup).build().perform();
				menuHeader.PF_abstractsSetup.click();
				break;
				
			case "Topics":
				build.moveToElement(menuHeader.PF_abstractTopics).build().perform();
				menuHeader.PF_abstractTopics.click();
				break;
				
			case "Reviewing Criteria":
				build.moveToElement(menuHeader.PF_abstractCriteria).build().perform();
				menuHeader.PF_abstractCriteria.click();
				break;
				
			case "Reviewers":
				build.moveToElement(menuHeader.PF_abstractReviewers).build().perform();
				menuHeader.PF_abstractReviewers.click();
				break;
				
			case "Presentation Methods":
				build.moveToElement(menuHeader.PF_abstractPresentationMethods).build().perform();
				menuHeader.PF_abstractPresentationMethods.click();
				break;
				
			case "AbstractSettings":
				build.moveToElement(menuHeader.PF_abstractSettings).build().perform();
				menuHeader.PF_abstractSettings.click();
				break;
				
			case "Abstracts Management":
				build.moveToElement(menuHeader.PF_abstractManagement).build().perform();
				menuHeader.PF_abstractManagement.click();
				break;
				
			case "Submissions":
				build.moveToElement(menuHeader.PF_abstractSubmissions).build().perform();
				menuHeader.PF_abstractSubmissions.click();
				break;
				
			case "Reviews":
				build.moveToElement(menuHeader.PF_abstractReviews).build().perform();
				menuHeader.PF_abstractReviews.click();
				break;
				
			case "RegistrationMain":
				build.moveToElement(menuHeader.PF_registrationsMain).build().perform();
				menuHeader.PF_registrationsMain.click();
				break;
				
			case "RegistrationSub":
				build.moveToElement(menuHeader.PF_registrationsSub).build().perform();
				menuHeader.PF_registrationsSub.click();
				break;
				
			case "Website":
				build.moveToElement(menuHeader.PF_website).build().perform();
				menuHeader.PF_website.click();
				break;
				
			case "WebsiteMainSettings":
				build.moveToElement(menuHeader.PF_websiteMainSettings).build().perform();
				menuHeader.PF_websiteMainSettings.click();
				break;
				
			case "Web Addresses":
				build.moveToElement(menuHeader.PF_webAddresses).build().perform();
				menuHeader.PF_webAddresses.click();
				break;
				
			case "Capacity":
				build.moveToElement(menuHeader.PF_capacity).build().perform();
				menuHeader.PF_capacity.click();
				break;
				
			case "Attendance Recording":
				build.moveToElement(menuHeader.PF_attendanceRecodring).build().perform();
				menuHeader.PF_attendanceRecodring.click();
				break;
				
			case "Bulk Edit":
				build.moveToElement(menuHeader.PF_bulkEdit).build().perform();
				menuHeader.PF_bulkEdit.click();
				break;
				
			case "Awards Setup":
				build.moveToElement(menuHeader.PF_awardSetup).build().perform();
				menuHeader.PF_awardSetup.click();
				break;
				
			case "Categories":
				build.moveToElement(menuHeader.PF_awardCategory).build().perform();
				menuHeader.PF_awardCategory.click();
				break;
				
			case "Sections":
				build.moveToElement(menuHeader.PF_awardSection).build().perform();
				menuHeader.PF_awardSection.click();
				break;
				
			case "Award Types":
				build.moveToElement(menuHeader.PF_awardTypes).build().perform();
				menuHeader.PF_awardTypes.click();
				break;
				
			case "Award Settings":
				build.moveToElement(menuHeader.PF_awardSettings).build().perform();
				menuHeader.PF_awardSettings.click();
				break;
				
			case "Awards Management":
				build.moveToElement(menuHeader.PF_awardManagement).build().perform();
				menuHeader.PF_awardManagement.click();
				break;
				
			case "Entries":
				build.moveToElement(menuHeader.PF_awardEntries).build().perform();
				menuHeader.PF_awardEntries.click();
				break;
			
			case "Search and Reports":
				build.moveToElement(menuHeader.PF_searchReports).build().perform();
				menuHeader.PF_searchReports.click();
				break;
				
			case "Search":
				build.moveToElement(menuHeader.PF_search).build().perform();
				menuHeader.PF_search.click();
				break;
				
			default:
				 break;
			
			}//switch ends
				pageLoader.waitForMenuToLoad();
				menuSoFar = menuSoFar + " > " + menuItem;
				
		}//for loop ends
		
		log.logMessage("");
		log.logDataToFile("info", "Navigated to menu item :", menuSoFar );
		log.logMessage("");
		pageLoader.waitForPageToLoad();
	}
}
