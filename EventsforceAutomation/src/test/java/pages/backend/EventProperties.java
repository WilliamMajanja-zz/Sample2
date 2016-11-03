package pages.backend;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import pages.CommonElements;

public class EventProperties extends CommonElements {
	
	@FindBy(id="event-name") 
	public  WebElement PF_eventName;
	
	@FindBy(id="event-start-date-time") 
	public  WebElement PF_eventStartDate;
	
	@FindBy(id="event-end-date-time") 
	public  WebElement PF_eventEndDate;
	
	@FindBy(id="event-time-zone") 
	public  WebElement PF_eventTimeZone;
	
	@FindBy(id="event-type") 
	public  WebElement PF_eventType;
	
	@FindBy(id="event-cost_1-label") 
	public  WebElement PF_eventCostFree;
	
	@FindBy(id="event-cost_2-label") 
	public  WebElement PF_eventCostPaid;
		
	@FindBy(id="ef_backend_button_save") 
	public  WebElement PF_saveEvent;
	
	@FindBy(id="event-status_2-label") 
	public  WebElement PF_eventLive;
	
	@FindBy(id="has-table-booking") 
	public  WebElement PF_tableBooking;
	
}
