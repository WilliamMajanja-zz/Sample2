package businessClasses;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pages.DynamicXPath;

public class TableBookingRegistration extends Registration {

	
protected TableBookingRegistration(WebDriver driver, Map<String, String> systemDataTemp, Map<String, String> eventDataTemp, Map<String, String> registrationDataTemp) {
		super(driver, systemDataTemp, eventDataTemp, registrationDataTemp);
		
	}
	

protected void performTableRegistration(){
		
		testRegistrationDefault();
	}

	
@Override
protected void createRegistration() {
		
		enterEmail();
		bookTableAndSeats();
		setupTableGuestDetailsPage();
		verifyRegContactDetailsOnBasketPage();
		verifyTablePricesOnBasketPage();
		selectPaymentMethod();
		verifyPaymentMethod();
	}
	
		
protected void bookTableAndSeats(){
		
		enterPersonalDetails();
		
		personalDetails.PF_reserveSeat.sendKeys("5");
		
		Select selectTable = new Select(personalDetails.PF_reserveTable);
		selectTable.selectByIndex(1);;
		
		personalDetails.PF_saveAndProceed.click();
		log.logMessage("5 seats and 1 table is selected for booking");
	}

	
protected void setupTableGuestDetailsPage(){
		
		DynamicXPath dynamicXPath = new DynamicXPath();
		
		wait.until(ExpectedConditions.visibilityOf(tableGuestDetails.PF_buttonSubmit));
		pageLoader.waitForPageToLoad(driver, tableGuestDetails.PF_buttonSubmit);
		
		for(int i = 1; i <6 ; i++)
		{
			String tableFirstName = dynamicXPath.DX_GuestTableDetails + "[" + i + "]" + "//tr[2]//td[2]//input";
			String tableLastName = dynamicXPath.DX_GuestTableDetails + "[" + i + "]" + "//tr[3]//td[2]//input";
			String seatFirstName = dynamicXPath.DX_GuestSeatDetails + "[" + i + "]" + "//tr[2]//td[2]//input";
			String seatLastName = dynamicXPath.DX_GuestSeatDetails + "[" + i + "]" + "//tr[3]//td[2]//input";
			
			driver.findElement(By.xpath(tableFirstName)).sendKeys("TableGuestFirstName" + i);
			driver.findElement(By.xpath(tableLastName)).sendKeys("TableGuestLasttName" + i);
			driver.findElement(By.xpath(seatFirstName)).sendKeys("SeatGuestFirstName" + i);
			driver.findElement(By.xpath(seatLastName)).sendKeys("SeatGuestLasttName" + i);
		}
		
		tableGuestDetails.PF_buttonSubmit.click();
		log.logMessage("Guest details submitted successfully");
	}
	
	
protected void verifyTablePricesOnBasketPage() {

		Assert.assertEquals(basketPage.PF_basketQuantityFree.getText(), "5",
				"Quantity on the basket page is not equal to 5");
		
		Assert.assertEquals(basketPage.PF_basketQuantityTable.getText(), "1",
				"Quantity on the basket page is not equal to 1");

		Integer tempSeatPrice = Integer.valueOf(eventData.get("SeatPrice")) * 5;
		Integer tempTablePrice = Integer.valueOf(eventData.get("TablePrice"));
		int intOutstanding = tempSeatPrice + tempTablePrice;
		String strOutstanding = "£" + intOutstanding + ".00";
		String seatPrice = "£" + tempSeatPrice + ".00";
		String tablePrice = "£" + eventData.get("TablePrice") + ".00";
			
		Assert.assertEquals(basketPage.PF_totalSeatPrice.getText(), seatPrice,
					"Seat price is not equal to the price is excel file");
		Assert.assertEquals(basketPage.PF_totalTablePrice.getText(), tablePrice,
					"Table price is not equal to the price is excel file");
		Assert.assertEquals(basketPage.PF_outstanding.getText(), strOutstanding,
					"Outstanding is not calculated correctly");
		log.logMessage("Table prices are verified successfully on the basket page");
	}
}
