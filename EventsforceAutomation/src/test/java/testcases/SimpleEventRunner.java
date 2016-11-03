package testcases;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import businessClasses.AttendanceEventCreator;
import businessClasses.BulkEditEventCreator;
import businessClasses.CapacityEventCreator;
import businessClasses.EventEntryCodeCreator;
import businessClasses.SimpleEventCreator;
import businessClasses.StandardSearch;
import commonLibrary.Log;

public class SimpleEventRunner {


public void createScreenshotFolder() {
	
		Log.createFolder();
	}


@Test
public void testSimpleEvent() throws Exception {
		
		SimpleEventCreator simpleEvent = new SimpleEventCreator();
		simpleEvent.createSimpleEvent("ExcelDataFiles/SimpleEventData.xlsx");
	}

	
@Test
public void testCapacityCheckingEvent() throws Exception {
		
		CapacityEventCreator capacityEvent = new CapacityEventCreator();
		capacityEvent.createCapacityEvent("ExcelDataFiles/CapacityCheckingEventData.xlsx");
	}

@Test
public void testAttendanceRecording() throws Exception {
	
		AttendanceEventCreator attendanceEvent = new AttendanceEventCreator();
		attendanceEvent.createAttendanceEvent("ExcelDataFiles/AttendanceRecording.xlsx");
	
	}

@Test
public void testBulkEdit() throws Exception {
	
		BulkEditEventCreator bulkEditEvent = new BulkEditEventCreator();
		bulkEditEvent.createBulkEditEvent("ExcelDataFiles/StandardEventData.xlsx");
	
	}

@Test
public void testEventEntryCode() throws Exception {
	
		EventEntryCodeCreator eventEntryCode = new EventEntryCodeCreator();
		eventEntryCode.createEventEntryCode("ExcelDataFiles/EventEntryCodeData.xlsx");
	
	}

@Test
public void testStandardSearch() throws Exception {
	
		StandardSearch standardSearch = new StandardSearch();
		standardSearch.performStandardSearch("ExcelDataFiles/StandardEventData.xlsx");
	
	}
}
