package commonLibrary;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import pages.backend.LoginPage;

public class Login {
		
	private WebDriver driver ;
	private Map<String, String> systemData = new LinkedHashMap<String, String>();
	public static WebDriver driverForScreenshot;

	
void browserFactory() {
		
		Log log = new Log();
					
		if(systemData.get("Url").equalsIgnoreCase("Error") || systemData.get("Browser").equalsIgnoreCase("Error"))
		{
			log.logDataToFile("Error", "Invalid sheet name or Browser name.", "Please check input file");
		}
		
		if(systemData.get("Browser").equalsIgnoreCase("Mozilla"))
		{
			/* System.setProperty("webdriver.gecko.driver", "Drivers/geckodriver.exe"); */
			driver = new FirefoxDriver();
		}
		
		if(systemData.get("Browser").equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
			driver = new ChromeDriver();
		}
			
		if(systemData.get("Browser").equalsIgnoreCase("Edge"))
		{
			System.setProperty("webdriver.ie.driver", "Drivers/MicrosoftWebDriver.exe");
			driver = new EdgeDriver();
		}
				
	}
	
	
public WebDriver loginToEventsForce(Map<String, String> systemDataTemp) {
		
		systemData = systemDataTemp;
		PageLoader pageLoader = new PageLoader(systemData);
		
		browserFactory();
		
		/* Page handle */
		LoginPage enterCredential = PageFactory.initElements(driver, LoginPage.class);
		
		driver.manage().window().maximize();
		driver.get(systemData.get("Url"));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		enterCredential.PF_username.sendKeys(systemData.get("UserName"));
		enterCredential.PF_password.sendKeys(systemData.get("Password"));
		enterCredential.PF_submitButton.click();
		
		pageLoader.waitForPageToLoad();
		driverForScreenshot = driver;
		return driver;
	}
}
