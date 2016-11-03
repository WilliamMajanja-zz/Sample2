package pages.backend;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.CommonElements;

public class Capacity extends CommonElements {

	@FindBy(xpath=".//*[@id='event-days-panel']//tr") 
	public  List<WebElement> PF_capacityPageTable;
}
