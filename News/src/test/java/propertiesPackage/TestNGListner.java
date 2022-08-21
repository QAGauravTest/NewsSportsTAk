package propertiesPackage;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListner implements ITestListener {
	
	public void onTestStart(ITestResult result) {
		System.out.println( "@@@@ From Listener\n Test Started : " + result.getName());
	}
	 public void onTestFailure(ITestResult result) {
		 System.out.println( "@@@@ From Listener\n Test Failed : " + result.getName()); 
	  }
	 
	  public void onTestSkipped(ITestResult result) {
		  System.out.println( "@@@@ From Listener\n Test Skipped : " + result.getName()); 
	  }
	 
	  public void onTestSuccess(ITestResult result) {
		  System.out.println( "@@@@ From Listener\n Test is Success : " + result.getName()); 
	  }
	  
	  public void onFinish(ITestResult result) {
		  System.out.println( "@@@@ From Listener\n Test is COMPLETED : " + result.getName()); 
	  }


}
