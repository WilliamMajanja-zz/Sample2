package pages.backend;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.CommonElements;

public class AwardCategories extends CommonElements {

	@FindBy(name="selSection") 
	public  WebElement PF_selectSection;
}
