package testcases;

import org.testng.annotations.Test;

import businessClasses.TableBookingEventCreator;

public class TableBookingEventRunner {

@Test
public void testTableBookingEvent() throws Exception {
			
			TableBookingEventCreator tableEvent = new TableBookingEventCreator();
			tableEvent.createTableBookingEvent("ExcelDataFiles/TableBookingEventData.xlsx");
	}
}
