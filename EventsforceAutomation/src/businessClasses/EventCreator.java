package businessClasses;


import java.io.FileInputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import commonLibrary.Log;
import commonLibrary.Login;
import commonLibrary.Logout;
import commonLibrary.PageLoader;
import commonLibrary.PageNavigator;
import commonLibrary.Utility;
import pages.DynamicXPath;
import pages.backend.AttendeeCategories;
import pages.backend.Capacity;
import pages.backend.EventProperties;
import pages.backend.FinancialSettings;
import pages.backend.HomePage;
import pages.backend.MenuHeader;
import pages.backend.PaymentMethods;
import pages.backend.Prices;
import pages.backend.RegistrationSettings;
import pages.backend.Registrations;
import pages.backend.TableList;
import pages.backend.TableSettings;

public abstract class EventCreator {

	protected WebDriver driver;
	protected PageNavigator pageNavigator;
	protected PageLoader pageLoader;
	protected Log log;
	
	protected EventProperties eventProperties;
	protected Capacity capacity;
	protected RegistrationSettings registrationSettings;
	protected AttendeeCategories attendeeCategories;
	protected Prices prices;
	protected FinancialSettings financialSettings;
	protected TableList tableList;
	protected TableSettings tableSettings;
	protected PaymentMethods paymentMethods;
	protected Registrations registrations;
	protected HomePage homePage;
	protected String eventCode;
	protected String Header;
	
