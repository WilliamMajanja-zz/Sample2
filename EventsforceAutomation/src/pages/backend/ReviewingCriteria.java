package pages.backend;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.CommonElements;

public class ReviewingCriteria extends CommonElements {

	@FindBy(name="topicID") 
	public  WebElement PF_selectTopic;
}
