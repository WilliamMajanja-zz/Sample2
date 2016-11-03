package pages.backend;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.CommonElements;

public class TableList extends CommonElements {

	@FindBy(xpath=".//img[@alt='Add']") 
	public  WebElement PF_addTable;
	
	@FindBy(name="txtTableSize") 
	public  WebElement PF_tableSize;
	
	@FindBy(name="txtNumberOfTables") 
	public  WebElement PF_numberOfTables;
	
	@FindBy(name="chkSellIndividualSeats") 
	public  WebElement PF_allowSeat;
	
	@FindBy(xpath=".//*[contains(text(),'New Table')]//following::input[4][@name='radPriceType_table']") 
	public  WebElement PF_radioPriceDescTable;
	
	@FindBy(xpath=".//*[contains(text(),'Individual Seats')]//following::input[4][@name='radPriceType_seat']") 
	public  WebElement PF_radioPriceDescSeat;
	
	@FindBy(name="selPriceDescriptor_table") 
	public  WebElement PF_listPriceDescTable;
	
	@FindBy(name="selPriceDescriptor_seat") 
	public  WebElement PF_listPriceDescSeat;
	
	@FindBy(xpath=".//div[@class='topsave']//following::table[4]//tr") 
	public  List<WebElement> PF_numberOfActualTables;
}
