package pages.backend;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.CommonElements;

public class Prices extends CommonElements {

	@FindBy(xpath=".//img[@alt='Add New Session Price']") 
	public  WebElement PF_addPrice;
	
	@FindBy(name="txtDescription") 
	public  WebElement PF_priceName;
	
	@FindBy(xpath=".//input[@type='radio' and @value='event']") 
	public  WebElement PF_applyEvent;
	
	@FindBy(xpath=".//input[@type='radio' and @value='session']") 
	public  WebElement PF_applyGeneral;
	
	@FindBy(name="txtAmount_") 
	public  WebElement PF_price;
	
	@FindBy(name="selCurrency_") 
	public  WebElement PF_currency;
	
	@FindBy(name="lineItemDescription_") 
	public  WebElement PF_lineDesc;
	
}
