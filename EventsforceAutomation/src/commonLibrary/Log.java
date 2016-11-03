package commonLibrary;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Log {

	 Logger logger = Logger.getLogger(Log.class);
	 private File file;
	 String filePath = "log/application.log";
	 protected static File destScreenshot;
	 
public void logDataToFile(String level, String columnName, String columnVal){
		
		PropertyConfigurator.configure("log4j.properties");
		
		if (level=="info")
		{
			logger.info(columnName +" : " + columnVal);
		}
        		
		if (level=="error")
		{
			logger.error(columnName +" : " + columnVal);
		}
		
		if (level=="warning")
		{
			logger.warn(columnName +" : " + columnVal);
		}
		
		if (level=="debug")
		{
			logger.debug(columnName +" : " + columnVal);
		}
		
	}
	
	
public void logDataToFile(String level, String columnName, int columnVal){
		
		PropertyConfigurator.configure("log4j.properties");
		
		if (level=="info")
		{
			logger.info(columnName +" : " + columnVal);
		}
        		
		if (level=="error")
		{
			logger.error(columnName +" : " + columnVal);
		}
		
		if (level=="warning")
		{
			logger.warn(columnName +" : " + columnVal);
		}
		
		if (level=="debug")
		{
			logger.debug(columnName +" : " + columnVal);
		}
	}
	
	
public void logFunctionTrace(String Trace){
		
		String functionName = null;
		PropertyConfigurator.configure("log4j.properties");
				
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		   
		boolean foundMe = false;
		for(int i=0; i<stacktrace.length; i++) 
		{
			StackTraceElement e = stacktrace[i];
			String methodName = e.getMethodName();
			if (foundMe) 
			{
				if (!methodName.startsWith("access$")) 
		        {
					/* Get the function name */
		            functionName = methodName;
		            break;
		        }
		    } 
			else 
		    {
		            if (methodName.equals("logFunctionTrace")) 
		            {
		                foundMe = true;
		            }
		        }
		    }
		    
		/* Log the function name with entered/exited text */
		if(Trace.equalsIgnoreCase("entry"))
		{
			Trace = "entered ";
		}
		
		if(Trace.equalsIgnoreCase("exit"))
		{
			Trace = "exited ";
		}
				
		functionName = "********** " + functionName;
		Trace = Trace + "**********";
		logDataToFile("info", functionName, Trace);
	}

	
public void clearContentsOfOldLogFile() throws FileNotFoundException{
	
		file = new File(filePath);
		PrintWriter pw = new PrintWriter(filePath);
		pw.close();
	}
	

public static void createFolder() {
	
	Utility util = new Utility();
	String time = util.generateTimeStamp();
	destScreenshot = new File("Screenshots/Test_Run_"+ time);
}
	
public void copyLogFileToFolder() throws IOException{
		
		file = new File(filePath);
		Utility util = new Utility();
		String time = util.generateTimeStamp();
		File destFile = new File("EventsForceLogFiles/EventsForceLog" + time + ".log");
		FileUtils.copyFile(file, destFile);
	}


public void logHeader(String headerString) {
	
		PropertyConfigurator.configure("log4j.properties");
		logger.info("----------------------------------------------------");
		logger.info("");
		logger.info("          " + headerString + "          ");
		logger.info("");
		logger.info("----------------------------------------------------");
	}


public void logMessage(String messageString) {
	
		PropertyConfigurator.configure("log4j.properties");
		logger.info(messageString);
	}


public void captureScreenshot(String screenshotName) {
	
		TakesScreenshot screenshot = (TakesScreenshot)Login.driverForScreenshot;
		File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
		File newFile = new File (screenshotName + ".png");
		
		srcFile.renameTo(newFile);
		try {
		
			FileUtils.copyFileToDirectory(newFile, destScreenshot );
			newFile.delete();
		
		} catch (IOException e1) {
		
			e1.printStackTrace();
		}
	}
}
