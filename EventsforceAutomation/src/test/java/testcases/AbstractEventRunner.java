package testcases;

import org.testng.annotations.Test;

import businessClasses.AbstractsEventCreator;

public class AbstractEventRunner {

@Test
public void testAbstractsEvent() throws Exception {
		
		AbstractsEventCreator abstractEvent = new AbstractsEventCreator();
		abstractEvent.createAbstractsEvent("ExcelDataFiles/AbstractsEventData.xlsx");
	}
}
