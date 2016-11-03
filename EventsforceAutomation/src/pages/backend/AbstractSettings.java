package pages.backend;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.CommonElements;

public class AbstractSettings extends CommonElements {

	@FindBy(id="allow_html_text_submission") 
	public  WebElement PF_allowTextSubmission;
	
	@FindBy(id="allow_html_tables") 
	public  WebElement PF_allowTables;
	
	@FindBy(id="allow_file_uploads") 
	public  WebElement PF_allowFileUploads;
	
	@FindBy(id="maximum_number_of_coauthors") 
	public  WebElement PF_maxCoAuthors;
	
	@FindBy(id="maximum_number_of_abstracts_per_person") 
	public  WebElement PF_maxAbstractPerPerson;
}
