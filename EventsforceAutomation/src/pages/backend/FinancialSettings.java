package pages.backend;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.CommonElements;

public class FinancialSettings extends CommonElements {

	@FindBy(name="selVATCode") 
	public  WebElement PF_vatCode;
	
	@FindBy(name="selBaseCurrency") 
	public  WebElement PF_eventCurrency;
		
	@FindBy(name="currencySelection") 
	public  WebElement PF_allowCurrencySelection;
}