	protected Map<String, String> systemData;
	protected Map<String, String> eventData;
	protected Map<String, String> registrationData;
	

protected EventCreator() {
		
		log = new Log();
	}
	

protected void initialise(){
	
		eventProperties =  PageFactory.initElements(driver, EventProperties.class); 
		capacity =  PageFactory.initElements(driver, Capacity.class); 
		registrationSettings =  PageFactory.initElements(driver, RegistrationSettings.class); 
		attendeeCategories =  PageFactory.initElements(driver, AttendeeCategories.class);
		prices =  PageFactory.initElements(driver, Prices.class);
		financialSettings =  PageFactory.initElements(driver, FinancialSettings.class);
		tableList =  PageFactory.initElements(driver, TableList.class);
		tableSettings =  PageFactory.initElements(driver, TableSettings.class);
		paymentMethods =  PageFactory.initElements(driver, PaymentMethods.class);
		registrations = PageFactory.initElements(driver, Registrations.class);
		homePage = PageFactory.initElements(driver, HomePage.class);
		
		pageNavigator = new PageNavigator(driver,systemData);
		pageLoader = new PageLoader(systemData);
	}

	
protected void goToEventCreationMenu() {

		pageLoader.waitForPageToLoad();
		
		MenuHeader menuHeader = PageFactory.initElements(driver, MenuHeader.class);
		pageLoader.waitForPageToLoad(driver, menuHeader.PF_plusButton);
		
		pageNavigator.navigateTo("Plus", "New Event");
	}

	
protected void setEventPropertiesPage() {
		
		String eventName;
		Utility util = new Utility();
			
		/* Append a random number to eventName */
		eventName = eventData.get("EventName") + util.generateTimeStamp();

		eventProperties.PF_eventName.sendKeys(eventName);
		log.logDataToFile("info","New event created. Event name",eventName);
		
		eventProperties.PF_eventStartDate.sendKeys(eventData.get("EventStartDate"));
		eventProperties.PF_eventEndDate.sendKeys(eventData.get("EventEndDate"));
		log.logMessage("Event is between " + eventData.get("EventStartDate") + " and " + eventData.get("EventEndDate") );
		
		Select selectEventTimeZone = new Select(eventProperties.PF_eventTimeZone);
		selectEventTimeZone.selectByVisibleText(eventData.get("TimeZone"));

		Select selectEventType = new Select(eventProperties.PF_eventType);
		selectEventType.selectByVisibleText(eventData.get("EventType"));

		if (eventData.get("EventCost").equalsIgnoreCase("Free"))
		{
			eventProperties.PF_eventCostFree.click();
			log.logDataToFile("info", "EventCost", "Free option is clicked");
		}

		if (eventData.get("EventCost").equalsIgnoreCase("Paid"))
		{
			eventProperties.PF_eventCostPaid.click();
			log.logDataToFile("info", "EventCost", "Paid option is clicked");
		}

		if (eventData.get("Feature").equalsIgnoreCase("TABLE"))
		{
			eventProperties.PF_tableBooking.click();
			log.logDataToFile("info", "Feature", "Table Booking option is clicked");
		}

		if (eventData.get("ChangeDayName").equalsIgnoreCase("Yes"))
		{
			eventProperties.PF_saveEvent.click();
			pageLoader.waitForPageToLoad(driver, eventProperties.PF_tableBooking);
			
			DynamicXPath dynamicXpath = new DynamicXPath();
			int size = driver.findElements(By.xpath(".//*[contains(@id,'" + dynamicXpath.dayNames + "')]")).size();
						
			for(int i = 1 ; i <= size ; i++)
			{
				String id = dynamicXpath.dayNames + i;
				String dayName = "New Day " + i;
				driver.findElement(By.id(id)).clear();
				driver.findElement(By.id(id)).sendKeys(dayName);
				log.logMessage("Day name for Day " + i + " is now changed to " + dayName);
			}
		}
		
		eventProperties.PF_saveEvent.click();
		pageLoader.waitForPageToLoad(driver, eventProperties.PF_tableBooking);
		
		log.logMessage("Event properties entered successfully");
	}

	
protected void setEventRegistrationPage() {

		pageNavigator.navigateTo("Event", "Settings", "Registration");
		
		if (eventData.get("RegistrationMode").equalsIgnoreCase("Email"))
		{
			registrationSettings.PF_regmodeEmail.click();
			log.logMessage("Registration mode is set to Email");
		}

		if (eventData.get("RegistrationMode").equalsIgnoreCase("Username"))
		{
			registrationSettings.PF_regmodeUsername.click();
			log.logMessage("Registration mode is set to Username");
		}

		if (eventData.get("RegistrationMode").equalsIgnoreCase("Registration reference"))
		{
			registrationSettings.PF_regmodeRegref.click();
			log.logMessage("Registration mode is set to Registration reference");
		}

		/* Set up registration access type */
		if (eventData.get("Access").equalsIgnoreCase("Public"))
		{
			registrationSettings.PF_accessPublic.click();
			log.logDataToFile("info", "Registration and event website are public :", "selected");
		}

		if (eventData.get("Access").equalsIgnoreCase("Private"))
		{
			registrationSettings.PF_accessPrivate.click();
			registrationSettings.PF_saveEvent.click();
			log.logDataToFile("info", "Registration and event website are private using external authentication :", "selected");
			
			pageLoader.waitForPageToLoad(driver, registrationSettings.PF_manageCode);
			registrationSettings.PF_manageCode.click();
			pageLoader.waitForPageToLoad(driver, registrationSettings.PF_eventCodeName);
			
			Utility util = new Utility();
			eventCode = eventData.get("EventEntryCode") + util.generateTimeStamp();
			registrationSettings.PF_eventCodeName.sendKeys(eventCode);
			registrationSettings.PF_save2Button.click();
			log.logDataToFile("info", "Event entry code is", eventData.get("EventEntryCode"));
			
			pageLoader.waitForPageToLoad(driver, registrationSettings.PF_backButton);
			registrationSettings.PF_backButton.click();
			
			pageLoader.waitForPageToLoad(driver, registrationSettings.PF_accessPrivate);
		}

		if (eventData.get("Access").equalsIgnoreCase("Invitation"))
		{
			registrationSettings.PF_accessInvitation.click();
			log.logDataToFile("info", "Registration is invitation-only :", "selected");
		}

		/* Set up check box for registration */
		if (eventData.get("AllowReg").equalsIgnoreCase("Yes")) 
		{
			if (!registrationSettings.PF_regAllow.isSelected()) 
			{
				registrationSettings.PF_regAllow.click();
				log.logDataToFile("info", "Allow registration :", "selected");
			}
		}

		if (eventData.get("AllowReg").equalsIgnoreCase("No")) 
		{
			if (registrationSettings.PF_regAllow.isSelected()) 
			{
				registrationSettings.PF_regAllow.click();
				log.logDataToFile("info", "Allow registration :", "cleared");
			}
		}

		/* Set up check box for Amendment */
		if (eventData.get("AllowAmend").equalsIgnoreCase("Yes")) 
		{
			if (!registrationSettings.PF_regAmendAllow.isSelected()) 
			{
				registrationSettings.PF_regAmendAllow.click();
				log.logDataToFile("info", "Allow registration amendment :", "selected");
			}
		}

		if (eventData.get("AllowAmend").equalsIgnoreCase("No")) 
		{
			if (registrationSettings.PF_regAmendAllow.isSelected()) 
			{
				registrationSettings.PF_regAmendAllow.click();
				log.logDataToFile("info", "Allow registration amendment :", "cleared");
			}
		}

		/* Set up check box for Cancellation */
		if (eventData.get("AllowCancel").equalsIgnoreCase("Yes")) 
		{
			if (!registrationSettings.PF_regCancelAllow.isSelected()) 
			{
				registrationSettings.PF_regCancelAllow.click();
				log.logDataToFile("info", "Allow cancellation :", "selected");
			}
		}

		if (eventData.get("AllowCancel").equalsIgnoreCase("No")) 
		{
			if (registrationSettings.PF_regCancelAllow.isSelected()) 
			{
				registrationSettings.PF_regCancelAllow.click();
				log.logDataToFile("info", "Allow cancellation :", "cleared");
			}
		}

		if(!eventData.get("EventType").equalsIgnoreCase("Awards"))
		{
			/* Set up check box for Session Amendment */
			if (eventData.get("AllowSession").equalsIgnoreCase("Yes")) 
			{
				if (!registrationSettings.PF_regSessionAllow.isSelected()) 
				{
					registrationSettings.PF_regSessionAllow.click();
					log.logDataToFile("info", "Allow session amendment :", "selected");
				}
			}
		
			if (eventData.get("AllowSession").equalsIgnoreCase("No")) 
			{
				if (registrationSettings.PF_regSessionAllow.isSelected()) 
				{
					registrationSettings.PF_regSessionAllow.click();
					log.logDataToFile("info", "Allow session amendment :", "cleared");
				}
			}

			/* Set up check box for Group Booking */
			if (eventData.get("AllowGroup").equalsIgnoreCase("Yes")) 
			{
				if (!registrationSettings.PF_regGroupAllow.isSelected()) 
				{
					registrationSettings.PF_regGroupAllow.click();
					log.logDataToFile("info", "Allow group registrations :", "selected");
				}
			}
			
			if (eventData.get("AllowGroup").equalsIgnoreCase("No")) 
			{
				if (registrationSettings.PF_regGroupAllow.isSelected()) 
				{
					registrationSettings.PF_regGroupAllow.click();
					log.logDataToFile("info", "Allow group registrations :", "cleared");
				}
			}
		}
		registrationSettings.PF_saveEvent.click();
		pageLoader.waitForPageToLoad(driver, registrationSettings.PF_regAllow);
		
		log.logMessage("Event registration page set up successfully");
	}

	
protected void selectAttendeeCategory() {

		WebElement selectedOptionElement;
		String selectedOption;

		if (eventData.get("Feature").equalsIgnoreCase("NA")) 
		{
			pageNavigator.navigateTo("Event", "Settings", "Attendee Category");

			if (eventData.get("AttendeeCategory").equalsIgnoreCase("Exhibitor")) 
			{
				Select attendeeByVal = new Select(attendeeCategories.PF_attendeeCatExhibitor);
				selectedOptionElement = attendeeByVal.getFirstSelectedOption();
				selectedOption = selectedOptionElement.getText();
				selectedOption = selectedOption.trim();

				/* If additional category is not active, make it active */
				if (selectedOptionElement.getText().equalsIgnoreCase("not active")) 
				{
					attendeeByVal.selectByValue("active");
					log.logMessage("Additional attendee category is made active");
				}
			}

			attendeeCategories.PF_save2Button.click();
			pageLoader.waitForPageToLoad(driver, attendeeCategories.PF_attendeeCatExhibitor );
		}
	}

	
protected void setCapacityPage() {

		DynamicXPath dynamicId = new DynamicXPath();
		
		pageNavigator.navigateTo("Event", "Settings", "Capacity");
		pageLoader.waitForPageToLoad(driver, driver.findElement(By.id(dynamicId.capacityNotification + "1")));
		
		int size = capacity.PF_capacityPageTable.size();
		registrationData.put("TotalEventDays", "" + (size-1));
		
		for(int i = 1 ; i < size ; i++)
		{
			Select capacityOption =  new Select (driver.findElement(By.id(dynamicId.capacityDayOption + i)));
			capacityOption.selectByVisibleText("Limited");
			log.logDataToFile("info", "Capacity option for Day " + i, "Limited");
			
			driver.findElement(By.id(dynamicId.capacityValue + i)).clear();
			driver.findElement(By.id(dynamicId.capacityValue + i)).sendKeys(i + "");
			log.logDataToFile("info", "Capacity for day " + i, i);
			
				if(!driver.findElement(By.id(dynamicId.capacityNotification + i)).isSelected())
				{
					driver.findElement(By.id(dynamicId.capacityNotification + i)).click();
					log.logDataToFile("info", "Capacity notification for day " + i, "checked");
				}	
				
				if(eventData.get("ChangeDayName").equalsIgnoreCase("Yes"))
				{
					int dayPresent = driver.findElements(By.xpath(dynamicId.dayNamesOnCapacityPage + i + "')]")).size();
					Assert.assertEquals(dayPresent, 1, "Day Name does not match with Event Properties page");
				}
		}
		
		capacity.PF_saveEvent.click();
		pageLoader.waitForPageToLoad(driver, driver.findElement(By.id(dynamicId.capacityNotification + "1")));
		
		log.logMessage("Capacity updated successfully");
	}
	
	
protected void makeEventLive() {
		
		Actions build = new Actions(driver);

		if (eventData.get("Feature").equalsIgnoreCase("NA")) 
		{
			pageNavigator.navigateTo("Event", "Settings", "Properties");
			build.moveToElement(eventProperties.PF_eventLive).build().perform();
			eventProperties.PF_eventLive.click();
			eventProperties.PF_saveEvent.click();
			pageLoader.waitForPageToLoad(driver, eventProperties.PF_tableBooking);
		}
		
		log.logMessage("Event is LIVE");
	}

	
protected void createEventPrice() {

		if (eventData.get("EventCost").equalsIgnoreCase("Paid")) 
		{
			if (!eventData.get("EventPrice").equalsIgnoreCase("NA")) 
			{
				pageNavigator.navigateTo("Event", "Financial Setup", "Prices");

				prices.PF_addPrice.click();
				pageLoader.waitForPageToLoad(driver, prices.PF_priceName);
				prices.PF_priceName.sendKeys("eventPrice");
				prices.PF_applyEvent.click();
				prices.PF_price.sendKeys(eventData.get("EventPrice"));

				Select selectCurrency = new Select(prices.PF_currency);
				selectCurrency.selectByValue("1");
				
				prices.PF_lineDesc.sendKeys("Standard Price");
				prices.PF_saveByName.click();
				pageLoader.waitForPageToLoad(driver, prices.PF_addPrice );
				
				log.logMessage("Event price created successfully successfully");
			}
		}
	}

	
protected void selectVATcode() {

		if (!eventData.get("VatCode").equalsIgnoreCase("NA")) 
		{
			pageNavigator.navigateTo("Event", "Financial Setup", "FinSettings");

			if (eventData.get("VatCode").equalsIgnoreCase("0")) 
			{
				Select vatCode = new Select(financialSettings.PF_vatCode);
				vatCode.selectByVisibleText("0% ()");
				log.logDataToFile("info", "VatCode selected is :", "0%");
			}

			if (eventData.get("VatCode").equalsIgnoreCase("GBSTD")) {
				Select vatCode = new Select(financialSettings.PF_vatCode);
				vatCode.selectByVisibleText("GBSTD ()");
				log.logDataToFile("info", "VatCode selected is :", "GBSTD");
			}
			financialSettings.PF_saveByName.click();
			pageLoader.waitForPageToLoad(driver, financialSettings.PF_allowCurrencySelection);
		}
	}

	
protected void setEventCurrency() {

		if (!eventData.get("EventCurrency").equalsIgnoreCase("NA")) 
		{
			pageNavigator.navigateTo("Event", "Financial Setup", "FinSettings");

			/* Set currency = GBP */
			if (eventData.get("EventCurrency").equalsIgnoreCase("GBP")) 
			{
				Select eventCurrency = new Select(financialSettings.PF_eventCurrency);
				eventCurrency.selectByVisibleText("GBP (pounds sterling)");
				log.logDataToFile("info", "EventCurrency selected is :", "GBP");
			}

			/* Set currency = EUR */
			if (eventData.get("EventCurrency").equalsIgnoreCase("EUR")) 
			{
				Select eventCurrency = new Select(financialSettings.PF_eventCurrency);
				eventCurrency.selectByVisibleText("EUR (Euros)");
				log.logDataToFile("info", "EventCurrency selected is :", "EUR");
			}
			
			/* Set currency = USD */
			if (eventData.get("EventCurrency").equalsIgnoreCase("USD")) 
			{
				Select eventCurrency = new Select(financialSettings.PF_eventCurrency);
				eventCurrency.selectByVisibleText("USD (US Dollars)");
				log.logDataToFile("info", "EventCurrency selected is :", "USD");
			}

			financialSettings.PF_saveByName.click();
			pageLoader.waitForPageToLoad(driver, financialSettings.PF_allowCurrencySelection);
		}
	}

	
protected void setPaymentMethod() {

		String[] payment_methods = eventData.get("PaymentMethod").split(",");
		int number_of_methods = payment_methods.length;

		if (!eventData.get("PaymentMethod").equalsIgnoreCase("NA")) 
		{
			pageNavigator.navigateTo("Event", "Financial Setup", "Payment Methods");

			if (paymentMethods.PF_checkCheque.isSelected()) 
			{
				paymentMethods.PF_checkCheque.click();
			}

			if (paymentMethods.PF_checkInvoice.isSelected()) 
			{
				paymentMethods.PF_checkInvoice.click();
			}

			if (paymentMethods.PF_checkBank.isSelected()) 
			{
				paymentMethods.PF_checkBank.click();
			}

			for (int i = 0; i < number_of_methods; i++) 
			{
				payment_methods[i] = payment_methods[i].trim();

				if (payment_methods[i].equalsIgnoreCase("BANK")) 
				{
					if (!paymentMethods.PF_checkBank.isSelected()) 
					{
						paymentMethods.PF_checkBank.click();
						log.logDataToFile("info", "PaymentMethod selected is :", "Bank");
					}
				} /* Bank ends */

				if (payment_methods[i].equalsIgnoreCase("CHEQUE")) 
				{
					if (!paymentMethods.PF_checkCheque.isSelected()) 
					{
						paymentMethods.PF_checkCheque.click();
						log.logDataToFile("info", "PaymentMethod selected is :", "Cheque");
					}
				} /* Cheque ends */

				if (payment_methods[i].equalsIgnoreCase("INVOICE")) 
				{
					if (!paymentMethods.PF_checkInvoice.isSelected()) 
					{
						paymentMethods.PF_checkInvoice.click();
						log.logDataToFile("info", "PaymentMethod selected is :", "Invoice");
					}
				} /* Cheque ends */

			} 
			paymentMethods.PF_savePaymentMethod.click();
			pageLoader.waitForPageToLoad(driver, paymentMethods.PF_checkInvoice);
		} 
	}


protected void viewRegistrationsInBackend(String mainFilter, String statusFilter, int numberOfRegistrations){
	
		pageNavigator.navigateTo("RegistrationMain", "RegistrationSub");
	
		Select tempMainFilter = new Select(registrations.PF_regMainFilter);
		tempMainFilter.selectByVisibleText(mainFilter);
		Select tempStatusFilter = new Select(registrations.PF_regStatusFilter);
		tempStatusFilter.selectByVisibleText(statusFilter);
	
		String results = registrations.PF_showingResult.getText();
		String showing = "Showing " + numberOfRegistrations + " people";
		Assert.assertEquals(results, showing, "No.of registrations are not equal to " + numberOfRegistrations);
	
		log.logMessage("Registrations verified successfully in the backend");
	}


protected void initEventDriver(WebDriver drv) {
	
	eventProperties =  PageFactory.initElements(drv, EventProperties.class); 
	capacity =  PageFactory.initElements(drv, Capacity.class); 
	registrationSettings =  PageFactory.initElements(drv, RegistrationSettings.class); 
	attendeeCategories =  PageFactory.initElements(drv, AttendeeCategories.class);
	prices =  PageFactory.initElements(drv, Prices.class);
	financialSettings =  PageFactory.initElements(drv, FinancialSettings.class);
	tableList =  PageFactory.initElements(drv, TableList.class);
	tableSettings =  PageFactory.initElements(drv, TableSettings.class);
	paymentMethods =  PageFactory.initElements(drv, PaymentMethods.class);
	registrations = PageFactory.initElements(drv, Registrations.class);
	homePage = PageFactory.initElements(drv, HomePage.class);
	pageNavigator = new PageNavigator(drv,systemData);
	pageLoader = new PageLoader(systemData);
	
	}
	

protected void createEvents(String filename) throws Exception {
		
		log.clearContentsOfOldLogFile();
		log.logHeader(Header);
		String data;
		String Key;
		
		int totalSystemRows;
		int totalSystemColumns;
		int totalEventRows;
		int totalEventColumns;
		int totalRegColumns;
		
		systemData = new LinkedHashMap<String, String>();
		eventData = new LinkedHashMap<String, String>();
		registrationData = new LinkedHashMap<String, String>();
	
		XSSFWorkbook excelWorkbook;
		FileInputStream fileInputStream;
		
		Login login = new Login();
		Logout logout = new Logout();

		fileInputStream = new FileInputStream(filename);
		excelWorkbook = new XSSFWorkbook(fileInputStream);
		
		XSSFSheet systemSheet = excelWorkbook.getSheetAt(0);
		totalSystemRows = systemSheet.getLastRowNum();
		totalSystemColumns = systemSheet.getRow(0).getLastCellNum();

		XSSFSheet eventSheet = excelWorkbook.getSheetAt(1);
		totalEventRows = eventSheet.getLastRowNum();
		totalEventColumns = eventSheet.getRow(0).getLastCellNum();

		XSSFSheet registrationSheet = excelWorkbook.getSheetAt(2);
		totalRegColumns = registrationSheet.getRow(0).getLastCellNum();
						
		for(int rowInSystem = 1 ; rowInSystem <= totalSystemRows; rowInSystem++ )
		{
			for (int columnInSystem = 0; columnInSystem < totalSystemColumns; columnInSystem++) 
			{
				Key = systemSheet.getRow(0).getCell(columnInSystem).getStringCellValue();
				Key = Key.trim();
				data = systemSheet.getRow(rowInSystem).getCell(columnInSystem).getStringCellValue();
				data = data.trim().replaceAll(" +", " ");
				systemData.put(Key, data);
			}
			
			driver = login.loginToEventsForce(systemData);
			initialise();
			
				for (int rowInEvent = 1; rowInEvent <= totalEventRows; rowInEvent++) 
				{
					for (int columnInEvent = 0; columnInEvent < totalEventColumns; columnInEvent++) 
					{
						Key = eventSheet.getRow(0).getCell(columnInEvent).getStringCellValue();
						Key = Key.trim();
						data = eventSheet.getRow(rowInEvent).getCell(columnInEvent).getStringCellValue();
						data = data.trim().replaceAll(" +", " ");
						eventData.put(Key, data);
					}

					setupEvent();
					
					for (int columnInRegistration = 0; columnInRegistration < totalRegColumns; columnInRegistration++) 
					{
						Key = registrationSheet.getRow(0).getCell(columnInRegistration).getStringCellValue();
						Key = Key.trim();
						data = registrationSheet.getRow(rowInEvent).getCell(columnInRegistration).getStringCellValue();
						data = data.trim().replaceAll(" +", " ");
						registrationData.put(Key, data);
					}
					
					testEvent();
				}
			
			logout.logoutFromEventsForce(driver);
			
		}
		
		excelWorkbook.close();
		
		log.copyLogFileToFolder();
	} 

		
/* Subclass must implement the following methods */
protected abstract void setupEvent();

protected abstract void testEvent();

}
