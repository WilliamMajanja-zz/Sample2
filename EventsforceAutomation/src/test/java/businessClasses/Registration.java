package businessClasses;


import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import commonLibrary.Log;
import commonLibrary.PageLoader;
import commonLibrary.PageNavigator;
import commonLibrary.Utility;
import pages.backend.MenuHeader;
import pages.backend.WebsiteMenu;
import pages.frontend.AbstractSubmission;
import pages.frontend.BasketPage;
import pages.frontend.CategorySelection;
import pages.frontend.DaySelectionPage;
import pages.frontend.Email;
import pages.frontend.PersonalDetails;
import pages.frontend.TableGuestDetails;

public abstract class Registration {

	protected Email email;
	protected PersonalDetails personalDetails;
	protected BasketPage basketPage;
	protected CategorySelection categorySelection;
	protected TableGuestDetails tableGuestDetails;
	protected AbstractSubmission abstractSubmission;
	protected DaySelectionPage daySelectionPage;
	
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected PageNavigator pageNavigator;
	protected PageLoader pageLoader;
	protected MenuHeader menuHeader;
	protected Log log;
	protected Map<String, String> systemData;
	protected Map<String, String> eventData;
	protected Map<String, String> registrationData;
	
	
protected Registration(WebDriver driver, Map<String, String> systemDataTemp, Map<String, String> eventDataTemp, Map<String, String> registrationDataTemp) {

		this.driver = driver;
		log = new Log();
		
		menuHeader = PageFactory.initElements(driver, MenuHeader.class);
		email = PageFactory.initElements(driver, Email.class);
		personalDetails = PageFactory.initElements(driver, PersonalDetails.class);
		basketPage = PageFactory.initElements(driver, BasketPage.class);
		categorySelection = PageFactory.initElements(driver, CategorySelection.class);
		tableGuestDetails = PageFactory.initElements(driver, TableGuestDetails.class);
		abstractSubmission = PageFactory.initElements(driver, AbstractSubmission.class);
		daySelectionPage = PageFactory.initElements(driver, DaySelectionPage.class);
		
		wait = new WebDriverWait(driver, 30);
		
		pageNavigator = new PageNavigator(driver,systemDataTemp);
		pageLoader = new PageLoader(systemDataTemp);
		
		systemData = systemDataTemp;
		eventData = eventDataTemp;
		registrationData = registrationDataTemp;
	}


protected void testRegistrationDefault() {
	
		String original_handle;
		String current_title;
		
		/* handle for admin portal */
		original_handle = driver.getWindowHandle();
		
		pageNavigator.navigateTo("Preview");

		Set<String> handles = driver.getWindowHandles();

		for (String i : handles) 
		{
			driver.switchTo().window(i);
			current_title = driver.getTitle();

			/* Switch to eventsforce registration web site */
			if (!current_title.equals("Eventsforce")) 
			{
				email.PF_menuRegister.get(1).click();
				createRegistration();
				driver.close();
				break;
			}
		}

		/* switch back to admin portal */
		driver.switchTo().window(original_handle);
		pageLoader.waitForPageToLoad(driver, menuHeader.PF_eventDashboard);
		pageNavigator.navigateTo("Dashboard");
	}

	
protected void enterEmail() {

		Utility util = new Utility();
		String dummy_email = "email";

		wait.until(ExpectedConditions.visibilityOf(email.PF_email));
		dummy_email = dummy_email + util.generateTimeStamp() + "@dummy.dummy";
		email.PF_email.sendKeys(dummy_email);
		log.logDataToFile("info", "Email id used for registration", dummy_email);
		email.PF_proceedButton.click();
	}


protected void enterPersonalDetails() {
		
		wait.until(ExpectedConditions.visibilityOf(personalDetails.PF_firstName));
		
		personalDetails.PF_firstName.sendKeys(registrationData.get("FirstName"));
		personalDetails.PF_lastName.sendKeys(registrationData.get("LastName"));
		personalDetails.PF_company.sendKeys(registrationData.get("Company"));
		personalDetails.PF_address1.sendKeys(registrationData.get("Adr1"));
		personalDetails.PF_address2.sendKeys(registrationData.get("Adr2"));
		personalDetails.PF_town.sendKeys(registrationData.get("Town"));
		personalDetails.PF_postcode.sendKeys(registrationData.get("Postcode"));

		Select selectCountry = new Select(personalDetails.PF_country);
		selectCountry.selectByVisibleText(registrationData.get("Country"));

		personalDetails.PF_phone.sendKeys(registrationData.get("Phone"));
		
		log.logMessage("Personal Details entered successfully");
	}
	
	
protected void enterPersonalDetailsAndProceed() {

		enterPersonalDetails();
		personalDetails.PF_proceedButton.click();
		log.logMessage("Personal Details entered and proceeded to next page");
	}

	
protected void verifyRegContactDetailsOnBasketPage() {

		wait.until(ExpectedConditions.visibilityOf(basketPage.PF_proceedButton));

		Assert.assertEquals(basketPage.PF_basketName.getText(), (registrationData.get("FirstName") + " " + registrationData.get("LastName")),
				"Name on the Basket Page does not match with the FirstName from excel file");
		Assert.assertEquals(basketPage.PF_basketAdr1.getText(), registrationData.get("Adr1"),
				"ADR1 on the Basket Page does not match with the Adr1 from excel file");
		Assert.assertEquals(basketPage.PF_basketAdr2.getText(), registrationData.get("Adr2"),
				"ADR2 on the Basket Page does not match with Adr2 from excel file");
		Assert.assertEquals(basketPage.PF_basketCompany.getText(), registrationData.get("Company"),
				"COMPANY on the Basket Page does not match with the Company from excel file");
		Assert.assertEquals(basketPage.PF_basketTown.getText(), registrationData.get("Town"),
				"TOWN on the Basket Page does not match with the Town from excel file");
		Assert.assertEquals(basketPage.PF_basketPostcode.getText(), registrationData.get("Postcode"),
				"POSTCODE on the Basket Page does not match with the Postcode from excel file");
		Assert.assertEquals(basketPage.PF_basketPhone.getText(), registrationData.get("Phone"),
				"PHONE on the Basket Page does not match with the Phone from excel file");
		
		log.logMessage("Registration Contact Details verified successfully on the Basket page");
	}


protected void selectPaymentMethod() {

		if (!registrationData.get("PayByMethod").equalsIgnoreCase("NA")) 
		{
			if (registrationData.get("PayByMethod").equalsIgnoreCase("BANK")) 
			{
				basketPage.PF_selectPayBank.click();
				log.logDataToFile("info", "PayByMethod", "Bank option is selected");
			}

			if (registrationData.get("PayByMethod").equalsIgnoreCase("CHEQUE")) 
			{
				basketPage.PF_selectPayCheque.click();
				log.logDataToFile("info", "PayByMethod", "Cheque option is selected");
			}

			if (registrationData.get("PayByMethod").equalsIgnoreCase("INVOICE")) 
			{
				basketPage.PF_selectPayInvoice.click();
				log.logDataToFile("info", "PayByMethod", "Invoice option is selected");
			}
		}
		basketPage.PF_proceedButton.click();
		pageLoader.waitForPageToLoad(driver, basketPage.PF_print);
	}

	
protected void verifyPaymentMethod() {

		if (registrationData.get("PayByMethod").equalsIgnoreCase("INVOICE")) 
		{
			Assert.assertEquals(basketPage.PF_basketPayMethod.getText(), "Invoice",
					"Payment method is not equal to the PaymentMethod in excel file.");
		}

		if (registrationData.get("PayByMethod").equalsIgnoreCase("BANK")) 
		{
			Assert.assertEquals(basketPage.PF_basketPayMethod.getText(), "Bank Transfer",
					"Payment method is not equal to the PaymentMethod in excel file.");
		}

		if (registrationData.get("PayByMethod").equalsIgnoreCase("CHEQUE")) 
		{
			Assert.assertEquals(basketPage.PF_basketPayMethod.getText(), "Cheque",
					"Payment method is not equal to the PaymentMethod in excel file.");
		}

		log.logMessage("The selected payment method verified successfully on the Basket page");
	}
	
	
protected void selectAttendee(){
		
		if (eventData.get("AttendeeCategory").equalsIgnoreCase("Exhibitor")) 
		{
			categorySelection.PF_selectExhibitor.click();
			categorySelection.PF_selectCatProceed.click();
			log.logDataToFile("info", "AttendeeCategory", "Exhibitor attendee category is selected");
		}
	}
	
	
protected String getWebAddress(String webAddressFor) {
		
		String url = "";
		WebsiteMenu websiteMenu = PageFactory.initElements(driver, WebsiteMenu.class);
		pageNavigator.navigateTo("Website", "WebsiteMainSettings", "Web Addresses");
		
		switch(webAddressFor)
		{
			case "Homepage":
				url = websiteMenu.PF_homepageUrl.getAttribute("value");
				log.logDataToFile("info", "Url for " + webAddressFor, url);
				break;
				
			case "Registration":
				url = websiteMenu.PF_registrationUrl.getAttribute("value");
				log.logDataToFile("info", "Url for " + webAddressFor, url);
				break;
			
			case "Amendment":
				url = websiteMenu.PF_amendmentUrl.getAttribute("value");
				log.logDataToFile("info", "Url for " + webAddressFor, url);
				break;
				
			case "Decline":
				url = websiteMenu.PF_declineUrl.getAttribute("value");
				log.logDataToFile("info", "Url for " + webAddressFor, url);
				break;
				
			case "TermsConditions":
				url = websiteMenu.PF_termConditionsUrl.getAttribute("value");
				log.logDataToFile("info", "Url for " + webAddressFor, url);
				break;
			
			case "Venue":
				url = websiteMenu.PF_VenueUrl.getAttribute("value");
				log.logDataToFile("info", "Url for " + webAddressFor, url);
				break;
				
			case "Agenda":
				url = websiteMenu.PF_dailyAgendaUrl.getAttribute("value");
				log.logDataToFile("info", "Url for " + webAddressFor, url);
				break;
				
			case "Submitter Login":
				url = websiteMenu.PF_abstractSubmitterLoginUrl.getAttribute("value");
				log.logDataToFile("info", "Url for " + webAddressFor, url);
				break;
				
			case "Reviewer Login":
				url = websiteMenu.PF_reviewerLoginUrl.getAttribute("value");
				log.logDataToFile("info", "Url for " + webAddressFor, url);
				break;
				
			default:
				log.logDataToFile("error", webAddressFor, "Web Address not found");	
				break;
		}
	
		return url;
	}
	

protected WebDriver createDriver(String url) {
	
	System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
	
	ChromeOptions options = new ChromeOptions();
	options.addArguments("-incognito");
	DesiredCapabilities capabilities = DesiredCapabilities.chrome();
	capabilities.setCapability(ChromeOptions.CAPABILITY, options);
	WebDriver tempDriver = new ChromeDriver(capabilities);
	
	tempDriver.get(url);
	tempDriver.manage().window().maximize();
	tempDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	pageLoader.waitForPageToLoad();
	
	log.logMessage("New incognito chrome driver created");
	
	return tempDriver;
	}


protected void initRegistrationDriver(WebDriver drv) {
	
	pageNavigator = new PageNavigator(drv,systemData);
	pageLoader = new PageLoader(systemData);

	menuHeader = PageFactory.initElements(drv, MenuHeader.class);
	email = PageFactory.initElements(drv, Email.class);
	personalDetails = PageFactory.initElements(drv, PersonalDetails.class);
	basketPage = PageFactory.initElements(drv, BasketPage.class);
	categorySelection = PageFactory.initElements(drv, CategorySelection.class);
	tableGuestDetails = PageFactory.initElements(drv, TableGuestDetails.class);
	abstractSubmission = PageFactory.initElements(drv, AbstractSubmission.class);
	daySelectionPage = PageFactory.initElements(drv, DaySelectionPage.class);
	wait = new WebDriverWait(drv, 30);	
	}


/* Subclass must implement the following method */
protected abstract void createRegistration();

}
