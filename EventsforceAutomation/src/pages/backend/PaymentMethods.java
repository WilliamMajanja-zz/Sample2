package pages.backend;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.CommonElements;

public class PaymentMethods extends CommonElements {

	@FindBy(xpath=".//td[contains(text(),'Cheque')]//preceding::input[1]") 
	public  WebElement PF_checkCheque;
	
	@FindBy(xpath=".//td[contains(text(),'Bank Transfer')]//preceding::input[1]") 
	public  WebElement PF_checkBank;
	
	@FindBy(xpath=".//td[contains(text(),'Invoice')]//preceding::input[1]") 
	public  WebElement PF_checkInvoice;
	
	@FindBy(id="ef_backend_button_save_area") 
	public  WebElement PF_savePaymentMethod;
}
