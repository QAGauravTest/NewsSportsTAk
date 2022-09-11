package propertiesPackage;

import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Base.DriverInitializer;

public class TestNGListner implements ITestListener {
	
	ExtentReports extent = ExtentReportsSetup.ExtentReportsSetup() ;
	ExtentTest test ;
	Throwable runTimeException;
	static String screenPath ;
	static String screenPath1 ;
	
	public void onTestStart(ITestResult result) {
		System.out.println( "@@@@ From Listener\n Test Started : " + result.getName());
		test = extent.createTest(result.getName());
		screenPath =  DriverInitializer.current_working_directory + "\\screenshots\\"+result.getName()+"_1.png" ;
		screenPath1 = DriverInitializer.current_working_directory + "\\screenshots\\"+result.getName()+"_2.png" ;
		
		runTimeException = new RuntimeException("A runtime exception");
	}
	 public void onTestFailure(ITestResult result) {
		 System.out.println( "@@@@ From Listener\n Test Failed : " + result.getName());
		 test.log(Status.FAIL, "Test Case Failed ") ;
		 test.log(Status.INFO, "Test Case INFO : " + runTimeException.getMessage() ) ;
		 test.addScreenCaptureFromPath(screenPath);
		 test.addScreenCaptureFromPath( screenPath1 );
	  }
	 
	  public void onTestSkipped(ITestResult result) {
		  System.out.println( "@@@@ From Listener\n Test Skipped : " + result.getName()); 
	  }
	 
	  public void onTestSuccess(ITestResult result) {
		  System.out.println( "@@@@ From Listener\n Test is Success : " + result.getName()); 
		  test.log(Status.PASS, "Test Case Passed") ;
		  test.log(Status.INFO, "Test Case INFO : "  + runTimeException.getMessage() ) ;
		  test.addScreenCaptureFromPath( screenPath );
		  test.addScreenCaptureFromPath( screenPath1 );
		  
	  }
	  
	  public void onFinish(ITestContext context) {
		  System.out.println( "@@@@ From Listener\n Test is COMPLETED : " + context.getName()); 
		  extent.flush();
	  }


}
