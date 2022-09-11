package propertiesPackage;

import org.testng.annotations.Test;

import Base.DriverInitializer;
import Base.NavigateToURL;
import pageObjects.CricketTab;
import pageObjects.HomePage;
import pageObjects.Web_Stories;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

@Listeners(TestNGListner.class)
public class NewTest extends DriverInitializer {

	Duration time = Duration.ofSeconds(30);
	JavascriptExecutor js;
	HomePage HomePageObject;
	CricketTab CricketTabObject;
	WebDriverWait wait;
	Web_Stories WebStoryObject;
    File src ;
    static String screenPath = " ";
    static String screenPath1 = " ";
    ITestResult result ;

	@BeforeSuite
	public void beforeTest_setup() throws Throwable {

		NavigateToURL.UrlNavigation();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		HomePageObject = new HomePage(driver);
		CricketTabObject = new CricketTab(driver);
		WebStoryObject=new Web_Stories(driver);
		wait = new WebDriverWait(driver, time);
		js = (JavascriptExecutor) driver;		

	}
	

	@Test(priority = 1, testName = "Change Language" )
	public void selectLanguage_Test() throws InterruptedException, IOException {

		screenPath = TestNGListner.screenPath ;
		screenPath1 = TestNGListner.screenPath1 ;
		HomePageObject.switch_Language_To_Hindi();
		Thread.sleep(3000);
		//TakeScreens
		src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(src, new File( screenPath ) );
		Thread.sleep(3000);
		//TakeScreens
		HomePageObject.switch_Language_ToEnglish();
		Thread.sleep(3000);
		src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE) ;
		FileHandler.copy( src, new File( screenPath1 ) );
		
		Thread.sleep(3000);
		

	}

	@Test(priority = 4, testName = "Theme Test" , dependsOnMethods = {"selectLanguage_Test"})
	public void select_Theme() throws InterruptedException, IOException {

		screenPath = TestNGListner.screenPath ;
		screenPath1 = TestNGListner.screenPath1 ;
		HomePageObject.Home_Button.click();
		Thread.sleep(3000);
		//TakeScreens
		src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE) ;
		FileHandler.copy(src, new File( screenPath ) );
		Thread.sleep(3000);		
		
		HomePageObject.switch_to_Dark_Theme(driver);// Switch to Dark Theme
		Thread.sleep(3000);
		//TakeScreens
		src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE) ;
		FileHandler.copy(src, new File( screenPath1 ) );
		wait.until(ExpectedConditions.visibilityOf(HomePageObject.Light_Theme));
		Thread.sleep(3000);
		
		HomePageObject.switch_to_Light_Theme(driver);// Switch to Light Theme
		Thread.sleep(3000);
		Assert.assertTrue(HomePageObject.Dark_Theme.isDisplayed(), "******************* select_Theme Test Failed");

	}

	@Test(priority = 3, testName = "Cricket Test_scroll_Third")
	public void Cricket_Test() throws Throwable {
		screenPath = TestNGListner.screenPath ;
		screenPath1 = TestNGListner.screenPath1 ;
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(HomePageObject.Home_Page_Cricket));
		HomePageObject.Home_Page_Cricket.click();
		Thread.sleep(3000);
		//TakeScreens
		src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE) ;
		FileHandler.copy(src, new File( screenPath ) );
		CricketTabObject.ThirdPage.click();
		Thread.sleep(2000);
		//TakeScreens
		src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE) ;
		FileHandler.copy(src, new File( screenPath1 ) );
		CricketTabObject.validate_BrokenLink();
	}

	

	@Test(priority = 2)
	public void verify_Thumbnail_Links() throws Throwable {
		screenPath = TestNGListner.screenPath ;
		screenPath1 = TestNGListner.screenPath1 ;
		HomePageObject.Home_Button.click();
		Thread.sleep(3000);
		
		//TakeScreens
		src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE) ;
		FileHandler.copy(src, new File( screenPath ) );
		HomePageObject.HomePageAllButton.click();
		Thread.sleep(3000);
		
		//TakeScreens
		src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE) ;
		FileHandler.copy(src, new File( screenPath1 ) );
		
		HomePageObject.validate_BrokenLink_HomePage();

	}
	
	
//	@Test(priority = 5, testName = "Webstories Test 1")
//	public void Webstories_Test() throws Exception {
//
//		HomePageObject.WebStoryButton.click();
//		Thread.sleep(3000);
//		WebStoryObject.Webstory1();
//	}
	
	

	@AfterSuite
	public void afterTest() {
		driver.quit();
	}

}
