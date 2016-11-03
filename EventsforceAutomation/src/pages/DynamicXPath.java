package pages;

public class DynamicXPath {

	/* This element is a table created on clicking Add button on Table List page */
	public String DX_tableList = ".//div[@class='topsave']//following::table[4]//tr";
	
	/* These elements are on Table Guest Details page when user selects number of seats and tables on personal details page */
	public String DX_GuestTableDetails = ".//div[contains(text(),'Table:')]//following::table";
	public String DX_GuestSeatDetails = ".//div[text()='Individual seats']//following::table";
	
	/* These elements are on the Capacity page */
	public String capacityDayOption = "event-day-registration-status-";
	public String capacityNotification = "event-day-notification-on-";
	public String capacityValue = "event-day-capacity-";
	public String whichDayToAttend = ".//input[@name='chk_Delegate_";
	
	/* These elements are on the Attendance Recording page */
	public String attendedCountPartOne = ".//td[contains(text(),'Day ";
	public String attendedCountPartTwo = "')]//following::td[3]";
	public String dayNamesOnCapacityPage = ".//td[contains(text(),'New Day ";
	
	/* Day names on Event properties page */
	public String dayNames = "day-name-";
	
	/* Awards Submission Page - Catgeory Links */
	public String categoryLink1 = ".//td[contains(text(),'";
	public String categoryLink2 = "')]//following::a[1]";
}
