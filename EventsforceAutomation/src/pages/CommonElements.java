package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CommonElements {

	@FindBy(id="ef_backend_button_save") 
	public  WebElement PF_saveEvent;
	
	@FindBy(xpath=".//img[@alt='Add New']") 
	public  WebElement PF_addNew;
	
	@FindBy(id="description") 
	public  WebElement PF_description;
	
	@FindBy(name="description") 
	public  WebElement PF_descriptionByName;
	
	@FindBy(id="butProceed") 
	public  WebElement PF_proceedButton;
	
	@FindBy(name="butProceed") 
	public  WebElement PF_proceed2Button;
	
	@FindBy(id="butSave") 
	public  WebElement PF_saveAndProceed;
	
	@FindBy(name="butSave") 
	public  WebElement PF_saveByName;
	
	@FindBy(name="butSearch") 
	public  WebElement PF_searchButton;
	
	@FindBy(name="butEdit") 
	public  WebElement PF_editButton;
	
	@FindBy(name="butSave2") 
	public  WebElement PF_save2Button;
	
	@FindBy(xpath=".//img[@alt='cancel']") 
	public  WebElement PF_backButton;
	
	@FindBy(id="butSubmit") 
	public  WebElement PF_submitButton;
	
	@FindBy(name="butContinue") 
	public  WebElement PF_continueButton;
	
}
