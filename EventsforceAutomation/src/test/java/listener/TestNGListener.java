package listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import commonLibrary.Log;


public class TestNGListener implements ITestListener {

	@Override
	public void onFinish(ITestContext arg0) { }

	@Override
	public void onStart(ITestContext arg0) { }

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) { }

	@Override
	public void onTestFailure(ITestResult result) {
		
		Log log = new Log();
		log.captureScreenshot(result.getName());
	}

	@Override
	public void onTestSkipped(ITestResult arg0) { }

	@Override
	public void onTestStart(ITestResult arg0) { }

	@Override
	public void onTestSuccess(ITestResult arg0) { }
} 
