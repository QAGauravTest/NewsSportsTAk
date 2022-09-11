package propertiesPackage;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsSetup {

	static ExtentReports extent;
	static ExtentSparkReporter reports ;
	
	public static ExtentReports ExtentReportsSetup()
	{
		
		String path = System.getProperty("user.dir") + "\\Report\\ExtentReportNews.html" ;
	
		reports = new ExtentSparkReporter( path );
		
		reports.config().setReportName("Automation Report");
		reports.config().setDocumentTitle("Test Report");
		
	//	ExtentSparkReporter klov = new ExtentSparkReporter("MyProject");
				
		
		extent = new ExtentReports();
		extent.attachReporter(reports);
		return extent;
		
	}
	
}
