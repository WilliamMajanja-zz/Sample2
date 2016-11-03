package pages.backend;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.CommonElements;

public class AwardTypes extends CommonElements {

	@FindBy(name="sectionID") 
	public  WebElement PF_selectSection;
}
