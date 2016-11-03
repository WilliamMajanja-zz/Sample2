package commonLibrary;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {
	
public String generateTimeStamp()  {
	
	Date now = new Date();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd_HHmmss");
	String time = dateFormat.format(now);
	return time;
	}
}
