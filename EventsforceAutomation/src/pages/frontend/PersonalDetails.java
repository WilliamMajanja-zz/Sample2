package pages.frontend;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.CommonElements;

public class PersonalDetails extends CommonElements {

	@FindBy(xpath=".//input[contains(@id,'txtQuestion_1')]") 
	public  WebElement PF_firstName;
	
	@FindBy(xpath=".//input[contains(@id,'txtQuestion_2')]") 
	public  WebElement PF_lastName;
	
	@FindBy(xpath=".//input[contains(@id,'txtQuestion_14')]") 
	public  WebElement PF_company;
	
	@FindBy(xpath=".//input[contains(@id,'txtQuestion_4')]") 
	public  WebElement PF_address1;
	
	@FindBy(xpath=".//input[contains(@id,'txtQuestion_5')]") 
	public  WebElement PF_address2;
	
	@FindBy(xpath=".//input[contains(@id,'txtQuestion_7')]") 
	public  WebElement PF_town;
	
	@FindBy(xpath=".//input[contains(@id,'txtQuestion_8')]") 
	public  WebElement PF_postcode;
	
	@FindBy(xpath=".//select[contains(@id,'selQuestion_10')]") 
	public  WebElement PF_country;
	
	@FindBy(xpath=".//input[@type='email']") 
	public  WebElement PF_abstractEmail;
	
	@FindBy(xpath=".//*[@type='tel']") 
	public  WebElement PF_phone;
		
	@FindBy(xpath=".//input[@title='reserve seats']") 
	public  WebElement PF_reserveSeat;
	
	@FindBy(name="selIndividualTable") 
	public  WebElement PF_reserveTable;
}
