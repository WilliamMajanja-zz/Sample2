package pages.backend;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.CommonElements;

public class TableSettings extends CommonElements {

	@FindBy(name="enableEntireTable") 
	public  WebElement PF_enableTableBooking;
	
	@FindBy(name="chkBookerSelectTable") 
	public  WebElement PF_selectIndividualSeat;
	
	@FindBy(name="chkSuppressTablePrices") 
	public  WebElement PF_supressPrices;
}
