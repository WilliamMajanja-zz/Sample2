package pages.backend;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

	/*
	 * This section contains all the elements present on the login screen.
	 */
	
	@FindBy(id="txtUsername") 
	public  WebElement PF_username;
	
	@FindBy(id="txtPassword") 
	public  WebElement PF_password;
	
	@FindBy(id="efBackendSignInButton") 
	public  WebElement PF_submitButton;
}
