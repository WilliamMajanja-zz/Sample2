package pages.frontend;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.CommonElements;

public class BasketPage extends CommonElements {

	@FindBy(xpath=".//td[contains(text(),'Registration contact')]//following::td[1]") 
	public  WebElement PF_basketName;
	
	@FindBy(xpath=".//span[@class='ef-registration-contact-company']") 
	public  WebElement PF_basketCompany;
	
	@FindBy(xpath=".//span[@class='ef-registration-contact-addressline1']") 
	public  WebElement PF_basketAdr1;
	
	@FindBy(xpath=".//span[@class='ef-registration-contact-addressline2']") 
	public  WebElement PF_basketAdr2;
	
	@FindBy(xpath=".//span[@class='ef-registration-contact-town']") 
	public  WebElement PF_basketTown;
	
	@FindBy(xpath=".//span[@class='ef-registration-contact-postcode']") 
	public  WebElement PF_basketPostcode;
	
	@FindBy(xpath=".//span[@class='ef-registration-contact-phonenumber']") 
	public  WebElement PF_basketPhone;
	
	@FindBy(xpath=".//*[contains(text(),'Unit price')]//following::td[3]") 
	public  WebElement PF_basketQuantityPaid;
	
	@FindBy(xpath=".//td[contains(text(),'Quantity')]//following::tr[1]/td[2]") 
	public  WebElement PF_basketQuantityFree;
	
	@FindBy(xpath=".//td[contains(text(),'Outstanding')]//following::td[1]") 
	public  WebElement PF_outstanding;
	
	@FindBy(xpath=".//td[contains(text(),'Cheque')]//preceding::input[1]") 
	public  WebElement PF_selectPayCheque;
	
	@FindBy(xpath=".//td[contains(text(),'Invoice')]//preceding::input[1]") 
	public  WebElement PF_selectPayInvoice;
	
	@FindBy(xpath=".//td[contains(text(),'Bank')]//preceding::input[1]") 
	public  WebElement PF_selectPayBank;
		
	@FindBy(xpath=".//td[contains(text(),'Payment Method')]//following::td[1]") 
	public  WebElement PF_basketPayMethod;
		
	@FindBy(xpath=".//input[@value='Print']") 
	public  WebElement PF_print;
	
	/* Table booking stuff */
	
	@FindBy(xpath=".//td[contains(text(),'Quantity')]//following::tr[2]/td[2]") 
	public  WebElement PF_basketQuantityTable;
	
	@FindBy(xpath=".//td[contains(text(),'Quantity')]//following::tr[1]/td[4]") 
	public  WebElement PF_totalSeatPrice;
	
	@FindBy(xpath=".//td[contains(text(),'Quantity')]//following::tr[2]/td[4]") 
	public  WebElement PF_totalTablePrice;
}
