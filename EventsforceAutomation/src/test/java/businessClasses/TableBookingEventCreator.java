package businessClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import pages.DynamicXPath;
import pages.backend.MenuHeader;
import org.testng.Assert;


public class TableBookingEventCreator extends EventCreator{


public TableBookingEventCreator() {
	super();
	}
	

public void createTableBookingEvent(String fileName) throws Exception {
	
		Header = "TABLE BOOKING EVENT";
		createEvents(fileName);
	}
	
	
@Override
protected void setupEvent() {
		
		log.logFunctionTrace("entry");	
		
		goToEventCreationMenu();	
		setEventPropertiesPage();
		setEventRegistrationPage();
		createEventPrice();
		selectVATcode();
		setPaymentMethod();
		createTablePrice();
		setTableListPage();
		setTableSettingsPage();
		
		log.logFunctionTrace("exit");
	}


@Override
protected void testEvent() {
		
		log.logFunctionTrace("entry");
		
		TableBookingRegistration tableRegistration = new TableBookingRegistration(driver, systemData, eventData, registrationData);
		tableRegistration.performTableRegistration();
		log.logMessage("1 Registration successful");
		
		viewRegistrationsInBackend("Table guests", "Registered", 10);
		
		log.logFunctionTrace("exit");
	}
	
	
protected  void createTablePrice()  {
		
		pageNavigator.navigateTo("Event", "Financial Setup", "Prices");
		
		/* Create 2 prices, one for table and another for a seat */
		for(int i = 1 ; i<3; i++)
		{
			prices.PF_addPrice.click();
			pageLoader.waitForPageToLoad(driver, prices.PF_priceName );
			prices.PF_priceName.sendKeys("price_"+i);
			prices.PF_applyGeneral.click();
		
			if(i == 1 ) /* Create table price */
			{
				prices.PF_price.sendKeys(eventData.get("TablePrice"));
				prices.PF_lineDesc.sendKeys("Table Price");
				log.logMessage("Table price is created");
			}
		
			if(i == 2 ) /* Create seat price */
			{
				prices.PF_price.sendKeys(eventData.get("SeatPrice"));
				prices.PF_lineDesc.sendKeys("Seat Price");
				log.logMessage("Seat price is created");
			}
		
			Select selectCurrency = new Select(prices.PF_currency);
			selectCurrency.selectByValue("1");
			prices.PF_saveByName.click();
			
			MenuHeader menuHeader = PageFactory.initElements(driver, MenuHeader.class);
			pageLoader.waitForPageToLoad(driver, menuHeader.PF_eventMenuMain);
		}
	}

	
protected void setTableListPage() {
		
		DynamicXPath dynamicXPath = new DynamicXPath();
		pageNavigator.navigateTo("Event","Table Bookings","Table List");
	
		tableList.PF_addTable.click();
		log.logMessage("Clicked in Add button");
		tableList.PF_tableSize.sendKeys("5");
		log.logMessage("Set table size as 5");
		tableList.PF_numberOfTables.sendKeys("5");
		log.logMessage("Set number of tables to be created as 5");
		pageLoader.waitForMenuToLoad();
		tableList.PF_allowSeat.click();
		
		pageLoader.waitForPageToLoad(driver, tableList.PF_allowSeat);
	
		/* Set up table price */
		tableList.PF_radioPriceDescTable.click();
	
		Select selectTablePrice = new Select(tableList.PF_listPriceDescTable);
		selectTablePrice.selectByVisibleText("price_1");
		log.logMessage("Table price is set");
	
		/* Set up seat price */
		tableList.PF_radioPriceDescSeat.click();
	
		Select selectSeatPrice = new Select(tableList.PF_listPriceDescSeat);
		selectSeatPrice.selectByVisibleText("price_2");
	
		log.logMessage("Seat price is set");
		tableList.PF_saveByName.click();
		
		MenuHeader menuHeader = PageFactory.initElements(driver, MenuHeader.class);
		pageLoader.waitForPageToLoad(driver,menuHeader.PF_eventMenuMain);
	
		/* Verify that total number of total number of tables created = 5 */
		int total_tables_created = (tableList.PF_numberOfActualTables.size()) - 1;
		Assert.assertEquals(total_tables_created, 5,"Total tables created do not match with 5");
	
		/* Verify contents of the tables created */
		String tempTableName;
		String tempTablePrice;
		String tempTableSize;
		String tempTableStatus;
		String tempPrice = "£" + eventData.get("TablePrice") + ".00";
		String tempName;
		int j=1;
	
		for(int i = 2 ; i <= (total_tables_created + 1); i++ )
		{
			tempName = String.valueOf(i-1);
			
			/* Create dynamic xpath strings */
			tempTableName = dynamicXPath.DX_tableList + "[" + i + "]" + "/td[" + (j) + "]";
			tempTablePrice = dynamicXPath.DX_tableList + "[" + i + "]" + "/td[" + (j+1) + "]";
			tempTableSize = dynamicXPath.DX_tableList + "[" + i + "]" + "/td[" + (j+2) + "]";
			tempTableStatus = dynamicXPath.DX_tableList + "[" + i + "]" + "/td[" + (j+3) + "]";
		
			Assert.assertEquals(driver.findElement(By.xpath(tempTableName)).getText().trim(), tempName, 
					"Table name is not " + (i-1));
			Assert.assertEquals(driver.findElement(By.xpath(tempTablePrice)).getText().trim(), tempPrice, 
					"Table price is not " + tempPrice);
			Assert.assertEquals(driver.findElement(By.xpath(tempTableSize)).getText().trim(), "5", 
					"Table size is not 5");
			Assert.assertEquals(driver.findElement(By.xpath(tempTableStatus)).getText().trim(), 
					"None Booked, None Assigned", "Table status is not : None Booked, None Assigned");
		}
	}
	
		
protected void setTableSettingsPage() {
		
		pageNavigator.navigateTo("Event" , "Table Bookings", "Table Settings");
	
		tableSettings.PF_enableTableBooking.click();
		log.logMessage("Table booking is enabled");
		tableSettings.PF_selectIndividualSeat.click();
		log.logMessage("Individual seat sale option is enabled");
		tableSettings.PF_supressPrices.click();
		log.logMessage("Supress prices are checked");
		tableSettings.PF_saveByName.click();
		
		MenuHeader menuHeader = PageFactory.initElements(driver, MenuHeader.class);
		pageLoader.waitForPageToLoad(driver, menuHeader.PF_eventMenuMain);
		log.logMessage("Table settings are updated successfully");
	}
}


	
