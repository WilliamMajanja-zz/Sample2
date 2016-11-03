package businessClasses;

import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class SimpleRegistration extends Registration {

	
protected SimpleRegistration(WebDriver driver, Map<String, String> systemDataTemp, Map<String, String> eventDataTemp, Map<String, String> registrationDataTemp) {
		super(driver, systemDataTemp, eventDataTemp, registrationDataTemp );
	}
	
	
protected void performSimpleRegistration(){
		
		testRegistrationDefault();
	}

	
@Override
protected void createRegistration() {
		
		enterEmail();
		selectAttendee();
		enterPersonalDetailsAndProceed();
		verifyRegContactDetailsOnBasketPage();
		verifyOtherDetailsOnBasketPage();
		selectPaymentMethod();
		verifyPaymentMethod();
	}

	
protected void verifyOtherDetailsOnBasketPage(){
		
		if (!eventData.get("EventPrice").equalsIgnoreCase("NA")) 
		{
			String temp_price = "£" + eventData.get("EventPrice") + ".00";
			Assert.assertEquals(basketPage.PF_outstanding.getText(), temp_price,
							"Event price is not equal to the EventPrice in excel file");
			Assert.assertEquals(basketPage.PF_basketQuantityPaid.getText(), "1",
							"Quantity on the basket page is not equal to 1");
			log.logMessage("Verified Event Price and quantity on the basket page");
		}
	
	}
}
